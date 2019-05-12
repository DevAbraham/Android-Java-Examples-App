package com.dev_abraham.interview_developer_android_jr.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

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
 * {@link EventsFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link EventsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EventsFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    Spinner sDay;
    Spinner sMonth;
    Button btnYear;
    Button btnMonthDay;
    EditText etYear;
    TextView tvYear;
    TextView tvMonthDay;

    private Retrofit retrofit;
    private static final String TAG = "API";

    private OnFragmentInteractionListener mListener;

    public EventsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment EventsFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static EventsFragment newInstance(String param1, String param2) {
        EventsFragment fragment = new EventsFragment();
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


        final View view = inflater.inflate(R.layout.fragment_events, container, false);

        btnYear=(Button) view.findViewById(R.id.btnYear);
        btnMonthDay=(Button) view.findViewById(R.id.btnMonthDay);
        etYear =(EditText) view.findViewById(R.id.etYear);
        tvYear =(TextView) view.findViewById(R.id.tvYear);
        tvMonthDay =(TextView) view.findViewById(R.id.tvMonthDay);

        btnYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String value = etYear.getText().toString();
                if(value.length()>4){
                    Toast.makeText(view.getContext(),"El numero debe ser menor a 4 digitos",Toast.LENGTH_LONG).show();
                }else if(value.length()==0){
                    Toast.makeText(view.getContext(),"Ingresa un numero menor a 4 digitos",Toast.LENGTH_LONG).show();
                }else{
                    obtenerDatos(Integer.parseInt(value));
                }

            }
        });
        btnMonthDay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int day = Integer.parseInt(sDay.getSelectedItem().toString());
                String month = (sMonth.getSelectedItem().toString());
                switch (month){
                    case ("Enero"):
                        obtenerDatos(1,day);
                        break;
                    case ("Febrero"):
                        obtenerDatos(2,day);
                        break;
                    case ("Marzo"):
                        obtenerDatos(3,day);
                        break;
                    case ("Abril"):
                        obtenerDatos(4,day);
                        break;
                    case ("Mayo"):
                        obtenerDatos(5,day);
                        break;
                    case ("Junio"):
                        obtenerDatos(6,day);
                        break;
                    case ("Julio"):
                        obtenerDatos(7,day);
                        break;
                    case ("Agosto"):
                        obtenerDatos(8,day);
                        break;
                    case ("Septiembre"):
                        obtenerDatos(9,day);
                        break;
                    case ("Octubre"):
                        obtenerDatos(10,day);
                        break;
                     case ("Noviembre"):
                        obtenerDatos(11,day);
                        break;
                    case ("Diciembre"):
                        obtenerDatos(12,day);
                        break;
                }
            }
        });
        fillSpiners(view);
        sDay.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int myPosition, long myID) {

                updateSpinners(view);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });


        retrofit = new Retrofit.Builder()
                .baseUrl("http://numbersapi.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

       return view;
   }


    public void fillSpiners(View view) {
        sDay = (Spinner) view.findViewById(R.id.spinnerDay);
        sMonth = (Spinner) view.findViewById(R.id.spinnerMonth);

        ArrayAdapter<Integer> dayAdapter = new ArrayAdapter<Integer>(view.getContext(), android.R.layout.simple_spinner_item, values);
        dayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sDay.setAdapter(dayAdapter);
        sDay.setSelection(0);

        ArrayAdapter<String> monthAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, months1);
        monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sMonth.setAdapter(monthAdapter);
        sMonth.setSelection(0);
    }

    Integer [] values =
            {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
    String [] months1 = {
            "Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"
    };
    String [] months2 = {
            "Enero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"
    };
    String [] months3 = {
            "Enero","Marzo","Mayo","Julio","Agosto","Octubre","Diciembre"
    };

    private void updateSpinners(View view){

        int day = Integer.parseInt(sDay.getSelectedItem().toString());
        if(day>30){
            ArrayAdapter<String> monthAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, months3);
            monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sMonth.setAdapter(monthAdapter);
        }else if(day>29){
            ArrayAdapter<String> monthAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, months2);
            monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sMonth.setAdapter(monthAdapter);
        }else if(day>28){
            ArrayAdapter<String> monthAdapter = new ArrayAdapter<String>(view.getContext(), android.R.layout.simple_spinner_item, months1);
            monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            sMonth.setAdapter(monthAdapter);
        }

        sMonth.setSelection(0);

    }


    private void obtenerDatos(int year){

            ApiNumbers service = retrofit.create(ApiNumbers.class);
            Call<ApiNumberResponse> respuestaCall = service.getYear(year,0);
            respuestaCall.enqueue(new Callback<ApiNumberResponse>() {
                @Override
                public void onResponse(Call<ApiNumberResponse> call, Response<ApiNumberResponse> response) {

                    if(response.isSuccessful()){
                        ApiNumberResponse respuesta = response.body();
                        tvYear.setText(respuesta.getText());
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
    private void obtenerDatos(int month,int day){

        ApiNumbers service = retrofit.create(ApiNumbers.class);
        Call<ApiNumberResponse> respuestaCall = service.getDate(month,day,0);
        respuestaCall.enqueue(new Callback<ApiNumberResponse>() {
            @Override
            public void onResponse(Call<ApiNumberResponse> call, Response<ApiNumberResponse> response) {

                if(response.isSuccessful()){
                    ApiNumberResponse respuesta = response.body();
                    tvMonthDay.setText(respuesta.getText());
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
