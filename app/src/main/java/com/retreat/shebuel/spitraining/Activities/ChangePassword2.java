package com.retreat.shebuel.spitraining.Activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.retreat.shebuel.spitraining.App;
import com.retreat.shebuel.spitraining.Employee;
import com.retreat.shebuel.spitraining.R;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class ChangePassword2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    String oldPass,newPass,confirmPass;
    EditText oldE,newE,confirmE;
    Button proceed;
    private DatabaseReference mDatabase;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_password2);
        SharedPreferences sharedpreferences = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
        editor= sharedpreferences.edit();
        oldE=(EditText)findViewById(R.id.editText);
        newE=(EditText)findViewById(R.id.editText2);
        confirmE=(EditText)findViewById(R.id.editText3);
        proceed=(Button)findViewById(R.id.button);

        mDatabase = FirebaseDatabase.getInstance().getReference();
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

        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                oldPass=oldE.getText().toString();
                newPass=newE.getText().toString();
                confirmPass=confirmE.getText().toString();

                if(newPass.equals(confirmPass))
                {
                    mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for(DataSnapshot usersDS : dataSnapshot.getChildren())
                            {
                                for(DataSnapshot headingsDS : usersDS.getChildren())
                                {
                                    if(headingsDS.getKey().equals("EmployeeCode"))
                                    {
                                        if(headingsDS.getValue().toString().equals(((App)getApplication()).getGlobalVariable()))
                                        {
                                            Employee employee = usersDS.getValue(Employee.class);
                                            String DbPassword = employee.getPassword();
                                            if(DbPassword.equals(oldPass))
                                            {
                                                usersDS.getRef().child("Password").setValue(newPass);
                                                Toast.makeText(getBaseContext(),"Password Changed Successfully",Toast.LENGTH_SHORT).show();
                                                Intent i = new Intent(getBaseContext(),VideoActivity.class);
                                                startActivity(i);
                                            }
                                        }
                                    }
                                }

                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
                else
                {
                    Toast.makeText(getBaseContext(),"Confirm Password doesn't match",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.profile) {
            Intent i = new Intent(getBaseContext(),Profile.class);
            startActivity(i);

        } else if (id == R.id.language) {
            Intent i = new Intent(getBaseContext(),LanguageOptions.class);
            startActivity(i);

        } else if (id == R.id.settings) {
            editor.clear();
            editor.commit();
            Intent i = new Intent(getBaseContext(),Login.class);
            startActivity(i);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onResume()
    {
        SharedPreferences sharedpreferences = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
        Locale locale = new Locale(sharedpreferences.getString("language","en"));
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
        super.onResume();
    }
}
