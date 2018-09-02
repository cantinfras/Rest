package com.example.elton.rest.endpoint;

import com.example.elton.rest.dominio.Daruinda;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AppEndpoint {

    @GET("posts/{id}")
    Call<Daruinda> obterpost(@Path("id") int postID);
}
