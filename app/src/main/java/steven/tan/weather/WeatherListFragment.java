package steven.tan.weather;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import dagger.android.support.AndroidSupportInjection;
import steven.tan.weather.presenter.WeatherListPresenter;
import steven.tan.weather.view.WeatherListView;

/**
 * A placeholder fragment containing a simple view.
 */
public class WeatherListFragment extends Fragment implements WeatherListView {

    @Inject
    WeatherListPresenter presenter;

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
}
