package steven.tan.weather.presenter;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import steven.tan.weather.interactor.GetForecastInteractor;
import steven.tan.weather.model.Forecast;
import steven.tan.weather.view.WeatherListView;

/**
 * Created by steventan on 26/07/17.
 */

public class WeatherListPresenter implements Observer<Forecast> {
    private final WeatherListView view;
    private final GetForecastInteractor interactor;
    private Forecast forecast;
    private String lastQuery;
    private Disposable requestDisposable;


    public WeatherListPresenter(WeatherListView view, GetForecastInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    public void onQuerySubmitted(String query) {
        this.lastQuery = query;
        loadForecast(query);
    }

    private void loadForecast(String query) {
        view.hideError();
        view.hideList();
        view.startLoading();
        interactor.getForecast(query, this);
    }

    @Override
    public void onSubscribe(Disposable requestDisposable) {
        this.requestDisposable = requestDisposable;
    }

    @Override
    public void onNext(Forecast forecast) {
        this.forecast = forecast;
        view.setWeatherForecast(forecast.getWeather());
    }

    @Override
    public void onError(Throwable e) {
        view.showErrorMessage();
    }

    @Override
    public void onComplete() {
        view.stopLoading();
        view.showList();
    }

    public void onWeatherCardClicked(int position) {
        view.showWeatherDetail(forecast.getCity(), forecast.getWeather().get(position));
    }

    public void onRetryClicked() {
        if (lastQuery != null) {
            loadForecast(lastQuery);
        }
    }

    public void onStop() {
        if (requestDisposable != null && !requestDisposable.isDisposed()) {
            requestDisposable.dispose();
        }
    }
}
