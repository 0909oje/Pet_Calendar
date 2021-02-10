package com.example.pet_calendar_0130;


import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class activity_pet_add extends AppCompatActivity {

    TextView birth;
    ImageButton pic;
    EditText name;
    CheckBox dog,cat,etc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_pet_add);

        this.InitializeView();
        this.SetListener();

        // 날짜 클릭 시 달력 불러옴
        this.getCalendar();


    }

    public void InitializeView(){
        pic = (ImageButton)findViewById(R.id.activity_pet_add_pic);
        birth = (TextView)findViewById(R.id.activity_detail_birth_output);
        name = (EditText)findViewById(R.id.activity_pet_add_name_input);
        dog = (CheckBox)findViewById(R.id.activity_pet_add_dog);
        etc = (CheckBox)findViewById(R.id.activity_pet_add_etc);
        cat = (CheckBox)findViewById(R.id.activity_pet_add_cat);
    }

    public void SetListener() {


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
            birth.setText(String.format("%d-%d-%d", yy,mm+1,dd));
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
        birth.setText(String.format("%s-%s-%s", year_string,month_string,day_string ));
    }

    public void getCalendar(){
        Calendar cal = Calendar.getInstance();
        birth.setText(cal.get(Calendar.YEAR) +"-"+ (cal.get(Calendar.MONTH)+1) +"-"+ cal.get(Calendar.DATE));
    }

}