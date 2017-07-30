package steven.tan.weather.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

/**
 * Created by steventan on 27/07/17.
 */

public class Weather implements Parcelable {

    @SerializedName("dt")
    private final Date date;

    @SerializedName("temp")
    private final Temperature temperature;

    @SerializedName("deg")
    private final double windDirection;

    @SerializedName("humidity")
    private final double humidity;

    @SerializedName("weather")
    private final List<WeatherCondition> weatherCondition;

    public Weather(Date date, Temperature temperature, double windDirection, double humidity, List<WeatherCondition> weatherCondition) {
        this.date = date;
        this.temperature = temperature;
        this.windDirection = windDirection;
        this.humidity = humidity;
        this.weatherCondition = weatherCondition;
    }

    public Date getDate() {
        return date;
    }

    public Temperature getTemperature() {
        return temperature;
    }

    public double getRawWindDirection() {
        return windDirection;
    }

    public Direction getWindDirection() {
        return Direction.values() [(int)Math.round((windDirection / 45)) % 8];
    }

    public double getHumidity() {
        return humidity;
    }

    public List<WeatherCondition> getWeatherCondition() {
        return weatherCondition;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(this.date != null ? this.date.getTime() : -1);
        dest.writeParcelable(this.temperature, flags);
        dest.writeDouble(this.windDirection);
        dest.writeDouble(this.humidity);
        dest.writeTypedList(this.weatherCondition);
    }

    protected Weather(Parcel in) {
        long tmpDate = in.readLong();
        this.date = tmpDate == -1 ? null : new Date(tmpDate);
        this.temperature = in.readParcelable(Temperature.class.getClassLoader());
        this.windDirection = in.readDouble();
        this.humidity = in.readDouble();
        this.weatherCondition = in.createTypedArrayList(WeatherCondition.CREATOR);
    }

    public static final Creator<Weather> CREATOR = new Creator<Weather>() {
        @Override
        public Weather createFromParcel(Parcel source) {
            return new Weather(source);
        }

        @Override
        public Weather[] newArray(int size) {
            return new Weather[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Weather weather = (Weather) o;

        if (Double.compare(weather.windDirection, windDirection) != 0) return false;
        if (Double.compare(weather.humidity, humidity) != 0) return false;
        if (date != null ? !date.equals(weather.date) : weather.date != null) return false;
        if (temperature != null ? !temperature.equals(weather.temperature) : weather.temperature != null)
            return false;
        return weatherCondition != null ? weatherCondition.equals(weather.weatherCondition) : weather.weatherCondition == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = date != null ? date.hashCode() : 0;
        result = 31 * result + (temperature != null ? temperature.hashCode() : 0);
        temp = Double.doubleToLongBits(windDirection);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(humidity);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (weatherCondition != null ? weatherCondition.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Weather{" +
                "date=" + date +
                ", temperature=" + temperature +
                ", windDirection=" + windDirection +
                ", humidity=" + humidity +
                ", weatherCondition=" + weatherCondition +
                '}';
    }
}
