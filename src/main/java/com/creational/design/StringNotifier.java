package com.creational.design;

public class StringNotifier implements Notifier<String>{

    @Override
    public void notify(String str){
        System.out.println("Notifying" +str);
    }
}
