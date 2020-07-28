package com.academicbot.academicbot;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays.*;
import java.util.HashMap;
import java.util.List;

import static java.util.Arrays.asList;
import static java.util.Arrays.fill;

public class Enrollment extends AppCompatActivity {
    private ListView listView;
    private Button button;
    private CheckBox checkBox;
    String temp="";
    String val="";
    ArrayAdapter<String> adapter;
    private Spinner spinner;
    String[] sem={"sem 1","sem 2","sem 3","sem 4","sem 5","sem 6","sem 7","sem 8","Select Semester"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enrollment);

        spinner=findViewById(R.id.spinner);
        listView=findViewById(R.id.listview);
        checkBox=findViewById(R.id.checkBox);
        button=findViewById(R.id.enroll);


        ArrayAdapter<String> a=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,sem);
        spinner.setAdapter(a);


        List<HashMap<String, String>> listItems = new ArrayList<>();
        adapter=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(final AdapterView<?> adapterView, View view, int i, long l) {
                val=sem[i];
                adapter.clear();
                DatabaseReference dref=FirebaseDatabase.getInstance().getReference().child("Courses").child(val);
                dref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        for(DataSnapshot d:dataSnapshot.getChildren()){
                            adapter.add(d.getValue(String.class));

                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        button.setVisibility(View.INVISIBLE);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(checkBox.isChecked()){
                    button.setVisibility(View.VISIBLE);
                }
                else{
                    button.setVisibility(View.INVISIBLE);

                }

            }
        });
        listView.setAdapter(adapter);

    }
}
