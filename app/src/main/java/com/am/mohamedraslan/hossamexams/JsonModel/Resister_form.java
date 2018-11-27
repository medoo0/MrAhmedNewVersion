package com.am.mohamedraslan.hossamexams.JsonModel;

import android.os.Parcel;
import android.os.Parcelable;

public class Resister_form  implements Parcelable {

    private String nameStudent;
    private String email;
    private String phone;
    private String country;

    public Resister_form(String nameStudent, String email, String phone, String country) {
        this.nameStudent = nameStudent;
        this.email = email;
        this.phone = phone;
        this.country = country;
    }
    public Resister_form(){

    }

    protected Resister_form(Parcel in) {
        nameStudent = in.readString();
        email = in.readString();
        phone = in.readString();
        country = in.readString();
    }

    public static final Creator<Resister_form> CREATOR = new Creator<Resister_form>() {
        @Override
        public Resister_form createFromParcel(Parcel in) {
            return new Resister_form(in);
        }

        @Override
        public Resister_form[] newArray(int size) {
            return new Resister_form[size];
        }
    };

    public String getNameStudent() {
        return nameStudent;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getCountry() {
        return country;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(nameStudent);
        parcel.writeString(email);
        parcel.writeString(phone);
        parcel.writeString(country);
    }
}
