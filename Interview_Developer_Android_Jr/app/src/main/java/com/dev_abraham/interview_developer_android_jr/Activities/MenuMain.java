package com.dev_abraham.interview_developer_android_jr.Activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.dev_abraham.interview_developer_android_jr.Fragments.DogsFragment;
import com.dev_abraham.interview_developer_android_jr.Fragments.EventsFragment;
import com.dev_abraham.interview_developer_android_jr.Fragments.MainFragment;

import com.dev_abraham.interview_developer_android_jr.Fragments.ProfileFragment;
import com.dev_abraham.interview_developer_android_jr.Fragments.StarwarFragment;
import com.dev_abraham.interview_developer_android_jr.Fragments.TriviaFragment;
import com.dev_abraham.interview_developer_android_jr.R;

public class MenuMain extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener, DogsFragment.OnFragmentInteractionListener , EventsFragment.OnFragmentInteractionListener,
        MainFragment.OnFragmentInteractionListener , ProfileFragment.OnFragmentInteractionListener , StarwarFragment.OnFragmentInteractionListener ,
        TriviaFragment.OnFragmentInteractionListener {


    private  SharedPreferences.Editor editor ;
    private  SharedPreferences pref ;
    public static final String MY_PREFS_NAME = "profile";

    @Override
    public void onFragmentInteraction(Uri uri){
        //you can leave it empty
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        setFragment(0);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.itemMain) {
            setFragment(0);

        } else if (id == R.id.itemProfile) {
            setFragment(1);
        } else if (id == R.id.itemTrivia) {
            setFragment(2);
        }else if (id == R.id.itemEvents) {
            setFragment(3);
        }else if (id == R.id.itemStarWars) {
            setFragment(4);
        }else if (id == R.id.itemDogs) {
            setFragment(5);
       } else if (id == R.id.item_logout) {
            pref = getSharedPreferences(MY_PREFS_NAME, MODE_PRIVATE);
            editor = pref.edit();
            editor.putBoolean("logged", false);
            editor.commit();
            Intent intent = new Intent(MenuMain.this, Login.class);
            startActivity(intent);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void setFragment(int position) {
        FragmentManager fragmentManager;
        FragmentTransaction fragmentTransaction;
        switch (position) {
            case 0:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                MainFragment mainFragment = new MainFragment();
                fragmentTransaction.replace(R.id.conteiner,mainFragment );
                fragmentTransaction.commit();
                break;
            case 1:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                ProfileFragment profileFragment = new ProfileFragment();
                fragmentTransaction.replace(R.id.conteiner,profileFragment );
                fragmentTransaction.commit();
                break;
            case 2:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                TriviaFragment triviaFragment = new TriviaFragment();
                fragmentTransaction.replace(R.id.conteiner,triviaFragment );
                fragmentTransaction.commit();
                break;
            case 3:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                EventsFragment eventsFragment = new EventsFragment();
                fragmentTransaction.replace(R.id.conteiner,eventsFragment );
                fragmentTransaction.commit();
                break;
            case 4:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                StarwarFragment starwarsFragment = new StarwarFragment();
                fragmentTransaction.replace(R.id.conteiner,starwarsFragment );
                fragmentTransaction.commit();
                break;
            case 5:
                fragmentManager = getSupportFragmentManager();
                fragmentTransaction = fragmentManager.beginTransaction();
                DogsFragment dogsFragment = new DogsFragment();
                fragmentTransaction.replace(R.id.conteiner,dogsFragment );
                fragmentTransaction.commit();
                break;

        }
    }

}
