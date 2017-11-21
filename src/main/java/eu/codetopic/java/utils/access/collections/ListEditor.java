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

import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import eu.codetopic.java.utils.access.Accessible;
import eu.codetopic.java.utils.log.Log;

public class ListEditor<E, T extends ListAccess<E>> extends ListReader<E, T> {

    private static final String LOG_TAG = "ListEditor";

    private final ArrayList<ListModification<E>> modifications = new ArrayList<>();
    private Object tag = null;

    protected ListEditor(Accessible<List<E>, T> accessible) {
        super(accessible);
    }

    public synchronized Object getTag() {
        return tag;
    }

    public synchronized ListEditor<E, T> setTag(@Nullable Object tag) {
        this.tag = tag;
        return this;
    }

    public synchronized ListEditor<E, T> post(ListModification<E> modification) {
        modifications.add(modification);
        return this;
    }

    public ListEditor<E, T> add(final E object) {
        return post(new ListModification<E>() {
            @Override
            public void modify(List<E> toModify) {
                toModify.add(object);
            }
        });
    }

    public ListEditor<E, T> add(final int index, final E object) {
        return post(new ListModification<E>() {
            @Override
            public void modify(List<E> toModify) {
                toModify.add(index, object);
            }
        });
    }

    public <AT extends E> ListEditor<E, T> addAll(final AT[] array) {
        return addAll(Arrays.asList(array));
    }

    public ListEditor<E, T> addAll(final Collection<? extends E> collection) {
        return post(new ListModification<E>() {
            @Override
            public void modify(List<E> toModify) {
                toModify.addAll(collection);
            }
        });
    }

    public ListEditor<E, T> addAll(final int index, final Collection<? extends E> collection) {
        return post(new ListModification<E>() {
            @Override
            public void modify(List<E> toModify) {
                toModify.addAll(index, collection);
            }
        });
    }

    public ListEditor<E, T> clear() {
        return post(new ListModification<E>() {
            @Override
            public void modify(List<E> toModify) {
                toModify.clear();
            }
        });
    }

    public ListEditor<E, T> remove(final int index) {
        return post(new ListModification<E>() {
            @Override
            public void modify(List<E> toModify) {
                toModify.remove(index);
            }
        });
    }

    public ListEditor<E, T> remove(final Object object) {
        return post(new ListModification<E>() {
            @Override
            public void modify(List<E> toModify) {
                //noinspection SuspiciousMethodCalls
                toModify.remove(object);
            }
        });
    }

    public ListEditor<E, T> set(final int index, final E object) {
        return post(new ListModification<E>() {
            @Override
            public void modify(List<E> toModify) {
                toModify.set(index, object);
            }
        });
    }

    public ListEditor<E, T> removeAll(final Collection<?> collection) {
        return post(new ListModification<E>() {
            @Override
            public void modify(List<E> toModify) {
                //noinspection SuspiciousMethodCalls
                toModify.removeAll(collection);
            }
        });
    }

    public ListEditor<E, T> retainAll(final Collection<?> collection) {
        return post(new ListModification<E>() {
            @Override
            public void modify(List<E> toModify) {
                toModify.retainAll(collection);
            }
        });
    }

    public synchronized boolean apply() {
        try {
            getAccess().applyModifications(getTag(), modifications);
            modifications.clear();
            cancel();
            return true;
        } catch (Exception e) {
            Log.d(LOG_TAG, "apply", e);
            return false;
        }
    }

    public synchronized boolean cancel() {
        return close();
    }
}
