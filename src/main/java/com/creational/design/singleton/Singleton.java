package com.creational.design.singleton;

public class Singleton {

    private static Singleton instance ;

    private Singleton(){
        System.out.println("singleton created");
    }

    public static Singleton getInstance(){
        if(instance ==null){
            instance = new Singleton();
        }
        return instance;
    }

    public void ShowMessage(){
        System.out.println("Hello from Singleton");
    }
}
