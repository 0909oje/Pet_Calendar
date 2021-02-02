package com.example.pet_calendar_0130;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Calendar;

public class activity_detail extends AppCompatActivity {
    Button add_pro, add_pic, edit, save, back_detail, back_main;
    TextView date, output;
    EditText input;
    InputMethodManager imm;
    Uri uri;
    RecyclerView profile,picture;
    RecyclerView.LayoutManager mLayoutManager;

    ImageView img ;

    // db 생성 위한 인수
    DBHelper helper;
    SQLiteDatabase db;
    static final int REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_detail);

        this.InitializeView();
        this.SetListener();

        // 날짜 클릭 시 달력 불러옴
        this.getCalendar();

        // newdb 라는 database 에 mytable 이라는 table을 생성하고 수정가능하게 db를 불러온다.
        helper = new DBHelper(activity_detail.this, "newdb.db", null, 1);
        db = helper.getWritableDatabase();
        helper.onCreate(db);
        // https://popcorn16.tistory.com/76 에 db사용법 정리되어 있음

//        //리사이클뷰 구현 -> 후에 구현
//        this.setProfile();
//        this.setPicture();
    }

    public void InitializeView(){
        add_pro = (Button) findViewById(R.id.activity_detail_profile_add);
        add_pic = (Button) findViewById(R.id.activity_detail_picture_add);
        save = (Button) findViewById(R.id.activity_detail_save);
        edit = (Button) findViewById(R.id.activity_detail_edit);
        back_detail = (Button) findViewById(R.id.activity_detail_back_detail);
        back_main = (Button) findViewById(R.id.activity_detail_back);
        input = (EditText) findViewById(R.id.activity_detail_diary_input);
        output = (TextView) findViewById(R.id.activity_detail_diary_output);
        date =(TextView)findViewById(R.id.activity_detail_date_input);
        imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        profile = (RecyclerView)findViewById(R.id.activity_detail_date_profile);
        picture = (RecyclerView)findViewById(R.id.activity_detail_date_picture);
        img = (ImageView)findViewById(R.id.activity_detail_samplepic);
    }

    public void SetListener() {
        // 수정버튼 누르면
        edit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                add_pro.setVisibility(View.VISIBLE);
                add_pic.setVisibility(View.VISIBLE);

                save.setVisibility(View.VISIBLE);
                edit.setVisibility(View.INVISIBLE);

                back_detail.setVisibility(View.VISIBLE);
                back_main.setVisibility(View.INVISIBLE);

                output.setVisibility(View.INVISIBLE);
                input.setVisibility(View.VISIBLE);

                // 키보드 바깥 누르면 키보드 내려감
                imm.hideSoftInputFromWindow(input.getWindowToken(), 0);
            }

        });

        // 저장 버튼 누르면
        save.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                add_pro.setVisibility(View.INVISIBLE);
                add_pic.setVisibility(View.INVISIBLE);

                save.setVisibility(View.INVISIBLE);
                edit.setVisibility(View.VISIBLE);

                back_detail.setVisibility(View.INVISIBLE);
                back_main.setVisibility(View.VISIBLE);

                output.setVisibility(View.VISIBLE);
                input.setVisibility(View.INVISIBLE);

                output.setText(input.getText());
            }
        });

        // main 으로 돌아감
        back_main.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(intent);
            }
        });

        // 수정 중 back 누르면 변경사항 적용되지 않음
        back_detail.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                save.setVisibility(View.INVISIBLE);
                edit.setVisibility(View.VISIBLE);

                add_pro.setVisibility(View.INVISIBLE);
                add_pic.setVisibility(View.INVISIBLE);

                back_detail.setVisibility(View.INVISIBLE);
                back_main.setVisibility(View.VISIBLE);

                output.setVisibility(View.VISIBLE);
                input.setVisibility(View.INVISIBLE);
            }
        });

        // 갤러리에서 사진 가져오기
        add_pic.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(MediaStore.Images.Media.CONTENT_TYPE);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });
    }
    //이미지를 db에 저장함.
    @Override
    protected  void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE) {
            uri = data.getData();
        }
    }
    private void setImage(Uri uri) {
        try{
            InputStream in = getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(in);
            img.setImageBitmap(bitmap);
        } catch (FileNotFoundException e){
            e.printStackTrace();
        }
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

    public void getCalendar(){
        Calendar cal = Calendar.getInstance();
        date.setText(cal.get(Calendar.YEAR) +"-"+ (cal.get(Calendar.MONTH)+1) +"-"+ cal.get(Calendar.DATE));
    }

    public void setProfile(){
//        profile.setHasFixedSize(true);
//        mLayoutManager = new LinearLayoutManager(this);
//        profile.setLayoutManager(mLayoutManager);
//
//        ArrayList<Pet> PetInfoArr = new ArrayList<>();
//        PetInfoArr.add(Pet.drawableId);
//        MyAdapter myAdapter = new MyAdapter(PetInfoArr);
//
//        profile.setAdapter(myAdapter);

    }
    public void setPicture(){
//
//        picture.setHasFixedSize(true);
//        mLayoutManager = new LinearLayoutManager(this);
//        picture.setLayoutManager(mLayoutManager);
//
//        int[] PetInfoArr = new int[5];
//        PetInfoArr = (Diary.getPictureId());
//
//        MyAdapter myAdapter = new MyAdapter(PetInfoArr);
//
//        profile.setAdapter(myAdapter);
    }
    
}