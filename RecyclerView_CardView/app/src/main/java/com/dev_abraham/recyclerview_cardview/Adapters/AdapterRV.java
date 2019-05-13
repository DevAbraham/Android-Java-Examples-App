package com.dev_abraham.recyclerview_cardview.Adapters;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.dev_abraham.recyclerview_cardview.Models.Videogame;
import com.dev_abraham.recyclerview_cardview.R;

import java.util.List;

public class AdapterRV extends RecyclerView.Adapter<AdapterRV.ViewHolder> {

    public  List<Videogame> videogames;
    private int layout;
    private OnItemClickListener itemClickListener;
    public  Activity myActivity;

    public AdapterRV(List<Videogame> videogames,Activity activity,int layout,OnItemClickListener listener){
        this.videogames=videogames;
        this.layout=layout;
        this.myActivity=activity;
        this.itemClickListener=listener;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View v = LayoutInflater.from(parent.getContext()).inflate(layout,parent,false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        viewHolder.bind(videogames.get(i), itemClickListener);
    }

    @Override
    public int getItemCount() {
        return videogames.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder implements  View.OnCreateContextMenuListener,MenuItem.OnMenuItemClickListener{

        public TextView tvTitle;
        public TextView tvQuantity;
        public ImageView ivPoster;


        public ViewHolder(View v) {
            super(v);
            this.tvTitle = (TextView) v.findViewById(R.id.tvTitle);
            this.tvQuantity = (TextView) v.findViewById(R.id.tvQuantity);
            this.ivPoster = (ImageView) v.findViewById(R.id.ivPoster);
            v.setOnCreateContextMenuListener(this);
        }

        public void bind(final Videogame videogame,final  OnItemClickListener listener){

            this.tvTitle.setText(videogame.getName());
            this.ivPoster.setImageResource(videogame.getPoster());
            this.tvQuantity.setText(videogame.getQuantity()+"");
            this.ivPoster.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(videogame,getAdapterPosition());
                }
            });
        }

        @Override
        public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
            Videogame selectedVideogame = videogames.get(this.getAdapterPosition());
            menu.setHeaderTitle(selectedVideogame.getName());
            menu.setHeaderIcon(selectedVideogame.getIcon());
            MenuInflater inflater = myActivity.getMenuInflater();
            inflater.inflate(R.menu.dialog,menu);
            for(int i =0; i<menu.size();i++){
                menu.getItem(i).setOnMenuItemClickListener(this);
            }

        }


        @Override
        public boolean onMenuItemClick(MenuItem item) {

        switch (item.getItemId()){
            case R.id.delete:
                videogames.remove(getAdapterPosition());
                notifyItemRemoved(getAdapterPosition());
            return true;
            case R.id.reset:
                videogames.get(getAdapterPosition()).resetQuatity();
                notifyItemChanged(getAdapterPosition());
                return true;
            default:
                return false;

        }

        }
    }


    public interface OnItemClickListener {
        void onItemClick(Videogame v,int position);
    }


}
