package eu.codetopic.java.utils.access.collections;

import org.jetbrains.annotations.NotNull;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import eu.codetopic.java.utils.access.SynchronizedAccessible;

public abstract class AccessibleList<E, T extends ListAccess<E>> extends SynchronizedAccessible<List<E>, T> {

    private static final String LOG_TAG = "AccessibleList";

    private final List<E> list;

    public AccessibleList() {
        this.list = new ArrayList<>();
    }

    protected AccessibleList(@NotNull List<E> list) {
        this.list = list;
    }

    protected List<E> getList() {
        return list;
    }

    public ListReader<E, T> read() {
        return new ListReader<>(this);
    }

    public ListEditor<E, T> edit() {
        return new ListEditor<>(this);
    }


    public int size() {
        ListReader<E, T> reader = read();
        try {
            return reader.size();
        } finally {
            reader.close();
        }
    }

    public boolean isEmpty() {
        ListReader<E, T> reader = read();
        try {
            return reader.isEmpty();
        } finally {
            reader.close();
        }
    }

    public boolean contains(Object o) {
        ListReader<E, T> reader = read();
        try {
            return reader.contains(o);
        } finally {
            reader.close();
        }
    }

    @NotNull
    public Object[] toArray() {
        ListReader<E, T> reader = read();
        try {
            return reader.toArray();
        } finally {
            reader.close();
        }
    }

    public E[] toArray(Class<E> elementsClass) {
        ListReader<E, T> reader = read();
        try {
            //noinspection unchecked
            E[] array = (E[]) Array.newInstance(elementsClass, reader.size());
            return reader.toArray(array);
        } finally {
            reader.close();
        }
    }

    @NotNull
    public <ET> ET[] toArray(ET[] ts) {
        ListReader<E, T> reader = read();
        try {
            return reader.toArray(ts);
        } finally {
            reader.close();
        }
    }

    public boolean containsAll(Collection<?> collection) {
        ListReader<E, T> reader = read();
        try {
            return reader.containsAll(collection);
        } finally {
            reader.close();
        }
    }

    public E get(int i) {
        ListReader<E, T> reader = read();
        try {
            return reader.get(i);
        } finally {
            reader.close();
        }
    }

    public int indexOf(Object o) {
        ListReader<E, T> reader = read();
        try {
            return reader.indexOf(o);
        } finally {
            reader.close();
        }
    }

    public int lastIndexOf(Object o) {
        ListReader<E, T> reader = read();
        try {
            return reader.lastIndexOf(o);
        } finally {
            reader.close();
        }
    }
}
