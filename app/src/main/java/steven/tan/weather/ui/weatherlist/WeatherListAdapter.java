package steven.tan.weather.ui.weatherlist;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import steven.tan.weather.R;
import steven.tan.weather.model.Weather;

/**
 * Created by steventan on 27/07/17.
 */

public class WeatherListAdapter extends RecyclerView.Adapter<WeatherListAdapter.ItemViewHolder> {

    private final List<Weather> weatherList;

    public WeatherListAdapter(List<Weather> weatherList) {

        this.weatherList = weatherList;
    }

    @Override
    public ItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.forecast_row, parent, false);

        return new ItemViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ItemViewHolder holder, int position) {
        holder.locationText.setText(weatherList.get(position).getDate().toString());
    }


    @Override
    public int getItemCount() {
        return weatherList.size();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.location_text)
        TextView locationText;

        ItemViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}