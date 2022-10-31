package com.creational.design.factory;

public class StringNotifier implements Notifier<String>{

    @Override
    public void notify(String str){
        System.out.println("Notifying" +str);
    }
}
