package com.retreat.shebuel.spitraining;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

/**
 * Created by Shebuel on 07-06-2017 at 11:32.
 * Final Edits made on com.retreat.shebuel.spitraining
 */

public class CustomList extends ArrayAdapter<String>{
    private final Activity context;
    private final String[] web;
    private final Integer[] imageId;
    public CustomList(Activity context, String[] web,Integer[] imageId)
    {
        super(context,R.layout.list_single,web);
        this.context=context;
        this.web=web;
        this.imageId=imageId;
    }
    public View getView(int position, View view, ViewGroup parent)
    {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView = inflater.inflate(R.layout.list_single,null);
        ImageView imageView = (ImageView)rowView.findViewById(R.id.img);
        imageView.setImageResource(imageId[position]);
        return rowView;
    }
}
