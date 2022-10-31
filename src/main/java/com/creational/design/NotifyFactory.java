package com.creational.design;

import java.util.Date;

public class NotifyFactory {

    public <T> Notifier <T> getNotifier(Class<T> c){
        if(c== String.class){
            return Record.STRING.make();
        }
        if(c== Date.class){
            return Record.DATE.make();
        }


        return null;
    }

    public static void main(String[] args) {
        NotifyFactory factory = new NotifyFactory();
        Notifier<String> stringNotifier = factory.getNotifier(String.class);
        Notifier<Date> dateNotifier = factory.getNotifier(Date.class);

        stringNotifier.notify("Hello world!");
        dateNotifier.notify(new Date());
    }
}
