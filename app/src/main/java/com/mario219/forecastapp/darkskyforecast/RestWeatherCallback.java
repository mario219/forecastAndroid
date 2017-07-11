package com.mario219.forecastapp.darkskyforecast;

import com.mario219.forecastapp.models.Forecast;

/**
 * Created by marioalejndro on 10/07/17.
 */

public interface RestWeatherCallback {

    void onRequestCompleted(Forecast object);
    void onRequestFailure(String message);
}
