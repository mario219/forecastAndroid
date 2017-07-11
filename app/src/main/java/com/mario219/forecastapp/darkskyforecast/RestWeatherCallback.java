package com.mario219.forecastapp.darkskyforecast;

/**
 * Created by marioalejndro on 10/07/17.
 */

public interface RestWeatherCallback {

    void onRequestCompleted(String message);
    void onRequestFailure();
}
