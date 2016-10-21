package eu.codetopic.java.utils.log.base;

public class DefaultTarget implements LogTarget {

    @Override
    public void println(LogLine logLine) {
        if (Priority.ERROR.equals(logLine.getPriority())) System.err.println(logLine);
        else System.out.println(logLine);
    }
}