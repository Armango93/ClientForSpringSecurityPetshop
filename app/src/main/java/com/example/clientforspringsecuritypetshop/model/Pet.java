package com.example.clientforspringsecuritypetshop.model;


import android.os.Parcel;
import android.os.Parcelable;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class Pet implements Parcelable{
    private int id;
    private String name;
    private int age;
    private int userId;

    public Pet() {
    }

//    public Pet(int id, String name, int age, int userId) {
//        this.id = id;
//        this.name = name;
//        this.age = age;
//        this.userId = userId;
//    }

    protected Pet(Parcel in) {
        id = in.readInt();
        name = in.readString();
        age = in.readInt();
        userId = in.readInt();
    }

    @Generated(hash = 1020611277)
    public Pet(int id, String name, int age, int userId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.userId = userId;
    }

    public static final Creator<Pet> CREATOR = new Creator<Pet>() {
        @Override
        public Pet createFromParcel(Parcel in) {
            return new Pet(in);
        }

        @Override
        public Pet[] newArray(int size) {
            return new Pet[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeInt(age);
        parcel.writeInt(userId);
    }
}