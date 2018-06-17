package com.tntran.start;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tntran.start.adapter.IClickListener;
import com.tntran.start.adapter.RecyclerViewAdapter;
import com.tntran.start.api.APIInterface;
import com.tntran.start.model.ListMovie;
import com.tntran.start.model.Result;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Debugggg";


    List<Result> movie;


    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;

    @BindView(R.id.swipeRefresh)
    SwipeRefreshLayout swipeRefreshLayout;


    RecyclerViewAdapter movieAdapter;


    AlertDialog.Builder builder;


    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate( savedInstanceState );

        setContentView( R.layout.activity_main );

        ButterKnife.bind( this );


        if (movie == null) {

            init();

            getAllMovies();

        } else {

            movieAdapter.setData( movie );

        }


    }


    /**
     * Get movie data from API
     */

    void getAllMovies() {

        Retrofit.Builder builder = new Retrofit.Builder()

                .baseUrl( APIInterface.BASE_URL )

                .addConverterFactory( GsonConverterFactory.create() );

        Retrofit retrofit = builder.build();


        APIInterface retrofitClient = retrofit.create( APIInterface.class );

        Call<ListMovie> call = retrofitClient.getNowPlaying( APIInterface.API_KEY );

        call.enqueue( new Callback<ListMovie>() {

            @Override

            public void onResponse(@NonNull Call<ListMovie> call, @NonNull Response<ListMovie> response) {

                if (response.body() != null) {

                    movieAdapter.setData( response.body().getResults() );

                }

            }


            @Override

            public void onFailure(@NonNull Call<ListMovie> call, @NonNull Throwable t) {



            }

        } );


    }


    /**
     * Initialize for RecyclerView
     */

    void init() {

        movie = new ArrayList<>();


        recyclerView.setHasFixedSize( true );


        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( this );

        recyclerView.setLayoutManager( layoutManager );


        movieAdapter = new RecyclerViewAdapter( MainActivity.this );

        movieAdapter.setData( movie );



        /*

          Handle what happened when click on item of recycler view

         */


        this.recyclerView.setAdapter( movieAdapter );



        /*

          set up swipe refresh layout

         */

        swipeRefreshLayout.setOnRefreshListener( new SwipeRefreshLayout.OnRefreshListener() {

            @Override

            public void onRefresh() {

                movieAdapter.clearData();

                getAllMovies();

                swipeRefreshLayout.setRefreshing( false );

            }

        } );

        swipeRefreshLayout.setColorSchemeResources( android.R.color.holo_blue_bright,

                android.R.color.holo_green_light,

                android.R.color.holo_orange_light,

                android.R.color.holo_red_light );

    }

}
