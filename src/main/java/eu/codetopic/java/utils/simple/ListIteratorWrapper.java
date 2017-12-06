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

package eu.codetopic.java.utils.simple;

import java.util.ListIterator;

/**
 * Use kotlin's interface delegate instead
 * @param <E>
 */
@Deprecated
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
