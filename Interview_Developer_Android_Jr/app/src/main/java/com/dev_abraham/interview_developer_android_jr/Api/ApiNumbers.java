package com.dev_abraham.interview_developer_android_jr.Api;

import com.dev_abraham.interview_developer_android_jr.Models.ApiNumberResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiNumbers {


    @GET("{id}/trivia")
    Call<ApiNumberResponse> getTrivia(@Path("id") int numero, @Query("json") int json);

    @GET("{month}/{day}/date")
    Call<ApiNumberResponse> getDate(@Path("month") int month, @Path("day") int day,@Query("json") int json);

    @GET("{year}/year")
    Call<ApiNumberResponse> getYear(@Path("year") int year,@Query("json") int json);

}
