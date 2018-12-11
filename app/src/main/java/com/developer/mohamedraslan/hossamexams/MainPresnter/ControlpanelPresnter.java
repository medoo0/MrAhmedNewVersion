package com.developer.mohamedraslan.hossamexams.MainPresnter;


import com.developer.mohamedraslan.hossamexams.Contracts.ControlPanelContract;
import com.developer.mohamedraslan.hossamexams.JsonModel.FullRegisterForm;
import com.developer.mohamedraslan.hossamexams.MainModle.ControlPanelModel;

public class ControlpanelPresnter implements ControlPanelContract.ControlPresnterUI {

    private ControlPanelContract.ControlUI view;
    private ControlPanelContract.ControlModelUI controlPanelModel;

    public ControlpanelPresnter(ControlPanelContract.ControlUI view) {
        this.view = view;
        controlPanelModel = new ControlPanelModel(this);
    }

    @Override
    public void updateUitoViews() {
        view.initializeViews();
    }




    @Override
    public void CheckifUserBanned(String Uid) {
        controlPanelModel.CheckifUserBanned(Uid);
    }

    @Override
    public void CheckifUserBannedResult(String Result) {
        view.CheckifUserBannedResult(Result);
    }

    @Override
    public void CheckifAdmin(String uid) {


        controlPanelModel.CheckifAdmin(uid);
    }

    @Override
    public void HeIsAdmin() {
        view.AdminTools();
    }

    @Override
    public void HeIsUser(FullRegisterForm fullRegisterForm) {

        view.UserTools(fullRegisterForm);
    }

    @Override
    public void getuserName(String uid) {
        controlPanelModel.getuserName(uid);
    }

    @Override
    public void SetUsername(String nameStudent) {
        view.SetUsername(nameStudent);
    }

    @Override
    public void tellModeltoCheckIfYearExisitOrNot(String parent, String childYear) {

        controlPanelModel.checkIFYearExisitOrNot(parent,childYear);
    }

    @Override
    public void tellUI_yearisAlreadyExisit(String parent, String childYear) {
        view.yearAleradyExisitNoAdding(parent,childYear);
    }

    @Override
    public void tellUI_yearNotExisit(String parent, String childYear) {

        view.yearNotExistAddThisYearNow(parent,childYear);
    }

    @Override
    public void probYearsP() {
        view.yearP();
    }

    @Override
    public void tellModeltoAddYearQuickly(String parent, String childYear) {
        controlPanelModel.addYearTodatabase(parent,childYear);

    }

    @Override
    public void tellUIYearDoneAdded() {
        view.DoneYearAddesSussessfully();

    }

    @Override
    public void tellUIYearNotAdded() {

        view.NotDoneThereAreProblemWithConnection();
    }

    @Override
    public void tellModeltoDeleteUser(String uID) {
        controlPanelModel.removeUserFromAuth(uID);
    }


    @Override
    public void userDeleted() {
        view.userAreDeletedSussess();
    }

    @Override
    public void problemUserNotDeleted() {
        view.problemwithDeleteUser();
    }









    @Override
    public void tellModeltostoreToken(String token) {

        controlPanelModel.storingTokentoDatabase(token);

    }

    @Override
    public void tellUitokenStored() {
        view.tokenSussessfullystored();


    }

    @Override
    public void tellUitokenDosentStore() {
        view.problemwithtoken();
    }

    @Override
    public void tellmodelAretokenExisitorNot() {

        controlPanelModel.checkifTokenExisitorNot();
    }

    @Override
    public void tellUItokenisExisit() {
        view.tokenisExisitinFirebase();

    }

    @Override
    public void tellUItokennotExisit() {

        view.tokennotExisitinFirebase();

    }
}
