package steven.tan.weather.presenter;

import steven.tan.weather.model.City;
import steven.tan.weather.model.Weather;
import steven.tan.weather.view.WeatherDetailView;

/**
 * Created by steventan on 29/07/17.
 */

public class WeatherDetailPresenter {
    private final WeatherDetailView view;
    private final City city;
    private final Weather weather;

    public WeatherDetailPresenter(WeatherDetailView view, City city, Weather weather) {

        this.view = view;
        this.city = city;
        this.weather = weather;
    }

    public void onActivityCreated() {
        view.setLocation(city.getName());
        view.setDate(weather.getDate());
        view.setTemperature(weather.getTemperature().getMin(), weather.getTemperature().getMax());
        view.setWeatherCondition(weather.getWeatherCondition().get(0).getMain());
        view.setWindDirection(weather.getWindDirection());
        view.setHumidity(weather.getHumidity());
    }
}
