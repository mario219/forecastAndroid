package com.mario219.forecastapp.presentation.presenter;

import com.mario219.forecastapp.darkskyforecast.RestWeather;
import com.mario219.forecastapp.darkskyforecast.RestWeatherCallback;
import com.mario219.forecastapp.presentation.view.contract.MainView;

/**
 * Created by marioalejndro on 10/07/17.
 */

public class MainPresenter implements RestWeatherCallback {

    private MainView view;
    private RestWeather restWeather;

    public MainPresenter(MainView view) {
        this.view = view;
        restWeather = new RestWeather(this);
    }

    public void getWeather(double latitude, double longitude) {
        restWeather.getWeather(latitude, longitude);
    }

    /**
     * RestWeatherCallback methods
     */
    @Override
    public void onRequestCompleted(String message) {
        view.onWeatherRequestSucceded(message);
    }

    @Override
    public void onRequestFailure() {

    }
}
