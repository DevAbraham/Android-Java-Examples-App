package com.dev_abraham.interview_developer_android_jr.Adapter;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dev_abraham.interview_developer_android_jr.Models.CharacterStarWars;
import com.dev_abraham.interview_developer_android_jr.R;

import java.util.ArrayList;

public class CharacterStarWarsAdapter extends RecyclerView.Adapter<CharacterStarWarsAdapter.ViewHolder>{



    private ArrayList<CharacterStarWars> dataset;

    public CharacterStarWarsAdapter(){
        dataset= new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_character_starwars,viewGroup,false);
        return new ViewHolder(view );
    }

    @Override
    public void onBindViewHolder(@NonNull CharacterStarWarsAdapter.ViewHolder viewHolder, int i) {
        CharacterStarWars c = dataset.get(i);
        viewHolder.tvName.setText("Nombre: "+c.getName());
            viewHolder.tvHeight.setText("Altura: "+c.getHeight()+"");
        viewHolder.tvMass.setText("Peso: "+c.getMass()+"");
        viewHolder.tvGender.setText("Genero: "+c.getGender());

    }

    @Override
    public int getItemCount() {
        return  dataset.size();
    }

    public void addCharacters(CharacterStarWars character){
        dataset.add(character);
        notifyDataSetChanged();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvName;
        private TextView tvMass;
        private TextView tvHeight ;
        private TextView tvGender;

        public ViewHolder (View itemView){
            super(itemView);
            tvName=(TextView) itemView.findViewById(R.id.tvNameCharacter);
            tvMass=(TextView) itemView.findViewById(R.id.tvMassCharacter);
            tvHeight=(TextView) itemView.findViewById(R.id.tvHeightCharacter);
            tvGender=(TextView) itemView.findViewById(R.id.tvGenderCharacter);


        }

    }

}
