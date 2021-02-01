package com.example.pet_calendar_0130;

public class Diary {
    int date;
    int[] pictureId;
    String memo;

    public Diary(int date){
        this.date = date;
    }
    public void getPicture(int[] id){
        this.pictureId = id;
    }
    public int getDate(){
        return date;
    }
    public int[] getPictureId(){
        return pictureId;
    }
    public String getMemo(){
        return memo;
    }
}


