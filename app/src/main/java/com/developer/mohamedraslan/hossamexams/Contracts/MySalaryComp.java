package com.developer.mohamedraslan.hossamexams.Contracts;

import com.developer.mohamedraslan.hossamexams.JsonModel.Year_modle_json;

import java.util.Comparator;

public class MySalaryComp implements Comparator<Year_modle_json>{

    @Override
    public int compare(Year_modle_json e1, Year_modle_json e2) {

        if(e1.getTimeAdded() > e2.getTimeAdded()){
            return 1;
        } else {
            return -1;
        }
    }
}
