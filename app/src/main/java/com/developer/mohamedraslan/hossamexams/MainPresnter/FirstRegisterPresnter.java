package com.developer.mohamedraslan.hossamexams.MainPresnter;

import com.developer.mohamedraslan.hossamexams.Contracts.First_Register_Contract;
import com.developer.mohamedraslan.hossamexams.JsonModel.Year_modle_json;
import com.developer.mohamedraslan.hossamexams.MainModle.FirstRegisterModel;

import java.util.List;

public class FirstRegisterPresnter implements First_Register_Contract.ParentFirstRegisterPresnter {

    First_Register_Contract.ParentFirstRegisterView view;
    FirstRegisterModel firstRegisterModel;


    public FirstRegisterPresnter(First_Register_Contract.ParentFirstRegisterView view) {
        this.view         = view;
        firstRegisterModel = new FirstRegisterModel(this);
    }

    @Override
    public void tellModeltogetdeps() {
        firstRegisterModel.getAlldepsFromFirbase();

    }

    @Override
    public void tellUInotFoundDeps() {

        view.depsNotFound();

    }

    @Override
    public void tellUIDepsAreHere(List<String> list) {
        view.depsHereshowInSpinner(list);
    }

    @Override
    public void tellUiprobleming(String error) {

        view.problemsssssss(error);

    }

    @Override
    public void tellmodeltoGetyearsInDeps(String nameofDeps) {

        firstRegisterModel.getyearsinDeps(nameofDeps);

    }

    @Override
    public void yearsinDepshere(List<Year_modle_json> years) {


        view.showinSpinneryearFromDeps(years);
    }

    @Override
    public void noYearinThisDeps() {

        view.noYearsorry();
    }

    @Override
    public void tellUiProblemConnection(String error) {
        view.withthisDepProblems(error);

    }
}
