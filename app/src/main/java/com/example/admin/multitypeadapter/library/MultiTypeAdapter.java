package com.example.admin.multitypeadapter.library;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

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
        this.typePool.register(clazz, binder);
        binder.adapter = this;
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

    int indexInTypesOf(int position, @NonNull Object item) throws BinderNotFoundException {
        int index = typePool.firstIndexOf(item.getClass());
        if (index != -1) {
            return index;
        }
        throw new BinderNotFoundException(item.getClass());
    }

}
