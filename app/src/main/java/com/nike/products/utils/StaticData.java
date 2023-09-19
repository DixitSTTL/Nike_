package com.nike.products.utils;

public class StaticData {
    static {
        System.loadLibrary("securedata-lib");
    }

    public static native String getAPIKEY();

    public static native String getBaseURL();

}
