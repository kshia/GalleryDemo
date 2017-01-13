package shia.kristina.gallerydemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import cz.msebera.android.httpclient.Header;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Store URLs and create adapter to display images
        final ArrayList<String> images = new ArrayList<String>();
        final ImageAdapter adapter = new ImageAdapter(this, images);

        // Parameters for Yale's Flickr account
        RequestParams params = new RequestParams();
        params.put("method", "flickr.people.getPublicPhotos");
        params.put("api_key", "3cc1361b2cf4ea990e81990559f46567");
        params.put("user_id", "12208415@N08");
        params.put("format", "json");
        params.put("nojsoncallback", "?");

        // HTTP request for images for account
        AsyncHttpClient client = new AsyncHttpClient();
        String url = "https://api.flickr.com/services/rest/";
        Log.d("API CALL", url + "?" + params);
        client.get(url, params, new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                Log.d("API CALL", response.toString());
                JSONObject photos = null;

                try {
                    photos = response.getJSONObject("photos");
                    Log.d("API CALL", photos.toString());

                    JSONArray list = photos.getJSONArray("photo");
                    Log.d("API CALL", list.toString());

                    // changes arraylist and notifies adapter
                    images.addAll(Picture.fromJSONArray(list));
                    adapter.notifyDataSetChanged();

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                Log.d("API CALL", "FAILURE");
                super.onFailure(statusCode, headers, throwable, errorResponse);
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("API CALL", "FAILURE: " + statusCode);
                super.onFailure(statusCode, headers, responseString, throwable);
            }
        });

        // Load images into GridView using adapter
        GridView gvAll = (GridView) findViewById(R.id.gvAll);
        gvAll.setAdapter(adapter);
    }
}
