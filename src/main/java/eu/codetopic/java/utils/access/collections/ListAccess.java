package eu.codetopic.java.utils.access.collections;

import com.google.common.collect.ImmutableList;

import java.util.Collection;
import java.util.List;

import eu.codetopic.java.utils.access.Access;
import eu.codetopic.java.utils.access.Accessible;

public abstract class ListAccess<E> extends Access<List<E>> {

    private static final String LOG_TAG = "ListAccess";

    public ListAccess(Accessible<?, ?> creator, List<E> accessed) {
        super(creator, accessed);
    }

    public ImmutableList<E> getContentAsImmutable() {
        return ImmutableList.copyOf(getAccessed());
    }

    public abstract void applyModifications(Object editTag, Collection<ListModification<E>> modifications);
}
