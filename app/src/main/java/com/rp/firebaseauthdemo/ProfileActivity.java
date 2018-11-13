package com.rp.firebaseauthdemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class ProfileActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseAuth firebaseAuth;

    private TextView textViewEmail;
    private Button btnLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() == null){

            startActivity(new Intent(this, LoginActivity.class));

        }

        FirebaseUser user = firebaseAuth.getCurrentUser();

        textViewEmail = findViewById(R.id.textViewUserEmail);
        btnLogout = findViewById(R.id.btnLogout);

        textViewEmail.setText("Welcome, " + user.getEmail());

        btnLogout.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {

        if(v == btnLogout){

            firebaseAuth.signOut();
            finish();
            startActivity(new Intent(this, LoginActivity.class));

        }

    }
}
