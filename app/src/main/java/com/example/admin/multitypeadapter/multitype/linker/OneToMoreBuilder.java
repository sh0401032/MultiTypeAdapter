package com.example.admin.multitypeadapter.multitype.linker;

import android.support.annotation.NonNull;

import com.example.admin.multitypeadapter.multitype.ItemViewHolder;
import com.example.admin.multitypeadapter.multitype.MultiTypeAdapter;

import static com.example.admin.multitypeadapter.multitype.Preconditions.checkNotNull;

/**
 * Created by huanshao on 2018/7/4.
 */

public class OneToMoreBuilder<T> {

    private final MultiTypeAdapter adapter;

    private final Class<? extends T> clazz;

    private ItemViewHolder<T, ?>[] holders;

    public OneToMoreBuilder(@NonNull MultiTypeAdapter adapter, Class<? extends T> clazz) {
        this.adapter = adapter;
        this.clazz = clazz;
    }

    public OneToMoreBuilder<T> to(ItemViewHolder<T, ?>[] holders) {
        checkNotNull(holders);
        this.holders = holders;
        return this;
    }

    public void withLink(@NonNull Link<T> link) {
        checkNotNull(link);
        doRegister(link);
    }

    private void doRegister(Link<T> link) {
        for (ItemViewHolder<T, ?> holder : holders) {
            if (adapter != null) {
                adapter.register(clazz, holder, link);
            }
        }
    }
}


