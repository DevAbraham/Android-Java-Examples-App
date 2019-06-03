package com.dev_abraham.mvp_login.Models.Interfaces;

public interface LoginInteractor {

    void checkUser(String user,String password,LoginListener listener);

}
