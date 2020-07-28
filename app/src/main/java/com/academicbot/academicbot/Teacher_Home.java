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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class Teacher_Home extends AppCompatActivity {
    DrawerLayout drawerLayout1;
    NavigationView navigationView;
    View header;
    ActionBarDrawerToggle toggle;
    AlertDialog.Builder alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher__home);

        drawerLayout1=findViewById(R.id.drawerLayout);
        navigationView=findViewById(R.id.faculty_navbar);
        header=navigationView.getHeaderView(0);

        ActionBar actionBar=getSupportActionBar();

        toggle=new ActionBarDrawerToggle(Teacher_Home.this,drawerLayout1,R.string.navigation_drawer_open,R.string.navigation_drawer_close);
        drawerLayout1.addDrawerListener(toggle);
        toggle.syncState();

        actionBar.setDisplayHomeAsUpEnabled(true);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.t_logout:
                        Logout();
                        break;
                    case  R.id.give_assignments:
                        startActivity(new Intent(Teacher_Home.this,teacher_assignment.class));
                        break;
                    case R.id.mark_attendance:
                        startActivity(new Intent(Teacher_Home.this,Teacher_Attendance.class));
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
        alertDialog=new AlertDialog.Builder(Teacher_Home.this); //alertdialog when user wants to logout
        alertDialog.setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Logout")
                .setMessage("Are you sure you want to logout?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        FirebaseAuth.getInstance().signOut();
                        startActivity(new Intent(Teacher_Home.this,Login.class));
                    }
                })
                .setNegativeButton("No",null)
                .show();

    }

}
