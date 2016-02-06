package com.ishan.instagramclient;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by ishanpande on 2/4/16.
 */
public class InstagramPhotosAdapter extends ArrayAdapter<InstagramPhoto>{
    public InstagramPhotosAdapter(Context context, List<InstagramPhoto> instagramPhotos) {
        super(context, 0, instagramPhotos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        InstagramPhoto photo = getItem(position);



        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.instalayoutitem, parent, false);
        }
        // Lookup view for data population
        TextView tvUserName = (TextView) convertView.findViewById(R.id.tvInstaUser);
        TextView tvCaption = (TextView) convertView.findViewById(R.id.tvCaption);
        ImageView ivPhoto = (ImageView) convertView.findViewById(R.id.ivInstaImage);

        // Populate the data into the template view using the data object
        tvUserName.setText(photo.getUserName());
        tvCaption.setText(photo.getCaption());

        // clear prev image (if any)
        ivPhoto.setImageResource(0);
        //Insert image using picasso

        Picasso.with(getContext()).load(photo.getImageURL()).into(ivPhoto);

        // Return the completed view to render on screen

        return convertView;
    }
}
