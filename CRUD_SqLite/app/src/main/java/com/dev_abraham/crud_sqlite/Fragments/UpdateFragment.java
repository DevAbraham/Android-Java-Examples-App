package com.dev_abraham.crud_sqlite.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.dev_abraham.crud_sqlite.Database.DatabaseSQLite;
import com.dev_abraham.crud_sqlite.Moderls.ModelStudent;
import com.dev_abraham.crud_sqlite.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class UpdateFragment extends Fragment {


    private Button btnCreate ;
    private Button btnCancel ;
    private EditText etNombre;
    private EditText etEmail;
    private EditText etNumber;
    private ModelStudent id;

    public UpdateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_update, container, false);


        btnCreate = (Button) root.findViewById(R.id.btnCreate);
        btnCreate.setOnClickListener(createClickListener);

        btnCancel = (Button) root.findViewById(R.id.btnCancel);
        btnCancel.setOnClickListener(cancelClickListener);

        etNombre= (EditText)root.findViewById(R.id.etNombre);
        etEmail= (EditText)root.findViewById(R.id.etEmail);
        etNumber= (EditText)root.findViewById(R.id.etNumber);

         id = (ModelStudent) getArguments().get("id");

        setId(id);

        return root;
    }

    private View.OnClickListener createClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            ModelStudent contacto =new  ModelStudent(
                    etNombre.getText().toString(),
                    etNumber.getText().toString(),
                    etEmail.getText().toString());

            try {
                DatabaseSQLite.updateStudent(id,contacto);
                Toast.makeText(getContext(), "Usuario actualizado correctamente!", Toast.LENGTH_SHORT).show();
                getActivity().finish();
            } catch (Exception e) {
                Toast.makeText(getContext(), "No se pudo actualizar,intente mas tarde", Toast.LENGTH_SHORT).show();
                e.printStackTrace();
            }


        }
    };

    public void setId(ModelStudent id) {
        etNombre.setText(id.getName());
        etEmail.setText(id.getEmail());
        etNumber.setText(id.getPhone());

    }

    private View.OnClickListener cancelClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            getActivity().finish();


        }
    };


}
