package com.dev_abraham.mvp_login.Presenters;

import com.dev_abraham.mvp_login.Models.Interactors.MenuInteractorImpl;
import com.dev_abraham.mvp_login.Models.Interfaces.MenuInteractor;
import com.dev_abraham.mvp_login.Models.Interfaces.MenuListener;
import com.dev_abraham.mvp_login.Models.Interfaces.MenuPresenter;
import com.dev_abraham.mvp_login.Models.Interfaces.MenuView;

public class MenuPresenterImpl implements MenuPresenter, MenuListener {

    private MenuView view;
    private MenuInteractor menuInteractor;

    public MenuPresenterImpl(MenuView view) {
        this.view=view;
        menuInteractor =  new MenuInteractorImpl();

    }

    @Override
    public void sucessExit() {
        view.hideProgress();
        view.navigateToLogin();
    }

    @Override
    public void askExit() {
        view.showProgress();
        menuInteractor.exitSession(this);
    }
}
