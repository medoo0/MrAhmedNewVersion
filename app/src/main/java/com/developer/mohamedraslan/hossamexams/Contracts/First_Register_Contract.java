package com.developer.mohamedraslan.hossamexams.Contracts;

import com.developer.mohamedraslan.hossamexams.JsonModel.Year_modle_json;

import java.util.List;

public interface First_Register_Contract {



    interface ParentFirstRegisterView{


        void depsHereshowInSpinner(List<String>list);
        void depsNotFound();
        void problemsssssss(String error);

        void showinSpinneryearFromDeps(List<Year_modle_json>list);
        void noYearsorry();
        void withthisDepProblems(String problems);

    }


    interface ParentFirstRegisterPresnter{
        void tellModeltogetdeps();
        void tellUInotFoundDeps();
        void tellUIDepsAreHere(List<String>list);
        void tellUiprobleming(String error);



        void tellmodeltoGetyearsInDeps(String nameofDeps);
        void yearsinDepshere(List<Year_modle_json>years);
        void noYearinThisDeps();
        void tellUiProblemConnection(String error);

    }

    interface ParentFirstRegisterModel{


        void getAlldepsFromFirbase();
        void getyearsinDeps(String nameOfDpe);


    }

}
