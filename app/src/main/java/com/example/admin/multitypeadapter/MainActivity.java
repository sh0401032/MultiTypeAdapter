package com.example.admin.multitypeadapter;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.admin.multitypeadapter.library.MultiTypeAdapter;
import com.example.admin.multitypeadapter.library.onetomany.Linker;

import java.util.ArrayList;

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
        adapter.register(One.class).to(new OneType1ItemViewBinder(), new OneType2ItemViewBinder())
                .withLinker(new Linker<One>() {
                    @Override
                    public int index(int position, @NonNull One one) {
                        if (one.isMenu()) {
                            return 0;
                        } else {
                            return 1;
                        }
                    }
                });
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
                // 一对多
                boolean isMenu = false;
                for (int i = 0; i < 20; i++) {
                    One one = new One();
                    one.setMenu(!isMenu);
                    isMenu = !isMenu;
                    list.add(one);
                }
                adapter.setItems(list);
                adapter.notifyDataSetChanged();
            }
        }, 3000);
    }
}
