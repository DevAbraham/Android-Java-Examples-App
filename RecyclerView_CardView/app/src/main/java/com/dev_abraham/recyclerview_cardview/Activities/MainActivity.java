package com.dev_abraham.recyclerview_cardview.Activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.dev_abraham.recyclerview_cardview.Adapters.AdapterRV;
import com.dev_abraham.recyclerview_cardview.R;
import com.dev_abraham.recyclerview_cardview.Models.Videogame;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List <Videogame> videogames;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Llenar lista
        videogames = getAllVideogames();
        mRecyclerView = (RecyclerView) findViewById(R.id.rvMain);
        mLayoutManager = new LinearLayoutManager(this);
        mAdapter = new AdapterRV(videogames,this, R.layout.recycler_view_item,new AdapterRV.OnItemClickListener() {
            @Override
            public void onItemClick(Videogame v, int position) {

                Toast.makeText(MainActivity.this,v.getName(),Toast.LENGTH_SHORT).show();
                v.addQuantity();
                mAdapter.notifyItemChanged(position);
            }
        });

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.add_item:
                this.addVideogame(0);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }


    }

    private void addVideogame(int position) {
        videogames.add(position, new Videogame("Videojuego",R.drawable.game,R.mipmap.ic_game,0));
        mAdapter.notifyItemInserted(position);
        mLayoutManager.scrollToPosition(position);
    }

    private void deleteVideogame(int position){
        videogames.remove(position);
        mAdapter.notifyItemRemoved(position);

    }


    private List<Videogame> getAllVideogames(){
        return new ArrayList<Videogame>(){{
            add( new Videogame("Darksouls",R.drawable.dark,R.mipmap.ic_dark,0));
            add( new Videogame("Gears Of War",R.drawable.gears,R.mipmap.ic_gears,0));
            add( new Videogame("Halo",R.drawable.halo,R.mipmap.ic_halo,0));
            add( new Videogame("Smite",R.drawable.smite,R.mipmap.ic_smite,0));
            }};

    }

}
