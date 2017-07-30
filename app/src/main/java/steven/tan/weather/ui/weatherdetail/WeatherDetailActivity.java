package steven.tan.weather.ui.weatherdetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import steven.tan.weather.R;
import steven.tan.weather.model.City;
import steven.tan.weather.model.Weather;

/**
 * Created by steventan on 30/07/17.
 */

public class WeatherDetailActivity extends AppCompatActivity {
    public static final String EXTRA_CITY = "extra_city";
    public static final String EXTRA_WEATHER = "extra_weather";

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_detail);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            Intent intent = getIntent();
            City city = intent.getParcelableExtra(EXTRA_CITY);
            Weather weather = intent.getParcelableExtra(EXTRA_WEATHER);
            WeatherDetailFragment fragment = WeatherDetailFragment.newInstance(city, weather);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.content, fragment)
                    .commit();
        }
    }
}
