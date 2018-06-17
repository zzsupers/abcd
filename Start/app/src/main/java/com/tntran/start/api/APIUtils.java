package com.tntran.start.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by TTN on 6/17/2018.
 */

public class APIUtils {
    private static APIInterface interfaceApi;


    public static APIInterface getApi() {

        if (interfaceApi == null) {

            Retrofit retrofit = new Retrofit.Builder()

                    .baseUrl( APIInterface.BASE_URL )

                    .addConverterFactory( GsonConverterFactory.create() )

                    .build();

            interfaceApi = retrofit.create( APIInterface.class );

        }

        return interfaceApi;
    }
}
