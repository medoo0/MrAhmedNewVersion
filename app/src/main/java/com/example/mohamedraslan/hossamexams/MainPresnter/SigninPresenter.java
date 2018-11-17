package com.example.mohamedraslan.hossamexams.MainPresnter;

import com.example.mohamedraslan.hossamexams.Contracts.SigninContract;
import com.example.mohamedraslan.hossamexams.MainModle.SigninModel;

/**
 * Created by microprocess on 2018-09-30.
 */

public class SigninPresenter implements SigninContract.presenter {

    SigninContract.view view;
    SigninContract.model model;

    public SigninPresenter(SigninContract.view view){
        this.view = view;
        model = new SigninModel(this);
    }


    @Override
    public void passtocheck(String email, String password) {
      String result =  model.CheckisEmpty(email,password);
      view.checkResult(result);
    }

    @Override
    public void passlogIn(String email, String password) {
        model.logIn(email,password);
    }

    @Override
    public void updatelogInResult(String Result,String email) {
        view.logInResult(Result,email);
    }




}
