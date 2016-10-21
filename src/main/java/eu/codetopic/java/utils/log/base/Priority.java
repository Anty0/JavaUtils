package eu.codetopic.java.utils.log.base;

public enum Priority {

    VERBOSE('V'), DEBUG('D'), INFO('I'), WARN('W'), ERROR('E'), ASSERT('A');

    private final char displayID;

    Priority(char displayID) {
        this.displayID = displayID;
    }

    public char getDisplayID() {
        return displayID;
    }
}
