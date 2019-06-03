package com.dev_abraham.mvp_login.Models.Interactors;

import android.os.Handler;

import com.dev_abraham.mvp_login.Models.Interfaces.MenuInteractor;
import com.dev_abraham.mvp_login.Models.Interfaces.MenuListener;

public class MenuInteractorImpl implements MenuInteractor {


    @Override
    public void exitSession(final MenuListener listener) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                    listener.sucessExit();
            }
        },2000);

    }
}
