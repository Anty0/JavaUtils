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

package eu.codetopic.java.utils.log.base;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.Serializable;

import eu.codetopic.java.utils.JavaUtils;

public final class LogLine implements Serializable {

    private static final String LOG_TAG = "LogLine";

    @NotNull private final Priority priority;
    @NotNull private final String tag;
    @Nullable private final String msg;
    @Nullable private final Throwable tr;

    /**
     * @param priority The priority/type of this log message
     * @param tag      Used to identify the source of a log message.  It usually identifies
     *                 the class or activity where the log call occurs.
     * @param msg      The message you would like logged.
     * @param tr       Throwable to be added after your message
     */
    public LogLine(@NotNull Priority priority, @NotNull String tag,
                   @Nullable String msg, @Nullable Throwable tr) {
        this.priority = priority;
        this.tag = tag;
        this.msg = msg;
        this.tr = tr;
    }

    public LogLine(@NotNull Priority priority, @NotNull String tag, @Nullable String msg) {
        this(priority, tag, msg, null);
    }

    @NotNull
    public Priority getPriority() {
        return priority;
    }

    @NotNull
    public String getTag() {
        return tag;
    }

    @Nullable
    public String getMsg() {
        return msg;
    }

    @Nullable
    public Throwable getTr() {
        return tr;
    }

    @NotNull
    public String getMsgWithTr() {
        StringBuilder sb = new StringBuilder();
        if (msg != null) sb.append(msg);
        if (tr != null) {
            if (sb.length() > 0) sb.append('\n');
            sb.append(JavaUtils.getStackTraceString(tr));
        }
        return sb.toString();
    }

    @Override
    public String toString() {
        return toString(true);
    }

    public String toString(boolean showTr) {
        return priority.getDisplayID() + "/" + tag + ": " + (showTr ? getMsgWithTr() : getMsg());
    }
}
