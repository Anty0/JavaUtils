/*
 * javautils
 * Copyright (C)   2017  anty
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

package eu.codetopic.java.utils.debug

import eu.codetopic.java.utils.debug.DebugMode.isEnabled
import eu.codetopic.java.utils.exception.AssertionException

/**
 * @author anty
 */
object DebugAsserts {

    /**
     * Fails a test with the given message.
     *
     * @param message the identifying message for the [AssertionException] (`null` okay)
     * @see AssertionException
     */
    @Suppress("NOTHING_TO_INLINE")
    inline fun fail(message: String?): Nothing {
        throw AssertionException(message)
    }

    /**
     * Fails a test with no message.
     */
    @Suppress("NOTHING_TO_INLINE")
    inline fun fail(): Nothing = fail(null)

    /**
     * Asserts that a block returns true. If it isn't it throws an
     * [AssertionException] with the given message.
     *
     * @param block condition to be checked
     */
    inline fun <T> T.assert(block: (T) -> Boolean): T {
        if (isEnabled && !block(this)) fail("Assertion failed")
        return this
    }

    /**
     * Asserts that a block returns false. If it isn't it throws an
     * [AssertionException] with the given message.
     *
     * @param block condition to be checked
     */
    inline fun <T> T.assertNot(block: (T) -> Boolean): T {
        if (isEnabled && block(this)) fail("Assertion failed")
        return this
    }

    /**
     * Asserts that an object isn't null. If it is an [AssertionException] is
     * thrown with the given message.
     */
    @Suppress("NOTHING_TO_INLINE")
    inline fun <T> T.assertNotNull(): T {
        if (isEnabled && this == null) fail("Assertion failed -> object is null")
        return this
    }

    /**
     * Asserts that an object is null. If it is not, an [AssertionException]
     * is thrown with the given message.
     */
    @Suppress("NOTHING_TO_INLINE")
    inline fun <T> T.assertNull(): T {
        if (isEnabled && this != null) fail("Assertion failed -> object is not null")
        return this
    }
}