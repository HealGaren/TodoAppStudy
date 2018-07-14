package com.example.healgaren.studytodolist;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailMemoActivity extends AppCompatActivity {

    TextView titleText;
    TextView contentText;
    TextView detailContentText;

    Button confirmBtn;

    SharedPreferences pref;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_memo);

        pref = PreferenceManager.getDefaultSharedPreferences(this);

        titleText = findViewById(R.id.text_title);
        contentText = findViewById(R.id.text_content);
        detailContentText = findViewById(R.id.text_detail_content);

        confirmBtn = findViewById(R.id.btn_confirm);


        int position = getIntent().getIntExtra("position", 0);

        String title = pref.getString("title" + position, "");
        String content = pref.getString("content" + position, "");
        String detailContent = pref.getString("detailContent" + position, "");

        titleText.setText(title);
        contentText.setText(content);
        detailContentText.setText(detailContent);

        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
