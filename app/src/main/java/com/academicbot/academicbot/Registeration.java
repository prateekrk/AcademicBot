package com.academicbot.academicbot;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Objects;

public class Registeration extends AppCompatActivity {
EditText first_name,last_name,email,dob,usn,password,confirm_password;
Button register;
FirebaseAuth auth;
DatabaseReference ref;
ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registeration);

        first_name=findViewById(R.id.first_name);
        last_name=findViewById(R.id.last_name);
        email=findViewById(R.id.email);
        dob=findViewById(R.id.dob);
        usn=findViewById(R.id.usn);
        register=findViewById(R.id.register);
        password=findViewById(R.id.set_password);
        confirm_password=findViewById(R.id.confirm_password);
        auth=FirebaseAuth.getInstance();

        progressDialog=new ProgressDialog(Registeration.this);

        progressDialog.setTitle("Registering");
        progressDialog.setMessage("This may take few seconds");

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog.show();
                if (first_name.getText().toString().isEmpty()) {
                    first_name.setError("Required");
                    return;
                }
                if (last_name.getText().toString().isEmpty()) {
                    last_name.setError("Required");
                    return;
                }
                if (email.getText().toString().isEmpty()) {
                    email.setError("Required");
                    return;
                }
                if (dob.getText().toString().isEmpty()) {
                    dob.setError("Required");
                    return;
                }
                if (usn.getText().toString().isEmpty()) {
                    usn.setError("Required");
                    return;
                }
                if (password.getText().toString().isEmpty()) {
                    password.setError("Required");
                    return;
                }
                if (confirm_password.getText().toString().isEmpty()) {
                    confirm_password.setError("Required");
                    return;
                }
                if (!password.getText().toString().equals(confirm_password.getText().toString())) {
                    confirm_password.setError("password do not match!");
                    Toast.makeText(Registeration.this, "Passwords Do Not Match!", Toast.LENGTH_SHORT).show();
                }
                auth.createUserWithEmailAndPassword(email.getText().toString(), confirm_password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            ref=FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                            String name=first_name.getText().toString()+" "+last_name.getText().toString();
                            String dob1=dob.getText().toString();
                            String val="2";
                            String usn1=usn.getText().toString();
                            Userclass user=new Userclass(name,usn1,dob1,val);
                            ref.setValue(user);
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"Successfully registered",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(Registeration.this,Login.class));


                        }
                        if(!task.isSuccessful()){
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(),"Error While Registering",Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }

        });
    }
    }
