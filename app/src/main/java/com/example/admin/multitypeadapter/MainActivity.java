package com.example.admin.multitypeadapter;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.admin.multitypeadapter.library.MultiTypeAdapter;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.ScheduledThreadPoolExecutor;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rv;
    private MultiTypeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rv = findViewById(R.id.rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);
        adapter = new MultiTypeAdapter();
        adapter.register(String.class, new TitleItemViewHolder());
        adapter.register(Integer.class, new IntItemViewHolder());
        rv.setAdapter(adapter);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                ArrayList<Object> list = new ArrayList<>();
                for (int i = 0; i < 20; i++) {
                    list.add("Title " + i);
                    list.add(i);
                }
                for (int i = 0; i < 10; i++) {
                    list.add(i + 100);
                }
                adapter.setItems(list);
                adapter.notifyDataSetChanged();
            }
        }, 5000);
    }
}
