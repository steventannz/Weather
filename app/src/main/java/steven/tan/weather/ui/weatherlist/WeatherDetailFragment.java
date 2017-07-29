package steven.tan.weather.ui.weatherlist;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import dagger.android.support.AndroidSupportInjection;
import steven.tan.weather.R;
import steven.tan.weather.model.City;
import steven.tan.weather.model.Weather;

public class WeatherDetailFragment extends Fragment {

    public static final String EXTRA_LOCATION = "extra_location";
    public static final String EXTRA_WEATHER = "extra_weather";
    private City city;
    private Weather weather;

    public WeatherDetailFragment() {
    }

    public static WeatherDetailFragment newInstance(City location, Weather weather) {
        WeatherDetailFragment fragment = new WeatherDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(EXTRA_LOCATION, location);
        bundle.putParcelable(EXTRA_WEATHER, weather);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weather_detail, container, false);
    }

    @Override
    public void onAttach(Context context) {
        Bundle arguments = getArguments();
        city = arguments.getParcelable(EXTRA_LOCATION);
        weather = arguments.getParcelable(EXTRA_WEATHER);
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    public City getCity() {
        return city;
    }

    public Weather getWeather() {
        return weather;
    }
}
