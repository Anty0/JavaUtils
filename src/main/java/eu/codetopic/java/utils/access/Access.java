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

package eu.codetopic.java.utils.access;

import org.jetbrains.annotations.NotNull;

import java.lang.ref.WeakReference;

import eu.codetopic.java.utils.log.Log;

import static eu.codetopic.java.utils.ExtensionsKt.getOrThrow;

public class Access<A> {

    private static final String LOG_TAG = "Access";

    private final WeakReference<Accessible<?, ?>> creator;
    private A accessed;
    private boolean closed = false;

    public Access(Accessible<?, ?> creator, A accessed) {
        this.creator = new WeakReference<>(creator);
        this.accessed = accessed;
        creator.onAccessCreated(this);
    }

    @NotNull
    protected Accessible<?, ?> getCreator() {
        return getOrThrow(creator);
    }

    protected synchronized A getAccessed() {
        if (closed) throw new IllegalStateException(LOG_TAG + " is still closed");
        return accessed;
    }

    protected void onClose() {
    }

    public synchronized final boolean isClosed() {
        return closed;
    }

    public synchronized final boolean close() {
        if (closed) return false;
        onClose();
        closed = true;
        accessed = null;
        try {
            getCreator().onAccessClosed(this);
        } catch (Exception e) {
            Log.d(LOG_TAG, "close", e);
        }
        return true;
    }
}
