package com.nguyennhat.project1;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.TextView;
import android.widget.VideoView;

public class DetailActivity extends AppCompatActivity {
    private TextView movieName;
    private ImageView imgView;
    private Button btn;
    private VideoView video;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        btn = (Button) findViewById(R.id.detailwatch);
        video = (VideoView) findViewById(R.id.videoview);
        movieName = (TextView) findViewById(R.id.MovieName);
        String value = getIntent().getStringExtra("key");
        movieName.setText(value);
        imgView = (ImageView) findViewById(R.id.MovieImage);
        imgView.setImageResource(getIntent().getIntExtra("img", R.mipmap.anhmovie));


        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                video.setVisibility(View.VISIBLE);
                MediaController mediaC = new MediaController(DetailActivity.this);
                String videopath = "android.resource://com.nguyennhat.project1/" + R.raw.project1demo;
                Uri uri = Uri.parse(videopath);
                video.setVideoURI(uri);
                video.setMediaController(mediaC);
                video.start();
            }
        });
    }
}
