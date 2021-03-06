package com.dev_abraham.interview_developer_android_jr.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.dev_abraham.interview_developer_android_jr.Api.ApiNumbers;
import com.dev_abraham.interview_developer_android_jr.Models.ApiNumberResponse;
import com.dev_abraham.interview_developer_android_jr.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link TriviaFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link TriviaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class TriviaFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private TextView tvInformation;
    private Button btnTrivia;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;
    private Retrofit retrofit;
    private static final String TAG = "API";

    public TriviaFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment TriviaFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static TriviaFragment newInstance(String param1, String param2) {
        TriviaFragment fragment = new TriviaFragment();
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
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_trivia, container, false);

        btnTrivia=(Button) view.findViewById(R.id.btnTrivia);
        tvInformation =(TextView) view.findViewById(R.id.tvInformationRandom);

        btnTrivia.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            obtenerDatos();
            }
        });

        retrofit = new Retrofit.Builder()
                .baseUrl("http://numbersapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();



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

    private void obtenerDatos(){
        ApiNumbers service = retrofit.create(ApiNumbers.class);
        Call<ApiNumberResponse> respuestaCall = service.getTrivia(generateRandom(),0);
        respuestaCall.enqueue(new Callback<ApiNumberResponse>() {
            @Override
            public void onResponse(Call<ApiNumberResponse> call, Response<ApiNumberResponse> response) {

                if(response.isSuccessful()){
                    ApiNumberResponse respuesta = response.body();
                    tvInformation.setText(respuesta.getText());
                }else{
                    Log.e(TAG,"onResponse"+response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<ApiNumberResponse> call, Throwable t) {

                Log.e(TAG,"onFailure"+t.getMessage());
            }
        });

    }


    public int generateRandom(){

        int numero = (int) (Math.random() * 9999) + 1;
        return numero;
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
