package com.example.pet_calendar_0130;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Calendar;

public class activity_detail extends AppCompatActivity {
    Button add1, add2, edit, save, back_detail, back_main;
    TextView date, output;
    EditText input;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_detail);

        this.InitializeView();
        this.SetListener();

        Calendar cal = Calendar.getInstance();
        date.setText(cal.get(Calendar.YEAR) +"-"+ (cal.get(Calendar.MONTH)+1) +"-"+ cal.get(Calendar.DATE));

    }

    public void InitializeView(){
        add1 = (Button) findViewById(R.id.activity_detail_picture_add);
        add2 = (Button) findViewById(R.id.activity_detail_profile_add);
        save = (Button) findViewById(R.id.activity_detail_save);
        edit = (Button) findViewById(R.id.activity_detail_edit);
        back_detail = (Button) findViewById(R.id.activity_detail_back_detail);
        back_main = (Button) findViewById(R.id.activity_detail_back);
        input = (EditText) findViewById(R.id.activity_detail_diary_input);
        output = (TextView) findViewById(R.id.activity_detail_diary_output);
        date =(TextView)findViewById(R.id.activity_detail_date_input);

    }

    public void SetListener() {
        edit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                add1.setVisibility(View.VISIBLE);
                add2.setVisibility(View.VISIBLE);
                save.setVisibility(View.VISIBLE);
                back_detail.setVisibility(View.VISIBLE);
                edit.setVisibility(View.INVISIBLE);
                back_main.setVisibility(View.INVISIBLE);
            }
        });

        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                add1.setVisibility(View.INVISIBLE);
                add2.setVisibility(View.INVISIBLE);
                save.setVisibility(View.INVISIBLE);
                back_detail.setVisibility(View.INVISIBLE);
                edit.setVisibility(View.VISIBLE);
                back_main.setVisibility(View.VISIBLE);
            }
        });

        back_main.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        back_detail.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                add1.setVisibility(View.INVISIBLE);
                add2.setVisibility(View.INVISIBLE);
                save.setVisibility(View.INVISIBLE);
                back_detail.setVisibility(View.INVISIBLE);
                edit.setVisibility(View.VISIBLE);
                back_main.setVisibility(View.VISIBLE);
            }
        });
    }

    //datePicker 보여줌
    public void showDatePicker(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(),"datePicker");
    }

    DatePickerDialog.OnDateSetListener mDateSetListener =new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker datePicker, int yy, int mm, int dd) {
            // Date Picker에서 선택한 날짜를 TextView에 설정
            date.setText(String.format("%d-%d-%d", yy,mm+1,dd));
        }
    };

    public void mOnClick_DatePick(View view){ // 실행성공
        // DATE Picker가 처음 떴을 때, 오늘 날짜가 보이도록 설정.
        Calendar cal = Calendar.getInstance();
        new DatePickerDialog(this, mDateSetListener, cal.get(Calendar.YEAR),
                cal.get(Calendar.MONTH), cal.get(Calendar.DATE)).show();
    }

    public void processDatePickerResult(int year, int month, int day){
        String month_string = Integer.toString(month+1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        date.setText(String.format("%s-%s-%s", year_string,month_string,day_string ));
    }
    
}