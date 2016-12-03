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
        for (OnLoggedListener listener : listeners)
            if (ArrayTools.contains(listener.filterPriorities(), priority))
                listener.onLogged(logLine);
    }

    public interface OnLoggedListener {

        Priority[] filterPriorities();

        void onLogged(LogLine logLine);
    }

}
