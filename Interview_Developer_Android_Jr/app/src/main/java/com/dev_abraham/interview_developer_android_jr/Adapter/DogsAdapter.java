package com.dev_abraham.interview_developer_android_jr.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dev_abraham.interview_developer_android_jr.Models.ApiDogsResponse;
import com.dev_abraham.interview_developer_android_jr.R;

import java.util.ArrayList;

public class DogsAdapter extends RecyclerView.Adapter<DogsAdapter.ViewHolder> {

    private ArrayList<ApiDogsResponse> dataset;
    private Context context;


    public DogsAdapter (Context context){
        this.context=context;
        dataset= new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_dog,viewGroup,false);

        return new ViewHolder(view);    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {

        ApiDogsResponse d = dataset.get(i);

        Glide.with(context)
                .load(d.getMessage())
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(viewHolder.ivDog);



    }

    @Override
    public int getItemCount() {
        return  dataset.size();

    }
    public void getDog(ApiDogsResponse dog) {
        dataset.add(dog);
        notifyDataSetChanged();
    }

        public class ViewHolder extends RecyclerView.ViewHolder {
            private ImageView ivDog;


            public ViewHolder (View itemView){
                super(itemView);
                ivDog=(ImageView) itemView.findViewById(R.id.ivDog);


            }

        }
}
