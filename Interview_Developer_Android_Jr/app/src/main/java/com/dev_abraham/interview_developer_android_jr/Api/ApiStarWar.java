package com.dev_abraham.interview_developer_android_jr.Api;

import com.dev_abraham.interview_developer_android_jr.Models.ApiStarWarResponse;
import com.dev_abraham.interview_developer_android_jr.Models.CharacterStarWars;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiStarWar {

    @GET("people/{id}/")
    Call <CharacterStarWars> getCharacter(@Path("id") int id);

    @GET("films")
    Call <ApiStarWarResponse> getFilms();

}
