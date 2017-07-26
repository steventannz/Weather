package steven.tan.weather.modules;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import steven.tan.weather.app.WeatherApplication;
import steven.tan.weather.component.WeatherListSubComponent;

/**
 * Created by steventan on 26/07/17.
 */
@Module(subcomponents = {WeatherListSubComponent.class})
public class AppModule {
    @Provides
    static Context provideContext(WeatherApplication application) {
        return application.getApplicationContext();
    }
}
