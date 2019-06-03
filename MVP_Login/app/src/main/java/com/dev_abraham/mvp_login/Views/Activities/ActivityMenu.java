package com.dev_abraham.mvp_login.Views.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.dev_abraham.mvp_login.Models.Interfaces.MenuListener;
import com.dev_abraham.mvp_login.Models.Interfaces.MenuPresenter;
import com.dev_abraham.mvp_login.Models.Interfaces.MenuView;
import com.dev_abraham.mvp_login.Presenters.MenuPresenterImpl;
import com.dev_abraham.mvp_login.R;

public class ActivityMenu extends AppCompatActivity  implements MenuView {


    private Button btnExit;
    private MenuPresenter menuPresenter;
    private ProgressBar pbExit ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        pbExit = findViewById(R.id.pbExit);

        btnExit = findViewById(R.id.btnExit);
        btnExit.setOnClickListener(btnExitClick);

        menuPresenter =  new MenuPresenterImpl(this);

    }

    private View.OnClickListener btnExitClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
           menuPresenter.askExit();
        }
    };


    @Override
    public void showProgress() {
        pbExit.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pbExit.setVisibility(View.GONE);
    }

    @Override
    public void navigateToLogin() {
        startActivity(new Intent (ActivityMenu.this,ActivityLogin.class));
        this.finish();
    }
}
