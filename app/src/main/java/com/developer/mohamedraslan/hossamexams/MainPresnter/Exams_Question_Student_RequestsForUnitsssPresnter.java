package com.developer.mohamedraslan.hossamexams.MainPresnter;

import com.developer.mohamedraslan.hossamexams.Contracts.Exam_Question_Request_forUnitInterface;
import com.developer.mohamedraslan.hossamexams.MainModle.Exam_Question_RequestForUnitModel;

public class Exams_Question_Student_RequestsForUnitsssPresnter implements Exam_Question_Request_forUnitInterface.ExamQuestioRequestPresnter {


    Exam_Question_Request_forUnitInterface.ExamQuestionRequestUI examQuestionRequestUI;
    Exam_Question_RequestForUnitModel exam_question_requestForUnitModel;

    public Exams_Question_Student_RequestsForUnitsssPresnter(Exam_Question_Request_forUnitInterface.ExamQuestionRequestUI examQuestionRequestUI) {
        this.examQuestionRequestUI        = examQuestionRequestUI;
        exam_question_requestForUnitModel = new Exam_Question_RequestForUnitModel(this);
    }

    @Override
    public void tellModeltoCheckNowbeforeanyThing(String uID) {

        exam_question_requestForUnitModel.checkIfAdminorUsertoHideSomeButton(uID);

    }

    @Override
    public void yesThisAdmin() {
        examQuestionRequestUI.yesIsAdminShowButton();

    }

    @Override
    public void yesThisUser() {
        examQuestionRequestUI.yesThisUserHideButton();

    }
}
