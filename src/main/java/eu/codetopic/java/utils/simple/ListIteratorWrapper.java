package eu.codetopic.java.utils.simple;

import java.util.ListIterator;

public class ListIteratorWrapper<E> implements ListIterator<E> {

    private final ListIterator<E> mBase;

    public ListIteratorWrapper(ListIterator<E> base) {
        mBase = base;
    }

    public ListIterator<E> getBase() {
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
    public boolean hasPrevious() {
        return getBase().hasPrevious();
    }

    @Override
    public E previous() {
        return getBase().previous();
    }

    @Override
    public int nextIndex() {
        return getBase().nextIndex();
    }

    @Override
    public int previousIndex() {
        return getBase().previousIndex();
    }

    @Override
    public void remove() {
        getBase().remove();
    }

    @Override
    public void set(E e) {
        getBase().set(e);
    }

    @Override
    public void add(E e) {
        getBase().add(e);
    }
}
