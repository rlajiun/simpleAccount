package com.unvin.simpleaccount;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.unvin.simpleaccount.Activity.ChartActivity;
import com.unvin.simpleaccount.Activity.InvoiceActivity;
import com.unvin.simpleaccount.adapter.CheckListAdapter;
import com.unvin.simpleaccount.adapter.GridAdapter;
import com.unvin.simpleaccount.database.DBHelper;
import com.unvin.simpleaccount.models.checkList;
import com.unvin.simpleaccount.models.day;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends Activity {

    ImageButton stat;
    ImageButton invoice;

    static ArrayList<checkList> moneyDataList = new ArrayList<checkList>();

    private ListView listView;
    private  CheckListAdapter myAdapter;
    /**
     * 연/월 텍스트뷰
     */
    static public TextView tvDate;
    /**
     * 그리드뷰 어댑터
     */
    private GridAdapter gridAdapter;

    /**
     * 일 저장 할 리스트
     */
    private ArrayList<day> dayList;

    /**
     * 그리드뷰
     */
    private GridView gridView;

    /**
     * 캘린더 변수
     */
    static public Calendar mCal;

    @Override
    protected void onResume() {
        super.onResume();
        moneyDataList.clear();
        InitializeCheckData();
        Toast.makeText(this, "onResume", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        tvDate = (TextView)findViewById(R.id.tv_date);
        gridView = (GridView)findViewById(R.id.gridview);
        listView = (ListView)findViewById(R.id.checkList);

        stat = (ImageButton)findViewById(R.id.stat);
        invoice = (ImageButton)findViewById(R.id.invoice);

        stat.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), ChartActivity.class);
                startActivity(intent);
            }
        });
        invoice.setOnClickListener(new View.OnClickListener(){
            public void onClick(View v){
                Intent intent = new Intent(getApplicationContext(), InvoiceActivity.class);
                startActivity(intent);
            }
        });

        InitializeCheckData();

        myAdapter = new CheckListAdapter(this, moneyDataList);

        listView.setAdapter(myAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id){
                Toast.makeText(getApplicationContext(),
                        myAdapter.getItem(position).getCost(),
                        Toast.LENGTH_LONG).show();
            }
        });

        // 오늘에 날짜를 세팅 해준다.
        long now = System.currentTimeMillis();
        final Date date = new Date(now);
        //연,월,일을 따로 저장
        final SimpleDateFormat curYearFormat = new SimpleDateFormat("yyyy", Locale.KOREA);
        final SimpleDateFormat curMonthFormat = new SimpleDateFormat("MM", Locale.KOREA);
        final SimpleDateFormat curDayFormat = new SimpleDateFormat("dd", Locale.KOREA);

        //현재 날짜 텍스트뷰에 뿌려줌
        tvDate.setText(curYearFormat.format(date) + "/" + curMonthFormat.format(date));

        //gridview 요일 표시
        dayList = new ArrayList<day>();

        mCal = Calendar.getInstance();

        //이번달 1일 무슨요일인지 판단 mCal.set(Year,Month,Day)
        mCal.set(Integer.parseInt(curYearFormat.format(date)), Integer.parseInt(curMonthFormat.format(date)) - 1, 1);
        int dayNum = mCal.get(Calendar.DAY_OF_WEEK);
        //1일 - 요일 매칭 시키기 위해 공백 add
        for (int i = 1; i < dayNum; i++) {
            dayList.add(new day("","","", ""));
        }
        setCalendarDate(mCal.get(Calendar.MONTH) + 1);

        gridAdapter = new GridAdapter(getApplicationContext(), dayList);
        gridView.setAdapter(gridAdapter);

    }

    public void InitializeCheckData() {
        DBHelper dbHelper = new DBHelper(this, "unvinDB", null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(dbHelper.selectShareSQL, null);

        while(cursor.moveToNext()){
            String drawableString = "getmoney";
            String whatString ="받기";
            if(cursor.getString(1).equals("1")){
                drawableString="sendmoney";
                whatString="보내기";
            }
            String datetimeStrimg = cursor.getString(5).substring(6);
            moneyDataList.add((new checkList(getResources().getIdentifier("@drawable/"+drawableString, "drawable", this.getPackageName())
                    , cursor.getString(3)+"한테",

                    cursor.getString(2)+"원", whatString, datetimeStrimg+"까지")));
        }

//        moneyDataList.add(new checkList(R.drawable.sendmoney, "막내동생한테","10,000원","보내기","17일까지"));
//        moneyDataList.add(new checkList(R.drawable.getmoney, "친구한테","30,000원","받기", "18일까지"));
//        moneyDataList.add(new checkList(R.drawable.getmoney, "펭수한테","20,000원","받기", "17일까지"));
    }

    /**
     * 해당 월에 표시할 일 수 구함
     *
     * @param month
     */
    private void setCalendarDate(int month) {
        mCal.set(Calendar.MONTH, month - 1);

        for (int i = 0; i < mCal.getActualMaximum(Calendar.DAY_OF_MONTH); i++) {
            dayList.add(new day("" + (i + 1), "", "", ""));
        }

    }

}