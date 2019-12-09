package com.unvin.simpleaccount.Activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.DatePicker;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.unvin.simpleaccount.R;
import com.unvin.simpleaccount.adapter.CategoryAdapter;

import java.util.Calendar;

public class AddActivity extends Activity {
    ImageButton back;
    TextView date, time;

    private Spinner spinner_cat;
    String[] spinnerNames;
    int[] spinnerImages;
    int selected_cat_idx = 0;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        init();

        back = (ImageButton)findViewById(R.id.back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        spinner_cat = (Spinner)findViewById(R.id.spin_category);


        // 스피너에 보여줄 문자열과 이미지 목록을 작성합니다.
//        spinnerNames = new String[]{"식사", "교통", "기타"};
//        spinnerNames = new String[]{};
        spinnerNames = getResources().getStringArray(R.array.category_consume);
        spinnerImages = new int[]{R.drawable.cutlery
                                ,R.drawable.ic_snack
                                ,R.drawable.ic_room
                                ,R.drawable.ic_heart
                                ,R.drawable.ic_health
                                ,R.drawable.ic_gift
                                ,R.drawable.automobile
                                ,R.drawable.ic_brush
                                ,R.drawable.ic_salon
                                ,R.drawable.ic_towel
                                ,R.drawable.ic_bone
                                ,R.drawable.ic_movie
                                ,R.drawable.ic_health
                                ,R.drawable.ic_tshirt
                                ,R.drawable.ic_money_pig
                                ,R.drawable.ic_phone
                                ,R.drawable.ic_coffee
                                ,R.drawable.ic_drinking
                                ,R.drawable.ic_home_appliance
                                ,R.drawable.ic_labtop
                                ,R.drawable.ic_users
                                ,R.drawable.more};

        // 어댑터와 스피너를 연결합니다.
        CategoryAdapter categoryAdapter = new CategoryAdapter(AddActivity.this, spinnerNames, spinnerImages);
        spinner_cat.setAdapter(categoryAdapter);




        // 스피너에서 아이템 선택시 호출하도록 합니다.

        spinner_cat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                selected_cat_idx = spinner_cat.getSelectedItemPosition();
                Toast.makeText(AddActivity.this, spinnerNames[selected_cat_idx], Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }


        });

    }

    private void init() {
        //Calendar를 이용하여 년, 월, 일, 시간, 분을 PICKER에 넣어준다.
        final Calendar cal = Calendar.getInstance();
        String datemsg = String.format("%d년 %d월 %d일", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1, cal.get(Calendar.DATE));
        String timemsg = String.format("%d시 %d분", cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE));

        //DATE PICKER DIALOG
        date = (TextView)findViewById(R.id.txt_date);
        date.setText(datemsg);
        date.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(AddActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                        String datemsg = String.format("%d년 %d월 %d일", year, month+1, day);
                        date.setText(datemsg);
                    }
                }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE));
//                dialog.getDatePicker().setMaxDate(new Date().getTime());    //입력한 날짜 이후로 클릭 안되게 옵션
                dialog.show();

            }
        });


        //TIME PICKER DIALOG
        time = (TextView)findViewById(R.id.txt_time);
        time.setText(timemsg);
        time.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                TimePickerDialog dialog = new TimePickerDialog(AddActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hour, int min) {
                        String timemsg = String.format("%d시 %d분", hour, min);
                        time.setText(timemsg);
                    }
                }, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true);  //마지막 boolean 값은 시간을 24시간으로 보일지 아닐지
                dialog.show();
            }
        });
    }
}