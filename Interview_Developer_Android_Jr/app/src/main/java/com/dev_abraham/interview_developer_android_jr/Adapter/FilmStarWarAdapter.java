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

public class FilmStarWarAdapter extends RecyclerView.Adapter<FilmStarWarAdapter.ViewHolder> {


    private ArrayList<CharacterStarWars> dataset;

    public FilmStarWarAdapter(){
        dataset= new ArrayList<>();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_movie_starwars,viewGroup,false);
        return new ViewHolder(view );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        CharacterStarWars c = dataset.get(i);
        viewHolder.tvTitle.setText("Titulo: "+c.getTitle());
        viewHolder.tvChapter.setText("Capitulo: "+c.getEpisode_id()+"");
        viewHolder.tvMessage.setText("Mensaje Inicial: "+c.getOpening_crawl()+"");
        viewHolder.tvDirector.setText("Director: "+c.getDirector());
        viewHolder.tvProducer.setText("Productor: "+c.getProducer());
        viewHolder.tvDate.setText("Fecha Lanzamiento: "+c.getRelease_date());

    }

    @Override
    public int getItemCount() {
        return  dataset.size();
    }

    public void addListFilms(ArrayList<CharacterStarWars> listCharacter){
        dataset.addAll(listCharacter);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvTitle;
        private TextView tvChapter;
        private TextView tvMessage ;
        private TextView tvDirector;
        private TextView tvDate;
        private TextView tvProducer;
        public ViewHolder (View itemView){
            super(itemView);
            tvTitle=(TextView) itemView.findViewById(R.id.tvTitleFilm);
            tvChapter=(TextView) itemView.findViewById(R.id.tvChapterFilm);
            tvMessage=(TextView) itemView.findViewById(R.id.tvMessageFilm);
            tvDirector=(TextView) itemView.findViewById(R.id.tvProductorFilm);
            tvDate=(TextView) itemView.findViewById(R.id.tvDateFilm);
            tvProducer=(TextView) itemView.findViewById(R.id.tvProductorFilm);
        }

    }

}
