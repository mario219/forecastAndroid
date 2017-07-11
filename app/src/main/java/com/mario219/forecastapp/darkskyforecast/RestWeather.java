package com.mario219.forecastapp.darkskyforecast;

import android.util.Log;

import com.mario219.forecastapp.R;
import com.mario219.forecastapp.models.Forecast;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by marioalejndro on 10/07/17.
 */

public class RestWeather {

    private static final String URL = "https://api.darksky.net/";
    private static final String TAG = RestWeather.class.getSimpleName();
    private RestWeatherCallback callback;

    public RestWeather(RestWeatherCallback callback) {
        this.callback = callback;
    }

    public void getWeather (double latitude, double longitude){
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        Api service = retrofit.create(Api.class);

        Call<Forecast> call = service.getForecast(latitude, longitude);
        call.enqueue(new Callback<Forecast>(){

            @Override
            public void onResponse(Call<Forecast> call, Response<Forecast> response) {
                Log.i(TAG, response.toString());
                callback.onRequestCompleted(response.body());
            }

            @Override
            public void onFailure(Call<Forecast> call, Throwable t) {
                t.printStackTrace();
                callback.onRequestFailure(""+R.string.messageInfo);
            }
        });
    }

}
