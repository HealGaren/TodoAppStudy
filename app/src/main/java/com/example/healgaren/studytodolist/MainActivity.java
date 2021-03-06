package com.example.healgaren.studytodolist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    RecyclerView memoRecycler;
    MemoAdapter memoAdapter;

    Button backupBtn, restoreBtn;

    SharedPreferences pref;

    String token;

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

        pref = PreferenceManager.getDefaultSharedPreferences(this);

        backupBtn = findViewById(R.id.btn_backup);
        restoreBtn = findViewById(R.id.btn_restore);


        token = pref.getString("token", null);
        if (token == null) {
            NetworkHelper.getInstance().memoService.createToken().enqueue(new Callback<TokenResult>() {
                @Override
                public void onResponse(Call<TokenResult> call, Response<TokenResult> response) {
                    TokenResult result = response.body();
                    token = result.getToken();
                }

                @Override
                public void onFailure(Call<TokenResult> call, Throwable t) {

                }
            });
        }


        backupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                BackupRequest request = new BackupRequest(token, loadMemoList());
//                NetworkHelper.getInstance().memoService.backup(request);
                NetworkHelper.getInstance().memoService.backup(new BackupRequest(token, loadMemoList())).enqueue(new Callback<BaseResult>() {
                    @Override
                    public void onResponse(Call<BaseResult> call, Response<BaseResult> response) {
                        Toast.makeText(MainActivity.this, response.body().getMessage(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onFailure(Call<BaseResult> call, Throwable t) {

                    }
                });

            }
        });


        memoRecycler = findViewById(R.id.recycler_memo);
        memoAdapter = new MemoAdapter();
        memoRecycler.setAdapter(memoAdapter);

        memoRecycler.setLayoutManager(new LinearLayoutManager(this));

        memoAdapter.setOnItemClickListener(new MemoAdapter.OnItemClickListener() {
            @Override
            public void onClick(int position) {
                Intent intent = new Intent(MainActivity.this, DetailMemoActivity.class);
                intent.putExtra("position", position);
                startActivity(intent);
            }

            @Override
            public void onClickRemoveButton(int position) {
                int currentSize = pref.getInt("memoSize", 0);;
                pref.edit().putInt("memoSize", currentSize - 1).apply();
                memoAdapter.remove(position);
            }
        });

        reloadMemoList();
    }

    private List<Memo> loadMemoList() {
        List<Memo> memoList = new ArrayList<>();
        int size = pref.getInt("memoSize", 0);
        for (int i=0; i<size; i++) {
            String title = pref.getString("title" + i, "");
            String content = pref.getString("content" + i, "");
            String detailContent = pref.getString("detailContent" + i, "");
            Memo memo = new Memo(title, content, detailContent);
            memoList.add(memo);
        }
        return memoList;
    }

    private void reloadMemoList() {

        memoAdapter.clear();
        memoAdapter.addAll(loadMemoList());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1001) {

            if (resultCode == RESULT_OK) {
                reloadMemoList();
            }
            else if (resultCode == RESULT_CANCELED) {

            }

        }
    }
}
