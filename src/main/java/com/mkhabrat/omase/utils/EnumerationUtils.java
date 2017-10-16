package com.mkhabrat.omase.utils;

import java.util.Enumeration;
import java.util.function.Consumer;

/**
 * Created by Maxon on 16.10.2017.
 */
public class EnumerationUtils {

    public static <T> void forEachRemaining(Enumeration<T> e, Consumer<? super T> c) {
        while(e.hasMoreElements()) c.accept(e.nextElement());
    }
}
