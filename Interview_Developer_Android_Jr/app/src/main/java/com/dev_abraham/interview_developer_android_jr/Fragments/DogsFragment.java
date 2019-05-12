package com.dev_abraham.interview_developer_android_jr.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dev_abraham.interview_developer_android_jr.Adapter.DogsAdapter;
import com.dev_abraham.interview_developer_android_jr.Api.ApiDogs;
import com.dev_abraham.interview_developer_android_jr.Models.ApiDogsResponse;
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
 * {@link DogsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DogsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DogsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private Retrofit retrofit;
    private static final String TAG = "API";
    private RecyclerView recyclerView;
    private DogsAdapter dogsAdapter;
    private ArrayList<ApiDogsResponse> listDogs;
    protected View mView;

    private OnFragmentInteractionListener mListener;

    public DogsFragment() {
        // Required empty public constructor
    }

    // TODO: Rename and change types and number of parameters
    public static DogsFragment newInstance(String param1, String param2) {
        DogsFragment fragment = new DogsFragment();
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

    private void obtenerDatos(){
        ApiDogs service = retrofit.create(ApiDogs.class);
        Call<ApiDogsResponse> respuestaCall = service.getDog();
        respuestaCall.enqueue(new Callback<ApiDogsResponse>() {
            @Override
            public void onResponse(Call<ApiDogsResponse> call, Response<ApiDogsResponse> response) {

                if(response.isSuccessful()){
                    ApiDogsResponse respuesta = response.body();
                    listDogs.add(respuesta);
                    dogsAdapter.getDog(respuesta);
                }else{
                    Log.e(TAG,"onResponse"+response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ApiDogsResponse> call, Throwable t) {

                Log.e(TAG,"onFailure"+t.getMessage());
            }
        });

    }



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dogs, container, false);
        this.mView = view;
        recyclerView = (RecyclerView) mView.findViewById(R.id.rvDogs);
        dogsAdapter = new DogsAdapter(getActivity());
        recyclerView.setAdapter(dogsAdapter);
        listDogs = new ArrayList<>();

        recyclerView.setHasFixedSize(true);
        final GridLayoutManager layoutManager = new GridLayoutManager(getActivity(),2);
        recyclerView.setLayoutManager(layoutManager);

        retrofit = new Retrofit.Builder()
                .baseUrl("https://dog.ceo/api/breeds/image/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        for (int i=0;i<10;i++){
            obtenerDatos();
        }
        return view;
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
