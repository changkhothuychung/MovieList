package com.nguyennhat.project1;

import android.widget.ImageView;

public class NewMovie {
    private String movieName;
    private String releaseDate;
    private int imgView;
    public NewMovie(String movieName, String releaseDate, String rating, String overview, int imgView){
        this.movieName = movieName;
        this.releaseDate = releaseDate;
        this.rating = rating;
        this.overview = overview;
        this.imgView = imgView;
    }
    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setImgView(int imgView){this.imgView = imgView;}

    public int getImgView() {return imgView;}

    private String rating;
    private String overview;

}
