package com.ishan.instagramclient;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import cz.msebera.android.httpclient.Header;

public class PhotosActivity extends AppCompatActivity {
    public static final String CLIENT_ID = "e05c462ebd86446ea48a5af73769b602";
    public static final String APP_TAG = "IntaClient";

    private ArrayList<InstagramPhoto> instagramPhotos;
    private InstagramPhotosAdapter instagramPhotosAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        instagramPhotos = new ArrayList<>();
        instagramPhotosAdapter = new InstagramPhotosAdapter(this,instagramPhotos);

        fetchPopularPhotos();


        ListView lvphotos = (ListView) findViewById(R.id.lvPhotos);

        lvphotos.setAdapter(instagramPhotosAdapter);

    }

//     send nw request
//    Client ID: e05c462ebd86446ea48a5af73769b602
//
//    URL: https://api.instagram.com/v1/media/popular?access_token=ACCESS-TOKEN
//
//    JSON:
//            - Type -> (data -> [X] -> "type" - image/video)
//            - URL -> (data ->[x] -> images -> standard_resolution -> url)
//            - Caption -> (data -> [X] -> "caption" -> text)
//            - Author Name -> (data -> [X] -> user -> username)
    private void fetchPopularPhotos() {

        AsyncHttpClient client = new AsyncHttpClient();

        String URL = "https://api.instagram.com/v1/media/popular?client_id=" + CLIENT_ID;
        client.get(URL,null,new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
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
                        photo.setLikesCount(photoJSON.getJSONObject("likes").getInt("count"));

                        instagramPhotos.add(photo);
                    }

                    instagramPhotosAdapter.notifyDataSetChanged();

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
