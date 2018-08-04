package com.example.healgaren.studytodolist;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface MemoService {

    @POST("createToken")
    Call<TokenResult> createToken();

    @GET("checkToken")
    Call<ValidResult> checkToken(@Query("token") String token);

    @POST("backup")
    Call<BaseResult> backup(@Body BackupRequest body);

    @GET("backup")
    Call<GetBackupResult> getBackup(@Query("token") String token);
}


