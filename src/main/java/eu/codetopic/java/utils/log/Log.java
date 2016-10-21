package eu.codetopic.java.utils.log;

import eu.codetopic.java.utils.log.base.LogLine;
import eu.codetopic.java.utils.log.base.Priority;

public final class Log {

    private static boolean DEBUG_MODE = false;

    private Log() {
    }

    public static void setDebugMode(boolean debugMode) {
        DEBUG_MODE = debugMode;
    }

    public static boolean isInDebugMode() {
        return DEBUG_MODE;
    }

    /**
     * Low-level logging call.
     *
     * @param logLine LogLine to log into log
     */
    public static void println(LogLine logLine) {
        Logger.println(logLine);
    }

    /**
     * Send a VERBOSE log message.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static void v(String tag, String msg) {
        println(new LogLine(Priority.VERBOSE, tag, msg));
    }

    /**
     * Send a VERBOSE log message and log the exception.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr  An exception to log
     */
    public static void v(String tag, String msg, Throwable tr) {
        println(new LogLine(Priority.VERBOSE, tag, msg, tr));
    }

    /**
     * Send a DEBUG log message.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static void d(String tag, String msg) {
        println(new LogLine(Priority.DEBUG, tag, msg));
    }

    /**
     * Send a DEBUG log message and log the exception.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr  An exception to log
     */
    public static void d(String tag, String msg, Throwable tr) {
        println(new LogLine(Priority.DEBUG, tag, msg, tr));
    }

    /**
     * Send an INFO log message.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static void i(String tag, String msg) {
        println(new LogLine(Priority.INFO, tag, msg));
    }

    /**
     * Send a INFO log message and log the exception.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr  An exception to log
     */
    public static void i(String tag, String msg, Throwable tr) {
        println(new LogLine(Priority.INFO, tag, msg, tr));
    }

    /**
     * Send a WARN log message.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static void w(String tag, String msg) {
        println(new LogLine(Priority.WARN, tag, msg));
    }

    /**
     * Send a WARN log message and log the exception.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr  An exception to log
     */
    public static void w(String tag, String msg, Throwable tr) {
        println(new LogLine(Priority.WARN, tag, msg, tr));
    }

    /**
     * Send a WARN log message and log the exception.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param tr  An exception to log
     */
    public static void w(String tag, Throwable tr) {
        println(new LogLine(Priority.WARN, tag, null, tr));
    }

    /**
     * Send an ERROR log message.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     */
    public static void e(String tag, String msg) {
        println(new LogLine(Priority.ERROR, tag, msg));
    }

    /**
     * Send a ERROR log message and log the exception.
     *
     * @param tag Used to identify the source of a log message.  It usually identifies
     *            the class or activity where the log call occurs.
     * @param msg The message you would like logged.
     * @param tr  An exception to log
     */
    public static void e(String tag, String msg, Throwable tr) {
        println(new LogLine(Priority.ERROR, tag, msg, tr));
    }

}
