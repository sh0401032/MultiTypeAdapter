package com.example.admin.multitypeadapter.library;

import android.support.annotation.NonNull;

/**
 * Created by huanshao on 2018/6/9.
 */

public class Utils {

    public static @NonNull
    <T> T checkNotNull(@NonNull final T object) {
        if (object == null) {
            throw new NullPointerException();
        }
        return object;
    }
}
