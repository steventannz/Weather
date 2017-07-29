package steven.tan.weather.ui.weatherlist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import steven.tan.weather.R;
import steven.tan.weather.model.Weather;

public class WeatherListActivity extends AppCompatActivity implements WeatherListFragment.WeatherDetailClickedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.content, new WeatherListFragment())
                .commit();
    }

    @Override
    public void onWeatherDetailClicked(String location, Weather weather) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.content, WeatherDetailFragment.newInstance(location, weather))
                .addToBackStack(null)
                .commit();
    }
}
