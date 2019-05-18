package com.dev_abraham.crud_firebase.Activities;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.dev_abraham.crud_firebase.Fragments.CreateFragment;
import com.dev_abraham.crud_firebase.R;
import com.dev_abraham.crud_firebase.Fragments.ReadFragment;

public class MainActivity extends AppCompatActivity {


    Fragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        if(savedInstanceState == null ){
            currentFragment = new ReadFragment();
            changeFragment(currentFragment);
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.menuCreate:
                currentFragment= new CreateFragment();
                break;
            case R.id.menuRead:
                currentFragment= new ReadFragment();
                break;
        }
        changeFragment(currentFragment);
        return super.onOptionsItemSelected(item);
    }

    private void changeFragment(Fragment fragment){
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.fragmentContainer,fragment)
                    .commit();

    }



}
