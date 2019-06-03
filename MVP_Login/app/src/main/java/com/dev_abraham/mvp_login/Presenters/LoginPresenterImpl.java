package com.dev_abraham.mvp_login.Presenters;

import com.dev_abraham.mvp_login.Models.Interactors.LoginInteractorImpl;
import com.dev_abraham.mvp_login.Models.Interfaces.LoginInteractor;
import com.dev_abraham.mvp_login.Models.Interfaces.LoginListener;
import com.dev_abraham.mvp_login.Models.Interfaces.LoginPresenter;
import com.dev_abraham.mvp_login.Models.Interfaces.LoginView;

public class LoginPresenterImpl implements LoginPresenter , LoginListener {

    private LoginView view;
    private LoginInteractor loginInteractor;


    public LoginPresenterImpl (LoginView view){

        this.view=view;
        loginInteractor= new LoginInteractorImpl();

    }

    @Override
    public void checkUser(String user, String password) {
        if(view!=null){
            view.showProgress();
        }
        loginInteractor.checkUser(user,password,this);

    }

    @Override
    public void usernameError() {
        if(view!=null){
            view.setErrorUser();
            view.hideProgress();
        }

    }

    @Override
    public void passwordError() {
        if(view!=null) {
            view.setErrorPassword();
            view.hideProgress();
        }
    }

    @Override
    public void sucessLogin() {
        if(view!=null) {

            view.hideProgress();
            view.navigateToHome();
        }
    }
}
