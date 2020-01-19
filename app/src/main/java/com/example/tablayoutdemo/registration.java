package com.example.tablayoutdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class registration extends AppCompatActivity {
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";
    public static String var;
    sqliteDatabase myDb;
//    TextView textView;
    EditText editText_name,editText_age,editText_height,editText_weight;

    public static String name;
    public static String age;
    public static String height;
    public static String weight;
    public static Boolean isInserted;
    public static String Editname;
    public static String Editage;
    public static String Editheight;
    public static String Editweight;


    Button button_register;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        myDb = new sqliteDatabase(this);
//        textView = (TextView) findViewById(R.id.text_view);
        editText_name = (EditText) findViewById(R.id.edit_name);
        editText_age = (EditText) findViewById(R.id.edit_age);
        editText_height = (EditText) findViewById(R.id.edit_height);
        editText_weight = (EditText) findViewById(R.id.edit_weight);
        button_register = (Button) findViewById(R.id.btn_register);


//          getDatafromcursor();
//          setText();
    //        updateData();

        insertData();

        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS , MODE_PRIVATE);
        String value = prefs.getString("key","");
        if(!value.equals("")){
            Intent intent = new Intent(registration.this, Main3Activity.class);
            startActivity(intent);
        }

    }


    public void insertData() {

        button_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                name = editText_name.getText().toString();
                age = editText_age.getText().toString();
                height = editText_height.getText().toString();
                weight = editText_weight.getText().toString();

                boolean isVAlidated = true;
                    if (name.length()==0) {
                        editText_name.requestFocus();
                        editText_name.setError("Enter your Name");
                        isVAlidated=false;
                    }else if(!name.matches("[a-zA-Z ]+")){
                        editText_name.requestFocus();
                        editText_name.setError("Use Alphabetic Character Only");
                        isVAlidated=false;


                    }if (age.isEmpty()){
                        editText_age.requestFocus();
                        editText_age.setError("Enter Your Age");
                        isVAlidated=false;
                    }else if(age.matches("[a-zA-Z ]+")){
                             editText_age.requestFocus();
                             editText_age.setError("Use Numeric Character Only");
                             isVAlidated=false;

                }
                    if (height.isEmpty()){
                        editText_height.requestFocus();
                        editText_height.setError("Enter your Height");
                        isVAlidated=false;
                    }else if(height.matches("[a-zA-Z ]+")){
                        editText_height.requestFocus();
                        editText_height.setError("Use Numeric Character Only");
                        isVAlidated=false;

                    }
                    if (weight.isEmpty()){
                        editText_weight.requestFocus();
                        editText_weight.setError("Enter your Weight");
                        isVAlidated=false;
                    }else if (weight.matches("[a-zA-Z ]+")){
                        editText_weight.requestFocus();
                        editText_weight.setError("Use Numeric Character Only");
                        isVAlidated=false;

                    }

                    if(isVAlidated) {
                        Boolean isInserted = myDb.insertData(editText_name.getText().toString(),
                                Integer.parseInt((editText_age.getText().toString())),
                                editText_height.getText().toString(),
                                editText_weight.getText().toString());

                        if (isInserted == true) {
                            getData();
                            insertValueInShafredPrefs();
                            Toast.makeText(getApplicationContext(), "you Are Successfully Registered", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(registration.this, Main3Activity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(getApplicationContext(), "Something Went Wrong...", Toast.LENGTH_SHORT).show();
                        }
                    }
            }
        });
    }


    public void getData(){
        Cursor c = myDb.getAllDAta();
        c.moveToFirst();
        ArrayList<String> aaa = new ArrayList<>();
        while(!c.isAfterLast()) {
            aaa.add(c.getString(c.getColumnIndex(sqliteDatabase.COL_1)));
           Toast.makeText(this, c.getString(c.getColumnIndex(sqliteDatabase.COL_1)), Toast.LENGTH_SHORT).show();
           var = c.getString(c.getColumnIndex(sqliteDatabase.COL_1));
            c.moveToNext();
        }

    }

    public void insertValueInShafredPrefs(){

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS , MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("key" , var);
        editor.commit();

    }

//    public void updateData(){
//        myDb.updateData("2" , editText_name.getText().toString(), editText_age.getText().toString(), editText_height.getText().toString(),
//                editText_weight.getText().toString());
//    }

}
