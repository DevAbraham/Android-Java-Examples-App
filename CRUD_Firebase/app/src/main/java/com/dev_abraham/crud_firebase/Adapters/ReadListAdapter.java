package com.dev_abraham.crud_firebase.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dev_abraham.crud_firebase.Models.Model_Student;
import com.dev_abraham.crud_firebase.R;

import java.util.List;

public class ReadListAdapter extends RecyclerView.Adapter<ReadListAdapter.ViewHolder> {

    public List<Model_Student> students;
    private int layout;



    public ReadListAdapter(List<Model_Student> students,int layout){
        this.students=students;
        this.layout=layout;
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
        viewHolder.bind(students.get(i));
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvName;
        public TextView tvAPaterno;
        public TextView tvAMaterno;
        public TextView tvNumber;
        public TextView tvEmail;


        public ViewHolder(View v) {
            super(v);
            this.tvName = (TextView) v.findViewById(R.id.tvName);
            this.tvAPaterno = (TextView) v.findViewById(R.id.tvAPaterno);
            this.tvAMaterno = (TextView) v.findViewById(R.id.tvAMaterno);
            this.tvNumber = (TextView) v.findViewById(R.id.tvNumber);
            this.tvEmail = (TextView) v.findViewById(R.id.tvEmail);
        }

        public void bind(final Model_Student student){

            this.tvName.setText(student.getNombre());
            this.tvAPaterno.setText(student.getApellidoPaterno());
            this.tvAMaterno.setText(student.getApellidoMaterno());
            this.tvNumber.setText(student.getTelefono());
            this.tvEmail.setText(student.getEmail());
        }

    }


    public interface OnItemClickListener {
        void onItemClick(Model_Student v,int position);
    }


}
