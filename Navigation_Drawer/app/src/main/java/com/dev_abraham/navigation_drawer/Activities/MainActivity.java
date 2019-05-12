package com.dev_abraham.navigation_drawer.Activities;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.dev_abraham.navigation_drawer.Fragments.AlertsFragment;
import com.dev_abraham.navigation_drawer.Fragments.EmailFragment;
import com.dev_abraham.navigation_drawer.Fragments.InfoFragment;
import com.dev_abraham.navigation_drawer.R;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout ;
    private NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setToolbar();
        drawerLayout =(DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView =(NavigationView) findViewById(R.id.nav_view);
        setFragmentDefault();

        drawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {
            @Override
            public void onDrawerSlide(@NonNull View view, float v) {

            }

            @Override
            public void onDrawerOpened(@NonNull View view) {
                Toast.makeText(MainActivity.this,"Open",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerClosed(@NonNull View view) {
                Toast.makeText(MainActivity.this,"Close",Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerStateChanged(int i) {

            }
        });

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                boolean fragmentTransaction = false;
                Fragment fragment = null;
                switch (menuItem.getItemId()){
                    case R.id.menu_alert:
                        fragment= new AlertsFragment();
                        fragmentTransaction = true;
                        break;
                    case R.id.menu_email:
                        fragment= new EmailFragment();
                        fragmentTransaction = true;
                        break;
                    case R.id.menu_info:
                        fragment= new InfoFragment();
                        fragmentTransaction = true;
                        break;
                    case R.id.menu_opc1:
                        Toast.makeText(MainActivity.this,"Click Opc 1",Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.menu_opc2:
                        Toast.makeText(MainActivity.this,"Click Opc 2",Toast.LENGTH_SHORT).show();
                        break;
                }
                if(fragmentTransaction){
                    changeFragment(fragment,menuItem);
                    drawerLayout.closeDrawers();
                }
                return true;
            }
        });

    }

    private void setToolbar(){
        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_home);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }
    private void setFragmentDefault(){
        MenuItem menuItem = navigationView.getMenu().getItem(0);
        changeFragment(new EmailFragment(),menuItem);
    }

    private void changeFragment(Fragment fragment,MenuItem menuItem){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content_Frame,fragment)
                .commit();
        menuItem.setChecked(true);
        getSupportActionBar().setTitle(menuItem.getTitle());
    }

}
