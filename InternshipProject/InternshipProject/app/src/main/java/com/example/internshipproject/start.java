package com.example.internshipproject;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class start extends AppCompatActivity {
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    FirebaseAuth auth;
    private RecyclerView recyclerView;
    SubjectsAdapter adapter;
    DatabaseReference mbase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_start2);

        dl = (DrawerLayout)findViewById(R.id.activity_start2);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);

        dl.addDrawerListener(t);
        t.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        nv = (NavigationView)findViewById(R.id.nv);



        mbase = FirebaseDatabase.getInstance().getReference("materials/it/4");
        Query query=mbase.orderByChild("priority");

        recyclerView=findViewById(R.id.recycler1);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Subjects> options =
                new FirebaseRecyclerOptions.Builder<Subjects>()
                        .setQuery(query, Subjects.class)
                        .build();
        adapter=new SubjectsAdapter(options);
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new SubjectsAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position, DatabaseReference p, String ola) {

                String q= p+"/module";
                Intent intent=new Intent( start.this,ModuleActivity.class);
                intent.putExtra("r",q);
                intent.putExtra("s",ola);
                startActivity(intent);


            }

        });

        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.account:
                        Toast.makeText(start.this, "My Account",Toast.LENGTH_SHORT).show();break;
                    case R.id.ResetPassword: {

                        auth = FirebaseAuth.getInstance();
                        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        String Email =user.getEmail();
                        auth.sendPasswordResetEmail(Email)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(start.this, "We have sent you instructions to reset your password!", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(start.this, "Failed to send reset email!", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                        auth.signOut();
                        startActivity(new Intent(start.this, login.class));
                        finish();
                        break;
                    }
                    case R.id.SignOut: {
                        Toast.makeText(start.this, "Signing Out", Toast.LENGTH_SHORT).show();
                        auth = FirebaseAuth.getInstance();
                        final FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
                        auth.signOut();
                        startActivity(new Intent(start.this, login.class));
                        finish();
                        break;
                    }

                    default:
                        return true;
                }


                return true;

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;

        return super.onOptionsItemSelected(item);
    }
}
