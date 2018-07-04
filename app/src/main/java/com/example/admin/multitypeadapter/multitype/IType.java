package com.example.admin.multitypeadapter.multitype;

import android.support.annotation.NonNull;

/**
 * Created by huanshao on 2018/7/4.
 */

public interface IType {

    <T> void register(@NonNull Class<? extends T> clazz, @NonNull ItemViewHolder<T, ?> holder);

    boolean unregister(@NonNull Class<?> clazz);

    int size();

    int firstIndexOf(Class<?> clazz);

    @NonNull
    Class<?> getClass(int index);

    @NonNull
    ItemViewHolder<?, ?> getItemViewHolder(int index);

}
