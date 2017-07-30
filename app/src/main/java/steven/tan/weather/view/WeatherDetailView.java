package steven.tan.weather.view;

import java.util.Date;

/**
 * Created by steventan on 29/07/17.
 */

public interface WeatherDetailView {
    void setLocation(String name);

    void setDate(Date date);

    void setTemperature(double min, double max);

    void setWeatherCondition(String weatherCondition);

    void setWindDirection(String windDirectionText);

    void setHumidity(double humidity);
}
