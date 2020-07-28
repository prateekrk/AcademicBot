package com.academicbot.academicbot;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ThrowOnExtraProperties;
import com.google.firebase.database.ValueEventListener;

import java.util.Objects;

public class Login extends AppCompatActivity {
    Button Login, ForgotPassword, Register;
    private EditText email, password;
    private FirebaseAuth auth;
    private ProgressDialog progressDialog;
    private DatabaseReference databaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Login = findViewById(R.id.button1);
        ForgotPassword = findViewById(R.id.button2);
        email = findViewById(R.id.editText1);
        password = findViewById(R.id.editText2);
        Register = findViewById(R.id.register);

        auth = FirebaseAuth.getInstance();

        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Next to if conditions will return error if email and password are not entered
                if (email.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter Your Email ID", Toast.LENGTH_SHORT).show();
                    email.setError("Required");
                    return;
                }
                if (password.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Enter Your Password", Toast.LENGTH_SHORT).show();
                    password.setError("Required");
                    return;
                }
                progressDialog = new ProgressDialog(Login.this);
                progressDialog.setMessage("Logging in....");
                progressDialog.show();

                //Logging in user with email and password by authenticating using firebase authentication
                auth.signInWithEmailAndPassword(email.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            UserLoginType(FirebaseAuth.getInstance().getCurrentUser());
                            progressDialog.dismiss();
                        }
                        if (!task.isSuccessful()) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "Wrong EmailId or Password", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });

        //Forgot password activity
        ForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, ForgotPassword.class)); //opens forgot password activity
            }
        });

        //registration activity

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Login.this, Registeration.class)); //opens registration activity
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        UserLoginType(FirebaseAuth.getInstance().getCurrentUser());
    }


        void UserLoginType(FirebaseUser user){
        if(user!=null){
            DatabaseReference dref=FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("val");
            dref.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                   String value=dataSnapshot.getValue(String.class);
                    if(value.equals("1")){
                        startActivity(new Intent(Login.this,Home.class));
                        Toast.makeText(getApplicationContext(),"Logged In As Student",Toast.LENGTH_SHORT).show();
                    }
                    if (value.equals("0")){
                        startActivity(new Intent(Login.this,Teacher_Home.class));
                        Toast.makeText(getApplicationContext(),"Logged In As Faculty",Toast.LENGTH_SHORT).show();

                    }
                    if (value.equals("2")){
                        Toast.makeText(getApplicationContext(),"Registration Under Review",Toast.LENGTH_SHORT).show();

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });

        }



        }

    }





