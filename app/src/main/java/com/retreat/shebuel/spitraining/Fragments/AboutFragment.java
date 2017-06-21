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
 * Created by Shebuel on 21-06-2017 at 13:36.
 * Final Edits made on com.retreat.shebuel.spitraining.Fragments
 */
public class AboutFragment extends ListFragment {
    String[] item ;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        item = new String[]{getString(R.string.values)};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), R.layout.listitem,item);
        setListAdapter(adapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Intent i = new Intent(getActivity(),InstructionVideo.class);
        i.putExtra("option","Values");
        startActivity(i);
    }
}
