package com.example.socketpsp.service;

import com.example.socketpsp.model.Ardilla;
import com.example.socketpsp.model.Poema;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.*;

public interface ApiService {
    @GET("/ardilla")
    Call<List<Ardilla>> getArdillas();

    @GET("/ardilla/{id}")
    Call<Ardilla> getArdillaById(@Path("id") int id);

    @POST("/ardilla")
    Call<Ardilla> createOrUpdateArdilla(@Body Ardilla ardilla);

    @DELETE("/ardilla/{id}")
    Call<Void> deleteArdilla(@Path("id") int id);


    @GET("/poema")
    Call<List<Poema>> getAllPoemas();

    @GET("/poema/{id}")
    Call<Poema> getPoemaById(@Path("id") int id);

    @POST("/poema")
    Call<Poema> createOrUpdatePoema(@Body Poema poema);

    @DELETE("/poema/{id}")
    Call<Void> deletePoema(@Path("id") int id);
}
