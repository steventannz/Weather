package steven.tan.weather.presenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import steven.tan.weather.model.City;
import steven.tan.weather.model.Direction;
import steven.tan.weather.model.Temperature;
import steven.tan.weather.model.Weather;
import steven.tan.weather.model.WeatherCondition;
import steven.tan.weather.view.WeatherDetailView;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by steventan on 30/07/17.
 */
@RunWith(MockitoJUnitRunner.class)
public class WeatherDetailPresenterTest {
    private WeatherDetailPresenter presenter;

    @Mock
    WeatherDetailView view;

    @Mock
    City city;

    @Mock
    Weather weather;

    @Mock
    Temperature temperature;

    @Mock
    WeatherCondition weatherCondition;

    List<WeatherCondition> weatherList;

    @Before
    public void setup() {
        presenter = new WeatherDetailPresenter(view, city, weather);
        when(weather.getTemperature()).thenReturn(temperature);
        weatherList = new ArrayList<>();
        weatherList.add(weatherCondition);
        when(weather.getWeatherCondition()).thenReturn(weatherList);
    }

    @Test
    public void shouldSetLocationOnActivityCreated() {
        when(city.getName()).thenReturn("Wellington");
        presenter.onActivityCreated();
        verify(view).setLocation(city.getName());
    }

    @Test
    public void shouldSetDateOnActivityCreated() {
        when(weather.getDate()).thenReturn(new Date());
        presenter.onActivityCreated();
        verify(view).setDate(weather.getDate());
    }

    @Test
    public void shouldSetTemperatureOnActivityCreated() {
        when(temperature.getMin()).thenReturn(5.0);
        when(temperature.getMax()).thenReturn(10.0);
        presenter.onActivityCreated();
        verify(view).setTemperature(temperature.getMin(), temperature.getMax());
    }

    @Test
    public void shouldSetWeatherConditionOnActivityCreated() {
        when(weatherCondition.getMain()).thenReturn("Rain");
        presenter.onActivityCreated();
        verify(view).setWeatherCondition(weatherCondition.getMain());
    }

    @Test
    public void shouldSetWindDirectionOnActivityCreated() {
        when(weather.getWindDirection()).thenReturn(Direction.EAST);
        presenter.onActivityCreated();
        verify(view).setWindDirection(weather.getWindDirection());
    }

    @Test
    public void shouldSetHumidityOnActivityCreated() {
        when(weather.getHumidity()).thenReturn(88.8);
        presenter.onActivityCreated();
        verify(view).setHumidity(weather.getHumidity());
    }
}