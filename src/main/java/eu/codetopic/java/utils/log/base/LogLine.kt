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

package eu.codetopic.java.utils.log.base

import eu.codetopic.java.utils.printStackTraceToString
import eu.codetopic.java.utils.yieldNotNull
import java.io.Serializable
import kotlin.coroutines.experimental.buildSequence

class LogLine
/**
 * @param priority  The priority/type of this log message
 * @param tag       Used to identify the source of a log message.
 *                  It usually identifies the class or activity where the log call occurs.
 * @param message   The message you would like logged.
 * @param throwable Throwable to be added after your message
 */
constructor(val priority: Priority, val tag: String,
            val message: String?, val throwable: Throwable? = null) : Serializable {

    companion object {

        private const val LOG_TAG = "LogLine"
    }

    val messageWithThrowable: String
        get() = buildSequence {
            yieldNotNull(message)
            yieldNotNull(throwable?.printStackTraceToString())
        }.joinToString("\n")

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as LogLine

        if (priority != other.priority) return false
        if (tag != other.tag) return false
        if (message != other.message) return false
        if (throwable != other.throwable) return false

        return true
    }

    override fun hashCode(): Int {
        var result = priority.hashCode()
        result = 31 * result + tag.hashCode()
        result = 31 * result + (message?.hashCode() ?: 0)
        result = 31 * result + (throwable?.hashCode() ?: 0)
        return result
    }

    override fun toString() = toString(true)

    fun toString(showThrowable: Boolean) =
            "${priority.displayID}/$tag: ${if (showThrowable) messageWithThrowable else message}"
}
