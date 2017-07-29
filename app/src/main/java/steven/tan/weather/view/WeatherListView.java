package steven.tan.weather.view;

import java.util.List;

import steven.tan.weather.model.Weather;

/**
 * Created by steventan on 26/07/17.
 */

public interface WeatherListView {
    void setWeatherForecast(List<Weather> weather);

    void showWeatherDetail(String location, Weather weather);
}
