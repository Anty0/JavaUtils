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

import java.util.*

/**
 * @author anty
 */
object DebugMode {

    private val listeners = WeakHashMap<() -> Unit, Unit>()

    @field:Volatile
    var isEnabled = false
        set(value) {
            field = value
            listeners.keys.forEach { it() }
        }

    @Suppress("NOTHING_TO_INLINE")
    inline fun <T> T.takeIfInDebugMode(): T? =
            if (isEnabled) this else null

    @Suppress("NOTHING_TO_INLINE")
    inline fun <T> T.takeUnlessInDebugMode(): T? =
            if (!isEnabled) this else null

    inline fun ifEnabled(block: () -> Unit) {
        if (isEnabled) block()
    }

    inline fun ifDisabled(block: () -> Unit) {
        if (!isEnabled) block()
    }

    fun addChangeListener(listener: () -> Unit) {
        listeners[listener] = Unit
    }

    fun removeChangeListener(listener: () -> Unit) {
        listeners.remove(listener)
    }
}