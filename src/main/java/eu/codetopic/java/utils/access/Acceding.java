package eu.codetopic.java.utils.access;

public class Acceding<E, T extends Access<E>> {

    private static final String LOG_TAG = "Acceding";

    private final T access;

    public Acceding(Accessible<E, T> accessible) {
        access = accessible.createAccess();
    }

    public T getAccess() {
        return access;
    }

    public synchronized final boolean isClosed() {
        return access.isClosed();
    }

    public synchronized final boolean close() {
        return access.close();
    }
}
