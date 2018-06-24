package com.tntran.tryhard.utils;

import android.util.Log;

import com.tntran.tryhard.api.APILink;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by TTN on 6/25/2018.
 */

public class RetrofitUtil {
    private static APILink instance = null;
    private static final String BASE_URL = "https://api.nytimes.com/svc/search/v2/";
    public static final String BASE_URL_IMAGE = "https://www.nytimes.com/";
    public static String API_KEY = "096eeb45be864eff85316843895bc9d7";



    public static APILink getInstance() {

        if (instance == null) {

            synchronized (APILink.class) {

                if (instance == null) {
                    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();

                    httpClient.writeTimeout(15 * 60 * 1000, TimeUnit.MILLISECONDS);

                    httpClient.readTimeout(60 * 1000, TimeUnit.MILLISECONDS);

                    httpClient.connectTimeout(20 * 1000, TimeUnit.MILLISECONDS);

                    HttpLoggingInterceptor logging = new HttpLoggingInterceptor(

                            new HttpLoggingInterceptor.Logger() {

                                @Override

                                public void log(String message) {

                                    Log.d("API", message);

                                }

                            }

                    );

                    logging.setLevel(HttpLoggingInterceptor.Level.BODY);

                    httpClient.addNetworkInterceptor(logging);



                    OkHttpClient client = httpClient.build();

                    Retrofit retrofit = new Retrofit.Builder()

                            .baseUrl(BASE_URL)

                            .addConverterFactory( GsonConverterFactory.create())
                            .client( client )

                            .build();

                    instance = retrofit.create(APILink.class);

                }

            }

        }



        return instance;

    }
}
