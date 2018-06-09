package com.example.admin.multitypeadapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.admin.multitypeadapter.library.ItemViewBinder;

/**
 * Created by huanshao on 2018/6/9.
 */

public class IntItemViewHolder extends ItemViewBinder<Integer, IntItemViewHolder.IntViewHolder> {


    @Override
    protected IntViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_title, parent, false);
        return new IntViewHolder(view);
    }

    @Override
    protected void onBinderViewHolder(@NonNull IntViewHolder holder, @NonNull Integer item) {
        holder.tv.setText(item.toString());
    }

    class IntViewHolder extends RecyclerView.ViewHolder {

        private TextView tv;

        public IntViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_title);
        }
    }
}
