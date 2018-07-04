package com.example.admin.multitypeadapter.multitype;

import android.support.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import static com.example.admin.multitypeadapter.multitype.Preconditions.checkNotNull;


/**
 * Created by huanshao on 2018/7/4.
 */

public class MultiType implements IType {

    private final List<Class<?>> classes;
    private final List<ItemViewHolder<?, ?>> holders;

    public MultiType() {
        classes = new ArrayList<>();
        holders = new ArrayList<>();
    }

    public MultiType(int initialCapacity) {
        this.classes = new ArrayList<>(initialCapacity);
        this.holders = new ArrayList<>(initialCapacity);
    }

    @Override
    public <T> void register(@NonNull Class<? extends T> clazz, @NonNull ItemViewHolder<T, ?> holder) {
        checkNotNull(clazz);
        checkNotNull(holder);
        this.classes.add(clazz);
        this.holders.add(holder);
    }

    @Override
    public boolean unregister(@NonNull Class<?> clazz) {
        checkNotNull(clazz);
        boolean removed = false;
        while (true) {
            int index = this.classes.indexOf(clazz);
            if (index != -1) {
                this.classes.remove(index);
                this.holders.remove(index);
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
    public int firstIndexOf(Class<?> clazz) {
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
    public ItemViewHolder<?, ?> getItemViewHolder(int index) {
        return this.holders.get(index);
    }
}
