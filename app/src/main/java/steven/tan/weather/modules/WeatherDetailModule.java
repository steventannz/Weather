package steven.tan.weather.modules;

import dagger.Module;
import dagger.Provides;
import steven.tan.weather.model.City;
import steven.tan.weather.model.Weather;
import steven.tan.weather.presenter.WeatherDetailPresenter;
import steven.tan.weather.ui.weatherlist.WeatherDetailFragment;
import steven.tan.weather.view.WeatherDetailView;

/**
 * Created by steventan on 29/07/17.
 */
@Module
public class WeatherDetailModule {

    @Provides
    public WeatherDetailPresenter providePresenter(WeatherDetailView view, City city, Weather weather) {
        return new WeatherDetailPresenter(view, city, weather);
    }

    @Provides
    public City provideCity(WeatherDetailFragment fragment) {
        return fragment.getCity();
    }

    @Provides
    public Weather provideWeather(WeatherDetailFragment fragment) {
        return fragment.getWeather();
    }
}
