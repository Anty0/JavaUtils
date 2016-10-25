package eu.codetopic.java.utils.access;

import java.util.concurrent.locks.Lock;

import eu.codetopic.java.utils.simple.SimpleLock;

public abstract class SynchronizedAccessible<E, T extends Access<E>> implements Accessible<E, T> {

    private static final String LOG_TAG = "SynchronizedAccessible";

    private final Lock lock = new SimpleLock();

    @Override
    public void onAccessCreated(Access<?> access) {
        lock.lock();
    }

    @Override
    public void onAccessClosed(Access<?> access) {
        lock.unlock();
    }
}
