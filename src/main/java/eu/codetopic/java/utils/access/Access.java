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
