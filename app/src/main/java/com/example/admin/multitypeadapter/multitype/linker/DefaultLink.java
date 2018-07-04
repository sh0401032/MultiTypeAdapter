package com.example.admin.multitypeadapter.multitype.linker;

import android.support.annotation.NonNull;

/**
 * Created by huanshao on 2018/7/4.
 */

public class DefaultLink implements Link {

    @Override
    public int index(int position, @NonNull Object o) {
        return 0;
    }
}
