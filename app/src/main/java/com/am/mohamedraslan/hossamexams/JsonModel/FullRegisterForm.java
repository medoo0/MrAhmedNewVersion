package com.am.mohamedraslan.hossamexams.JsonModel;

import android.os.Parcel;
import android.os.Parcelable;

public class FullRegisterForm implements Parcelable{

    private String nameStudent;
    private String email;
    private String phone;
    private String uID;
    private String country;
    private String areINGroup;

    public FullRegisterForm(String nameStudent, String email, String phone, String uID, String country,String areINGroup) {
        this.nameStudent = nameStudent;
        this.email = email;
        this.phone = phone;
        this.uID = uID;
        this.country = country;
        this.areINGroup = areINGroup;
    }

    public String getAreINGroup() {
        return areINGroup;
    }

    public FullRegisterForm(){

    }

    public String getCountry() {
        return country;
    }

    protected FullRegisterForm(Parcel in) {
        nameStudent = in.readString();
        email = in.readString();
        phone = in.readString();
        uID = in.readString();
        country = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nameStudent);
        dest.writeString(email);
        dest.writeString(phone);
        dest.writeString(uID);
        dest.writeString(country);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<FullRegisterForm> CREATOR = new Creator<FullRegisterForm>() {
        @Override
        public FullRegisterForm createFromParcel(Parcel in) {
            return new FullRegisterForm(in);
        }

        @Override
        public FullRegisterForm[] newArray(int size) {
            return new FullRegisterForm[size];
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

    public String getuID() {
        return uID;
    }

}
