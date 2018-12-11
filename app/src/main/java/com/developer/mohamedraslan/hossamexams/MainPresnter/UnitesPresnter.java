package com.developer.mohamedraslan.hossamexams.MainPresnter;

import com.developer.mohamedraslan.hossamexams.Contracts.Unites_Contract;
import com.developer.mohamedraslan.hossamexams.JsonModel.Unites_Model_Json;
import com.developer.mohamedraslan.hossamexams.MainModle.UnitesModel;

import java.util.List;

public class UnitesPresnter implements Unites_Contract.ParentUnitesPresnter {


    Unites_Contract.ParentUnitesUI parentUnitesUI;
    UnitesModel unitesModel ;

    public UnitesPresnter(Unites_Contract.ParentUnitesUI parentUnitesUI) {
        this.parentUnitesUI = parentUnitesUI;
        unitesModel         = new UnitesModel(this);
    }


    @Override
    public void tellModeltoDeleteUnit(String depName, String yearName, String unitName) {
        unitesModel.deleteUnit(depName,yearName,unitName);
    }

    @Override
    public void unitDeleted() {
        parentUnitesUI.unitsAndDetailsofHisAreDeleted();

    }

    @Override
    public void problemwithD(String p) {

        parentUnitesUI.probelmunitNotDeleted(p);
    }

    @Override
    public void tellModeltoAddUnite(String depName, String yearName, String unitName) {
        unitesModel.addUnite(depName,yearName,unitName);
    }

    @Override
    public void unitSussAdded() {
        parentUnitesUI.UniteTrueadded();
    }

    @Override
    public void unitedcassesProblem() {
        parentUnitesUI.UniteFalseadded();

    }

    @Override
    public void tellModeltogetUnits(String depName, String yearName) {
        unitesModel.getUnites(depName,yearName);
    }

    @Override
    public void ifoundUnites(List<Unites_Model_Json> units) {

        parentUnitesUI.showUnites(units);
    }

    @Override
    public void inoFoundUnites() {

        parentUnitesUI.noUnitestoShow();

    }

    @Override
    public void problemOcurrs(String error) {

        parentUnitesUI.whatproblemthatOcurr(error);
    }

    @Override
    public void tellmodeltocheckaboutUnit(String depName, String yearName, String unitName) {
        unitesModel.checkingUnitofYear(depName,yearName,unitName);
    }

    @Override
    public void tellUIunitExisitinData() {
        parentUnitesUI.unitalreadyheresorryCantaddthis();

    }

    @Override
    public void tellUiunitNotFount(String unitName) {
        parentUnitesUI.unitNotExistingofyouwanttoaddAdd(unitName);

    }

    @Override
    public void callUIaboutConnection(String error) {
        parentUnitesUI.poorConnection(error);

    }

    @Override
    public void tellModeltoChecking(String uID) {
        unitesModel.checkifUserAdminorNot(uID);
    }

    @Override
    public void happyImAdmin() {
        parentUnitesUI.imAdminShowMeButton();
    }

    @Override
    public void sadImUser() {

        parentUnitesUI.imUserHideButton();

    }


}
