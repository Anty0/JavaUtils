/*
 * ApplicationPurkynka
 * Copyright (C)  2017  anty
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package eu.codetopic.java.utils.log;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

import eu.codetopic.java.utils.log.base.DefaultTarget;
import eu.codetopic.java.utils.log.base.LogLine;
import eu.codetopic.java.utils.log.base.LogTarget;

public final class Logger {

    private static final String LOG_TAG = "Logger";
    private static final List<LogLine> LOG_LINES_CACHE = new ArrayList<>();// TODO: 5.9.16 write to and read from session files in debug activity
    private static final LogsHandler ERROR_LOG = new LogsHandler();
    @NotNull private static LogTarget TARGET = new DefaultTarget();

    private Logger() {
    }

    public static LogsHandler getErrorLogsHandler() {
        return ERROR_LOG;
    }

    @NotNull
    public static LogTarget getLogTarget() {
        return TARGET;
    }

    public static void setLogTarget(@NotNull LogTarget target) {
        TARGET = target;
    }

    public static String getLogLinesCache() {
        StringBuilder sb = new StringBuilder();
        synchronized (LOG_LINES_CACHE) {
            for (LogLine logLine : LOG_LINES_CACHE) {
                sb.append(logLine).append('\n');
            }
        }
        sb.deleteCharAt(sb.length() - 1);
        return sb.toString();
    }

    static void println(LogLine logLine) {
        ERROR_LOG.onLogged(logLine);

        if (!Log.isInDebugMode()) return;
        synchronized (LOG_LINES_CACHE) {
            LOG_LINES_CACHE.add(logLine);
        }
        TARGET.println(logLine);
    }

}
