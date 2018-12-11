package com.developer.mohamedraslan.hossamexams.Contracts;

import com.developer.mohamedraslan.hossamexams.JsonModel.Unites_Model_Json;

import java.util.List;

public interface Unites_Contract {


    interface ParentUnitesUI{





        void unitsAndDetailsofHisAreDeleted();
        void probelmunitNotDeleted(String p);





        void imAdminShowMeButton();

        void imUserHideButton();


        void setNumberOfunit(int uum);




        void detailsforUnits(String depName , String yearName , String unitName);
        void UniteTrueadded();
        void UniteFalseadded();
        void showUnites(List<Unites_Model_Json>units);
        void noUnitestoShow();
        void whatproblemthatOcurr(String problem);



        //  مجموعه ال check علي الوحده
        void nameUnite(String unitName);
        void unitNotExistingofyouwanttoaddAdd(String unitName);
        void unitalreadyheresorryCantaddthis();
        void poorConnection(String error);


    }

    interface ParentUnitesPresnter{

        void tellModeltoDeleteUnit(String depName , String yearName,String unitName);

        void unitDeleted();
        void problemwithD(String p);




        void tellModeltoAddUnite(String depName , String yearName,String unitName);
        void unitSussAdded();
        void unitedcassesProblem();



        void tellModeltogetUnits(String depName , String yearName);
        void ifoundUnites(List<Unites_Model_Json> units);
        void inoFoundUnites();
        void problemOcurrs(String error);




        //  مجموعه ال check علي الوحده
        void tellmodeltocheckaboutUnit(String depName , String yearName ,String unitName);
        void tellUIunitExisitinData();
        void tellUiunitNotFount(String unitName);
        void callUIaboutConnection(String error);



        void tellModeltoChecking(String uID);
        void happyImAdmin();
        void sadImUser();

    }

    interface ParentUnitesModel{


        void addUnite(String depName , String yearName,String unitName);

        void getUnites(String depName  , String yearName);

        void checkingUnitofYear(String depName , String yearName , String unitName);

        void checkifUserAdminorNot(String uID);


        void deleteUnit(String depName , String yearNmae , String unitName);
    }

}
