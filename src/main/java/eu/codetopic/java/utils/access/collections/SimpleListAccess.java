package eu.codetopic.java.utils.access.collections;

import java.util.Collection;
import java.util.List;

import eu.codetopic.java.utils.access.Accessible;

public class SimpleListAccess<E> extends ListAccess<E> {

    private static final String LOG_TAG = "SimpleListAccess";

    public SimpleListAccess(Accessible<?, ?> creator, List<E> accessed) {
        super(creator, accessed);
    }

    @Override
    public void applyModifications(Object editTag, Collection<ListModification<E>> listModifications) {
        List<E> toModify = getAccessed();
        for (ListModification<E> modification : listModifications) {
            modification.modify(toModify);
        }
    }
}
