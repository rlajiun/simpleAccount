package com.unvin.simpleaccount.Activity;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.unvin.simpleaccount.R;
import com.unvin.simpleaccount.adapter.ListAdapter;
import com.unvin.simpleaccount.database.DBHelper;
import com.unvin.simpleaccount.models.checkList;
import com.unvin.simpleaccount.models.gagebu;

import java.util.ArrayList;

public class InvoiceActivity extends Activity implements View.OnClickListener {

    ListView listView;
    @Override
    protected void onResume() {
        super.onResume();
        gagebuDataList.clear();
        InitializeGagebuData();
        final ListAdapter myAdapter = new ListAdapter(this, gagebuDataList);

        listView.setAdapter(myAdapter);
    }

    ImageButton back;

    ArrayList<gagebu> gagebuDataList;

    private Animation fab_open, fab_close;
    private Boolean isFabOpen = false;
    private FloatingActionButton fab, fab1, fab2, fab3;

    String[] spinnerNames;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_invoice);

        spinnerNames = getResources().getStringArray(R.array.category_consume);
        back = (ImageButton)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        fab_open = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_open);
        fab_close = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fab_close);

        fab = (FloatingActionButton) findViewById(R.id.fab_main);
        fab1 = (FloatingActionButton) findViewById(R.id.fab_sub1);
        fab2 = (FloatingActionButton) findViewById(R.id.fab_sub2);
        fab3 = (FloatingActionButton) findViewById(R.id.fab_sub3);

        fab.setOnClickListener(this);
        fab1.setOnClickListener(this);
        fab2.setOnClickListener(this);
        fab3.setOnClickListener(this);

        InitializeGagebuData();

        listView = (ListView)findViewById(R.id.invoiceList);
        final ListAdapter myAdapter = new ListAdapter(this, gagebuDataList);

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

    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.fab_main:
                anim();
                break;
            case R.id.fab_sub1:
                anim();
                Intent intent = new Intent(getApplicationContext(), AddActivity.class);
                startActivity(intent);
                break;
            case R.id.fab_sub2:
                anim();
                intent = new Intent(getApplicationContext(), SubActivity.class);
                startActivity(intent);
                break;
            case R.id.fab_sub3:
                anim();
                intent = new Intent(getApplicationContext(), ShareActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void anim() {

        if (isFabOpen) {
            fab1.startAnimation(fab_close);
            fab2.startAnimation(fab_close);
            fab3.startAnimation(fab_close);
            fab1.setClickable(false);
            fab2.setClickable(false);
            fab3.setClickable(false);
            isFabOpen = false;
        } else {
            fab1.startAnimation(fab_open);
            fab2.startAnimation(fab_open);
            fab3.startAnimation(fab_open);
            fab1.setClickable(true);
            fab2.setClickable(true);
            fab3.setClickable(true);
            isFabOpen = true;
        }
    }

    private void InitializeGagebuData() {
        gagebuDataList = new ArrayList<gagebu>();

        gagebuDataList.add(new gagebu(R.drawable.cutlery, "식사","10,000원","17일 - 4:15"));
        gagebuDataList.add(new gagebu(R.drawable.automobile, "교통","30,000원","17일 - 4:15"));
        gagebuDataList.add(new gagebu(R.drawable.more, "기타","20,000원","17일 - 4:15"));

        DBHelper dbHelper = new DBHelper(this, "unvinDB", null, 1);
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(dbHelper.selectAccountSQL, null);

        while(cursor.moveToNext()){
            String drawablePositionString = cursor.getString(4);
            String flagString = "-";
            if(cursor.getString(0).equals("0")){
                flagString="+";
            }
            gagebuDataList.add((new gagebu(AddActivity.spinnerImages[Integer.parseInt(drawablePositionString)],
                    spinnerNames[Integer.parseInt(drawablePositionString)], flagString+cursor.getString(1)+"원", cursor.getString(3))));

            Log.d("position", cursor.getString(1));
        }
        db.close();
    }
}
