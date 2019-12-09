package com.unvin.simpleaccount.Activity;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.unvin.simpleaccount.R;
import com.unvin.simpleaccount.adapter.ChartListAdapter;
import com.unvin.simpleaccount.adapter.CheckListAdapter;
import com.unvin.simpleaccount.models.chartList;

import java.util.ArrayList;

public class ChartActivity extends Activity {
    PieChart pieChart;
    ImageButton back;

    ArrayList<chartList> movieDataList;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        pieChart = (PieChart)findViewById(R.id.pie_chart);
        back = (ImageButton)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,5,5);

        pieChart.setDragDecelerationFrictionCoef(0.95f);

        pieChart.setDrawHoleEnabled(false);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(61f);

        ArrayList<PieEntry> yValues = new ArrayList<PieEntry>();

        yValues.add(new PieEntry(40f,"식비"));
        yValues.add(new PieEntry(35f,"생활비"));
        yValues.add(new PieEntry(23f,"교통비"));
        yValues.add(new PieEntry(14f,"기타"));

        Description description = new Description();
        description.setText("지출"); //라벨
        description.setTextSize(15);
        pieChart.setDescription(description);

        pieChart.animateY(1000, Easing.EasingOption.EaseInOutCubic); //애니메이션

        PieDataSet dataSet = new PieDataSet(yValues,"카테고리");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.JOYFUL_COLORS);

        PieData data = new PieData((dataSet));
        data.setValueTextSize(10f);
        data.setValueTextColor(Color.YELLOW);

        pieChart.setData(data);

        this.InitializeMovieData();

        ListView listView = (ListView)findViewById(R.id.chart_dataList);
        final ChartListAdapter myAdapter = new ChartListAdapter(this,movieDataList);

        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id){
                Toast.makeText(getApplicationContext(),
                        myAdapter.getItem(position).getCost(),
                        Toast.LENGTH_LONG).show();
            }
        });
    }

    private void InitializeMovieData() {
        movieDataList = new ArrayList<chartList>();

        movieDataList.add(new chartList(R.drawable.cutlery, "식사","70%","333400원"));
        movieDataList.add(new chartList(R.drawable.automobile, "교통","30%","22200원"));
        movieDataList.add(new chartList(R.drawable.more, "기타","25%","20000원"));
    }
}