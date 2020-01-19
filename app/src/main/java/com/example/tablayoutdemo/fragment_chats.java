package com.example.tablayoutdemo;

import android.app.DatePickerDialog;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.data.Entry;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class fragment_chats extends Fragment implements DatePickerDialog.OnDateSetListener{

    public View view;

    public static String Bp;
    public static String Bs;
    public static String Ci;

    EditText edit_bp, edit_bs, edit_ci,edit_Date;
    Button button_submit,button_current_date;
    DatePickerDialog.OnDateSetListener setListener;
    sqliteDatabase myDb;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragement_chat, container, false);

        edit_bp = view.findViewById(R.id.edit_bp);
        edit_bs = view.findViewById(R.id.edit_bs);
        edit_ci = view.findViewById(R.id.edit_ci);
        edit_Date = view.findViewById(R.id.edit_date);
        button_submit = view.findViewById(R.id.btn_submit);
        button_current_date = view.findViewById(R.id.btn_current_date);
//        button_set_date = view.findViewById(R.id.btn_set_date);
        myDb = new sqliteDatabase(getActivity());

        Calendar calendar = Calendar.getInstance();
        String currenDate = DateFormat.getDateInstance(DateFormat.FULL).format(calendar.getTime());
        edit_Date.setText(currenDate);

        insertDetailValue();
        current_Date();
        return view;

    }

    Calendar calendar = Calendar.getInstance();
    final int year = calendar.get(Calendar.YEAR);
    final int month = calendar.get(Calendar.MONTH);
    final int DOM = calendar.get(Calendar.DAY_OF_MONTH);

    public void current_Date(){
        button_current_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity() , android.R.style.Theme_DeviceDefault_NoActionBar_TranslucentDecor, setListener,year,month,DOM);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            }
        });
        setListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int dayOfmonth) {
                month = month + 1;
                String date = DOM + "/" + month + "/" + year;
                edit_Date.setText(date);
            }
        };

        button_current_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        month = month + 1;
                        String date = DOM + "/" + month + "/" + year;
                        edit_Date.setText(date);
                    }
                },year,month,DOM);
                datePickerDialog.show();
            }
        });
    }

    public void insertDetailValue(){
        button_submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bp = edit_bp.getText().toString();
                Bs = edit_bs.getText().toString().trim();
                Ci = edit_ci.getText().toString().trim();

                boolean isValidated = true;
                if (Bp.isEmpty()){
                    edit_bp.setError("Enter Your Blood Pressure");
                    isValidated=false;
                }
                if (Bs.isEmpty()){
                    edit_bs.setError("Enter Your Blood Sugar");
                    isValidated=false;
                }
                if (Ci.isEmpty()){
                    edit_ci.setError("Enter Your Calorie Intake");
                    isValidated=false;
                }

                if (isValidated){

                    boolean isInserted = myDb.insertDetails(Integer.parseInt(edit_bp.getText().toString().trim()),
                            Integer.parseInt(edit_bs.getText().toString().trim()),
                            Integer.parseInt(edit_ci.getText().toString().trim()));
                    if (isInserted == true){
                        Toast.makeText(getActivity(), "Data is Inserted..", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(getActivity(), "Data is not Inserted...", Toast.LENGTH_SHORT).show();
                    }
                }

            }
        });
    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

    }
}

