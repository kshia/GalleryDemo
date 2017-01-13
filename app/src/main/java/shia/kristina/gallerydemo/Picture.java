package shia.kristina.gallerydemo;

/**
 * Created by Kristina on 1/13/17.
 */

import android.util.Log;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by kshia on 6/20/16.
 */
public class Picture implements Serializable {

    // Parse Picture info
    // In separate class to allow for additional attributes to be used (ex. description, etc)
    public static ArrayList<String> fromJSONArray(JSONArray array) {
        ArrayList<String> results = new ArrayList<>();

        for (int x = 0; x < array.length(); x++) {
            try {
                // https://farm{farm-id}.staticflickr.com/{server-id}/{id}_{secret}.jpg
                JSONObject photo = array.getJSONObject(x);
                Log.d("API CALL", photo.toString());

                // Generate url for image
                String farm = Integer.toString(photo.getInt("farm"));
                String server = photo.getString("server");
                String id = photo.getString("id");
                String secret = photo.getString("secret");

                String url = "https://farm" + farm + ".staticflickr.com/" + server + "/" + id + "_" + secret + ".jpg";

                Log.d("API CALL", url);

                results.add(url);

            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return results;
    }
}