package com.example.internshipproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;

import java.net.URL;

public class ModulesAdapter extends FirebaseRecyclerAdapter<Modules, ModulesAdapter.ModuleViewholder> {
    private OnItemClickListener listener;


    public ModulesAdapter(@NonNull FirebaseRecyclerOptions<Modules> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull ModuleViewholder holder, int position, @NonNull Modules model) {
        Context context= holder.itemView.getContext();

        holder.name.setText(model.getName());
        Glide.with(context).load(model.getImg()).into(holder.lala);
        holder.src =model.getSrc();
    }

    @NonNull
    @Override
    public ModuleViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.modulescard, parent, false);
        return new ModulesAdapter.ModuleViewholder(view);
    }


    class ModuleViewholder extends RecyclerView.ViewHolder{

        TextView name;
        ImageView lala;
        String src;
        public ModuleViewholder(@NonNull View itemView) {
            super(itemView);

            name= itemView.findViewById(R.id.name);
            lala= itemView.findViewById(R.id.moduleimage);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String ola = name.getText().toString();
                    int position =getAdapterPosition();
                    DatabaseReference w=getRef(position);
                    if (position != RecyclerView.NO_POSITION && listener != null) {
                        listener.onItemClick(position,src,ola,w);
                    }
                }
            });
        }
    }
    public interface OnItemClickListener {
        void onItemClick(int position , String p, String ola,DatabaseReference w);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}