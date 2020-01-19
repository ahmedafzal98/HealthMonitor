package com.example.tablayoutdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class splashScreen extends AppCompatActivity {

    ImageView imageView;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        imageView = (ImageView) findViewById(R.id.splash);
        textView = (TextView) findViewById(R.id.txt_view);
        textView.animate().scaleX(3).scaleY(3).setDuration(5000).start();
        imageView.animate().scaleX(3).scaleY(3).setDuration(5000).start();
        Thread thread = new Thread(){
            public void run(){
                try {
                    sleep(5000);
                }catch (InterruptedException e){
                    e.printStackTrace();
                }finally {
                    Intent intent = new Intent(splashScreen.this , registration.class);
                    startActivity(intent);
                }
            }
        };
        thread.start();
    }
}
