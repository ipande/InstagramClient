package com.ishan.instagramclient.activities;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.ishan.instagramclient.helpers.InstagramPhoto;
import com.ishan.instagramclient.adapters.InstagramPhotosAdapter;
import com.ishan.instagramclient.R;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;

public class PhotosActivity extends AppCompatActivity {
    public static final String CLIENT_ID = "e05c462ebd86446ea48a5af73769b602";
    public static final String APP_TAG = "IntaClient";

    private ArrayList<InstagramPhoto> instagramPhotos;
    private InstagramPhotosAdapter instagramPhotosAdapter;
    @Bind(R.id.swipeContainer)SwipeRefreshLayout swipeContainer;

    @Bind(R.id.lvPhotos)ListView lvphotos;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        instagramPhotos = new ArrayList<>();
        instagramPhotosAdapter = new InstagramPhotosAdapter(this,instagramPhotos);
        // Lookup the swipe container view
        ButterKnife.bind(this);

        // Setup refresh listener which triggers new data loading
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                // Make sure you call swipeContainer.setRefreshing(false)
                // once the network request has completed successfully.
                fetchPopularPhotos();
            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        lvphotos.setAdapter(instagramPhotosAdapter);

    }

    private void fetchPopularPhotos() {

        AsyncHttpClient client = new AsyncHttpClient();

        String URL = "https://api.instagram.com/v1/media/popular?client_id=" + CLIENT_ID;
        client.get(URL,null,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // Remember to CLEAR OUT old items before appending in the new ones
                instagramPhotosAdapter.clear();

                Log.d(APP_TAG,"Resp: "+response.toString());

                JSONArray photosJSON = null;

                try{
                    photosJSON = response.getJSONArray("data");

                    for(int i=0; i < photosJSON.length(); i++){
                        JSONObject photoJSON = photosJSON.getJSONObject(i);

                        InstagramPhoto photo = new InstagramPhoto();
                        photo.setUserName(photoJSON.getJSONObject("user").getString("username"));
                        photo.setCaption(photoJSON.getJSONObject("caption").getString("text"));
                        photo.setImageURL(photoJSON.getJSONObject("images").getJSONObject("standard_resolution").getString("url"));
                        photo.setImageHeight(photoJSON.getJSONObject("images").getJSONObject("standard_resolution").getInt("height"));
                        photo.setImageWidth(photoJSON.getJSONObject("images").getJSONObject("standard_resolution").getInt("width"));
                        photo.setLikesCount(photoJSON.getJSONObject("likes").getInt("count"));
                        photo.setProfilePicURL(photoJSON.getJSONObject("user").getString("profile_picture"));

                        instagramPhotos.add(photo);
                    }
                    instagramPhotosAdapter.addAll(instagramPhotos);
                    instagramPhotosAdapter.notifyDataSetChanged();
                    swipeContainer.setRefreshing(false);

                    //instagramPhotosAdapter.notifyDataSetChanged();

                }catch(JSONException e){

                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d(APP_TAG,"Failed: "+statusCode+" cause: "+throwable.toString());
            }
        });

    }
}
