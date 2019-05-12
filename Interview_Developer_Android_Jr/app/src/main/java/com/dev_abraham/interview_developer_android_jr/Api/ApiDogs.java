package com.dev_abraham.interview_developer_android_jr.Api;

import com.dev_abraham.interview_developer_android_jr.Models.ApiDogsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiDogs {


    @GET("random")
    Call<ApiDogsResponse> getDog();
}
