package com.developer.mohamedraslan.hossamexams.Contracts;

public interface MainActivityContract {

    interface View{
        void showFragmentRegister(String yearName,String parentYear);
        void showLoginFragment();
        void openControlPanel(String email, String password,String depOfStudent);


        void showParentOrStudentFragment();

        void showParentRegister();
        void showFirstRegisterStudent();
    }


    interface Presnter{

    }

    interface Modle{

    }

}
