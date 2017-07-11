package com.mario219.forecastapp.darkskyforecast;

import com.mario219.forecastapp.models.Forecast;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by marioalejndro on 10/07/17.
 */

public interface Api {

    @GET("forecast/a4518a44109e9dc604142063069f91e0/{latitude},{longitude}")
    Call<Forecast> getForecast(@Path("latitude") double latitude, @Path ("longitude") double longitude);

}
