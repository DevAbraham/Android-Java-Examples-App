package com.dev_abraham.crud_sqlite.Fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dev_abraham.crud_sqlite.Database.DatabaseSQLite;
import com.dev_abraham.crud_sqlite.Moderls.ModelStudent;
import com.dev_abraham.crud_sqlite.R;
import com.dev_abraham.crud_sqlite.Adapters.ReadListAdapter;
import com.dev_abraham.crud_sqlite.Activities.UpdateActivity;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReadFragment extends Fragment {

    private ArrayList<ModelStudent> students;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView textView;

    public ReadFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View root = inflater.inflate(R.layout.fragment_read, container, false);


        students = new ArrayList<ModelStudent>();

        textView= root.findViewById(R.id.tvEmpty);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.rvRead);
        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new ReadListAdapter(students, R.layout.item_student, new ReadListAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(ModelStudent v, int position) {

                dialogDeleteUpdate(position);
            }
        }
        );
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);




        loadStudents();

        return root;
    }

    public void loadStudents (){

        Cursor c =  DatabaseSQLite.getAllStudents(getContext());

        students.clear();
        mAdapter.notifyDataSetChanged();



        while(c.moveToNext()){
            mRecyclerView.setVisibility(View.VISIBLE);
            textView.setVisibility(View.GONE);
            ModelStudent student = new ModelStudent(
                    c.getString(c.getColumnIndex("name")),
                    c.getString(c.getColumnIndex("phone")),
                    c.getString(c.getColumnIndex("email")));
         students.add(student);


        }

        if(students.size()==0){
        mRecyclerView.setVisibility(View.GONE);
        textView.setVisibility(View.VISIBLE);
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onResume() {
        super.onResume();
        loadStudents();
    }

    private void dialogDeleteUpdate(int i) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final int position = i;
        final String nombre = students.get(position).getName();
        String opciones []= {"Actualizar","Borrar"};
        builder
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                    }
                })
                .setTitle("Actualizar o borrar : "+nombre)
                .setItems(opciones, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which) {
                            case 1:
                                //borrar
                                DatabaseSQLite.deleteStudent(students.get(position));

                                Toast.makeText(getContext(),"Se elimino al estudiante :"+nombre,Toast.LENGTH_SHORT).show();
                                loadStudents();
                                break;
                            case 0 :
                                Intent intent = new Intent(getActivity(), UpdateActivity.class);
                                intent.putExtra("student",students.get(position));
                                startActivity(intent);
                                break;
                        }
                    }});

        AlertDialog dialog = builder.create();
        dialog.show();
    }


}
