package main.model;

public class IDGenerator {
    private static int counter = 1;

    public static int nextId() {
        return counter++;
    }
}
