package steven.tan.weather.interactor;


import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import steven.tan.weather.model.Forecast;
import steven.tan.weather.service.WeatherService;

/**
 * Created by steventan on 26/07/17.
 */

public class GetForecastInteractor {
    private final WeatherService service;

    public GetForecastInteractor(WeatherService service) {

        this.service = service;
    }

    public void getForecast(String city, Observer<Forecast> observer) {
        service.loadWeather(city)
                .flatMap(location -> service.loadForecast(location.getId()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observer);
    }
}
