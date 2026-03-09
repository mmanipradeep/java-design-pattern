package com.creational.design.singleton;

public class EagerSingleton {


    private static final EagerSingleton instance = new EagerSingleton();


    private EagerSingleton(){
        System.out.println("singleton created");
    }

    public static EagerSingleton getInstance(){

        return instance;
    }

    public void ShowMessage(){
        System.out.println("Hello from Singleton");
    }
}
