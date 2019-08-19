package com.nguyennhat.project1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DetailActivity extends AppCompatActivity {
    private TextView movieName;
    private ImageView imgView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        movieName = (TextView) findViewById(R.id.MovieName);
        String value = getIntent().getStringExtra("key");
        movieName.setText(value);
        imgView = (ImageView) findViewById(R.id.MovieImage);
        imgView.setImageResource(getIntent().getIntExtra("img", R.mipmap.anhmovie));

    }
}
