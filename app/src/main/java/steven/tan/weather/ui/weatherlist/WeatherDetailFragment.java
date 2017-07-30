package steven.tan.weather.ui.weatherlist;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Date;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;
import steven.tan.weather.R;
import steven.tan.weather.model.City;
import steven.tan.weather.model.Weather;
import steven.tan.weather.presenter.WeatherDetailPresenter;
import steven.tan.weather.view.WeatherDetailView;

public class WeatherDetailFragment extends Fragment implements WeatherDetailView {

    public static final String EXTRA_LOCATION = "extra_location";
    public static final String EXTRA_WEATHER = "extra_weather";
    private City city;
    private Weather weather;

    @Inject
    WeatherDetailPresenter presenter;

    @BindView(R.id.location)
    TextView locationText;

    @BindView(R.id.date)
    TextView dateText;

    @BindView(R.id.temperature)
    TextView temperatureText;

    @BindView(R.id.weather_condition)
    TextView weatherConditionText;

    @BindView(R.id.wind_direction)
    TextView windDirectionText;

    @BindView(R.id.humidity)
    TextView humidityText;

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
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.onActivityCreated();
    }

    @Override
    public void setLocation(String name) {
        locationText.setText(name);
    }

    @Override
    public void setDate(Date date) {
        String formattedText = DateUtils.formatDateTime(getContext(), date.getTime(),
                DateUtils.FORMAT_SHOW_WEEKDAY | DateUtils.FORMAT_SHOW_DATE);
        dateText.setText(formattedText);
    }

    @Override
    public void setTemperature(double min, double max) {
        temperatureText.setText(getString(R.string.min_max_temperature, min, max));
    }

    @Override
    public void setWeatherCondition(String weatherCondition) {
        weatherConditionText.setText(weatherCondition);
    }

    @Override
    public void setWindDirection(String windDirection) {
        windDirectionText.setText(getString(R.string.wind_direction, windDirection));
    }

    @Override
    public void setHumidity(double humidity) {
        humidityText.setText(getString(R.string.humidity, humidity));
    }
}
