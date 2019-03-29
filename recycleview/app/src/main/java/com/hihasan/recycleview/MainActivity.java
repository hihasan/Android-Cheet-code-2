package com.hihasan.recycleview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.hihasan.recycleview.adapter.TestAdapter;

public class MainActivity extends AppCompatActivity {
    RecyclerView r1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        r1=(RecyclerView) findViewById (R.id.recycler_test_layout);
        r1.setLayoutManager(new LinearLayoutManager(this));
        String[]t={"C","C++","Java","Python","Kotlin","Php","Javascript","Jquery"};
        r1.setAdapter(new TestAdapter(t));
    }
}
