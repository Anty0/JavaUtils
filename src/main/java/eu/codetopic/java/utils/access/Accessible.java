package eu.codetopic.java.utils.access;

public interface Accessible<E, T extends Access<E>> {

    T createAccess();

    void onAccessCreated(Access<?> access);

    void onAccessClosed(Access<?> access);
}
