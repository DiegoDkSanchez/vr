package com.example.usuario.guia6;

import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;

import com.google.vr.sdk.widgets.pano.VrPanoramaView;
import com.squareup.picasso.Callback;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PanoramicActivity extends AppCompatActivity {


    @BindView(R.id.pano_view)
    VrPanoramaView panoramaView;

    private VrPanoramaView.Options panoOptions = new VrPanoramaView.Options();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panoramic);

        ButterKnife.bind(this);

        Image panoramicImage = (Image) getIntent().getSerializableExtra("serializedimage");

        String url = MainActivity.IMAGE_BASE_URL+ panoramicImage.getUrl();

        final ImageView tmpImageView = new ImageView(this);

        Log.d(PanoramicActivity.class.getSimpleName(),"NOT FOUND" + url);

        Picasso.with(this).load(url).into(tmpImageView, new Callback() {
            @Override
            public void onSuccess() {
                Bitmap b = ((BitmapDrawable)tmpImageView.getDrawable()).getBitmap();
                panoramaView.loadImageFromBitmap(b,panoOptions);
                panoOptions.inputType = VrPanoramaView.Options.TYPE_MONO;

            }

            @Override
            public void onError() {
                Log.d(PanoramicActivity.class.getSimpleName(),"Error Loading" );
            }
        });





        //We are going to ask if GYRO is available
        PackageManager packageManager = getPackageManager();
        boolean gyroExists = packageManager.hasSystemFeature(PackageManager.FEATURE_SENSOR_GYROSCOPE);

        if(!gyroExists) {
            //Enabling only touch tracking
            panoramaView.setPureTouchTracking(true);
            Log.d(PanoramicActivity.class.getSimpleName(),"No gyro found");
        }

    }

    @Override
    protected void onPause() {
        panoramaView.pauseRendering();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        panoramaView.resumeRendering();


    }

    @Override
    protected void onDestroy() {
        panoramaView.shutdown();
        super.onDestroy();
    }
}

