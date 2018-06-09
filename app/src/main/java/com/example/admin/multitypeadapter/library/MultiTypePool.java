package com.example.admin.multitypeadapter.library;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import static com.example.admin.multitypeadapter.library.Utils.checkNotNull;

/**
 * Created by huanshao on 2018/6/9.
 */

public class MultiTypePool implements TypePool {

    private final List<Class<?>> classes;
    private final List<ItemViewBinder<?, ?>> binders;

    public MultiTypePool() {
        this.classes = new ArrayList<>();
        this.binders = new ArrayList<>();
    }

    public MultiTypePool(int initialCapacity) {
        this.classes = new ArrayList<>(initialCapacity);
        this.binders = new ArrayList<>(initialCapacity);
    }

    /*public MultiTypePool(
            @NonNull List<Class<?>> classes,
            @NonNull List<ItemViewBinder<?, ?>> binders) {
        checkNotNull(classes);
        checkNotNull(binders);
        this.classes = classes;
        this.binders = binders;
    }*/


    @Override
    public <T> void register(@NonNull Class<? extends T> clazz, @NonNull ItemViewBinder<T, ?> binder) {
        checkNotNull(clazz);
        checkNotNull(binder);
        this.classes.add(clazz);
        this.binders.add(binder);
    }

    @Override
    public boolean unregister(@NonNull Class<?> clazz) {
        checkNotNull(clazz);
        boolean removed = false;
        while (true) {
            int index = this.classes.indexOf(clazz);
            if (index != -1) {
                this.classes.remove(index);
                this.binders.remove(index);
                removed = true;
            } else {
                break;
            }
        }
        return removed;
    }

    @Override
    public int size() {
        return this.classes.size();
    }

    @Override
    public int firstIndexOf(@NonNull Class<?> clazz) {
        checkNotNull(clazz);
        int index = this.classes.indexOf(clazz);
        if (index != -1) {
            return index;
        }
        // 判断是否是子类
        for (int i = 0; i < this.classes.size(); i++) {
            if (this.classes.get(i).isAssignableFrom(clazz)) {
                return i;
            }
        }
        return -1;
    }

    @NonNull
    @Override
    public Class<?> getClass(int index) {
        return this.classes.get(index);
    }

    @NonNull
    @Override
    public ItemViewBinder<?, ?> getItemViewBinder(int index) {
        return this.binders.get(index);
    }
}
