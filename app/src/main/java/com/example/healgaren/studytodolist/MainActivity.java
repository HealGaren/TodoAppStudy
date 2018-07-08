package com.example.healgaren.studytodolist;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    RecyclerView memoRecycler;
    MemoAdapter memoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddMemoActivity.class);
                startActivityForResult(intent, 1001);
            }
        });

        memoRecycler = findViewById(R.id.recycler_memo);
        memoAdapter = new MemoAdapter();
        memoRecycler.setAdapter(memoAdapter);

        memoRecycler.setLayoutManager(new LinearLayoutManager(this));

        memoAdapter.addAll(Arrays.asList(
                new Memo("양치하기", "양치는 잘 하자"),
                new Memo("안드로이드 앱 개발", "스터디 하려면 해야지"),
                new Memo("롤 하기", "가렌 S랭크를 찍어볼까?"),
                new Memo("설거지 하기", "밥을 먹었으면 설거지를 해야지")
        ));


    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1001) {

            if (resultCode == RESULT_OK) {
                Snackbar.make(memoRecycler, "add ok!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
            else if (resultCode == RESULT_CANCELED) {
                Snackbar.make(memoRecycler, "remove ok!", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }

        }
    }
}
