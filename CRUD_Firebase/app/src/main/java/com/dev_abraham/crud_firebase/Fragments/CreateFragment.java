package com.dev_abraham.crud_firebase.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dev_abraham.crud_firebase.Models.Model_Student;
import com.dev_abraham.crud_firebase.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class CreateFragment extends Fragment {




    private Button btnCreate ;
    private EditText etNombre;
    private EditText etAPaterno;
    private EditText etAMaterno;
    private EditText etEmail;
    private EditText etNumber;
    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("students").child("user");

    public CreateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View root = inflater.inflate(R.layout.fragment_create, container, false);

        btnCreate = (Button) root.findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(createClickListener);

        etNombre= (EditText)root.findViewById(R.id.etNombre);
        etAPaterno = (EditText)root.findViewById(R.id.etAPaterno);
        etAMaterno= (EditText)root.findViewById(R.id.etAMaterno);
        etEmail= (EditText)root.findViewById(R.id.etEmail);
        etNumber= (EditText)root.findViewById(R.id.etNumber);


        return root;
    }

        private View.OnClickListener createClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Model_Student contacto =new  Model_Student(
                        etNombre.getText().toString(),
                        etAPaterno.getText().toString(),
                        etAMaterno.getText().toString(),
                        etNumber.getText().toString(),
                        etEmail.getText().toString());

                try {
                    myRef.push().setValue(contacto);
                    Toast.makeText(getContext(), "Usuario agregado correctamente!", Toast.LENGTH_SHORT).show();
                    getActivity().getSupportFragmentManager().beginTransaction()
                            .replace(R.id.fragmentContainer,new ReadFragment())
                            .commit();
                } catch (Exception e) {
                Toast.makeText(getContext(), "No se pudo agregar,intente mas tarde", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }

            }
        };


}
