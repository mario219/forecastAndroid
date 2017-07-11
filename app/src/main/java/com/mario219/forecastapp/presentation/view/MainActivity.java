package com.mario219.forecastapp.presentation.view;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.mario219.forecastapp.R;
import com.mario219.forecastapp.presentation.presenter.MainPresenter;
import com.mario219.forecastapp.presentation.view.contract.MainView;

import java.util.concurrent.Executor;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity implements MainView{

    /**
     * UI
     */
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    /**
     * State
     */
    private MainPresenter presenter;
    private LocationManager locationManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        setSupportActionBar(toolbar);
        presenter = new MainPresenter(this);

    }

    @OnClick(R.id.btnGetWeather)
    public void onGetWeatherClick(){
        progressBar.setVisibility(View.VISIBLE);
        presenter.getWeather(10.389698, -75.521873);
    }

    /**
     * Contract methods
     */
    @Override
    public void onWeatherRequestSucceded(String message) {
        progressBar.setVisibility(View.INVISIBLE);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }


}
