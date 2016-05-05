package com.ishan.instagramclient.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ishan.instagramclient.helpers.DeviceResolutionHelper;
import com.ishan.instagramclient.helpers.InstagramPhoto;
import com.ishan.instagramclient.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by ishanpande on 2/4/16.
 */
public class InstagramPhotosAdapter extends ArrayAdapter<InstagramPhoto>{
    ViewHolder holder;
    public InstagramPhotosAdapter(Context context, List<InstagramPhoto> instagramPhotos) {
        super(context, 0, instagramPhotos);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        InstagramPhoto photo = getItem(position);



        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.instalayoutitem, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        }
        
        // Use a view holder and ButterKnife annotations
        // To avoid findViewByID every time
        holder = (ViewHolder) convertView.getTag();

        // Populate the data into the template view using the data object
        holder.tvUserName.setText(photo.getUserName());
        holder.tvCaption.setText(photo.getCaption());

        holder.tvNumLikes.setText(" "+photo.getLikesCount());

        // clear prev image (if any)
        holder.ivPhoto.setImageResource(0);
        holder.ivProfilePic.setImageResource(0);

        //Insert image using picasso

        Picasso.with(getContext()).load(photo.getImageURL()).into(holder.ivPhoto);
        int displayWidth = DeviceResolutionHelper.getDisplayWidth(getContext());
        int imageTargetHeight = (photo.getImageWidth())*(displayWidth)/photo.getImageWidth();

        holder.ivPhoto.getLayoutParams().height = imageTargetHeight;
        Picasso.with(getContext()).load(photo.getProfilePicURL()).
                placeholder(R.drawable.loading_spinner).into(holder.ivProfilePic);

        // Return the completed view to render on screen

        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.tvInstaUser) TextView tvUserName;
        @Bind(R.id.tvCaption) TextView tvCaption;
        @Bind(R.id.ivInstaImage) ImageView ivPhoto;
        @Bind(R.id.ivProfilePic) ImageView ivProfilePic;
        @Bind(R.id.tvNumLikes) TextView tvNumLikes;


        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
