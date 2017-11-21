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

package eu.codetopic.java.utils.access.collections;

import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import eu.codetopic.java.utils.access.Acceding;
import eu.codetopic.java.utils.access.Accessible;
import eu.codetopic.java.utils.simple.IteratorWrapper;
import eu.codetopic.java.utils.simple.ListIteratorWrapper;

public class ListReader<E, T extends ListAccess<E>> extends Acceding<List<E>, T> implements Iterable<E> {

    private static final String LOG_TAG = "ListReader";

    private List<E> content;

    protected ListReader(Accessible<List<E>, T> accessible) {
        super(accessible);
        content = getAccess().getContentAsImmutable();
    }

    public int size() {
        return content.size();
    }

    public boolean isEmpty() {
        return content.isEmpty();
    }

    public boolean contains(Object o) {
        //noinspection SuspiciousMethodCalls
        return content.contains(o);
    }

    @NotNull
    @Override
    public Iterator<E> iterator() {
        return new IteratorWrapper<E>(content.iterator()) {
            @Override
            public Iterator<E> getBase() {
                if (isClosed()) throw new IllegalStateException(LOG_TAG + " is closed");
                return super.getBase();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("AccessibleList cannot be edited from iterator.");
            }
        };
    }

    @NotNull
    public Object[] toArray() {
        return content.toArray();
    }

    @NotNull
    public <ET> ET[] toArray(ET[] ts) {
        //noinspection SuspiciousToArrayCall
        return content.toArray(ts);
    }

    public boolean containsAll(Collection<?> collection) {
        return content.containsAll(collection);
    }

    public E get(int i) {
        return content.get(i);
    }

    public int indexOf(Object o) {
        //noinspection SuspiciousMethodCalls
        return content.indexOf(o);
    }

    public int lastIndexOf(Object o) {
        //noinspection SuspiciousMethodCalls
        return content.lastIndexOf(o);
    }

    @NotNull
    public ListIterator<E> listIterator() {
        return wrapListIterator(content.listIterator());
    }

    @NotNull
    public ListIterator<E> listIterator(int i) {
        return wrapListIterator(content.listIterator(i));
    }

    private ListIterator<E> wrapListIterator(ListIterator<E> listIterator) {
        return new ListIteratorWrapper<E>(listIterator) {
            @Override
            public ListIterator<E> getBase() {
                if (isClosed()) throw new IllegalStateException(LOG_TAG + " is closed");
                return super.getBase();
            }

            @Override
            public void remove() {
                throw new UnsupportedOperationException("AccessibleList cannot be edited from iterator.");
            }

            @Override
            public void set(E e) {
                throw new UnsupportedOperationException("AccessibleList cannot be edited from iterator.");
            }

            @Override
            public void add(E e) {
                throw new UnsupportedOperationException("AccessibleList cannot be edited from iterator.");
            }
        };
    }
}
