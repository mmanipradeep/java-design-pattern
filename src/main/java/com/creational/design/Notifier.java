package com.creational.design;

public interface Notifier<T> {
    void notify(T obj);
}

//A factory is a class that creates objects of a prototype class, aka interface, from a method call:
//The factory pattern is good when we want to create objects of a common interface while hiding the creation logic from the user.