package eu.codetopic.java.utils.simple;

import java.util.Iterator;

public class IteratorWrapper<E> implements Iterator<E> {

    private final Iterator<E> mBase;

    public IteratorWrapper(Iterator<E> base) {
        mBase = base;
    }

    public Iterator<E> getBase() {
        return mBase;
    }

    @Override
    public boolean hasNext() {
        return getBase().hasNext();
    }

    @Override
    public E next() {
        return getBase().next();
    }

    @Override
    public void remove() {
        getBase().next();
    }
}
