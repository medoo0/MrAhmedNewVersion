package com.am.mohamedraslan.hossamexams.JsonModel;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by microprocess on 2018-10-16.
 */

public class WorngQestion implements Parcelable{

    String questionID , question , correctAnswer , studentAnswer ;

    public WorngQestion() {
    }

    public WorngQestion(String questionID, String question, String correctAnswer, String studentAnswer) {
        this.questionID = questionID;
        this.question = question;
        this.correctAnswer = correctAnswer;
        this.studentAnswer = studentAnswer;
    }

    protected WorngQestion(Parcel in) {
        questionID = in.readString();
        question = in.readString();
        correctAnswer = in.readString();
        studentAnswer = in.readString();
    }

    public static final Creator<WorngQestion> CREATOR = new Creator<WorngQestion>() {
        @Override
        public WorngQestion createFromParcel(Parcel in) {
            return new WorngQestion(in);
        }

        @Override
        public WorngQestion[] newArray(int size) {
            return new WorngQestion[size];
        }
    };

    public String getQuestionID() {
        return questionID;
    }

    public String getQuestion() {
        return question;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public String getStudentAnswer() {
        return studentAnswer;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(questionID);
        parcel.writeString(question);
        parcel.writeString(correctAnswer);
        parcel.writeString(studentAnswer);
    }
}
