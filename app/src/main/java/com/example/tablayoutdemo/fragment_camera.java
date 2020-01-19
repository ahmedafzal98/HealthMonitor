package com.example.tablayoutdemo;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class fragment_camera extends Fragment {
    public  ArrayList<Integer> arrayId = new ArrayList<Integer>();
    public  ArrayList<String> arrayList = new ArrayList<String>();
    public  ArrayList<String> arrayInfo = new ArrayList<String>();

    public View view;
    sqliteDatabase myDb;


    ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragement_camera , container,false);
        listView = view.findViewById(R.id.list_view);
        myDb = new sqliteDatabase(getActivity());
        myDb.clearData();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getActivity(),Info_Activity.class);
                intent.putExtra("info",arrayInfo.get(i));
                startActivity(intent);
//                Toast.makeText(getActivity(), "clicked item is " +arrayId.get(i) + arrayInfo.get(i), Toast.LENGTH_SHORT).show();

            }
        });

        insertData();
      //  showData();


        getDataFromCursor();
//        getInfoFromCursor();
        return view;
    }
    public void getDataFromCursor(){
        arrayList = new ArrayList<String>();
        arrayInfo = new ArrayList<>();
        arrayId = new ArrayList<Integer>();

        Cursor data = myDb. getListName();

        data.moveToFirst();
        while (!data.isAfterLast()){
            arrayList.add(data.getString(data.getColumnIndex(sqliteDatabase.COL_HEALTH_NAME)));
            arrayInfo.add(data.getString(data.getColumnIndex(sqliteDatabase.COL_INFO)));
            arrayId.add(data.getInt(data.getColumnIndex(sqliteDatabase.COL_ID)));
            data.moveToNext();
        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1,arrayList);
        listView.setAdapter(arrayAdapter);
    }

    public void insertData(){
//        myDb.insertList("Health");
//        myDb.insertList("Blood Pressure");
//        myDb.insertList("Diet");
//        myDb.insertList("Desase");
//        myDb.insertList("Sugar");
         myDb.insertList("Health" , "Health information is the data related to a person's medical history, including symptoms, diagnoses, procedures, and outcomes. Health information records include patient histories, lab results, x-rays, clinical information, and notes. ... It is a combination of business, science, and information technology.");
        myDb.insertList("Desease" , "A disease is a particular abnormal condition that negatively affects the structure or function of part or all of an organism, and that is not due to any external injury. ... A disease may be caused by external factors such as pathogens or by internal dysfunctions.");
        myDb.insertList("Diabities" , "Diabetes is a disease that occurs when your blood glucose, also called blood sugar, is too high. ... Insulin, a hormone made by the pancreas, helps glucose from food get into your cells to be used for energy. Sometimes your body doesn't make enough—or any—insulin or doesn't use insulin well.");
//        myDb.insertList("Diabites" , "It is a long established fact that a reader will be distracted by the readable content of a page when looking at its layout. The point of using Lorem Ipsum is that it has a more-or-less normal distribution of letters, as opposed to using 'Content here, content here', making it look like readable English. Many desktop publishing packages and web page editors now use Lorem Ipsum as their default model text, and a search for 'lorem ipsum' will uncover many web sites still in their infancy. Various versions have evolved over the years, sometimes by accident, sometimes on purpose (injected humour and the like");

        myDb.insertList("Sugar" , "The blood sugar level, blood sugar concentration, or blood glucose level is the concentration of glucose present in the blood of humans and other animals. Glucose is a simple sugar and approximately 4 grams of glucose are present in the blood of a 70-kilogram (150 lb) human at all times.[2] The body tightly regulates blood glucose levels as a part of metabolic homeostasis.[2] Glucose is stored in skeletal muscle and liver cells in the form of glycogen.");
        myDb.insertList("Diet" , "A healthy diet may contain fruits, vegetables, and whole grains, and includes little to no processed food and sweetened beverages. The requirements for a healthy diet can be met from a variety of plant-based and animal-based foods, although a non-animal source of vitamin B12 is needed for those following a vegan diet.[3] Various nutrition guides are published by medical and governmental institutions to educate individuals on what they should be eating to be healthy.");
    }

}
