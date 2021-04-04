package com.example.internshipproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class ModuleActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    ModulesAdapter adapter;
    DatabaseReference mbase;
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_module);
        Intent intent = getIntent();
        String s = intent.getStringExtra("r");
        String q = intent.getStringExtra("s");

        textView = findViewById(R.id.Subjectname);
        textView.setText(q);
        mbase = FirebaseDatabase.getInstance().getReferenceFromUrl(s);
        Query query =mbase;

        recyclerView=findViewById(R.id.recyclerr);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Modules> options =
                new FirebaseRecyclerOptions.Builder<Modules>()
                        .setQuery(query,Modules.class)
                        .build();
        adapter=new ModulesAdapter(options);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new ModulesAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, String p, String ola, DatabaseReference w) {

                if(p.equals("null")) {

                    String q= w+"/materials";
                    Intent intent=new Intent( ModuleActivity.this,materialsActivity.class);
                    intent.putExtra("r",q);
                    intent.putExtra("s",ola);
                    startActivity(intent);


                }
                else {
                    Intent intent = new Intent(ModuleActivity.this, PdfViewer.class);
                    intent.putExtra("r", p);
                    intent.putExtra("s", ola);
                    startActivity(intent);
                }

            }

        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop(){
        super.onStop();
        adapter.stopListening();
    }
}
