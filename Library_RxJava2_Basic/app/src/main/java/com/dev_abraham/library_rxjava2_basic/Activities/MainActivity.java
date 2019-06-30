package com.dev_abraham.library_rxjava2_basic.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.dev_abraham.library_rxjava2_basic.R;
import com.dev_abraham.library_rxjava2_basic.Models.User;

import java.util.concurrent.Callable;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {


    private final CompositeDisposable disposables = new CompositeDisposable();
    private final String TAG = "TAG";
    TextView txtName, txtEmail, txtNumber, txtUserName;
    Button btnStart, btnReiniciar;
    ProgressBar pgLoading;
    CardView cvUser;
    Switch swError;
    Boolean error = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        configView();
    }

    private void configView() {
        txtName = findViewById(R.id.ActivityMainTxtNombre);
        txtEmail = findViewById(R.id.ActivityMainTxtEmail);
        txtNumber = findViewById(R.id.ActivityMainTxtTelefono);
        txtUserName = findViewById(R.id.ActivityMainTxtUserName);

        pgLoading = findViewById(R.id.ActivityMainPb);
        cvUser = findViewById(R.id.ActivityMainCard);
        swError = findViewById(R.id.ActivityMainSwError);
        swError.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (swError.isChecked()) {
                    error = true;
                } else {
                    error = false;
                }
            }
        });

        btnStart = findViewById(R.id.ActivityMainBtnStart);
        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startPeticion();
                onRunSchedulerExampleButtonClicked();
            }
        });

        btnReiniciar = findViewById(R.id.ActivityMainBtnReiniciar);
        btnReiniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cvUser.setVisibility(View.GONE);
            }
        });
    }

    void startPeticion() {
        btnStart.setEnabled(false);
        cvUser.setVisibility(View.GONE);
        pgLoading.setVisibility(View.VISIBLE);
    }

    void finisPeticion() {
        btnStart.setEnabled(true);
        pgLoading.setVisibility(View.GONE);
    }

    void onRunSchedulerExampleButtonClicked() {
        // Example peticion by id
        disposables.add(sampleObservable("idusuario")
                // Run on a background thread
                .subscribeOn(Schedulers.io())
                // Be notified on the main thread
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableObserver<String>() {
                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete()");
                        finisPeticion();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.e(TAG, "onError()", e);
                        finisPeticion();
                        Toast.makeText(MainActivity.this, "ERROR", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(String string) {
                        Log.d(TAG, "onNext(" + string + ")");
                        //Simulation data access
                        getUserById(string);
                    }
                }));
    }

    static Observable<String> sampleObservable(final String idUser) {
        return Observable.defer(new Callable<ObservableSource<? extends String>>() {
            @Override
            public ObservableSource<? extends String> call() throws Exception {
                // Do some long running operation
                return Observable.just(idUser);
            }
        });
    }

    private void getUserById(String id) {
        try {
            Thread.sleep(2000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        User user = new User();
        user.setNombre("Abraham");
        user.setEmail("Ruaabraham@gmail.com");
        user.setNumero("992222222");
        user.setUsername("Lid Abraham");
        if (!this.error) {
            this.configCard(user);
            Toast.makeText(MainActivity.this, "Tarea completada", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(MainActivity.this, "Error en la peticion", Toast.LENGTH_SHORT).show();
        }
    }

    private void configCard(User user) {
        txtName.setText(user.getNombre());
        txtEmail.setText(user.getEmail());
        txtNumber.setText(user.getNumero());
        txtUserName.setText(user.getUsername());
        cvUser.setVisibility(View.VISIBLE);
    }

}
