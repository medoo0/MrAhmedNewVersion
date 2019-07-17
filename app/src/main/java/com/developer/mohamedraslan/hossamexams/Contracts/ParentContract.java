package com.developer.mohamedraslan.hossamexams.Contracts;

import com.developer.mohamedraslan.hossamexams.JsonModel.FullRegisterForm;

public interface ParentContract {



    interface ParentView{

        void StudentdetailsHere(FullRegisterForm fullRegisterForm);
        void noDetailsFound();
        void problemOcurr(String p);
    }

    interface ParentModel{


        void getDetailsForStudentFromFirebase(String codeStudent);

    }

    interface ParentPresnter{


        void tellModeltoGetAllDetailsForstudent(String studentCode);
        void detaisUserHere(FullRegisterForm fullRegisterForm);
        void StudentNotFound();
        void getProblemFromModel(String error);

    }
}
