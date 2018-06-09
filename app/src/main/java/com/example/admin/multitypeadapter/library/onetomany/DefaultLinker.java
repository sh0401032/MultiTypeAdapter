package com.example.admin.multitypeadapter.library.onetomany;

import android.support.annotation.NonNull;

/**
 * Created by huanshao on 2018/6/9.
 */

public class DefaultLinker<T> implements Linker<T> {

    @Override
    public int index(int position, @NonNull T item) {
        return 0;
    }
}
