package com.developer.mohamedraslan.hossamexams.Contracts;

import java.util.List;

public interface Years_inDepsContract {


    interface ViewMainYear{


        void exisityear(List<String> years);

        void problemsyearnotFound();
        void connectionPoor(String error);

        void getSizeofarray(int size);


    }

    interface MainPYear{


        void tellModeltogetYears(String parentofYear);
        void tellUIYearExisit(List<String> years);
        void tellUIYearNotExisit();
        void connectionPoooring(String error);


    }


    interface MainModelYear{

        void getYearsDepartments(String parentOfYear);

    }


}
