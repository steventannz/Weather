package steven.tan.weather.ui.weatherlist;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import steven.tan.weather.R;
import steven.tan.weather.model.Weather;

/**
 * A placeholder fragment containing a simple view.
 */
public class WeatherDetailFragment extends Fragment {

    public static final String EXTRA_LOCATION = "extra_location";
    public static final String EXTRA_WEATHER = "extra_weather";

    public WeatherDetailFragment() {
    }

    public static WeatherDetailFragment newInstance(String location, Weather weather) {
        WeatherDetailFragment fragment = new WeatherDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putString(EXTRA_LOCATION, location);
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
        super.onAttach(context);
        Bundle arguments = getArguments();
        Log.d("Test", arguments.toString());
    }
}
