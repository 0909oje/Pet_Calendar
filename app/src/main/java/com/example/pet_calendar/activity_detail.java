package com.example.pet_calendar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class activity_detail extends AppCompatActivity {
    Button add1 ;
    Button add2 ;
    Button save ;
    Button edit ;
    Button back_detail ;
    Button back_main ;
    EditText date ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_detail);

        // back 클릭 시 메인화면

        ////edit 클릭 후 back 클릭 시 저장안되는 것 만들지 않음

        //edit 클릭 시 +버튼, save 버튼 보이기, back 버튼 보이기, 텍스트, 사진 수정 가능
        //edit, back 사라지기
    }

    public void InitializeView(){
        add1 = (Button) findViewById(R.id.activity_detail_picture_add);
        add2 = (Button) findViewById(R.id.activity_detail_profile_add);
        save = (Button) findViewById(R.id.activity_detail_save);
        edit = (Button) findViewById(R.id.activity_detail_edit);
        back_detail = (Button) findViewById(R.id.activity_detail_back_detail);
        back_main = (Button) findViewById(R.id.activity_detail_back);
        date = (EditText)findViewById(R.id.activity_detail_date_input);
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


    //    날짜 선택하기
    public void showDatePicker(View view) {
        DialogFragment newFragment = new DatePickerFragment();
        newFragment.show(getSupportFragmentManager(),"datePicker");
    }

    public void processDatePickerResult(int year, int month, int day){
        String month_string = Integer.toString(month+1);
        String day_string = Integer.toString(day);
        String year_string = Integer.toString(year);
        String dateMessage = (month_string + "/" + day_string + "/" + year_string);


        Toast.makeText(this,"Date: "+dateMessage,Toast.LENGTH_SHORT).show();
    }
}