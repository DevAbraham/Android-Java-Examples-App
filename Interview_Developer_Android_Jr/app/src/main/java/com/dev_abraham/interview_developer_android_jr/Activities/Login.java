package com.dev_abraham.interview_developer_android_jr.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.dev_abraham.interview_developer_android_jr.R;

public class Login extends AppCompatActivity {

    private EditText txtPassword,txtUser;
    private Boolean logged;
    private  SharedPreferences.Editor editor ;
    private  SharedPreferences pref ;
    public static final String MY_PREFS_NAME = "profile";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        pref = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
        logged = pref.getBoolean("logged",false);
        txtUser=(EditText) findViewById(R.id.etUser);
        txtPassword=(EditText) findViewById(R.id.etUser);
        if(logged){
            logged();
        }


    }


    public void Acceder(View v){
        String usuarioname=txtUser.getText().toString();
        if(usuarioname.equals("")){
            Toast.makeText(this,"Ingrese un usuario", Toast.LENGTH_SHORT).show();
        }else {
            editor = pref.edit();
            editor.putBoolean("logged", true);
            editor.commit();
            logged();
        }
    }

    public void logged (){
        Intent intent = new Intent(Login.this, MenuMain.class);
        startActivity(intent);
        finish();
    }

}
