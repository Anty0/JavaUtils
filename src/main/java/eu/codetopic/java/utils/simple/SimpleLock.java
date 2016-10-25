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
