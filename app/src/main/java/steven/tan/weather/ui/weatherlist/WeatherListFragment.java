package steven.tan.weather.ui.weatherlist;

import android.content.Context;
import android.content.Intent;
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
import steven.tan.weather.model.City;
import steven.tan.weather.model.Weather;
import steven.tan.weather.presenter.WeatherListPresenter;
import steven.tan.weather.ui.weatherdetail.WeatherDetailActivity;
import steven.tan.weather.view.WeatherListView;


public class WeatherListFragment extends Fragment implements WeatherListView,
        SearchView.OnQueryTextListener, WeatherListAdapter.OnWeatherCardClickedListener {

    @Inject
    WeatherListPresenter presenter;

    @BindView(R.id.search_view)
    SearchView searchView;

    @BindView(R.id.forecast_recycler)
    RecyclerView forecastRecyclerView;

    @BindView(R.id.progress)
    View progressBar;

    @BindView(R.id.retry)
    View retryButton;

    @BindView(R.id.error_layer)
    View errorLayer;

    @BindView(R.id.content)
    View contentLayer;

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
        retryButton.setOnClickListener(v -> presenter.onRetryClicked());
    }

    @Override
    public void onStop() {
        super.onStop();
        presenter.onStop();
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
        forecastRecyclerView.setAdapter(new WeatherListAdapter(weather, this));
    }

    @Override
    public void showWeatherDetail(City location, Weather weather) {
        Intent intent = new Intent(getActivity(), WeatherDetailActivity.class);
        intent.putExtra(WeatherDetailActivity.EXTRA_CITY, location);
        intent.putExtra(WeatherDetailActivity.EXTRA_WEATHER, weather);
        startActivity(intent);
    }

    @Override
    public void showList() {
        forecastRecyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideList() {
        forecastRecyclerView.setVisibility(View.INVISIBLE);
    }

    @Override
    public void startLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void stopLoading() {
        progressBar.setVisibility(View.INVISIBLE);
    }

    @Override
    public void showErrorMessage() {
        contentLayer.setVisibility(View.GONE);
        errorLayer.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideError() {
        errorLayer.setVisibility(View.GONE);
        contentLayer.setVisibility(View.VISIBLE);
    }

    @Override
    public void onWeatherCardClicked(int position) {
        presenter.onWeatherCardClicked(position);
    }
}
