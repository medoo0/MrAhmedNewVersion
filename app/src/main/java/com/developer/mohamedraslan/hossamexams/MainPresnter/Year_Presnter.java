package com.developer.mohamedraslan.hossamexams.MainPresnter;

import com.developer.mohamedraslan.hossamexams.Contracts.Years_inDepsContract;
import com.developer.mohamedraslan.hossamexams.JsonModel.Year_modle_json;
import com.developer.mohamedraslan.hossamexams.MainModle.Year_Model;

import java.util.List;

public class Year_Presnter implements Years_inDepsContract.MainPYear {


    Years_inDepsContract.ViewMainYear mainYear;
    Year_Model yearsModel;

    public Year_Presnter(Years_inDepsContract.ViewMainYear mainYear) {
        this.mainYear = mainYear;
        yearsModel    = new Year_Model(this);
    }

    @Override
    public void tellModeltogetYears(String parentofYear) {

        yearsModel.getYearsDepartments(parentofYear);

    }

    @Override
    public void tellUIYearExisit(List<Year_modle_json> years) {
        mainYear.exisityear(years);
    }



    @Override
    public void tellUIYearNotExisit() {
        mainYear.problemsyearnotFound();
    }

    @Override
    public void connectionPoooring(String error) {
        mainYear.connectionPoor(error);

    }
}
