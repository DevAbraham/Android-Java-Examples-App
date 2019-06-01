package com.dev_abraham.take_photo_and_display.Activities;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.dev_abraham.take_photo_and_display.Fragments.PhotoFragment;
import com.dev_abraham.take_photo_and_display.R;

public class MainActivity extends AppCompatActivity {

    Fragment currentFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        currentFragment = new PhotoFragment();

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frame,currentFragment)
                .commit();


    }



}
