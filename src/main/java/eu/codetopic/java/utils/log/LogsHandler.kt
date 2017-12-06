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

import java.util.ArrayList

import eu.codetopic.java.utils.ArrayTools
import eu.codetopic.java.utils.log.base.LogLine
import eu.codetopic.java.utils.log.base.Priority

class LogsHandler internal constructor() {

    companion object {

        private const val LOG_TAG = "LogsHandler"
    }

    private val listeners = mutableListOf<OnLoggedListener>()

    @Synchronized
    fun addOnLoggedListener(listener: OnLoggedListener) {
        listeners.add(listener)
    }

    @Synchronized
    fun removeOnLoggedListener(listener: OnLoggedListener) {
        listeners.remove(listener)
    }

    @Synchronized
    internal fun onLogged(logLine: LogLine) {
        listeners.filter { it.filterPriorities?.contains(logLine.priority) ?: true }
                .forEach { it.onLogged(logLine) }
    }

    interface OnLoggedListener {

        val filterPriorities: Array<Priority>? get() = null

        fun onLogged(logLine: LogLine)
    }

}
