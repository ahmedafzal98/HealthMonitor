package com.example.tablayoutdemo;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class fragment_status extends Fragment {

    public static ArrayList<Entry> entryArrayBp = new ArrayList<Entry>();
    public static ArrayList<Entry> entryArrayBs = new ArrayList<Entry>();
    public static ArrayList<Entry> entryArrayCi = new ArrayList<Entry>();

    public View view;
    LineChart lineChart;
    TextView textViewBp,textViewBs,textViewCi,textViewCheBp,textViewCheBs,textViewCheci,hbptextViewclickHere,lbptextViewclickHere,hbstextViewclickHere,lbstextViewclickHere,hcitextViewclickHere,lcitextViewclickHere;
    sqliteDatabase myDb;
    WebView webView;

    ArrayList<ILineDataSet> iLineDataSets = new ArrayList<>();
    LineData lineData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragement_status,null);

        lineChart = view.findViewById(R.id.line_chart);
        myDb = new sqliteDatabase(getActivity());
        textViewBp = view.findViewById(R.id.bp_txt);
        textViewBs = view.findViewById(R.id.bs_txt);
        textViewCi = view.findViewById(R.id.ci_txt);
        textViewCheBp = view.findViewById(R.id.check_Bp);
        textViewCheBs = view.findViewById(R.id.check_Bs);
        textViewCheci = view.findViewById(R.id.check_ci);
        hbptextViewclickHere = view.findViewById(R.id.txt_clickherehbp);
        lbptextViewclickHere = view.findViewById(R.id.txt_clickherelbp);
        hbstextViewclickHere = view.findViewById(R.id.txt_clickherehbs);
        lbstextViewclickHere = view.findViewById(R.id.txt_clickherelbs);
        hcitextViewclickHere = view.findViewById(R.id.txt_clickherehci);
        lcitextViewclickHere = view.findViewById(R.id.txt_clickherelci);

        getGraphData();

        LineDataSet lineDataSet = new LineDataSet(entryArrayBp , "Blood Pressure");
        lineDataSet.setLabel("Blood Pressure");
        lineDataSet.setColor(Color.RED);
        lineDataSet.setLineWidth(4);

        LineDataSet lineDataSet2 = new LineDataSet(entryArrayBs , "Blood Sugar");
        lineDataSet2.setLabel("Blood Sugar");
        lineDataSet2.setColor(Color.CYAN);
        lineDataSet2.setLineWidth(4);

        LineDataSet lineDataSet3 = new LineDataSet(entryArrayCi , "Calorine Intake");
        lineDataSet3.setLabel("Calorie Intake");
        lineDataSet3.setColor(Color.GREEN);
        lineDataSet3.setLineWidth(4);


        iLineDataSets.clear();
        iLineDataSets.add(lineDataSet);
        iLineDataSets.add(lineDataSet2);
        iLineDataSets.add(lineDataSet3);
        lineData = new LineData(iLineDataSets);
        lineChart.clear();
        lineChart.setData(lineData);
        lineChart.invalidate();

        return view;
    }

        public void getGraphData(){
            entryArrayBp = new ArrayList<>();
            entryArrayBs = new ArrayList<>();
            entryArrayCi = new ArrayList<>();
            String[] columns  = {"BP"};
            Cursor data = myDb.getGraphData();
            data.moveToFirst();
            int Bp = 0;
            int avgBp = 0;
            int sugar = 0;
            int avgSugar = 0;
            int calories = 0;
            int avgCalories = 0;


            while (!data.isAfterLast()) {
                entryArrayBp.add(new Entry(data.getInt(0), data.getInt(1), "testt"));
                entryArrayBs.add(new Entry(data.getInt(0), data.getInt(2), "testt"));
                entryArrayCi.add(new Entry(data.getInt(0), data.getInt(3), "testt"));
                Bp = Bp + data.getInt(1);
                sugar = sugar + data.getInt(2);
                calories = calories + data.getInt(3);
                data.moveToNext();
            }

            if(sugar > 0){
                avgSugar = sugar / entryArrayBp.size();
                avgBp = Bp / entryArrayBs.size();
                avgCalories = calories / entryArrayCi.size();


                textViewBp.setText("Your Blood Pressure is " + avgBp);
                textViewBs.setText("Your Blood Sugar is " + avgSugar);
                textViewCi.setText("Your Calories Intake is "+avgCalories);
            }


            String bpText = "";
            String sugarText = "";
            String caloriesText = "";

            String bpUrl = "";
            String bsUrl = "";
            String caUrl = "";


            if(avgBp > 120){
                bpText = "Your Blood Pressure is high Average blood pressure should be less than 120.If you want to control your BP";
                bpUrl = "https://www.mayoclinic.org/diseases-conditions/high-blood-pressure/in-depth/high-blood-pressure/art-20046974";
                hbptextViewclickHere.setText("Click Here");
            }else if (avgBp == 0){
                bpText = "Enter BP in health tab to show the result";
                hbptextViewclickHere.setText("");
            }
            else if(avgBp < 90){
                bpText = "Your Blood Pressure is low Average blood pressure should be greater than 90.If you want to control your BP";
                bpUrl = "https://www.medicalnewstoday.com/articles/319506.php#natural-ways-to-raise-blood-pressure";
                hbptextViewclickHere.setText("Click Here");
            }else if (avgBp < 120 || avgBp > 90 || avgBp == 120 || avgBp == 90){
                bpText = "Your Blood Pressure is Normal";
                hbptextViewclickHere.setText("");
            }

            final String BpfinalUrl = bpUrl;

            textViewCheBp.setText(bpText);

            hbptextViewclickHere.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), web_view.class);
                    intent.putExtra("web_url", BpfinalUrl);
                    startActivity(intent);
                }
            });

            if(avgSugar > 120){
                sugarText = "Your Blood Sugar is high Average blood pressure should be less than 120.If you want to control your BP";
                bsUrl = "https://www.healthline.com/nutrition/15-ways-to-lower-blood-sugar";
                hbstextViewclickHere.setText("Click Here");
            }else if(avgSugar == 0){
                sugarText = "Enter BS in health tab to show the result";
                hbstextViewclickHere.setText("");
            }else if(avgSugar < 90) {
                sugarText = "Your Blood Sugar is low Average blood pressure should be greater than 90.If you want to control your BP";
                bsUrl = "https://www.medicalnewstoday.com/articles/319506.php#natural-ways-to-raise-blood-pressure";
                hbstextViewclickHere.setText("Click Here");
            }else if (avgSugar < 120 || avgSugar > 90 || avgSugar == 120 || avgSugar == 90){
                sugarText = "Your Blood Sugar Is Normal";
                hbstextViewclickHere.setText("");
            }


            final String BsfinalUrl = bsUrl;

            textViewCheBs.setText(sugarText);

            hbstextViewclickHere.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), web_view.class);
                    intent.putExtra("web_url", BsfinalUrl);
                    startActivity(intent);
                }
            });

            if (avgCalories > 120){
                caloriesText = "Your Calories is high Average blood pressure should be less than 120.If you want to control your BP";
                caUrl = "https://www.wcrf-uk.org/uk/preventing-cancer/cancer-prevention-recommendations/avoid-high-calorie-foods";
                hcitextViewclickHere.setText("Click Here");


            }else if (avgCalories == 0){
                caloriesText = "Enter Calorires in health tab to show the result";
                hcitextViewclickHere.setText("");

            }else if (avgCalories < 90){
                caloriesText = "Your Calorires is low Average blood pressure should be greater than 90.If you want to control your BP";
                bsUrl = "https://www.healthline.com/nutrition/35-ways-to-cut-calories";
                hcitextViewclickHere.setText("Click Here");

            }else if (avgCalories < 120 || avgCalories > 90 || avgCalories == 120 || avgCalories == 90){
                caloriesText = "Your Calorires Is Normal";
                hcitextViewclickHere.setText("");
            }


            final String CifinalUrl = caUrl;

            textViewCheci.setText(caloriesText);

            hcitextViewclickHere.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), web_view.class);
                    intent.putExtra("web_url", CifinalUrl);
                    startActivity(intent);
                }
            });

        }

}
