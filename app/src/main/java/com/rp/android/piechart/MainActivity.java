package com.rp.android.piechart;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private static String TAG = "MainActivity";

    private float[] yData = {25.3f,10.6f,66.76f,44.32f,46.01f,16.89f,23.9f};
    private String[] xData ={"Mitch", "Jessica", "Mohammad","Kelsey","Sam","Robert","Ashley"};

    PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG,"onCreate: starting to create chart");

        pieChart = (PieChart) findViewById(R.id.piechart);
        String text = "Sales by employees in Rs.";
        Description desc = new Description();
        desc.setText(text);
        pieChart.setDescription(desc);

        pieChart.setRotationEnabled(true);
        pieChart.setTransparentCircleAlpha(0);
        pieChart.setCenterText("I am cool");
        pieChart.setCenterTextSize(10);
        pieChart.setHoleRadius(25f);

        pieChart.setOnChartValueSelectedListener(new OnChartValueSelectedListener() {
            @Override
            public void onValueSelected(Entry e, Highlight h) {
                Log.d(TAG,"onValueSelected: Value select from chart ");
                Log.d(TAG,"onValueSelected: "+e.toString());
                Log.d(TAG,"onValueSelected: "+h.toString());

                float x = h.getX();
                float y = h.getY();

                Toast.makeText(MainActivity.this,"Employee "+xData[(int)x]+"\n Sales : "+ y,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected() {

            }
        });

        addDataSet();

    }

    private void addDataSet() {
        Log.d(TAG,"addDataSet started");
        List<PieEntry> yEntries = new ArrayList<>();
        List<String> xEntries = new ArrayList<>();
        for(int i=0;i<yData.length;i++){
            yEntries.add(new PieEntry(yData[i],i));
        }

        xEntries.addAll(Arrays.asList(xData));

        PieDataSet pieDataSet = new PieDataSet(yEntries,"Employee sales");
        pieDataSet.setSliceSpace(2);
        pieDataSet.setValueTextSize(12 );

        List<Integer> colors = new ArrayList<>();
        colors.add(Color.GRAY);
        colors.add(Color.BLUE);
        colors.add(Color.RED);
        colors.add(Color.GREEN);
        colors.add(Color.CYAN);
        colors.add(Color.YELLOW);
        colors.add(Color.MAGENTA);

        pieDataSet.setColors(colors);

        Legend legend = pieChart.getLegend();
        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setPosition(Legend.LegendPosition.LEFT_OF_CHART);


        PieData pieData = new PieData(pieDataSet);
        pieChart.setData(pieData);
        pieChart.invalidate();

    }
}
