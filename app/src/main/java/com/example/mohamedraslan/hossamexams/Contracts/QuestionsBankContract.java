package com.example.mohamedraslan.hossamexams.Contracts;

import com.example.mohamedraslan.hossamexams.JsonModel.Questions_Form;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

/**
 * Created by microprocess on 2018-10-05.
 */

public interface QuestionsBankContract {
    interface model {
        void getQuestionData();
        void addQuestionToAddTestRecycler(String questionID);
        void removingQuestionFromDatabase(DatabaseReference reference, String Qid, QuestionsBankContract.presenter presenter, int position);

    }
    interface presenter{

        void Qremoved(int position);
        void Q_notRemoved_checking();


        void tellModletoDeleteQuestion(DatabaseReference reference, String Qid, int position);
        void callQuestionData();
        void SendListToView(List<Questions_Form> Result);
        void problem(String problem);
        void sentSuccessfully(String Result);
        void addQuestionToAddTestRecycler(String questionID);
    }
    interface view{
        void RecyclerConfig(List<Questions_Form> Result);
        void problem(String problem);
        void sentSuccessfully(String Result);
        void updateFragbyValuesTogoEditFrag(String questionID);
        void removingQuestion(String questionID, int position);
        void Q_Removed_InUI(int position);
        void Q_notRemoved_InUI();
    }
}
