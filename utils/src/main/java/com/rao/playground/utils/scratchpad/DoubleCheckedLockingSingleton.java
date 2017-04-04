package com.rao.playground.utils.scratchpad;

public class DoubleCheckedLockingSingleton {

    private static volatile DoubleCheckedLockingSingleton instance = null;

    private DoubleCheckedLockingSingleton() {

    }

    private static DoubleCheckedLockingSingleton getInstance() {

        if (instance == null) {
            synchronized (DoubleCheckedLockingSingleton.class) {
                if (instance == null) {
                    instance = new DoubleCheckedLockingSingleton();
                }
            }
        }
        return instance;
    }


}
