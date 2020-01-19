package com.example.tablayoutdemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    sqliteDatabase myDb;
    ListView listView;
    TextView profile, Camera, Chat, Status;
    ViewPager viewPager;
    PagerViewAdapter pagerViewAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new sqliteDatabase(this);

        viewPager = (ViewPager) findViewById(R.id.fragment_container);

        pagerViewAdapter = new PagerViewAdapter(getSupportFragmentManager());


        viewPager.setAdapter(pagerViewAdapter);

    }
}
