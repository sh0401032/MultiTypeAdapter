package com.example.admin.multitypeadapter.library.onetomany;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

import com.example.admin.multitypeadapter.library.ItemViewBinder;
import com.example.admin.multitypeadapter.library.MultiTypeAdapter;

import static com.example.admin.multitypeadapter.library.Utils.checkNotNull;

/**
 * Created by huanshao on 2018/6/9.
 */

public class OneToManyBuilder<T> implements OneToManyFlow<T>, OneToManyEndpoint<T> {

    private final @NonNull MultiTypeAdapter adapter;
    private final @NonNull Class<? extends T> clazz; // class对象
    private ItemViewBinder<T, ?>[] binders;// 多个itemViewBinder

    public OneToManyBuilder(@NonNull MultiTypeAdapter adapter, Class<? extends T> item) {
        this.adapter = adapter;
        this.clazz = item;
    }

    @CheckResult
    @Override
    public final OneToManyEndpoint<T> to(@NonNull ItemViewBinder<T, ?>[] binders) {
        checkNotNull(binders);
        this.binders = binders;
        return this;
    }


    @Override
    public void withLinker(@NonNull Linker<T> linker) {
        checkNotNull(linker);
        doRegister(linker);
    }

    @Override
    public void withClassLinker(@NonNull ClassLinker<T> classLinker) {
        checkNotNull(classLinker);

    }

    private void doRegister(Linker<T> linker) {
        for (ItemViewBinder<T, ?> binder : binders) {
            if (adapter != null) {
                adapter.register(clazz, binder, linker);
            }
        }
    }
}
