package steven.tan.weather.modules;

import dagger.Binds;
import dagger.Module;
import dagger.Provides;
import steven.tan.weather.ui.weatherlist.WeatherListFragment;
import steven.tan.weather.presenter.WeatherListPresenter;
import steven.tan.weather.view.WeatherListView;

/**
 * Created by steventan on 26/07/17.
 */
@Module
public abstract class WeatherListModule {

    @Provides
    static WeatherListPresenter providePresenter(WeatherListView view) {
        return new WeatherListPresenter(view);
    }

    @Binds
    abstract WeatherListView bindFragmentToView(WeatherListFragment fragment);
}
