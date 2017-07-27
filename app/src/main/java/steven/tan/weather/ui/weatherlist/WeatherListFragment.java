package steven.tan.weather.ui.weatherlist;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import dagger.android.support.AndroidSupportInjection;
import steven.tan.weather.R;
import steven.tan.weather.model.Weather;
import steven.tan.weather.presenter.WeatherListPresenter;
import steven.tan.weather.view.WeatherListView;

/**
 * A placeholder fragment containing a simple view.
 */
public class WeatherListFragment extends Fragment implements WeatherListView, SearchView.OnQueryTextListener {

    @Inject
    WeatherListPresenter presenter;

    @BindView(R.id.search_view)
    SearchView searchView;

    @BindView(R.id.forecast_recycler)
    RecyclerView forecastRecyclerView;

    public WeatherListFragment() {
    }

    @Override
    public void onAttach(Context context) {
        AndroidSupportInjection.inject(this);
        super.onAttach(context);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_weather_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);

        searchView.setOnQueryTextListener(this);
        forecastRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        presenter.onActivityCreated();
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        presenter.onQuerySubmitted(query);
        return true;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }

    @Override
    public void setWeatherForecast(List<Weather> weather) {
        forecastRecyclerView.setAdapter(new WeatherListAdapter(weather));
    }
}
