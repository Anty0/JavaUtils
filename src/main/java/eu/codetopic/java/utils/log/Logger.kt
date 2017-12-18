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

import eu.codetopic.java.utils.debug.DebugMode
import java.util.ArrayList

import eu.codetopic.java.utils.log.base.DefaultTarget
import eu.codetopic.java.utils.log.base.LogLine
import eu.codetopic.java.utils.log.base.LogTarget

object Logger {

    private const val LOG_TAG = "Logger"

    private val logLinesCache = mutableListOf<LogLine>()// TODO: 5.9.16 write to and read from session files in debug activity

    val logsHandler = LogsHandler()
    var logTarget: LogTarget = DefaultTarget()

    val cachedLogLines: String get() = synchronized(logLinesCache) {
        logLinesCache.joinToString("\n")
    }

    internal fun println(logLine: LogLine) {
        logsHandler.onLogged(logLine)

        if (!DebugMode.isEnabled) return

        synchronized(logLinesCache) {
            logLinesCache.add(logLine)
        }

        logTarget.println(logLine)
    }

}
