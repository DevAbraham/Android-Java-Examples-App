package com.dev_abraham.mvp_login.Models.Interactors;

import com.dev_abraham.mvp_login.Models.Interfaces.LoginInteractor;
import com.dev_abraham.mvp_login.Models.Interfaces.LoginListener;

import android.os.Handler;

public class LoginInteractorImpl implements LoginInteractor {

    @Override
    public void checkUser(final String user, final String password, final LoginListener listener) {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(!user.equals("")&&!password.equals("")) {

                    listener.sucessLogin();

                }else {
                if(user.equals("")) {
                    listener.usernameError();
                }
                if (password.equals("")) {
                    listener.passwordError();
                }

                }

            }
        },2000);



    }
}
