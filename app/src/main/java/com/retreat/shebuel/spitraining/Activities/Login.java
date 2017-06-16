package com.retreat.shebuel.spitraining.Activities;

import android.content.Intent;
import android.graphics.Paint;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.retreat.shebuel.spitraining.App;
import com.retreat.shebuel.spitraining.Employee;
import com.retreat.shebuel.spitraining.R;

public class Login extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    EditText id,password;
    Button login;
    TextView forgotpassword;
    String ids,passwords,empCode,value,empLoginCode;
    private DatabaseReference mDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        id=(EditText)findViewById(R.id.editText);
        password=(EditText)findViewById(R.id.editText2);
        login=(Button)findViewById(R.id.button);
        forgotpassword=(TextView)findViewById(R.id.textView);
        forgotpassword.setPaintFlags(forgotpassword.getPaintFlags() |   Paint.UNDERLINE_TEXT_FLAG);
        value="Not Found";
        mDatabase = FirebaseDatabase.getInstance().getReference();



        //Listener for Login button
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                empLoginCode=id.getText().toString();
                passwords=password.getText().toString();
                mDatabase.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot usersDS : dataSnapshot.getChildren())
                        {
                            for(DataSnapshot headingsDS : usersDS.getChildren())
                            {
                                if(headingsDS.getKey().equals("EmployeeCode"))
                                {
                                    if(headingsDS.getValue().toString().equals(empLoginCode))
                                    {
                                        Employee employee = usersDS.getValue(Employee.class);
                                        if(employee.getPassword().equals(passwords))
                                        {
                                            ((App)getApplication()).setGlobalVariable(empLoginCode);
                                            Intent i = new Intent(getBaseContext(),VideoActivity.class);
                                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                                            startActivity(i);
                                        }
                                        else {
                                            Toast.makeText(getBaseContext(),"Wrong Password!!!",Toast.LENGTH_SHORT).show();
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
        });
        forgotpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(),value,Toast.LENGTH_LONG).show();
                Log.i("TAG",value);
            }
        });
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



    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.profile) {
            Intent i = new Intent(getBaseContext(),Profile.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        } else if (id == R.id.language) {
            Intent i = new Intent(getBaseContext(),LanguageOptions.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(i);
        } else if (id == R.id.settings) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onDestroy()
    {
        super.onDestroy();
        
    }
}
