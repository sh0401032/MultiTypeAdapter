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

public class OneType1ItemViewBinder extends ItemViewBinder<One, OneType1ItemViewBinder.ViewHolder> {

    @Override
    protected OneType1ItemViewBinder.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new OneType1ItemViewBinder.ViewHolder(inflater.inflate(R.layout.item_title, parent, false));
    }

    @Override
    protected void onBinderViewHolder(@NonNull OneType1ItemViewBinder.ViewHolder holder, @NonNull One item) {
        holder.tv.setText("isMenu = " + item.isMenu() + "=== type1");
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_title);
        }
    }
}

