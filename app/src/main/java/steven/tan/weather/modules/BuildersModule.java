package steven.tan.weather.modules;

import android.support.v4.app.Fragment;

import dagger.Binds;
import dagger.Module;
import dagger.android.AndroidInjector;
import dagger.android.support.FragmentKey;
import dagger.multibindings.IntoMap;
import steven.tan.weather.ui.weatherlist.WeatherListFragment;
import steven.tan.weather.component.WeatherListSubComponent;

/**
 * Created by steventan on 26/07/17.
 */
@Module
public abstract class BuildersModule {

    @Binds
    @IntoMap
    @FragmentKey(WeatherListFragment.class)
    abstract AndroidInjector.Factory<? extends Fragment> bindWeatherListFragmentInjectorFactory(WeatherListSubComponent.Builder builder);
}
