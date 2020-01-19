package com.example.tablayoutdemo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

import static android.content.Context.MODE_PRIVATE;

public class fragment_personal_profile extends Fragment {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static String name;
    public static String age;
    public static String height;
    public static String weight;

    public static ArrayList arrayname = new ArrayList();
    public static ArrayList arrayage = new ArrayList();
    public static ArrayList arrayheight = new ArrayList();
    public static ArrayList arrayweight = new ArrayList();
    public String value;
    Button btn_name, btn_age, btn_height, btn_weight , btn_Logout;
    EditText edit_name,edit_age,edit_height,edit_weight;
    sqliteDatabase myDb;
    registration reg;

    public View view;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.activity_fragment_personal_profile , null , false);
        btn_name = view.findViewById(R.id.btn_name);
        btn_age = view. findViewById(R.id.btn_age);
        btn_height = view. findViewById(R.id.btn_height);
        btn_weight = view. findViewById(R.id.btn_weight);
        btn_Logout = view.findViewById(R.id.btn_logout);
        edit_name = view. findViewById(R.id.edit_name);
        edit_age = view. findViewById(R.id.edit_age);
        edit_height= view. findViewById(R.id.edit_height);
        edit_weight = view.findViewById(R.id.edit_weight);
        myDb = new sqliteDatabase(getActivity());

        getDatafromcursor();
        setText();

//        Toast.makeText(getActivity(), "" + age, Toast.LENGTH_SHORT).show();


        //updateData();

        btn_Logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences pref =  getActivity().getSharedPreferences(SHARED_PREFS , MODE_PRIVATE);
                pref.edit().clear().commit();
                myDb.deleteData();
                Intent intent = new Intent(getActivity() , registration.class);
                startActivity(intent);
            }
        });
        btn_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edit_name.isEnabled() == true){
                    updateData();
                    edit_name.setEnabled(false);
                    btn_name.setText("Edit");
                }else {
                    edit_name.setEnabled(true);
                    btn_name.setText("Save");
                }
               }
        });

        btn_age.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edit_age.isEnabled() == true){
                    updateData();
                    edit_age.setEnabled(false);
                    btn_age.setText("Edit");
                }else {
                    edit_age.setEnabled(true);
                    btn_age.setText("Save");
                }
            }
        });

        btn_height.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edit_height.isEnabled() == true){
                    updateData();
                    edit_height.setEnabled(false);
                    btn_height.setText("Edit");
                }else {
                    edit_height.setEnabled(true);
                    btn_height.setText("Save");
                }
            }
        });

        btn_weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (edit_weight.isEnabled() == true){
                    updateData();
                    edit_weight.setEnabled(false);
                    btn_weight.setText("Edit");
                }else {
                    edit_weight.setEnabled(true);
                    btn_weight.setText("Save");
                }

            }
        });

        return view;
    }

    public void getDatafromcursor(){

        SharedPreferences sharedPreferences = getActivity().getSharedPreferences(SHARED_PREFS , MODE_PRIVATE);
        value = sharedPreferences.getString("key" , "");

        Cursor data = myDb.getRow(value);

            data.moveToFirst();
             name = data.getString(data.getColumnIndex(sqliteDatabase.COL_2));
             age = data.getString(data.getColumnIndex(sqliteDatabase.COL_3));
             height = data.getString(data.getColumnIndex(sqliteDatabase.COL_4));
             weight = data.getString(data.getColumnIndex(sqliteDatabase.COL_5));
            data.close();

       // Toast.makeText(getActivity(), "" +value, Toast.LENGTH_SHORT).show();

    }

    public void setText(){
        edit_name.setText(name);
        edit_age.setText(age);
        edit_height.setText(height);
        edit_weight.setText(weight);

    }
    public void updateData(){
      boolean isUpdate =  myDb.updateData(value, edit_name.getText().toString(),edit_age.getText().toString() , edit_height.getText().toString(),edit_weight.getText().toString());
      if (isUpdate == true){
          Toast.makeText(getActivity(), "Data is Updated..", Toast.LENGTH_SHORT).show();
      }else {
          Toast.makeText(getActivity(), "Data is not Updated..", Toast.LENGTH_SHORT).show();
      }

    }
//    public void getDatafromCursor(){
//        arrayname = new ArrayList<String>();
//        arrayage = new ArrayList();
//        arrayheight = new ArrayList();
//        arrayweight = new ArrayList();
//        Cursor data = myDb.getAllDAta();
//
//        data.moveToFirst();
//        while(!data.isAfterLast()) {
//            arrayname.add(data.getString(data.getColumnIndex(sqliteDatabase.COL_2))); //add the item
//            arrayage.add(data.getInt(data.getColumnIndex(sqliteDatabase.COL_3)));
//            arrayheight.add(data.getString(data.getColumnIndex(sqliteDatabase.COL_4)));
//            arrayweight.add(data.getString(data.getColumnIndex(sqliteDatabase.COL_5)));
//            data.moveToNext();
//        }
//
//    }
//        public void updateData(){
//        myDb.updateData("7" , edit_name.getText().toString(), edit_age.getText().toString(), edit_height.getText().toString(),
//                edit_weight.getText().toString());
//    }
}
