package com.retreat.shebuel.spitraining.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.retreat.shebuel.spitraining.Activities.InstructionVideo;
import com.retreat.shebuel.spitraining.R;

import java.util.List;

/**
 * Created by Shebuel on 11-06-2017 at 17:04.
 * Final Edits made on com.retreat.shebuel.spitraining
 */

public class SearchFragment extends ListFragment {

    private List<String> item;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Bundle b = getArguments();
        item=b.getStringArrayList("result");
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.listitem, item);
        setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        /** Getting the fragment manager for fragment related operations */
        FragmentManager fragmentManager = getFragmentManager();

        /** Getting the fragmenttransaction object, which can be used to add, remove or replace a fragment */
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        if(item.get(position)=="Policy")
        {
            //TODO:Fragement for Policy
        }
        else if(item.get(position)=="Benefits")
        {
            //TODO:Fragemnt for Benefits
        }
        else if (item.get(position)=="Facility")
        {
            //TODO:Remove the Toast when the app is done :)
            Toast.makeText(getActivity(), "This is working!!", Toast.LENGTH_SHORT).show();

            FacilityMenuFragment fragment = new FacilityMenuFragment();
            /** Adding the fragment to the fragment transaction */
            fragmentTransaction.replace(R.id.content_view, fragment, "Unique_fragment_tag");
            /** Adding this transaction to backstack */
            fragmentTransaction.addToBackStack(null);

            /** Making this transaction in effect */
            fragmentTransaction.commit();

        }
        else if (item.get(position)=="Employee Empowerment")
        {
            //TODO:Remove the Toast when the app is done :)
            Toast.makeText(getActivity(), "This is working!!", Toast.LENGTH_SHORT).show();
            EEMenuFragment fragment = new EEMenuFragment();
            /** Adding the fragment to the fragment transaction */
            fragmentTransaction.replace(R.id.content_view, fragment, "Unique_fragment_tag");
            /** Adding this transaction to backstack */
            fragmentTransaction.addToBackStack(null);

            /** Making this transaction in effect */
            fragmentTransaction.commit();
        }
        else {
            Intent i = new Intent(getActivity(),InstructionVideo.class);
            i.putExtra("option",item.get(position));
            startActivity(i);
        }
    }
}
