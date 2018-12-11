package com.developer.mohamedraslan.hossamexams.Contracts;

import com.developer.mohamedraslan.hossamexams.JsonModel.Year_modle_json;

import java.util.List;

public interface Years_inDepsContract {


    interface ViewMainYear{


        void exisityear(List<Year_modle_json> years);
        void problemsyearnotFound();
        void connectionPoor(String error);
        void getSizeofarray(int size);
        void getValuesofdepandyear(String depName , String yearName);
//        void valuetogetQuestionofThisYear(String dep , String year);


    }

    interface MainPYear{


        void tellModeltogetYears(String parentofYear);
        void tellUIYearExisit(List<Year_modle_json> years);
        void tellUIYearNotExisit();
        void connectionPoooring(String error);


    }


    interface MainModelYear{

        void getYearsDepartments(String parentOfYear);

    }


}
