package com.developer.mohamedraslan.hossamexams.Contracts;

import com.developer.mohamedraslan.hossamexams.JsonModel.FullRegisterForm;

public interface MainActivityContract {

    interface View{
        void showFragmentRegister(String yearName,String parentYear);
        void showLoginFragment();
        void openControlPanel(String email, String password,String depOfStudent);


        void showParentOrStudentFragment();

        void showParentRegister();
        void showFirstRegisterStudent();


        void fullRegisterForStudent(FullRegisterForm fullRegisterForm);


    }


    interface Presnter{




    }

    interface Modle{


    }

}
