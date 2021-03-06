package com.example.healgaren.studytodolist;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddMemoActivity extends AppCompatActivity {

    private EditText titleEdit;
    private EditText contentEdit;
    private EditText detailContentEdit;

    private Button cancelBtn;
    private Button confirmBtn;

    private SharedPreferences pref;
    private int currentMemoPosition;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_memo);

        pref = PreferenceManager.getDefaultSharedPreferences(this);

        titleEdit = findViewById(R.id.edit_title);
        contentEdit = findViewById(R.id.edit_content);
        detailContentEdit = findViewById(R.id.edit_detail_content);

        cancelBtn = findViewById(R.id.btn_cancel);
        confirmBtn = findViewById(R.id.btn_confirm);


        currentMemoPosition = pref.getInt("memoSize", 0);


        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CANCELED);
                finish();
            }
        });

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_OK);
                pref
                        .edit()
                        .putString("title" + currentMemoPosition, titleEdit.getText().toString())
                        .putString("content" + currentMemoPosition, contentEdit.getText().toString())
                        .putString("detailContent" + currentMemoPosition, detailContentEdit.getText().toString())
                        .putInt("memoSize", currentMemoPosition + 1)
                        .apply();

                finish();
            }
        });
    }
}
