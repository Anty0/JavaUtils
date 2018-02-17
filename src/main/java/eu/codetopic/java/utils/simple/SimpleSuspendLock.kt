/*
 * javautils
 * Copyright (C)   2018  anty
 *
 * This program is free  software: you can redistribute it and/or modify
 * it under the terms  of the GNU General Public License as published by
 * the Free Software  Foundation, either version 3 of the License, or
 * (at your option) any  later version.
 *
 * This program is distributed in the hope that it  will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied  warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.   See the
 * GNU General Public License for more details.
 *
 * You  should have received a copy of the GNU General Public License
 * along  with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package eu.codetopic.java.utils.simple

import eu.codetopic.java.utils.collections.queueOf
import kotlin.coroutines.experimental.Continuation
import kotlin.coroutines.experimental.suspendCoroutine

/**
 * @author anty
 */
class SimpleSuspendLock {

    companion object {

        private const val LOG_TAG = "SimpleSuspendLock"
    }

    private val lockSyncObj = Any()
    private var locked = false
    private val waiters = queueOf<Continuation<Nothing?>>()

    fun isLocked(): Boolean {
        synchronized(lockSyncObj) {
            return locked
        }
    }

    fun lock() {
        var success = false
        do {
            success = tryLock()
            if (!success) Thread.yield()
        } while (!success)
    }

    suspend fun suspendLock() {
        suspendCoroutine<Nothing?> {
            synchronized(lockSyncObj) {
                if (!tryLock()) {
                    waiters.put(it)
                } else it.resume(null)
                return@synchronized
            }
        }
    }

    fun tryLock(): Boolean =
            synchronized(lockSyncObj) {
                if (!locked) {
                    locked = true
                    return@synchronized true
                } else return@synchronized false
            }

    fun unlock() {
        synchronized(lockSyncObj) {
            if (!locked) throw IllegalStateException("$LOG_TAG is not locked")

            waiters.take()
                    ?.apply { resume(null) }
                    ?: run { locked = false }
            return@synchronized
        }
    }
}