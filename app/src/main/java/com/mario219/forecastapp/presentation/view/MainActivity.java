package com.mario219.forecastapp.presentation.view;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.mario219.forecastapp.Connectivity;
import com.mario219.forecastapp.utils.GPSTracker;
import com.mario219.forecastapp.R;
import com.mario219.forecastapp.presentation.presenter.MainPresenter;
import com.mario219.forecastapp.presentation.view.contract.MainView;

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
    @BindView(R.id.tv_temperature)
    TextView tvTemperature;
    @BindView(R.id.tv_summary)
    TextView tvSummary;
    @BindView(R.id.tv_hummidity)
    TextView tvHummidity;

    /**
     * State
     */
    private MainPresenter presenter;

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
        tvTemperature.setText(""); tvSummary.setText(""); tvHummidity.setText("");
        presenter.getWeather(new GPSTracker(this), new Connectivity(this));
    }

    /**
     * Contract methods
     */
    @Override
    public void onWeatherRequestSucceded(String temperature, String summary, String hummidity) {
        progressBar.setVisibility(View.INVISIBLE);
        tvTemperature.setText(temperature);
        tvSummary.setText(summary);
        tvHummidity.setText(hummidity);
    }

    @Override
    public void onWeatherRequestFailure(String message) {
        //Showing message failure
        progressBar.setVisibility(View.INVISIBLE);
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void launchGPSSettings() {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(R.string.alert_title);
        alertDialog.setMessage(R.string.alert_message);

        // On pressing Settings button
        alertDialog.setPositiveButton("Settings", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog,int which) {
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);
            }
        });
        // on pressing cancel button
        alertDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        // Showing alert message
        alertDialog.show();

        // Hiding progressbar
        progressBar.setVisibility(View.INVISIBLE);
    }


}
