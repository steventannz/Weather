package steven.tan.weather.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

/**
 * Created by steventan on 27/07/17.
 */

public class WeatherCondition implements Parcelable {

    @SerializedName("id")
    private final int id;

    @SerializedName("main")
    private final String main;

    @SerializedName("description")
    private final String description;

    @SerializedName("icon")
    private final String icon;

    public WeatherCondition(int id, String main, String description, String icon) {
        this.id = id;
        this.main = main;
        this.description = description;
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public String getMain() {
        return main;
    }

    public String getDescription() {
        return description;
    }

    public String getIcon() {
        return icon;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.id);
        dest.writeString(this.main);
        dest.writeString(this.description);
        dest.writeString(this.icon);
    }

    protected WeatherCondition(Parcel in) {
        this.id = in.readInt();
        this.main = in.readString();
        this.description = in.readString();
        this.icon = in.readString();
    }

    public static final Creator<WeatherCondition> CREATOR = new Creator<WeatherCondition>() {
        @Override
        public WeatherCondition createFromParcel(Parcel source) {
            return new WeatherCondition(source);
        }

        @Override
        public WeatherCondition[] newArray(int size) {
            return new WeatherCondition[size];
        }
    };

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WeatherCondition that = (WeatherCondition) o;

        if (id != that.id) return false;
        if (main != null ? !main.equals(that.main) : that.main != null) return false;
        if (description != null ? !description.equals(that.description) : that.description != null)
            return false;
        return icon != null ? icon.equals(that.icon) : that.icon == null;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (main != null ? main.hashCode() : 0);
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + (icon != null ? icon.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "WeatherCondition{" +
                "id=" + id +
                ", main='" + main + '\'' +
                ", description='" + description + '\'' +
                ", icon='" + icon + '\'' +
                '}';
    }
}
