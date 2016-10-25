package eu.codetopic.java.utils.access.collections;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class SimpleAccessibleList<E> extends AccessibleList<E, SimpleListAccess<E>> {

    private static final String LOG_TAG = "SimpleAccessibleList";

    public SimpleAccessibleList() {
    }

    public SimpleAccessibleList(@NotNull List<E> list) {
        super(list);
    }

    @Override
    public SimpleListAccess<E> createAccess() {
        return new SimpleListAccess<>(this, getList());
    }
}
