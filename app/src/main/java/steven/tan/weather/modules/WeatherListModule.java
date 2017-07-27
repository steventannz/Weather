package steven.tan.weather.modules;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import steven.tan.weather.interactor.GetForecastInteractor;
import steven.tan.weather.service.WeatherService;
import steven.tan.weather.ui.weatherlist.WeatherListFragment;
import steven.tan.weather.presenter.WeatherListPresenter;
import steven.tan.weather.view.WeatherListView;

/**
 * Created by steventan on 26/07/17.
 */
@Module
public abstract class WeatherListModule {

    @Provides
    static WeatherListPresenter providePresenter(WeatherListView view, GetForecastInteractor interactor) {
        return new WeatherListPresenter(view, interactor);
    }

    @Binds
    abstract WeatherListView bindFragmentToView(WeatherListFragment fragment);

    @Provides
    static GetForecastInteractor provideGetForecastInteractor(WeatherService service) {
        return new GetForecastInteractor(service);
    }
}
