package com.dev_abraham.crud_sqlite.Adapters;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dev_abraham.crud_sqlite.Moderls.ModelStudent;
import com.dev_abraham.crud_sqlite.R;

import java.util.List;

public class ReadListAdapter extends RecyclerView.Adapter<ReadListAdapter.ViewHolder> {

    public List<ModelStudent> students;
    private int layout;
    private OnItemClickListener itemClickListener;


    public ReadListAdapter(List<ModelStudent> students, int layout, OnItemClickListener listener){
        this.students=students;
        this.layout=layout;
        this.itemClickListener=listener;
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
        viewHolder.bind(students.get(i),itemClickListener);
    }

    @Override
    public int getItemCount() {
        return students.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder {

        public TextView tvName;
        public TextView tvNumber;
        public TextView tvEmail;
        LinearLayout container ;

        public ViewHolder(View v) {
            super(v);
            this.tvName = (TextView) v.findViewById(R.id.tvName);
            this.tvNumber = (TextView) v.findViewById(R.id.tvNumber);
            this.tvEmail = (TextView) v.findViewById(R.id.tvEmail);
            container = itemView.findViewById(R.id.container);

        }

        public void bind(final ModelStudent student,final  OnItemClickListener listener) {

            this.tvName.setText("Nombre: "+student.getName());
            this.tvNumber.setText("Numero: "+student.getPhone());
            this.tvEmail.setText("Email: "+student.getEmail());
            container.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    listener.onItemClick(student,getAdapterPosition());
                }
            });
        }

    }


    public interface OnItemClickListener {
        void onItemClick(ModelStudent v,int position);
    }


}
