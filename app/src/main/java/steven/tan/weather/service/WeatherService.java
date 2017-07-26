package steven.tan.weather.service;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;
import steven.tan.weather.model.Forecast;
import steven.tan.weather.model.Weather;

/**
 * Created by steventan on 26/07/17.
 */

public interface WeatherService {

    @GET("weather/")
    Observable<Weather> loadWeather(@Query("q") String location);

    @GET("forecast/daily")
    Observable<Forecast> loadForecast(@Query("id") int id);
}
