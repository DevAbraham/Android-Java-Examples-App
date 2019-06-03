package com.dev_abraham.mvp_login.Models.Interfaces;

public interface LoginView {

    void showProgress();
    void hideProgress();

    void setErrorUser();
    void setErrorPassword();

    void navigateToHome();

}
