package com.tntran.tryhard.article.repository;

import android.util.Log;

import com.tntran.tryhard.adapter.AdapterRecyclerView;
import com.tntran.tryhard.model.Doc;
import com.tntran.tryhard.model.ObjectArticle;
import com.tntran.tryhard.utils.RetrofitUtil;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by TTN on 6/21/2018.
 */

public class DocRepository implements  Repository {
    RetrofitUtil retrofitUtil;
    ObjectArticle objectArticle;
    List<Doc> docList;
    AdapterRecyclerView adapter;

    public void callAPI(final DataListener listener) {

        RetrofitUtil.getInstance().getBeginDate( "20160101", RetrofitUtil.API_KEY).enqueue( new Callback<ObjectArticle>() {
            @Override
            public void onResponse(Call<ObjectArticle> call, Response<ObjectArticle> response) {
                Log.d( "xxx",call.request().toString() );
                if(response.body() != null) {
                    objectArticle = response.body();

                    docList = objectArticle.getResponse().getDocs();

                    listener.onResponse(docList);

                }
            }

            @Override
            public void onFailure(Call<ObjectArticle> call, Throwable t) {
                listener.onError(t.getMessage());
            }
        } );


    }
}
