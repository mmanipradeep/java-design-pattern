package com.creational.design.singleton;

public class SynchronizedSingleton {

    private static SynchronizedSingleton instance ;

    private SynchronizedSingleton(){
        System.out.println("singleton created");
    }

    public static synchronized SynchronizedSingleton getInstance(){
        if(instance ==null){
            instance = new SynchronizedSingleton();
        }
        return instance;
    }

    public void ShowMessage(){
        System.out.println("Hello from Singleton");
    }
}
