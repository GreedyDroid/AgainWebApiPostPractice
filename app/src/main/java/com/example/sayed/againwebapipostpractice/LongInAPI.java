package com.example.sayed.againwebapipostpractice;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * Created by nurud on 10/1/2017.
 */

public interface LongInAPI {

    @POST("meal/api/users/post")
    Call<LogInResponse> logInUser(@Body LogInModel logInModel);
}
