package steven.tan.weather.component;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import steven.tan.weather.ui.weatherlist.WeatherListFragment;
import steven.tan.weather.modules.WeatherListModule;

/**
 * Created by steventan on 26/07/17.
 */
@Subcomponent(modules = {WeatherListModule.class})
public interface WeatherListSubComponent extends AndroidInjector<WeatherListFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<WeatherListFragment>{}
}
