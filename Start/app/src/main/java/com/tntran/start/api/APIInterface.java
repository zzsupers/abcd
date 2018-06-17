package com.tntran.start.api;

import com.tntran.start.model.ListMovie;
import com.tntran.start.model.Result;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by TTN on 6/17/2018.
 */

public interface APIInterface {
    String API_KEY = "ffa283a5c3467a0ccb81cb3b88e750b6";

    String BASE_URL = "http://api.themoviedb.org";

    String BASE_IMAGES_URL = "http://image.tmdb.org/t/p/";

    String POSTER_SIZE = "w200";
    String BACKDROP = "w400";
    double VOTE_AVERAGE = 5.0;





    @GET("/3/movie/now_playing")

    Call<ListMovie> getNowPlaying(@Query("api_key") String values);

}
