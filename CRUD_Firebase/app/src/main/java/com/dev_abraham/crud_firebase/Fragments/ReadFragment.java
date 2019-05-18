package com.dev_abraham.crud_firebase.Fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.dev_abraham.crud_firebase.Activities.UpdateActivity;
import com.dev_abraham.crud_firebase.Models.Model_Student;
import com.dev_abraham.crud_firebase.R;
import com.dev_abraham.crud_firebase.Adapters.ReadListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class ReadFragment extends Fragment {

    private ArrayList<Model_Student> students;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private TextView textView;
    ValueEventListener mValueListeneroContactos;

    private FirebaseDatabase database = FirebaseDatabase.getInstance();
    private DatabaseReference myRef = database.getReference("students").child("user");

    public ReadFragment() {
        // Required empty public constructor
    }




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_read, container, false);


        students = new ArrayList<Model_Student>();

        textView= root.findViewById(R.id.tvEmpty);
        mRecyclerView = (RecyclerView) root.findViewById(R.id.rvRead);
        mLayoutManager = new LinearLayoutManager(getContext());
        mAdapter = new ReadListAdapter(students,R.layout.student);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(mAdapter);

        final GestureDetector mGestureDetector = new GestureDetector(root.getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override public boolean onSingleTapUp(MotionEvent e) {
                return true;
            }
        });

        mRecyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
            @Override
            public boolean onInterceptTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
                View child = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());

                if (child != null && mGestureDetector.onTouchEvent(motionEvent)) {
                    int position = recyclerView.getChildAdapterPosition(child);
                    dialogDeleteUpdate(position);
                }
                return false;
            }

            @Override
            public void onTouchEvent(@NonNull RecyclerView recyclerView, @NonNull MotionEvent motionEvent) {
                Log.i("TAG","click");
            }

            @Override
            public void onRequestDisallowInterceptTouchEvent(boolean b) {

            }
        });

        getStudents();

        return root;
    }

    private void dialogDeleteUpdate(int i) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        final int position = i;
        final String nombre = students.get(position).getNombre();
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
                       switch (which){
                           case 1 :
                               //borrar
                               DatabaseReference currentUserBD = myRef.child(students.get(position).getKey());
                               currentUserBD.removeValue();
                               Toast.makeText(getContext(),"Se elimino al estudiante :"+nombre,Toast.LENGTH_SHORT).show();
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



    private void getStudents() {
        mValueListeneroContactos = myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){ //si existen datos, hay que obtener la información
                    mRecyclerView.setVisibility(View.VISIBLE);
                    textView.setVisibility(View.GONE);
                    students.clear();
                    mAdapter.notifyDataSetChanged();
                    for (DataSnapshot snapshotContacto : dataSnapshot.getChildren()) {
                        Model_Student contacto = snapshotContacto.getValue(Model_Student.class);
                        contacto.setKey(snapshotContacto.getKey());
                        students.add(contacto);
                    }
                    mAdapter.notifyDataSetChanged();
                }else{
                    mRecyclerView.setVisibility(View.GONE);
                    textView.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        });

    }

}
