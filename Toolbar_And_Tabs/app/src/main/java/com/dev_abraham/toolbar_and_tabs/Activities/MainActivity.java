package com.dev_abraham.toolbar_and_tabs.Activities;

import android.net.Uri;
import android.support.design.widget.TabLayout;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.dev_abraham.toolbar_and_tabs.Adapters.PagerAdapter;
import com.dev_abraham.toolbar_and_tabs.Fragments.FirstFragment;
import com.dev_abraham.toolbar_and_tabs.Fragments.SecondFragment;
import com.dev_abraham.toolbar_and_tabs.Fragments.ThirdFragment;
import com.dev_abraham.toolbar_and_tabs.R;

public class MainActivity extends AppCompatActivity
        implements FirstFragment.OnFragmentInteractionListener,
        SecondFragment.OnFragmentInteractionListener,
        ThirdFragment.OnFragmentInteractionListener       {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar myToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(myToolbar);

        TabLayout myTabLayout = (TabLayout) findViewById(R.id.tabLayout);
        myTabLayout.addTab(myTabLayout.newTab().setText("Tab 1"));
        myTabLayout.addTab(myTabLayout.newTab().setText("Tab 2"));
        myTabLayout.addTab(myTabLayout.newTab().setText("Tab 3"));
        myTabLayout.setTabGravity(TabLayout.GRAVITY_FILL);


        final ViewPager myViewPager = (ViewPager) findViewById(R.id.viewPager);
        PagerAdapter pagerAdapter = new PagerAdapter(getSupportFragmentManager(),myTabLayout.getTabCount());
        myViewPager.setAdapter(pagerAdapter);
        myViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(myTabLayout));

        myTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position =  tab.getPosition();
                myViewPager.setCurrentItem(position);
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.itemExit:
                Toast.makeText(this,"Click Exit",Toast.LENGTH_SHORT).show();
               return true;
            default:
               return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
