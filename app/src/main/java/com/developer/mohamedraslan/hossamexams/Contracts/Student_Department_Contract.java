package com.developer.mohamedraslan.hossamexams.Contracts;

import java.util.List;

public interface Student_Department_Contract {



    interface mainView{

        void depsHere(List<String>deps);
        void problems(String Errors);

        void setSizeOfList(int size);

        void getDetailsForDeps(String depsName);

    }



    interface PresnterStudentDep{


        void tellModeltoGetDeps();

        void updateUIbyArrayDeps(List<String> list);
        void problemswithRefrence(String error);


    }
    interface ModelStudentDep{

        void getDepsRefrences();

    }

}
