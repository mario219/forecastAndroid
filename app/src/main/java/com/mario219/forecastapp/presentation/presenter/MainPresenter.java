package com.mario219.forecastapp.presentation.presenter;

import com.mario219.forecastapp.Connectivity;
import com.mario219.forecastapp.utils.GPSTracker;
import com.mario219.forecastapp.darkskyforecast.RestWeather;
import com.mario219.forecastapp.darkskyforecast.RestWeatherCallback;
import com.mario219.forecastapp.models.Forecast;
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

    public void getWeather(GPSTracker tracker, Connectivity connectivity) {
        if(connectivity.isOnline()){
            if(tracker.canGetLocation()){
                restWeather.getWeather(tracker.getLatitude(), tracker.getLongitude());
            }else{
                view.launchGPSSettings();
            }
        }else{
            view.onWeatherRequestFailure("You don´ have an internet connection");
        }
    }

    /**
     * RestWeatherCallback methods
     */
    @Override
    public void onRequestCompleted(Forecast forecast) {
        view.onWeatherRequestSucceded(
                String.valueOf(forecast.getCurrently().getTemperature()),
                forecast.getCurrently().getSummary().toString(),
                String.valueOf(forecast.getCurrently().getHumidity())
        );
    }

    @Override
    public void onRequestFailure(String message) { view.onWeatherRequestFailure(message); }
}
