package com.dev_abraham.interview_developer_android_jr.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.Toast;

import com.dev_abraham.interview_developer_android_jr.Adapter.CharacterStarWarsAdapter;
import com.dev_abraham.interview_developer_android_jr.Adapter.FilmStarWarAdapter;
import com.dev_abraham.interview_developer_android_jr.Api.ApiStarWar;
import com.dev_abraham.interview_developer_android_jr.Models.ApiStarWarResponse;
import com.dev_abraham.interview_developer_android_jr.Models.CharacterStarWars;
import com.dev_abraham.interview_developer_android_jr.R;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link StarwarFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link StarwarFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class StarwarFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private Retrofit retrofit;
    private static final String TAG = "API";
    private RecyclerView recyclerViewCharacter;
    private RecyclerView recyclerViewFilms;
    private CharacterStarWarsAdapter characterAdapter;
    private FilmStarWarAdapter filmsAdapter;
    private int characterNumber ;
    private boolean load ;
    private ArrayList<CharacterStarWars> listCharacterStarWars;
    private ArrayList<CharacterStarWars> listFilmsStarWars;

    private OnFragmentInteractionListener mListener;

    public StarwarFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment StarwarFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static StarwarFragment newInstance(String param1, String param2) {
        StarwarFragment fragment = new StarwarFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }








    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_starwar, container, false);
        TabHost tab = (TabHost) view.findViewById(R.id.tabHost);
        tab.setup();
        TabHost.TabSpec spec1 = tab.newTabSpec("TAB 1");
        spec1.setIndicator("Personajes");
        spec1.setContent(R.id.layout2);
        tab.addTab(spec1);

        TabHost.TabSpec spec2 = tab.newTabSpec("TAB2");
        spec2.setIndicator("Peliculas");
        spec2.setContent(R.id.layout1);
        tab.addTab(spec2);

        final GestureDetector mGestureDetector = new GestureDetector(view.getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });

        recyclerViewCharacter = (RecyclerView) view.findViewById(R.id.rvCharacter);
        characterAdapter = new CharacterStarWarsAdapter();
        recyclerViewCharacter.setAdapter(characterAdapter);
        listCharacterStarWars = new ArrayList<>();
         recyclerViewCharacter.setHasFixedSize(true);
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerViewCharacter.setLayoutManager(layoutManager);


        recyclerViewCharacter.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if(dy > 0){
                    int visibleItemCount = layoutManager.getChildCount();
                    int totalItemCount = layoutManager.getItemCount();
                    int pastVisibleItems = layoutManager.findFirstVisibleItemPosition();

                    if(load){
                        if((visibleItemCount+pastVisibleItems)>= totalItemCount){
                            Log.i(TAG,"LLegamos al final");
                            load=false;
                            for (int i =0; i<20; i++){
                                characterNumber++;
                                if(characterNumber<=87){
                                    getCharacter(characterNumber);
                                }else{
                                    Toast.makeText(getContext(),"Se mostraron los 87 personajes",Toast.LENGTH_SHORT).show();
                                    return;
                                }
                            }
                        }
                    }
                }
            }
        });

        recyclerViewCharacter.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
                try {
                    View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());

                    if (child != null && mGestureDetector.onTouchEvent(motionEvent)) {

                        int position = recyclerView.getChildAdapterPosition(child);
                        Toast.makeText(getContext(),listCharacterStarWars.get(position).getName(),Toast.LENGTH_SHORT).show();


                    }
                }catch (Exception e){
                    e.printStackTrace();
                }

                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
                Log.i(TAG,"click");
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean b) {

            }
        });


        recyclerViewFilms = (RecyclerView) view.findViewById(R.id.rvMovies);
        filmsAdapter= new FilmStarWarAdapter();
        recyclerViewFilms.setAdapter(filmsAdapter);
        listFilmsStarWars = new ArrayList<>();
        recyclerViewFilms.setHasFixedSize(true);
        final LinearLayoutManager layoutManager2 = new LinearLayoutManager(getActivity());
        recyclerViewFilms.setLayoutManager(layoutManager2);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://swapi.co/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        load=true;
        Toast.makeText(getActivity(),"Cargando",Toast.LENGTH_SHORT).show();

        getFilms();
        for (characterNumber =1; characterNumber<20; characterNumber++){
            getCharacter(characterNumber);
        }

        return view;
    }

    private void getFilms(){
        ApiStarWar service = retrofit.create(ApiStarWar.class);
        Call<ApiStarWarResponse> respuestaCall2 = service.getFilms();
        respuestaCall2.enqueue(new Callback<ApiStarWarResponse>() {
            @Override
            public void onResponse(Call<ApiStarWarResponse> call, Response<ApiStarWarResponse> response) {
                load=true;
                if(response.isSuccessful()){

                    ApiStarWarResponse respuesta = response.body();
                    listFilmsStarWars.addAll(respuesta.getResults());
                    filmsAdapter.addListFilms(respuesta.getResults ());

                }else{
                    Log.e(TAG,"onResponse"+response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ApiStarWarResponse> call, Throwable t) {
                load=true;

                Log.e(TAG,"onFailure"+t.getMessage());
            }
        });
    }

    private void getCharacter(int number){
        ApiStarWar service = retrofit.create(ApiStarWar.class);
        Log.i(TAG,"Numero"+ number);

        Call<CharacterStarWars> respuestaCall = service.getCharacter(number);
        respuestaCall.enqueue(new Callback<CharacterStarWars>() {
            @Override
            public void onResponse(Call<CharacterStarWars> call, Response<CharacterStarWars> responses) {
                load=true;
                if(responses.isSuccessful()){

                    CharacterStarWars response = responses.body();
                    listCharacterStarWars.add(response);

                     characterAdapter.addCharacters(response);

                }else{
                    Log.e(TAG,"onResponse"+responses.errorBody());
                }
            }

            @Override
            public void onFailure(Call<CharacterStarWars> call, Throwable t) {
                load=true;

                Log.e(TAG,"onFailure"+t.getMessage());
            }
        });




    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
