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
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.retreat.shebuel.spitraining.App;
import com.retreat.shebuel.spitraining.CustomList;
import com.retreat.shebuel.spitraining.R;

import java.util.Locale;

public class LanguageOptions extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    ListView l;
    SharedPreferences.Editor editor;
    String web[]={"English","Tamil","Telegu","Hindi"};
    Integer[] imageId={
            R.drawable.english,
            R.drawable.tamil,
            R.drawable.telugu,
            R.drawable.hindi
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language_options);
        SharedPreferences sharedpreferences = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
        editor = sharedpreferences.edit();
        l=(ListView)findViewById(R.id.list_item);
        CustomList adapter= new CustomList(LanguageOptions.this,web,imageId);
        l.setAdapter(adapter);
        l.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(position==0)
                {
                    editor.putString("language","en");
                    editor.commit();
                    Toast.makeText(LanguageOptions.this, "Language Changed to English", Toast.LENGTH_SHORT).show();
                    ((App)getApplication()).setLanguageChanged(true);
                    finish();

                }
                else if(position==1)
                {
                    editor.putString("language","ta");
                    editor.commit();
                    Toast.makeText(LanguageOptions.this, "Language Changed to Tamil", Toast.LENGTH_SHORT).show();
                    ((App)getApplication()).setLanguageChanged(true);
                    finish();
                }
                else if(position==2){
                    editor.putString("language","te");
                    editor.commit();
                    Toast.makeText(LanguageOptions.this, "Language Changed to Telugu", Toast.LENGTH_SHORT).show();
                    ((App)getApplication()).setLanguageChanged(true);
                    finish();
                }
                else if(position==3){
                    editor.putString("language","hi");
                    editor.commit();
                    Toast.makeText(LanguageOptions.this, "Language Changed to Hindi", Toast.LENGTH_SHORT).show();
                    ((App)getApplication()).setLanguageChanged(true);
                    finish();

                }
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
}
