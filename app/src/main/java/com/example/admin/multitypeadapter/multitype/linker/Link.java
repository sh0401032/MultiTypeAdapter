package com.example.admin.multitypeadapter.multitype.linker;

import android.support.annotation.IntRange;
import android.support.annotation.NonNull;

/**
 * Created by huanshao on 2018/7/4.
 */

public interface Link<T> {
    @IntRange(from = 0)
    int index(int position, @NonNull T t);
}
