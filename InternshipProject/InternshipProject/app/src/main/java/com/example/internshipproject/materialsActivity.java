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

public class materialsActivity extends AppCompatActivity {

    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_materials);

        Intent intent = getIntent();
        String s = intent.getStringExtra("r");
        String q = intent.getStringExtra("s");

        textView = findViewById(R.id.Modulename);
        textView.setText(q);

    }

    @Override
    protected void onStart() {
        super.onStart();
      //  adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
      //  adapter.stopListening();
    }
}
