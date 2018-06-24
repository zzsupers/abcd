package com.tntran.tryhard.api;

import com.tntran.tryhard.dialog.DialogActivity;
import com.tntran.tryhard.model.Doc;
import com.tntran.tryhard.model.ObjectArticle;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by TTN on 6/20/2018.
 */

public interface APILink {

    @GET("articlesearch.json")
    Call<ObjectArticle> getDoc(@Query( "begin_date" ) String date,
                               @Query( "sort" ) String sort,
                               @Query( "fq" ) String desk,
                               @Query("api-key" ) String api_key);
    @GET("articlesearch.json")
    Call<ObjectArticle> getBeginDate(@Query( "begin_date" ) String date,
                               @Query("api-key" ) String api_key);
}
