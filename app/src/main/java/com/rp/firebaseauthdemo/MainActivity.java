package com.rp.firebaseauthdemo;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private Button buttonRegister;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignin;

    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance();

        if(firebaseAuth.getCurrentUser() != null) {

            //start profile activity
            finish();
            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));

        }

        progressDialog = new ProgressDialog(this);

        buttonRegister = findViewById(R.id.buttonRegister);
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);

        textViewSignin = findViewById(R.id.textViewSignIn);

        buttonRegister.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);

    }

    private void registerUser(){

        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if(TextUtils.isEmpty(email)) {

            //email is empty
            Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();
            //stopping the function execution further
            return;

        }
        if (TextUtils.isEmpty(password)) {

            //password is empty
            Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
            //stopping the function execution further
            return;

        }
        //if validations are ok
        //we will first show a progressbar

        progressDialog.setMessage("Registering User...");
        progressDialog.show();

        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        progressDialog.dismiss();

                        if(task.isSuccessful()) {

                            //user is successfully registered and logged in
                            //we will start profile activity
                            //start profile activity

                            finish();
                            startActivity(new Intent(getApplicationContext(), ProfileActivity.class));

                            Toast.makeText(MainActivity.this, "Registered Successfully", Toast.LENGTH_LONG).show();

                        } else {

                            Toast.makeText(MainActivity.this, "Could not register. Please try again.", Toast.LENGTH_LONG).show();

                        }

                    }
                });

    }

    @Override
    public void onClick(View v) {

        if(v == buttonRegister) {

            registerUser();

        }
        if(v == textViewSignin) {

            //opens login activity
            finish();
            startActivity(new Intent(this, LoginActivity.class));

        }

    }
}
