package steven.tan.weather.component;

import dagger.Subcomponent;
import dagger.android.AndroidInjector;
import steven.tan.weather.modules.WeatherDetailModule;
import steven.tan.weather.ui.weatherdetail.WeatherDetailFragment;

/**
 * Created by steventan on 29/07/17.
 */
@Subcomponent(modules = {WeatherDetailModule.class})
public interface WeatherDetailSubComponent extends AndroidInjector<WeatherDetailFragment> {

    @Subcomponent.Builder
    abstract class Builder extends AndroidInjector.Builder<WeatherDetailFragment>{}
}
