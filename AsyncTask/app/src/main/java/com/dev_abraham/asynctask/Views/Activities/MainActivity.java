package com.dev_abraham.asynctask.Views.Activities;

import android.os.AsyncTask;
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

import com.dev_abraham.asynctask.Models.User;
import com.dev_abraham.asynctask.R;

public class MainActivity extends AppCompatActivity {
    
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
                new getUserById().execute("LID_Abraham");
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

    private User getUserById(String id) {
        if (!this.error) {
            //SIMULATED DATA FROM ID
            User user = new User();
            user.setNombre("Abraham");
            user.setEmail("Ruaabraham@gmail.com");
            user.setNumero("992222222");
            user.setUsername(id);
            return user;
        } else {
            //ERROR SIMULATED
            return null;
        }
    }

    private void configCard(User user) {
        txtName.setText(user.getNombre());
        txtEmail.setText(user.getEmail());
        txtNumber.setText(user.getNumero());
        txtUserName.setText(user.getUsername());
        cvUser.setVisibility(View.VISIBLE);
    }


    private class getUserById extends AsyncTask<String, Integer, User> {

        String TAG = getClass().getSimpleName();

        protected void onPreExecute() {
            super.onPreExecute();
            Log.d(TAG + " PreExecute", "On pre Exceute......");
        }

        protected User doInBackground(String... strings) {
            Log.d(TAG + " DoINBackGround", "On doInBackground...");
            Log.d(TAG + " DoINBackGround", strings[0]);


            for (int i = 0; i < 10; i++) {
                Integer in = new Integer(i);
                try {
                    Thread.sleep(100);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                publishProgress(i * 10);
            }
            User user = getUserById(strings[0]);
            return user;
        }

        protected void onProgressUpdate(Integer... a) {
            super.onProgressUpdate(a);
            Log.d(TAG + " onProgressUpdate", "You are in progress update ... " + a[0]);
        }

        protected void onPostExecute(User result) {
            super.onPostExecute(result);
            Log.d(TAG + " onPostExecute", "" + result);
            if (result != null) {
                Toast.makeText(MainActivity.this, "Tarea completada", Toast.LENGTH_SHORT).show();
                configCard(result);
                finisPeticion();
            } else {
                Toast.makeText(MainActivity.this, "Error en la peticion", Toast.LENGTH_SHORT).show();
                finisPeticion();
            }

        }

    }

}
