package com.dev_abraham.google_books_api.Api;

import com.dev_abraham.google_books_api.Models.ModelResponseGoogleBooks;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ApiGoogleBooks {

    @GET("volumes")
    Call<ModelResponseGoogleBooks> getBooks(@Query("q") String query);

}
