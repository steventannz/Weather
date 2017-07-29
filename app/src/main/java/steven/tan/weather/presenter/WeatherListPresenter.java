package steven.tan.weather.presenter;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import steven.tan.weather.interactor.GetForecastInteractor;
import steven.tan.weather.model.Forecast;
import steven.tan.weather.model.Weather;
import steven.tan.weather.view.WeatherListView;

/**
 * Created by steventan on 26/07/17.
 */

public class WeatherListPresenter implements Observer<Forecast> {
    private final WeatherListView view;
    private final GetForecastInteractor interactor;
    private Forecast forecast;


    public WeatherListPresenter(WeatherListView view, GetForecastInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    public void onActivityCreated() {
        updateViewForecast();
    }

    public void onQuerySubmitted(String query) {
        interactor.getForecast(query, this);
    }

    @Override
    public void onSubscribe(Disposable d) {

    }

    @Override
    public void onNext(Forecast forecast) {
        this.forecast = forecast;
        updateViewForecast();
    }

    private void updateViewForecast() {
        if (forecast != null) {
            view.setWeatherForecast(forecast.getWeather());
        }
    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }

    public void onWeatherCardClicked(Weather weather) {
        view.showWeatherDetail(forecast.getCity().getName(), weather);
    }
}
