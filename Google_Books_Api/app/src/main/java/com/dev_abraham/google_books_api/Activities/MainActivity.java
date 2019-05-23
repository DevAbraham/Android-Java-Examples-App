package com.dev_abraham.google_books_api.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.dev_abraham.google_books_api.R;
import com.dev_abraham.google_books_api.Fragments.SearchFragment;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_Frame,new SearchFragment())
                .commit();

    }
}
