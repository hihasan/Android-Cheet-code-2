package com.hihasan.vsmweightmachinecheckup.utils;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.hihasan.vsmweightmachinecheckup.R;

public class CustomList extends ArrayAdapter
{
    private final Activity context;
    private final String[] web;
    private final String[] web2;
    private final Integer[] imageId;
    public CustomList(Activity context,String[] web,String[]web2, Integer[] imageId)
    {
        super(context, R.layout.activity_list, web);
        this.context = context;
        this.web = web;
        this.web2=web2;
        this.imageId = imageId;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent)
    {
        LayoutInflater inflater = context.getLayoutInflater();
        View rowView= inflater.inflate(R.layout.activity_list, null, true);
        TextView txtTitle = (TextView) rowView.findViewById(R.id.name);

        TextView txtDescription=(TextView) rowView.findViewById (R.id.description);

        ImageView imageView = (ImageView) rowView.findViewById(R.id.img);
        txtTitle.setText(web[position]);
        txtDescription.setText(web2[position]);

        imageView.setImageResource(imageId[position]);
        return rowView;
    }
}
