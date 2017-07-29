package steven.tan.weather.ui.weatherlist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import steven.tan.weather.R;
import steven.tan.weather.model.Temperature;
import steven.tan.weather.model.Weather;

/**
 * Created by steventan on 27/07/17.
 */

public class WeatherListAdapter extends RecyclerView.Adapter<WeatherListAdapter.ItemViewHolder> {

    private final List<Weather> weatherList;
    private final OnWeatherCardClickedListener listener;

    public WeatherListAdapter(List<Weather> weatherList, OnWeatherCardClickedListener listener) {

        this.weatherList = weatherList;
        this.listener = listener;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.forecast_row, parent, false);

        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        Context context = holder.itemView.getContext();

        Weather weather = weatherList.get(position);
        holder.weatherCard.setOnClickListener(v -> listener.onWeatherCardClicked(weather));

        String formattedDate = DateUtils.formatDateTime(context,
                weather.getDate().getTime(), DateUtils.FORMAT_SHOW_WEEKDAY | DateUtils.FORMAT_SHOW_DATE);
        holder.dateText.setText(formattedDate);

        Temperature temperature = weather.getTemperature();
        String minMaxTemp = context.getString(R.string.min_max_temperature, temperature.getMin(), temperature.getMax());
        holder.temperatureText.setText(minMaxTemp);

        holder.weatherConditionText.setText(weather.getWeatherCondition().get(0).getDescription());
    }


    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.weather_card)
        View weatherCard;

        @BindView(R.id.date)
        TextView dateText;

        @BindView(R.id.temperature)
        TextView temperatureText;

        @BindView(R.id.weather_condition)
        TextView weatherConditionText;


        ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public interface OnWeatherCardClickedListener {
        void onWeatherCardClicked(Weather date);
    }
}