package com.dev_abraham.mvp_login.Views.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dev_abraham.mvp_login.Models.Interfaces.LoginPresenter;
import com.dev_abraham.mvp_login.Models.Interfaces.LoginView;
import com.dev_abraham.mvp_login.Presenters.LoginPresenterImpl;
import com.dev_abraham.mvp_login.R;

public class ActivityLogin extends AppCompatActivity implements LoginView {


    private EditText etUser,etPassword;
    private ProgressBar pbLogin;
    private Button btnLogin;
    private LoginPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etUser=findViewById(R.id.etUser);
        etPassword=findViewById(R.id.etPassword);
        pbLogin=findViewById(R.id.pbLogin);
        btnLogin=findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(btnLoginClick);

        loginPresenter = new LoginPresenterImpl(this);

    }

    @Override
    public void showProgress() {
        pbLogin.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pbLogin.setVisibility(View.GONE);
    }

    @Override
    public void setErrorUser() {
        etUser.setError("Campo Obligatorio");
    }

    @Override
    public void setErrorPassword() {
        etPassword.setError("Campo Obligatorio");
    }

    @Override
    public void navigateToHome() {
        startActivity(new Intent(ActivityLogin.this,ActivityMenu.class));
        this.finish();
    }

    private View.OnClickListener btnLoginClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            loginPresenter.checkUser(etUser.getText().toString(),etPassword.getText().toString());
        }
    };



}
