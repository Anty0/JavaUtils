/*
 * Copyright 2017 Jiří Kuchyňka (Anty)
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
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
