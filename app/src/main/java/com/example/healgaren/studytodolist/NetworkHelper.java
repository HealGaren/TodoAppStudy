package com.example.healgaren.studytodolist;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkHelper {


    private static NetworkHelper instance = null;
    public static NetworkHelper getInstance(){
        if (instance == null) instance = new NetworkHelper();
        return instance;
    }



    private Retrofit retrofit;
    public MemoService memoService;

    private NetworkHelper() {
        retrofit = new Retrofit.Builder()
                .baseUrl("http://memobackup.herokuapp.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        memoService = retrofit.create(MemoService.class);


    }
}
