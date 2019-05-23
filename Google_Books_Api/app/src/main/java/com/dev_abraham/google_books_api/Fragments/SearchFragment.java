package com.dev_abraham.google_books_api.Fragments;


import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.dev_abraham.google_books_api.Adapters.AdapterBooks;
import com.dev_abraham.google_books_api.Api.ApiGoogleBooks;
import com.dev_abraham.google_books_api.Models.ModelBook;
import com.dev_abraham.google_books_api.Models.ModelItems;
import com.dev_abraham.google_books_api.Models.ModelResponseGoogleBooks;
import com.dev_abraham.google_books_api.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


/**
 * A simple {@link Fragment} subclass.
 */
public class SearchFragment extends Fragment {

    private Retrofit retrofit;
    private static final String TAG = "API";
    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapterBooks;
    private ArrayList<ModelItems> listItems;
    protected View root;
    private EditText etTitle;
    private Button btnSearch;
    private TextView tvEmpty;
    private Activity myActivity;
    private RecyclerView.LayoutManager mLayoutManager;
    private ProgressDialog progress ;

    public SearchFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        root = inflater.inflate(R.layout.fragment_search, container, false);


        listItems = new ArrayList<>();

        tvEmpty =  root.findViewById(R.id.tvBookEmpty);
        recyclerView = root.findViewById(R.id.rvBooks);
        etTitle = root.findViewById(R.id.etSearch);
        mLayoutManager = new LinearLayoutManager(getContext());
        adapterBooks = new AdapterBooks(listItems, getActivity(), R.layout.item_book, new AdapterBooks.OnItemClickListener() {
            @Override
            public void onItemClick(ModelBook book, int position) {
                Toast.makeText(getActivity(),book.getTitle(),Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setAdapter(adapterBooks);


        btnSearch = root.findViewById(R.id.btnSearchBooks);
        btnSearch.setOnClickListener(btnSearchClick);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://www.googleapis.com/books/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        recyclerView.setVisibility(View.GONE);
        tvEmpty.setVisibility(View.VISIBLE);

        progress = new ProgressDialog(getContext());

        progress.setTitle("Cargando");
        progress.setMessage("Buscando libros");
        progress.setCancelable(false);
        return root;
    }


    private void getBooks(String query){
        ApiGoogleBooks service = retrofit.create(ApiGoogleBooks.class);
        Call<ModelResponseGoogleBooks> respuestaCall = service.getBooks(query);
        respuestaCall.enqueue(new Callback<ModelResponseGoogleBooks>() {
            @Override
            public void onResponse(Call<ModelResponseGoogleBooks> call, Response<ModelResponseGoogleBooks> response) {

                if(response.isSuccessful()){
                    ModelResponseGoogleBooks respuesta = response.body();
                    listItems.addAll(respuesta.getItems());
                    adapterBooks.notifyDataSetChanged();
                                        if (listItems.size()>1){
                        tvEmpty.setVisibility(View.GONE);
                        recyclerView.setVisibility(View.VISIBLE);
                    }
                    progress.dismiss();
                }else{
                    Log.e("API","onResponse"+response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ModelResponseGoogleBooks> call, Throwable t) {
                Toast.makeText(getContext(), "Intente mas tarde", Toast.LENGTH_SHORT).show();
                progress.dismiss();
                Log.e("API","onFailure"+t.getMessage());
            }
        });

    }

    private View.OnClickListener btnSearchClick = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String query = etTitle.getText().toString();
            if(query.trim().equals("")){
                Toast.makeText(getContext(), "Ingresa un titulo", Toast.LENGTH_SHORT).show();
                return;
            }

            try {
                progress.show();
                listItems.clear();
                adapterBooks.notifyDataSetChanged();
                getBooks(query);

            } catch (Exception e) {
                progress.dismiss();
                Toast.makeText(getContext(), "Intente mas tarde", Toast.LENGTH_SHORT).show();
            }

        }
    };



}
