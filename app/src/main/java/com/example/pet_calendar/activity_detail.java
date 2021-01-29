package com.example.pet_calendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;

import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;

public class activity_detail extends AppCompatActivity {
    Button add1 ;
    Button add2 ;
    Button save ;
    Button edit ;
    Button back_detail ;
    Button back_main ;
    TextView date;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_detail);

        date =(TextView)findViewById(R.id.activity_detail_date_input);
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

    }

    public void SetListener() {
        View.OnClickListener Listener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (v.getId()) {

                    //edit 클릭 후 back 클릭 시 저장안되는 것 만들지 않음
                    //edit 클릭 시 +버튼, save 버튼 보이기, back 버튼 보이기, 텍스트, 사진 수정 가능
                    //edit, back 사라지기
                    case R.id.activity_detail_edit:
                        add1.setVisibility(View.VISIBLE);
                        add2.setVisibility(View.VISIBLE);
                        save.setVisibility(View.VISIBLE);
                        back_detail.setVisibility(View.VISIBLE);
                        edit.setVisibility(View.INVISIBLE);
                        back_main.setVisibility(View.INVISIBLE);
                        break;

                    // back 클릭 시 메인화면
                    case R.id.activity_detail_back:
                        Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                        startActivity(intent);
                        break;
                }
            }
        };
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