package com.academicbot.academicbot;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class teacher_assignment extends AppCompatActivity {
   private EditText code,decription,subject,due_date,sem;
   private Button upload;
   private DatabaseReference databaseReference;
   private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_assignment);
        subject=findViewById(R.id.editText1);
        code=findViewById(R.id.editText2);
        decription=findViewById(R.id.editText3);
        due_date=findViewById(R.id.editText4);
        sem=findViewById(R.id.editText5);
        upload=findViewById(R.id.button1);

        progressDialog=new ProgressDialog(this);
        progressDialog.setTitle("Uploading");
        progressDialog.setMessage("This may take few seconds");


        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressDialog.show();
                databaseReference=FirebaseDatabase.getInstance().getReference("Assignments").child(sem.getText().toString()).child(code.getText().toString());
                assignmentModel assignment=new assignmentModel(subject.getText().toString(),decription.getText().toString(),due_date.getText().toString());
                databaseReference.setValue(assignment);
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Assignment "+code.getText().toString()+" is successfully uploaded",Toast.LENGTH_SHORT).show();
                startActivity(new Intent(teacher_assignment.this,Teacher_Home.class));

            }
        });


    }
}
