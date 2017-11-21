/*
 * Copyright 2017 Jiří Kuchyňka (Anty)
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package eu.codetopic.java.utils.simple;

import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class SimpleLock implements Lock {

    private static final String LOG_TAG = "SimpleLock";

    private final Object syncObj = new Object();
    private volatile boolean locked = false;

    @Override
    public void lock() {
        while (!tryLock()) Thread.yield();
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        if (Thread.interrupted()) throw new InterruptedException();

        while (!tryLock()) {
            if (Thread.interrupted()) throw new InterruptedException();
            Thread.yield();
        }
    }

    @Override
    public boolean tryLock() {
        synchronized (syncObj) {
            if (!locked) {
                locked = true;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean tryLock(long l, @NotNull TimeUnit timeUnit) throws InterruptedException {
        if (Thread.interrupted()) throw new InterruptedException();

        long maxNanos = System.nanoTime() + timeUnit.toNanos(l);
        while (System.nanoTime() < maxNanos) {
            if (tryLock()) return true;
            if (Thread.interrupted()) throw new InterruptedException();
            Thread.yield();
        }
        return false;
    }

    @Override
    public void unlock() {
        synchronized (syncObj) {
            if (!locked) throw new IllegalMonitorStateException("Can't unlock unlocked lock");
            locked = false;
        }
    }

    @NotNull
    @Override
    public Condition newCondition() {
        throw new UnsupportedOperationException(LOG_TAG + " does not support conditions");
    }
}
