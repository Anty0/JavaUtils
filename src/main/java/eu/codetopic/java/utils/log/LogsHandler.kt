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

package eu.codetopic.java.utils.log

import eu.codetopic.java.utils.log.base.LogLine
import java.util.*

class LogsHandler internal constructor() {

    companion object {

        private const val LOG_TAG = "LogsHandler"
    }

    private val listeners = WeakHashMap<(LogLine) -> Unit, Unit>()

    @Synchronized
    fun addOnLoggedListener(listener: (LogLine) -> Unit) {
        listeners[listener] = Unit
    }

    @Synchronized
    fun removeOnLoggedListener(listener: (LogLine) -> Unit) {
        listeners.remove(listener)
    }

    @Synchronized
    internal fun onLogged(logLine: LogLine) {
        listeners.keys.forEach { it(logLine) }
    }

}
