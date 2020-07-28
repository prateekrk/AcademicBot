package com.academicbot.academicbot;

import android.content.Intent;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Timer;

public class ForgotPassword extends AppCompatActivity {
EditText email;
Button button; //button to send reset link to the registered mail id
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        email=findViewById(R.id.text);
        button=findViewById(R.id.button3);

        //button to reset password
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Returns error if email field is left empty
                if(email.getText().toString().isEmpty()){
                    Toast.makeText(getApplicationContext(),"Enter Your Email ID",Toast.LENGTH_SHORT).show();
                    email.setError("Required");
                    return;
                }
                //firebase function to reset password
                FirebaseAuth.getInstance().sendPasswordResetEmail(email.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            //if task is successful mail will be sent to emailID and automatically it will be redirected to login screen after 5 secs
                            Toast.makeText(getApplicationContext(),"Follow the further instrcution given in the Email",Toast.LENGTH_SHORT).show();

                            new Handler().postDelayed(new Runnable(){
                                @Override
                                public void run(){
                                    startActivity(new Intent(ForgotPassword.this,Login.class));
                                    finish();
                                }
                            },5*1000);
                        }
                        if(!task.isSuccessful()){
                            //if task is not successful it will popup and message
                            Toast.makeText(getApplicationContext(),"Make sure you have entered registered Email ID",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });


    }
}
