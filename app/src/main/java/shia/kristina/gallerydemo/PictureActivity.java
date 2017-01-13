package shia.kristina.gallerydemo;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class PictureActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        // Display larger photo
        Intent i = getIntent();
        String url = i.getStringExtra("url");
        ImageView ivFull = (ImageView) findViewById(R.id.ivFull);
        Picasso.with(this).load(url).into(ivFull);
    }
}
