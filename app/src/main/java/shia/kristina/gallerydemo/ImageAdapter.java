package shia.kristina.gallerydemo;

/**
 * Created by Kristina on 1/13/17.
 */

import android.widget.*;
import java.util.*;
import android.view.*;
import android.content.*;

import com.squareup.picasso.Picasso;

public class ImageAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<String> urls;

    public ImageAdapter(Context context, ArrayList<String> urls) {
        this.context = context;
        this.urls = urls;
    }

    public int getCount() {
        return this.urls.size();
    }

    public Object getItem(int position) {
        return null;
    }

    public long getItemId(int position) {
        return 0;
    }

    public View getView(final int position, final View convertView, ViewGroup parent) {
        ImageView imageView;
        if (convertView == null) {
            // Create new ImageView
            imageView = new ImageView(this.context);

            // Calculate size according to dp
            int px =  Math.round(100 * context.getResources().getDisplayMetrics().density);
            imageView.setLayoutParams(new GridView.LayoutParams(px, px));
            imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        } else {
            imageView = (ImageView) convertView;
        }

        // Start new activity with larger view of photo
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, PictureActivity.class);
                i.putExtra("url", urls.get(position));
                context.startActivity(i);
            }
        });

        Picasso.with(context).load(this.urls.get(position)).into(imageView);
        return imageView;
    }

}
