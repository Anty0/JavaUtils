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
