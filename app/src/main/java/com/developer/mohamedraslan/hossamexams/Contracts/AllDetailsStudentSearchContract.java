package com.developer.mohamedraslan.hossamexams.Contracts;

import com.developer.mohamedraslan.hossamexams.JsonModel.Result_Pojo;

import java.util.List;

public interface AllDetailsStudentSearchContract {


    interface AllStudentSerachUI{

        void notResultFound();
        void ResultHere(List<Result_Pojo>result);
        void problems(String error);

    }


    interface AllStudentPresnter{

        void tellModeltoGetResult(String StudenID,String depName , String yearName);
        void resultExisit        (List<Result_Pojo>list);
        void thisStudentDosnthaveAnyResult();
        void probleming(String error);


    }

    interface AllstudentModel{


        void getResultsinAllUnits(String StudentID,String depName , String yearName);



    }

}
