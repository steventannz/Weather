package steven.tan.weather.component;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjectionModule;
import steven.tan.weather.app.WeatherApplication;
import steven.tan.weather.modules.AppModule;
import steven.tan.weather.modules.BuildersModule;
import steven.tan.weather.modules.RetrofitModule;
import steven.tan.weather.modules.ServicesModule;

/**
 * Created by steventan on 26/07/17.
 */

@Singleton
@Component(modules = {AndroidInjectionModule.class, AppModule.class, BuildersModule.class, ServicesModule.class, RetrofitModule.class})
public interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(WeatherApplication application);
        AppComponent build();
    }

    void inject(WeatherApplication application);
}
