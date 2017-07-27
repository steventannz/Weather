package steven.tan.weather.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by steventan on 26/07/17.
 */

public class WeatherLocation implements Parcelable {

    @SerializedName("id")
    private final int id;

    public WeatherLocation(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
    }

    protected WeatherLocation(Parcel in) {
        this.id = in.readInt();
    }

    public static final Parcelable.Creator<WeatherLocation> CREATOR = new Parcelable.Creator<WeatherLocation>() {
        @Override
        public WeatherLocation createFromParcel(Parcel source) {
            return new WeatherLocation(source);
        }

        @Override
        public WeatherLocation[] newArray(int size) {
            return new WeatherLocation[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeatherLocation that = (WeatherLocation) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        return "WeatherLocation{" +
                "id=" + id +
                '}';
    }
}
