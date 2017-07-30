package steven.tan.weather.modules;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import steven.tan.weather.model.City;
import steven.tan.weather.model.Weather;
import steven.tan.weather.presenter.WeatherDetailPresenter;
import steven.tan.weather.ui.weatherdetail.WeatherDetailFragment;
import steven.tan.weather.view.WeatherDetailView;

/**
 * Created by steventan on 29/07/17.
 */
@Module
public abstract class WeatherDetailModule {

    @Provides
    static WeatherDetailPresenter providePresenter(WeatherDetailView view, City city, Weather weather) {
        return new WeatherDetailPresenter(view, city, weather);
    }

    @Binds
    abstract WeatherDetailView bindFragmentToView(WeatherDetailFragment fragment);

    @Provides
    static City provideCity(WeatherDetailFragment fragment) {
        return fragment.getCity();
    }

    @Provides
    static Weather provideWeather(WeatherDetailFragment fragment) {
        return fragment.getWeather();
    }
}
