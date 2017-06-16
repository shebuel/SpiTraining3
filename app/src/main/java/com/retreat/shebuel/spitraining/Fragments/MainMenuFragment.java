package com.retreat.shebuel.spitraining.Fragments;

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

import com.retreat.shebuel.spitraining.Fragments.EEMenuFragment;
import com.retreat.shebuel.spitraining.Fragments.FacilityMenuFragment;
import com.retreat.shebuel.spitraining.Fragments.FaqFragment;
import com.retreat.shebuel.spitraining.R;

/**
 * Created by Shebuel on 08-06-2017 at 19:59.
 * Final Edits made on com.retreat.shebuel.spitraining
 */

public class MainMenuFragment extends ListFragment {
    //ListFragmentItemClickListener listFragmentItemClickListener;
    public String[] item = new String[]{"Policy", "Benefits", "Facility", "Employee Empowerment","FAQs"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), R.layout.listitem, item);
        setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

  /* List click listener*/
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        /** Getting the fragment manager for fragment related operations */
        FragmentManager fragmentManager = getFragmentManager();

        /** Getting the fragmenttransaction object, which can be used to add, remove or replace a fragment */
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

        if (position == 2) {

            FacilityMenuFragment fragment = new FacilityMenuFragment();
            /** Adding the fragment to the fragment transaction */
            fragmentTransaction.replace(R.id.content_view, fragment, "Unique_fragment_tag");


        }
        else if(position==3)
        {
            EEMenuFragment fragment = new EEMenuFragment();
            /** Adding the fragment to the fragment transaction */
            fragmentTransaction.replace(R.id.content_view, fragment, "Unique_fragment_tag");

        }
        else if(position==4)
        {
            FaqFragment fragment = new FaqFragment();
            /** Adding the fragment to the fragment transaction */
            fragmentTransaction.replace(R.id.content_view, fragment, "Unique_fragment_tag");

        }
        /** Adding this transaction to backstack */
        fragmentTransaction.addToBackStack(null);

        /** Making this transaction in effect */
        fragmentTransaction.commit();
    }

}
