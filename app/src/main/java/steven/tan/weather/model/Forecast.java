package steven.tan.weather.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by steventan on 26/07/17.
 */

public class Forecast implements Parcelable {

    @SerializedName("list")
    private final List<Weather> weather;

    public Forecast(List<Weather> weather) {
        this.weather = weather;
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
        dest.writeTypedList(this.weather);
    }

    protected Forecast(Parcel in) {
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

        return weather != null ? weather.equals(forecast.weather) : forecast.weather == null;
    }

    @Override
    public int hashCode() {
        return weather != null ? weather.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "Forecast{" +
                "weather=" + weather +
                '}';
    }
}
