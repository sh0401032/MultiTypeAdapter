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

public class TitleItemViewHolder extends ItemViewBinder<String, TitleItemViewHolder.TitleViewHolder> {
    @Override
    protected TitleViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_title, parent,false);
        return new TitleViewHolder(view);
    }

    @Override
    protected void onBinderViewHolder(@NonNull TitleViewHolder holder, @NonNull String item) {
        holder.tvTitle.setText(item);
    }

    static class TitleViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTitle;

        public TitleViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
        }
    }
}
