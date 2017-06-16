package com.retreat.shebuel.spitraining.Activities;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.miguelcatalan.materialsearchview.MaterialSearchView;
import com.retreat.shebuel.spitraining.Fragments.MainMenuFragment;
import com.retreat.shebuel.spitraining.Fragments.SearchFragment;
import com.retreat.shebuel.spitraining.R;

import java.util.ArrayList;
import java.util.List;

public class MainOptionsMenu extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    MaterialSearchView searchView;
    final String[] values = new String[] { "Policy","Benefits","Facility","Employee Empowerment","FAQ","Staff Dining","Dorm","Lockers","Uniforms","Staff Screening","Birthday Celebration","Team Outing" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_options_menu);


        //Initialize the toolbar an
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        //Initialize the navigation drawer layout
        final DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        final ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        //Defining and setting listeners fr the search view :)
        searchView = (MaterialSearchView)findViewById(R.id.search_view);
        searchView.setVoiceSearch(true);
        searchView.setOnSearchViewListener(new MaterialSearchView.SearchViewListener() {
            @Override
            public void onSearchViewShown() {
                //Do some magic
                toggle.setDrawerIndicatorEnabled(false);
            }

            @Override
            public void onSearchViewClosed() {
                //Do some magic
                toggle.setDrawerIndicatorEnabled(true);
            }
        });

        //On text change listener fr search
        searchView.setOnQueryTextListener(new MaterialSearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //Do some magic
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                if(newText!=null && !newText.isEmpty()) {
                    List<String> lstFound = new ArrayList<String>();
                    for(String item:values)
                    {
                        if(item.toLowerCase().contains(newText)| item.contains(newText) | item.toUpperCase().contains(newText))
                        {
                            lstFound.add(item);
                        }
                    }
                    Bundle b = new Bundle();
                    b.putStringArrayList("result", (ArrayList<String>) lstFound);
                    /** Getting the fragment manager for fragment related operations */
                    FragmentManager fragmentManager = getSupportFragmentManager();

                    /** Getting the fragmenttransaction object, which can be used to add, remove or replace a fragment */
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    //Toast.makeText(getBaseContext(), "This is working!!", Toast.LENGTH_SHORT).show();
                    SearchFragment searchFragment = new SearchFragment();
                    searchFragment.setArguments(b);
                    /** Adding the fragment to the fragment transaction */
                    fragmentTransaction.replace(R.id.content_view, searchFragment, "Unique_fragment_tag");

                    /** Making this transaction in effect */
                    fragmentTransaction.commit();
                }
                else
                {
                    /** Getting the fragment manager for fragment related operations */
                    FragmentManager fragmentManager = getSupportFragmentManager();

                    /** Getting the fragmenttransaction object, which can be used to add, remove or replace a fragment */
                    FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

                    //Toast.makeText(getActivity(), "This is working!!", Toast.LENGTH_SHORT).show();
                    MainMenuFragment mainMenuFragment = new MainMenuFragment();
                    /** Adding the fragment to the fragment transaction */
                    fragmentTransaction.replace(R.id.content_view, mainMenuFragment, "Unique_fragment_tag");

                    /** Making this transaction in effect */
                    fragmentTransaction.commit();
                }
                return true;
            }
        });


        //Initializes the default fragment and prevents multiple versions of fragment
        if(savedInstanceState==null)
        {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction().add(R.id.content_view,new MainMenuFragment(),"Unique_fragment_tag");
            fragmentTransaction.commit();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.profile) {
            Intent i = new Intent(getBaseContext(),Profile.class);
            startActivity(i);
        } else if (id == R.id.language) {
            Intent i = new Intent(getBaseContext(), LanguageOptions.class);
            startActivity(i);
        } else if (id == R.id.settings) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onBackPressed() {
        if (getFragmentManager().getBackStackEntryCount() > 0) {
            getFragmentManager().popBackStack();
        } else if (searchView.isSearchOpen()) {
            searchView.closeSearch();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search_item, menu);

        MenuItem item = menu.findItem(R.id.action_search);
        searchView.setMenuItem(item);
        return true;
    }
}