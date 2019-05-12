package com.dev_abraham.google_maps_gps_routes.Activities;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.dev_abraham.google_maps_gps_routes.Fragments.MapFragment;
import com.dev_abraham.google_maps_gps_routes.Fragments.WelcomeFragment;
import com.dev_abraham.google_maps_gps_routes.R;

public class MainActivity extends AppCompatActivity {


    Fragment currentFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(savedInstanceState == null ){
            currentFragment = new WelcomeFragment();
            changeFragment(currentFragment);
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){

            case R.id.menuWelcome:
                currentFragment= new WelcomeFragment();
                break;
            case R.id.menuMap:
                currentFragment= new MapFragment();
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
