package steven.tan.weather.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by steventan on 27/07/17.
 */

public class Temperature implements Parcelable {

    @SerializedName("max")
    private final double max;

    @SerializedName("min")
    private final double min;

    public Temperature(double max, double min) {
        this.max = max;
        this.min = min;
    }

    public double getMax() {
        return max;
    }

    public double getMin() {
        return min;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeDouble(this.max);
        dest.writeDouble(this.min);
    }

    protected Temperature(Parcel in) {
        this.max = in.readDouble();
        this.min = in.readDouble();
    }

    public static final Parcelable.Creator<Temperature> CREATOR = new Parcelable.Creator<Temperature>() {
        @Override
        public Temperature createFromParcel(Parcel source) {
            return new Temperature(source);
        }

        @Override
        public Temperature[] newArray(int size) {
            return new Temperature[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Temperature that = (Temperature) o;

        if (Double.compare(that.max, max) != 0) return false;
        return Double.compare(that.min, min) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        temp = Double.doubleToLongBits(max);
        result = (int) (temp ^ (temp >>> 32));
        temp = Double.doubleToLongBits(min);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "Temperature{" +
                "max=" + max +
                ", min=" + min +
                '}';
    }
}
