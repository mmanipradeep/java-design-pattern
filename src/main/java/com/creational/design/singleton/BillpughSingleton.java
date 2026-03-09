package com.creational.design.singleton;

public class BillpughSingleton {

    private BillpughSingleton() {
    }

    private static class SingletonHelper {
        private static final BillpughSingleton BILL_PUGH_SINGLETON_INSTANCE = new BillpughSingleton();
    }

    public static BillpughSingleton getInstance() {
        return SingletonHelper.BILL_PUGH_SINGLETON_INSTANCE;
    }
}
