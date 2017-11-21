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

package eu.codetopic.java.utils.access;

import org.jetbrains.annotations.NotNull;

import java.lang.ref.WeakReference;

import eu.codetopic.java.utils.JavaUtils;
import eu.codetopic.java.utils.log.Log;

public class Access<A> {

    private static final String LOG_TAG = "Access";

    private final WeakReference<Accessible<?, ?>> creator;
    private A accessed;
    private boolean closed = false;

    public Access(Accessible<?, ?> creator, A accessed) {
        this.creator = new WeakReference<Accessible<?, ?>>(creator);
        this.accessed = accessed;
        creator.onAccessCreated(this);
    }

    @NotNull
    protected Accessible<?, ?> getCreator() {
        return JavaUtils.extractReference(creator);
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
