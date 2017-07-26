package steven.tan.weather.modules;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import steven.tan.weather.service.WeatherService;

/**
 * Created by steventan on 26/07/17.
 */
@Module
public class ServicesModule {

    @Provides
    @Singleton
    WeatherService provideWeatherService(Retrofit retrofit) {
        return retrofit.create(WeatherService.class);
    }
}
