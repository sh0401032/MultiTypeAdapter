package com.example.admin.multitypeadapter.library;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.example.admin.multitypeadapter.library.onetomany.DefaultLinker;
import com.example.admin.multitypeadapter.library.onetomany.Linker;
import com.example.admin.multitypeadapter.library.onetomany.OneToManyBuilder;
import com.example.admin.multitypeadapter.library.onetomany.OneToManyFlow;

import java.util.Collections;
import java.util.List;

import static com.example.admin.multitypeadapter.library.Utils.checkNotNull;

/**
 * Created by huanshao on 2018/6/9.
 */

public class MultiTypeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "MultiTypeAdapter";

    private @NonNull
    List<?> items;

    private TypePool typePool;

    public MultiTypeAdapter() {
        this(Collections.emptyList());
    }

    public MultiTypeAdapter(@NonNull List<?> items) {
        this(items, new MultiTypePool());
    }

    public MultiTypeAdapter(@NonNull List<?> items, int initialCapacity) {
        this(items, new MultiTypePool(initialCapacity));
    }

    public MultiTypeAdapter(@NonNull List<?> items, @NonNull TypePool pool) {
        checkNotNull(items);
        checkNotNull(pool);
        this.items = items;
        this.typePool = pool;
    }

    public <T> void register(Class<? extends T> clazz, ItemViewBinder<T, ?> binder) {
        checkNotNull(clazz);
        checkNotNull(binder);
        checkAndRemoveAllTypesIfNeeded(clazz);
        register(clazz, binder, new DefaultLinker());
    }

    public <T> void register(Class<? extends T> clazz, ItemViewBinder<T, ?> binder, Linker<T> linker) {
        this.typePool.register(clazz, binder, linker);
        binder.adapter = this;
    }

    /**
     * 一对多，检测方法返回结果并进行后续处理
     *
     * @param clazz
     * @param <T>
     * @return
     */
    @CheckResult
    public <T> OneToManyFlow<T> register(Class<? extends T> clazz) {
        checkNotNull(clazz);
        checkAndRemoveAllTypesIfNeeded(clazz);
        return new OneToManyBuilder<>(this, clazz);
    }

    public void setItems(@NonNull List<?> items) {
        checkNotNull(items);
        this.items = items;
    }

    public List<?> getItems() {
        return items;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int indexViewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        ItemViewBinder binder = this.typePool.getItemViewBinder(indexViewType);
        return binder.onCreateViewHolder(inflater, parent);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Object item = this.items.get(position);
        ItemViewBinder binder = this.typePool.getItemViewBinder(holder.getItemViewType());
        binder.onBinderViewHolder(holder, item);
    }

    @Override
    public int getItemCount() {
        return items == null ? 0 : items.size();
    }

    @Override
    public int getItemViewType(int position) {
        Object obj = this.items.get(position);
        return indexInTypesOf(position, obj);
    }

    private void checkAndRemoveAllTypesIfNeeded(@NonNull Class<?> clazz) {
        if (typePool.unregister(clazz)) {
            Log.w(TAG, "You have registered the " + clazz.getSimpleName() + " type. " +
                    "It will override the original binder(s).");
        }
    }

    private int indexInTypesOf(int position, @NonNull Object item) throws BinderNotFoundException {
        int index = typePool.firstIndexOf(item.getClass());
        if (index != -1) {
            @SuppressWarnings("unchecked")
            Linker<Object> linker = (Linker<Object>) typePool.getLinker(index);
            return index + linker.index(position, item);
        }
        throw new BinderNotFoundException(item.getClass());
    }

}
