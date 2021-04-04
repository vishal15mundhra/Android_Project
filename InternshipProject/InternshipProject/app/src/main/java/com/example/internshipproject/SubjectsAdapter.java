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

public class SubjectsAdapter extends FirebaseRecyclerAdapter<Subjects, SubjectsAdapter.SubjectsViewholder> {

    private OnItemClickListener listener;

    public SubjectsAdapter(@NonNull FirebaseRecyclerOptions<Subjects> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull SubjectsViewholder holder, int position, @NonNull Subjects model) {

        Context context= holder.itemView.getContext();

       if(model.getType().equals("Overview"))
        {
            holder.text.setText("Subjects:");
            Glide.with(context).load(R.drawable.theory).into(holder.image);
        }
       else if (!model.getType().equals("Overview"))
       {
           holder.text.setText("Modules:");
       }
        if(model.getType().equals("Practical"))
        {
            Glide.with(context).load(R.drawable.microscope).into(holder.image);
        }
        if(model.getType().equals("Theory"))
        {
            Glide.with(context).load(R.drawable.thoery_image).into(holder.image);

        }
        holder.name.setText(model.getName());
        holder.code.setText(model.getCode());
        holder.credit.setText(model.getCredit());
        holder.modules.setText(model.getModules());


    }

    @NonNull
    @Override
    public SubjectsViewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.subjectscard, parent, false);
        return new SubjectsViewholder(view);
    }

    class SubjectsViewholder extends RecyclerView.ViewHolder{

        TextView name,code,credit,modules,text;
        ImageView image;

        public SubjectsViewholder(@NonNull View itemView) {
            super(itemView);

            text= itemView.findViewById(R.id.textView9);
            name = itemView.findViewById(R.id.NAME);
            code = itemView.findViewById(R.id.CODE);
            credit = itemView.findViewById(R.id.CREDIT);
            modules = itemView.findViewById(R.id.MODULES);
            image = itemView.findViewById(R.id.imageView2);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    String ola = name.getText().toString();
                    int position =getAdapterPosition();
                    DatabaseReference p = getRef(position);
                    if (position != RecyclerView.NO_POSITION && listener != null) {
                        listener.onItemClick(position,p,ola);
                    }
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(int position , DatabaseReference p , String ola);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}