package com.mario219.forecastapp.presentation.view.contract;

/**
 * Created by marioalejndro on 10/07/17.
 */

public interface MainView {

    void onWeatherRequestSucceded(String temperature, String summary, String hummidity);
    void onWeatherRequestFailure(String message);
    void launchGPSSettings();

}
