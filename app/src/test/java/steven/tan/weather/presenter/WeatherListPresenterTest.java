package steven.tan.weather.presenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;

import io.reactivex.disposables.Disposable;
import steven.tan.weather.interactor.GetForecastInteractor;
import steven.tan.weather.model.City;
import steven.tan.weather.model.Forecast;
import steven.tan.weather.model.Weather;
import steven.tan.weather.view.WeatherListView;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by steventan on 27/07/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class WeatherListPresenterTest {

    private WeatherListPresenter presenter;

    @Mock
    WeatherListView view;

    @Mock
    GetForecastInteractor interactor;

    @Mock
    City city;

    @Mock
    Weather weather;

    @Mock
    Forecast forecast;

    @Mock
    List<Weather> weatherList;

    @Mock
    Throwable throwable;

    @Mock
    Disposable disposable;

    @Before
    public void setup() {
        presenter = new WeatherListPresenter(view, interactor);
        when(forecast.getCity()).thenReturn(city);
        when(forecast.getWeather()).thenReturn(weatherList);
        when(disposable.isDisposed()).thenReturn(false);
    }

    @Test
    public void shouldRequestForForecastOnQuerySubmitted() {
        final String query = "Wellington";
        presenter.onQuerySubmitted(query);
        verify(interactor).getForecast(query, presenter);
    }

    @Test
    public void shouldSetWeatherDataOnViewWhenDataRetrieved() {
        presenter.onNext(forecast);
        verify(view).setWeatherForecast(weatherList);
    }

    @Test
    public void shouldOpenDetailOnWeatherCardClicked() {
        presenter.onNext(forecast);
        final int position = 2;
        when(weatherList.get(position)).thenReturn(weather);

        presenter.onWeatherCardClicked(position);
        verify(view).showWeatherDetail(forecast.getCity(), weather);
    }

    @Test
    public void shouldShowLoadingDialogOnQuerySubmitted() {
        presenter.onQuerySubmitted("Wellington");
        verify(view).startLoading();
    }

    @Test
    public void shouldHideListOnQuerySubmitted() {
        presenter.onQuerySubmitted("Wellington");
        verify(view).hideList();
    }

    @Test
    public void shouldHideLoadingDialogOnDataRetrieved() {
        presenter.onComplete();
        verify(view).stopLoading();
    }

    @Test
    public void shouldShowListOnDataRetrieved() {
        presenter.onComplete();
        verify(view).showList();
    }

    @Test
    public void shouldShowErrorOnLoadFailed() {
        presenter.onError(throwable);
        verify(view).showErrorMessage();
    }

    @Test
    public void shouldRetryLastQueryAttemptOnRetryClicked() {
        final String query = "Wellington";
        presenter.onQuerySubmitted(query);
        presenter.onRetryClicked();
        verify(interactor, times(2)).getForecast(query, presenter);
    }

    @Test
    public void shouldHideErrorAfterQuerySubmitted() {
        presenter.onQuerySubmitted("Wellington");
        verify(view).hideError();
    }

    @Test
    public void shouldHideErrorAfterRetry() {
        presenter.onQuerySubmitted("Wellington");
        presenter.onRetryClicked();
        verify(view, times(2)).hideError();
    }

    @Test
    public void shouldCancelRequestOnStop() {
        presenter.onSubscribe(disposable);
        presenter.onStop();
        verify(disposable).dispose();
    }

}