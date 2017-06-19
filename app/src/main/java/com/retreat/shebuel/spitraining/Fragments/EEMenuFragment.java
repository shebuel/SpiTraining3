package com.retreat.shebuel.spitraining.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.retreat.shebuel.spitraining.Activities.InstructionVideo;
import com.retreat.shebuel.spitraining.R;

/**
 * Created by Shebuel on 09-06-2017 at 14:26.
 * Final Edits made on com.retreat.shebuel.spitraining
 */

public class EEMenuFragment extends ListFragment {
    String[] item = new String[]{getString(R.string.staff_screening),getString(R.string.birthday),getString(R.string.team_outing)};
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.listitem,item);
        setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Intent i = new Intent(getActivity(),InstructionVideo.class);
        i.putExtra("option",item[position]);
        startActivity(i);
    }
}
