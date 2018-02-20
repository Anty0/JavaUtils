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
import eu.codetopic.java.utils.log.base.Priority

object Log {

    /**
     * Low-level logging call.
     *
     * @param logLine LogLine to log into log
     */
    @JvmStatic
    fun println(logLine: LogLine) {
        Logger.println(logLine)
    }

    /**
     * Send a VERBOSE log message.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     * the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    @JvmStatic
    fun v(tag: String, msg: String) {
        println(LogLine(Priority.VERBOSE, tag, msg))
    }

    /**
     * Send a VERBOSE log message and log the exception.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     * the class or activity where the log call occurs.
     * @param tr  An exception to log
     */
    @JvmStatic
    fun v(tag: String, tr: Throwable) {
        println(LogLine(Priority.VERBOSE, tag, null, tr))
    }

    /**
     * Send a VERBOSE log message and log the exception.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     * the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr  An exception to log
     */
    @JvmStatic
    fun v(tag: String, msg: String, tr: Throwable) {
        println(LogLine(Priority.VERBOSE, tag, msg, tr))
    }

    /**
     * Send a DEBUG log message.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     * the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    @JvmStatic
    fun d(tag: String, msg: String) {
        println(LogLine(Priority.DEBUG, tag, msg))
    }

    /**
     * Send a DEBUG log message and log the exception.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     * the class or activity where the log call occurs.
     * @param tr  An exception to log
     */
    @JvmStatic
    fun d(tag: String, tr: Throwable) {
        println(LogLine(Priority.DEBUG, tag, null, tr))
    }

    /**
     * Send a DEBUG log message and log the exception.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     * the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr  An exception to log
     */
    @JvmStatic
    fun d(tag: String, msg: String, tr: Throwable) {
        println(LogLine(Priority.DEBUG, tag, msg, tr))
    }

    /**
     * Send an INFO log message.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     * the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    @JvmStatic
    fun i(tag: String, msg: String) {
        println(LogLine(Priority.INFO, tag, msg))
    }

    /**
     * Send a INFO log message and log the exception.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     * the class or activity where the log call occurs.
     * @param tr  An exception to log
     */
    @JvmStatic
    fun i(tag: String,tr: Throwable) {
        println(LogLine(Priority.INFO, tag, null, tr))
    }

    /**
     * Send a INFO log message and log the exception.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     * the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr  An exception to log
     */
    @JvmStatic
    fun i(tag: String, msg: String, tr: Throwable) {
        println(LogLine(Priority.INFO, tag, msg, tr))
    }

    /**
     * Send a BREAK_EVENT log message and log the exception.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     * the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    @JvmStatic
    fun b(tag: String, msg: String) {
        println(LogLine(Priority.BREAK_EVENT, tag, msg))
    }

    /**
     * Send a BREAK_EVENT log message and log the exception.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     * the class or activity where the log call occurs.
     * @param tr  An exception to log
     */
    @JvmStatic
    fun b(tag: String, tr: Throwable) {
        println(LogLine(Priority.BREAK_EVENT, tag, null, tr))
    }

    /**
     * Send a BREAK_EVENT log message and log the exception.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     * the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr  An exception to log
     */
    @JvmStatic
    fun b(tag: String, msg: String, tr: Throwable) {
        println(LogLine(Priority.BREAK_EVENT, tag, msg, tr))
    }

    /**
     * Send a WARN log message.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     * the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    @JvmStatic
    fun w(tag: String, msg: String) {
        println(LogLine(Priority.WARN, tag, msg))
    }

    /**
     * Send a WARN log message and log the exception.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     * the class or activity where the log call occurs.
     * @param tr  An exception to log
     */
    @JvmStatic
    fun w(tag: String, tr: Throwable) {
        println(LogLine(Priority.WARN, tag, null, tr))
    }

    /**
     * Send a WARN log message and log the exception.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     * the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr  An exception to log
     */
    @JvmStatic
    fun w(tag: String, msg: String, tr: Throwable) {
        println(LogLine(Priority.WARN, tag, msg, tr))
    }

    /**
     * Send an ERROR log message.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     * the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    @JvmStatic
    fun e(tag: String, msg: String) {
        println(LogLine(Priority.ERROR, tag, msg))
    }

    /**
     * Send a ERROR log message and log the exception.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     * the class or activity where the log call occurs.
     * @param tr  An exception to log
     */
    @JvmStatic
    fun e(tag: String, tr: Throwable) {
        println(LogLine(Priority.ERROR, tag, null, tr))
    }

    /**
     * Send a ERROR log message and log the exception.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     * the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr  An exception to log
     */
    @JvmStatic
    fun e(tag: String, msg: String, tr: Throwable) {
        println(LogLine(Priority.ERROR, tag, msg, tr))
    }

}
