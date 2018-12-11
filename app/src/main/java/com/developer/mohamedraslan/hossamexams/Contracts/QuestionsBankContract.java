package com.developer.mohamedraslan.hossamexams.Contracts;

import com.developer.mohamedraslan.hossamexams.JsonModel.Questions_Form;
import com.google.firebase.database.DatabaseReference;

import java.util.List;

/**
 * Created by microprocess on 2018-10-05.
 */

public interface QuestionsBankContract {
    interface model {
        void getQuestionData(String depName , String yearName , String unitName);

        void removallQinTheUnit(String depName , String yearName , String unitName);

        void addQuestionToAddTestRecycler(String questionID,String depName , String yearName ,String unitName);
        void removingQuestionFromDatabase(DatabaseReference reference, String Qid, QuestionsBankContract.presenter presenter, int position, String depName , String yearName , String unitName);

    }
    interface presenter{

        void Qremoved(int position, String depName , String yearName , String unitName);
        void Q_notRemoved_checking( String depName , String yearName , String unitName);



        void tellModeltoRemoveAllQuestions(String depName , String yearName , String unitName);
        void tellUIallQuestionRemoved(String depName , String yearName , String unitName);
        void tellUiallQuestionNotRemoved(String depName , String yearName , String unitName);



        void tellModletoDeleteQuestion(DatabaseReference reference, String Qid, int position , String depName , String yearName , String unitName);
        void callQuestionData(String depName , String yearName , String unitName);
        void SendListToView  (List<Questions_Form> Result);
        void problem         (String problem);
        void sentSuccessfully(String Result);
        void addQuestionToAddTestRecycler(String questionID,String depName , String yearName ,String unitName);      // mor Info
    }
    interface view{
        void RecyclerConfig(List<Questions_Form> Result);
        void problem(String problem);
        void sentSuccessfully(String Result);
        void updateFragbyValuesTogoEditFrag(String depName , String yearName , String unitName,String questionID);
        void removingQuestion(String questionID, int position , String depName , String yearName , String unitName);
        void Q_Removed_InUI(int position, String depName , String yearName , String unitName);
        void Q_notRemoved_InUI(String depName , String yearName , String unitName);

        void allQuestioninUnitRemoved(String depName , String yearName , String unitName);

        void allQuestioninUnitNotRemoved(String depName , String yearName , String unitName);

        void nuberQuestions(int number);
    }
}
