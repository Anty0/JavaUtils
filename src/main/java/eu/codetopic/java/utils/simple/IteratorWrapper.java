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
