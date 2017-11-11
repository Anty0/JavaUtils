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

import java.util.ArrayList;
import java.util.List;

import eu.codetopic.java.utils.ArrayTools;
import eu.codetopic.java.utils.log.base.LogLine;
import eu.codetopic.java.utils.log.base.Priority;

public final class LogsHandler {

    private static final String LOG_TAG = "LogsHandler";

    private final List<OnLoggedListener> listeners = new ArrayList<>();

    LogsHandler() {
    }

    public synchronized void addOnLoggedListener(OnLoggedListener listener) {
        listeners.add(listener);
    }

    public synchronized void removeOnLoggedListener(OnLoggedListener listener) {
        listeners.remove(listener);
    }

    synchronized void onLogged(final LogLine logLine) {
        Priority priority = logLine.getPriority();
        for (OnLoggedListener listener : listeners) {
            Priority[] allowedPriorities = listener.filterPriorities();
            if (allowedPriorities == null || ArrayTools.contains(allowedPriorities, priority)) {
                listener.onLogged(logLine);
            }
        }
    }

    public interface OnLoggedListener {

        Priority[] filterPriorities();

        void onLogged(LogLine logLine);
    }

}
