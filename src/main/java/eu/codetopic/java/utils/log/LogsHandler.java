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
