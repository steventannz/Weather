package steven.tan.weather.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by steventan on 26/07/17.
 */

public class Forecast implements Parcelable {

    @SerializedName("city")
    private final City city;

    @SerializedName("list")
    private final List<Weather> weather;

    public Forecast(City city, List<Weather> weather) {
        this.city = city;
        this.weather = weather;
    }

    public City getCity() {
        return city;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.city, flags);
        dest.writeTypedList(this.weather);
    }

    protected Forecast(Parcel in) {
        this.city = in.readParcelable(City.class.getClassLoader());
        this.weather = in.createTypedArrayList(Weather.CREATOR);
    }

    public static final Creator<Forecast> CREATOR = new Creator<Forecast>() {
        @Override
        public Forecast createFromParcel(Parcel source) {
            return new Forecast(source);
        }

        @Override
        public Forecast[] newArray(int size) {
            return new Forecast[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Forecast forecast = (Forecast) o;

        if (city != null ? !city.equals(forecast.city) : forecast.city != null) return false;
        return weather != null ? weather.equals(forecast.weather) : forecast.weather == null;
    }

    @Override
    public int hashCode() {
        int result = city != null ? city.hashCode() : 0;
        result = 31 * result + (weather != null ? weather.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Forecast{" +
                "city=" + city +
                ", weather=" + weather +
                '}';
    }
}
