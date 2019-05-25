package com.dev_abraham.crud_sqlite.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.dev_abraham.crud_sqlite.Fragments.UpdateFragment;
import com.dev_abraham.crud_sqlite.Moderls.ModelStudent;
import com.dev_abraham.crud_sqlite.R;

public class UpdateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);


        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        ModelStudent id = (ModelStudent) intent.getSerializableExtra("student");

        Bundle args = new Bundle();
        args.putSerializable("id",id);


        Fragment newFragment = new UpdateFragment();
        newFragment.setArguments(args);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragmentContainerUpdate,newFragment)
                .commit();

    }

}
