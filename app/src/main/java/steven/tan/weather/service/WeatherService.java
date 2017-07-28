package steven.tan.weather.service;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import steven.tan.weather.model.Forecast;
import steven.tan.weather.model.WeatherLocation;

/**
 * Created by steventan on 26/07/17.
 */

public interface WeatherService {

    @GET("weather/")
    Observable<WeatherLocation> loadWeather(@Query("q") String location);

    @GET("forecast/daily?units=metric")
    Observable<Forecast> loadForecast(@Query("id") int id);
}
