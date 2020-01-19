package com.example.tablayoutdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Info_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_);
        String info = getIntent().getStringExtra("info");
        TextView textView;
        Button button;
        EditText editText;

        textView = (TextView) findViewById(R.id.txt_info);
        textView.setText(info);
    }
}
