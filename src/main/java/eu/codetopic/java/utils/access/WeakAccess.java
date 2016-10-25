package eu.codetopic.java.utils.access;

import org.jetbrains.annotations.NotNull;

import java.lang.ref.WeakReference;

import eu.codetopic.java.utils.JavaUtils;

public class WeakAccess<A> extends Access<WeakReference<A>> {

    private static final String LOG_TAG = "WeakAccess";

    public WeakAccess(Accessible<?, ?> creator, @NotNull A accessed) {
        super(creator, new WeakReference<>(accessed));
    }

    @NotNull
    protected synchronized A getExtractedAccessed() {
        return JavaUtils.extractReference(getAccessed());
    }
}
