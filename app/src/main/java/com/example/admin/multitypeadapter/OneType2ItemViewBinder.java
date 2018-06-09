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

public class OneType2ItemViewBinder extends ItemViewBinder<One, OneType2ItemViewBinder.ViewHolder> {

    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.item_title, parent, false));
    }

    @Override
    protected void onBinderViewHolder(@NonNull ViewHolder holder, @NonNull One item) {
        holder.tv.setText("isMenu = " + item.isMenu() + "===type2");
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv_title);
        }
    }
}
