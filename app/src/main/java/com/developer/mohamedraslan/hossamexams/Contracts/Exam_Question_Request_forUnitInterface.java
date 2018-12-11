package com.developer.mohamedraslan.hossamexams.Contracts;

public interface Exam_Question_Request_forUnitInterface {





    interface ExamQuestionRequestUI{

        void yesIsAdminShowButton();
        void yesThisUserHideButton();


    }
    interface ExamQuestioRequestPresnter{


        void tellModeltoCheckNowbeforeanyThing(String uID);

        void yesThisAdmin();
        void yesThisUser();

    }
    interface ExamQuestionRequestModel{

        void checkIfAdminorUsertoHideSomeButton(String uID);

    }
}
