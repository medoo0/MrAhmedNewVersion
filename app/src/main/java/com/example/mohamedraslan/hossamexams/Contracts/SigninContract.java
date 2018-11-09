package com.example.mohamedraslan.hossamexams.Contracts;

/**
 * Created by microprocess on 2018-09-30.
 */

public interface SigninContract {

    interface model {
        String CheckisEmpty(String email, String password);
        void logIn(String email, String password);
    }
    interface presenter{

        void passtocheck(String et_email, String et_password);
        void passlogIn(String email, String password);
        void updatelogInResult(String Result);
    }
    interface view{
        void checkResult(String Result);
        void logInResult(String Result);
    }
}
