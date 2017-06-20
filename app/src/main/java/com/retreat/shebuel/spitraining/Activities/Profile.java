package com.retreat.shebuel.spitraining.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.retreat.shebuel.spitraining.App;
import com.retreat.shebuel.spitraining.Employee;
import com.retreat.shebuel.spitraining.R;

import java.util.Locale;

public class Profile extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    TextView nameT,dojT,deptT,desigT,siteT,changepassT;
    DatabaseReference mDatabase;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        nameT=(TextView)findViewById(R.id.nameText);
        dojT=(TextView)findViewById(R.id.dojText);
        deptT=(TextView)findViewById(R.id.deptText);
        desigT=(TextView)findViewById(R.id.desigText);
        siteT=(TextView)findViewById(R.id.siteText);
        changepassT=(TextView)findViewById(R.id.changePassword);
        changepassT.setPaintFlags(changepassT.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
        SharedPreferences sharedpreferences = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);

        Locale locale = new Locale(sharedpreferences.getString("language","en"));
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        //Creating a custom toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Defining the drawer layout
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        mDatabase= FirebaseDatabase.getInstance().getReference();
        mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot usersDS : dataSnapshot.getChildren())
                {
                    for(DataSnapshot headingsDS : usersDS.getChildren())
                    {
                        if(headingsDS.getKey().toString().equals("EmployeeCode"))
                        {
                            if(headingsDS.getValue().toString().equals(((App)getApplication()).getGlobalVariable()))
                            {
                                Employee employee = usersDS.getValue(Employee.class);
                                nameT.setText(employee.getEmployeeName());
                                dojT.setText(employee.getDOJ());
                                desigT.setText(employee.getDesignation());
                                deptT.setText(employee.getDepartment());
                                siteT.setText(employee.getSite());
                            }
                        }
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        changepassT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getBaseContext(), ChangePassword2.class);
                startActivity(i);
            }
        });



    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.profile) {
            Intent i = new Intent(getBaseContext(),Profile.class);
            startActivity(i);
            finish();
        } else if (id == R.id.language) {
            Intent i = new Intent(getBaseContext(),LanguageOptions.class);
            startActivity(i);
            finish();
        } else if (id == R.id.settings) {
            editor.clear();
            editor.commit();
            Intent i = new Intent(getBaseContext(),Login.class);
            startActivity(i);
            finish();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onResume()
    {
        super.onResume();
        if(((App)getApplication()).isLanguageChanged())
        {
            ((App)getApplication()).setLanguageChanged(false);
            super.recreate();
        }

    }
}
