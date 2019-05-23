package com.dev_abraham.google_books_api.Adapters;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.dev_abraham.google_books_api.Fragments.SearchFragment;
import com.dev_abraham.google_books_api.Models.ModelBook;
import com.dev_abraham.google_books_api.Models.ModelItems;
import com.dev_abraham.google_books_api.R;

import java.util.List;

public class AdapterBooks extends RecyclerView.Adapter<AdapterBooks.ViewHolder>{

    public  List<ModelItems> books;
    private int layout;
    private OnItemClickListener itemClickListener;
    public Activity myActivity;

    public AdapterBooks(List<ModelItems> books, Activity activity, int layout, OnItemClickListener listener){
        this.books=books;
        this.layout=layout;
        this.myActivity=activity;
        this.itemClickListener=listener;

        //Request option for Glide
      //  options = new RequestOptions().centerCrop().placeholder(R.drawable.loading_shape).error(R.drawable.loading_shape);
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

        viewHolder.bind(books.get(i).getVolumeInfo(), itemClickListener,myActivity);


    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView ivThumbnail ;
        TextView tvTitle , tvCategory , tvAuthor,tvDate,tvEditorial,tvISBN;
        LinearLayout container ;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            ivThumbnail = itemView.findViewById(R.id.thumbnail);
            tvTitle = itemView.findViewById(R.id.title);
            tvAuthor = itemView.findViewById(R.id.author);
            tvCategory = itemView.findViewById(R.id.category);
            tvDate = itemView.findViewById(R.id.date);
            tvEditorial = itemView.findViewById(R.id.editorial);
            tvISBN = itemView.findViewById(R.id.isbn);
            container = itemView.findViewById(R.id.container);
        }

        public void bind(final ModelBook book,final  OnItemClickListener listener,final Activity myActivity) {

            String title = "Titulo: "+book.getTitle();
            tvTitle.setText(title);
            String [] authors = book.getAuthors();
            String author="Autor: ";
            if(authors   != null&&authors.length >= 1){
                author+=authors[0];
            }
            tvAuthor.setText(author );

            String [] categorys = book.getCategories();
            String category = "Categoria: ";
            if(categorys != null && categorys.length >= 1){
                category += categorys[0];
            }
            tvCategory.setText(category);
            tvDate.setText("Fecha De Publicacion: "+book.getPublishedDate());
            tvEditorial.setText("Editorial: "+book.getPublisher());
            String isbn = book.getIndustryIdentifiers()[0].getIdentifier();
            tvISBN.setText("ISBN: "+isbn);
            String url = book.getImageLinks().getThumbnail().replace("http","https");
            Glide.with(myActivity)
                    .load(url)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(ivThumbnail);
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(book,getAdapterPosition());
                }
            });
        }

    }


    public interface OnItemClickListener {
        void onItemClick(ModelBook book,int position);
    }
}
