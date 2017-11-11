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

package eu.codetopic.java.utils;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.ref.WeakReference;
import java.net.UnknownHostException;

import eu.codetopic.java.utils.exception.SourceUnavailableException;

public final class JavaUtils {

    private static final String LOG_TAG = "JavaUtils";

    private JavaUtils() {
    }

    //////////////////////////////////////
    //////REGION - TEXTS_AND_STRINGS//////
    //////////////////////////////////////

    public static String substring(@NotNull String base, @Nullable String start,
                                   @Nullable String end) {

        int startIndex = start != null ? base.indexOf(start) : -1;
        if (startIndex != -1) startIndex += start.length();
        int endIndex = end != null ? base.indexOf(end) : -1;
        return base.substring(startIndex != -1 ? startIndex : 0,
                endIndex != -1 ? endIndex : base.length());
    }

    public static String fillToMaxLen(int toFill) {
        return fillToLen(Integer.toString(toFill),
                Integer.toString(Integer.MAX_VALUE).length());
    }

    public static String fillToLen(CharSequence toFill, int len) {
        StringBuilder builder = new StringBuilder();
        for (int i = toFill.length(); i < len; i++)
            builder.append(" ");
        builder.append(toFill);
        return builder.toString();
    }

    public static String addBeforeEveryLine(String toEdit, String toAdd) {
        return toAdd + toEdit.replace("\n", "\n" + toAdd);
    }

    public static double parseDouble(String string) {
        try {
            return Double.parseDouble(string.replace(",", ".").replaceAll("[^\\d.]", ""));
        } catch (Exception e) {
            return 0;
        }
    }

    public static String getStackTraceString(Throwable tr) {
        if (tr == null) return "";

        // This is to reduce the amount of log spew that apps do in the non-error
        // condition of the network being unavailable.
        Throwable t = tr;
        while (t != null) {
            if (t instanceof UnknownHostException) return "";
            t = t.getCause();
        }

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        tr.printStackTrace(pw);
        pw.flush();
        return sw.toString();
    }

    //////////////////////////////////////
    //////REGION - WEAK_REFERENCE/////////
    //////////////////////////////////////

    @NotNull
    public static <T> T extractReference(WeakReference<T> reference) {
        T referent = reference.get();
        if (referent == null)
            throw new SourceUnavailableException("Referent was garbage collected");
        return referent;
    }
}
