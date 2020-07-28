package com.academicbot.academicbot;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Home extends AppCompatActivity {
private NavigationView navigationView;
private DrawerLayout drawerLayout;
private ActionBarDrawerToggle toggle;
private View header;
DatabaseReference databaseReference;
FirebaseAuth auth;
String x="";
private TextView student_name,USN;
private AlertDialog.Builder alertDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        navigationView=findViewById(R.id.nav_view);
        drawerLayout=findViewById(R.id.drawerLayout);
        header=navigationView.getHeaderView(0); //declaring Navigation Bar header

        student_name=header.findViewById(R.id.student_name);
        USN=header.findViewById(R.id.USN);
      //Setting up navigation bar
        toggle=new ActionBarDrawerToggle(Home.this,drawerLayout,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        databaseReference=FirebaseDatabase.getInstance().getReference().child("Users").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                Userclass u=dataSnapshot.getValue(Userclass.class);
                student_name.setText(u.name);
                USN.setText(u.usn);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        ActionBar actionBar=getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);

        Toast.makeText(getApplicationContext(),x,Toast.LENGTH_SHORT).show();

        //Navigation through activities when items are selected from side bar
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.Enrollment:
                        menuItem.setCheckable(true);
                        DatabaseReference dref=FirebaseDatabase.getInstance().getReference().child("Update");
                        dref.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                String val=dataSnapshot.getValue(String.class);
                                if(val.equals("1")){
                                    startActivity(new Intent(Home.this,Enrollment.class));

                                }
                                else{
                                    Toast.makeText(getApplicationContext(),"Currently its inactive",Toast.LENGTH_SHORT).show();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                        break;
                    case  R.id.Announcements:
                        menuItem.setCheckable(true);
                        break;
                    case R.id.Assignments:
                        menuItem.setCheckable(true);
                        startActivity(new Intent(Home.this,Assignments.class));

                        break;
                    case R.id.Attendance:
                        menuItem.setCheckable(true);
                        startActivity(new Intent(Home.this,Attendance.class));

                        break;
                    case R.id.Dashboard:
                        menuItem.setCheckable(true);
                        break;
                    case R.id.Logout:
                        Logout();
                        menuItem.setCheckable(true);
                        break;

                }
                return true;
            }
        });


    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);

    }
    public void Logout(){
    //Function to logout user
        alertDialog=new AlertDialog.Builder(Home.this); //alertdialog when user wants to logout
        alertDialog.setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Logout")
                .setMessage("Are you sure you want to logout?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(Home.this,Login.class));
                    }
                })
                .setNegativeButton("No",null)
                .show();

    }
    public void sem(FirebaseUser firebaseUser) {
        auth = FirebaseAuth.getInstance();
        if (firebaseUser != null) {
            databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(auth.getCurrentUser().getUid()).child("student");
            databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    x = dataSnapshot.getValue(String.class);

                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }


    }
}
