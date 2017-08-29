// IDaemonInterface.aidl
package com.tonychen.trainingapp;

// Declare any non-default types here with import statements

interface IMainInterface {
    /**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
    void basicTypes(int anInt, long aLong, boolean aBoolean, float aFloat,
            double aDouble, String aString);

     int getPID();

     void simulateHome();
     void simulateBack();
}
