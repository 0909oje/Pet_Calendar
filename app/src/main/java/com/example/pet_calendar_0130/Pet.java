package com.example.pet_calendar_0130;

// Pet 에 필요한 것 : 사진, 이름, 종류, 생일, 메모
public class Pet {
    public int drawableId;
    public String name;
    public int birth;

    public Pet(int drawableId, String name, int birth ){
        this.drawableId = drawableId;
        this.name = name;
        this.birth = birth;
    }
}
