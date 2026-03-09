package com.creational.design.singleton;

public class DoubleCheckLocking {

    // volatile ensures visibility and prevents partially constructed objects
    private static volatile DoubleCheckLocking instance;

    private DoubleCheckLocking() {}

    public static DoubleCheckLocking getInstance() {
        if (instance == null) { // First check (no lock)
            synchronized (DoubleCheckLocking.class) {
                if (instance == null) { // Second check (with lock)
                    instance = new DoubleCheckLocking();
                }
            }
        }
        return instance;
    }
}
