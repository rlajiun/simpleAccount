package com.unvin.simpleaccount.Activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.unvin.simpleaccount.MainActivity;
import com.unvin.simpleaccount.R;
import com.unvin.simpleaccount.adapter.CategoryAdapter;
import com.unvin.simpleaccount.database.DBHelper;

import java.util.Calendar;

public class ShareActivity extends Activity {
    ImageButton back;
    EditText editTextWho, editTextCost;
    TextView date, time;
    Button btnshareadd;

    private Spinner spinner_cat;
    String[] spinnerNames;
    int[] spinnerImages;
    int selected_cat_idx = 0;
    String datemsg, timemsg;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

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
        spinnerNames = new String[]{"받기", "보내기"};
//        spinnerNames = new String[]{};
//        spinnerNames = getResources().getStringArray(R.array.category_consume);
        spinnerImages = new int[]{R.drawable.getmoney, R.drawable.sendmoney};

        // 어댑터와 스피너를 연결합니다.
        CategoryAdapter categoryAdapter = new CategoryAdapter(ShareActivity.this, spinnerNames, spinnerImages);
        spinner_cat.setAdapter(categoryAdapter);

        // 스피너에서 아이템 선택시 호출하도록 합니다.

        spinner_cat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                selected_cat_idx = spinner_cat.getSelectedItemPosition();
                Toast.makeText(ShareActivity.this, spinnerNames[selected_cat_idx], Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }

        });

        editTextWho = findViewById(R.id.edit_who);
        editTextCost = findViewById(R.id.edit_cost);
        final EditText editTextDetail = findViewById(R.id.edit_detail);
        btnshareadd = findViewById(R.id.shareAddButton);
        btnshareadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(editTextCost.getText().toString().length() != 0 && editTextWho.getText().toString().length() != 0){

                    DBHelper dbHelper = new DBHelper(view.getContext(), "unvinDB",null, 1);
                    SQLiteDatabase db = dbHelper.getWritableDatabase();
                    db.execSQL(dbHelper.insertShareSQL, new String[]{String.valueOf(selected_cat_idx), editTextCost.getText().toString(), editTextWho.getText().toString(),
                    editTextDetail.getText().toString(), datemsg});
                    db.close();
                    Toast.makeText(view.getContext(), "추가되었습니다", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    private void init() {
        //Calendar를 이용하여 년, 월, 일, 시간, 분을 PICKER에 넣어준다.
        final Calendar cal = Calendar.getInstance();
        datemsg = String.format("%d년 %d월 %d일", cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)+1, cal.get(Calendar.DATE));

        //DATE PICKER DIALOG
        date = (TextView)findViewById(R.id.txt_date);
        date.setText(datemsg);
        date.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(ShareActivity.this, new DatePickerDialog.OnDateSetListener() {
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
                TimePickerDialog dialog = new TimePickerDialog(ShareActivity.this, new TimePickerDialog.OnTimeSetListener() {
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