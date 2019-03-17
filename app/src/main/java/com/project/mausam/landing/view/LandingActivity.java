package com.project.mausam.landing.view;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.project.mausam.R;
import com.project.mausam.common.BaseActivity;
import com.project.mausam.common.ConnectionDetector;
import com.project.mausam.landing.model.WeatherModel;
import com.project.mausam.landing.presenter.LandingImp;
import com.project.mausam.landing.presenter.LandingPresenter;
import com.project.mausam.settings.SettingMenuActivity;
import com.project.mausam.start.SettingsActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

public class LandingActivity extends BaseActivity
        implements LandingView {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    @BindView(R.id.tv_city)
    TextView tvCity;
    @BindView(R.id.tv_degree)
    TextView tvDegree;
    @BindView(R.id.tv_current_status)
    TextView tvCurrentStatus;
    @BindView(R.id.rv_weatherForecast)
    RecyclerView rvWeatherForecast;
    @BindView(R.id.pull_to_refresh)
    SwipeRefreshLayout pullToRefresh;
    @BindView(R.id.rv_weatherForecastTomorrow)
    RecyclerView rvWeatherForecastTomorrow;
    public static String todaysDate;
    SharedPreferences settings;
    ConnectionDetector connectionDetector;
    LandingPresenter landingPresenter;

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        connectionDetector = new ConnectionDetector(this);

        settings = getSharedPreferences(SettingsActivity.MY_DATA, MODE_PRIVATE);
        String zipCodeDataCheck = settings.getString(SettingsActivity.zip, "");


        if (zipCodeDataCheck.equalsIgnoreCase("")) {
            Toast.makeText(this, "Empty ZipCode", Toast.LENGTH_SHORT).show();
            Log.d("zipload", String.valueOf(zipCodeDataCheck));
            Intent i = new Intent(this, SettingsActivity.class);
            startActivity(i);
            finish();
        } else {
            Toast.makeText(this, "Loaded" + " " + zipCodeDataCheck, Toast.LENGTH_SHORT).show();

            SimpleDateFormat currentDate = new SimpleDateFormat("yyyy-MM-dd");
            Date todayDate = new Date();
            todaysDate = currentDate.format(todayDate);

            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 4);
            rvWeatherForecast.setLayoutManager(layoutManager);
            rvWeatherForecast.setHasFixedSize(true);

            LinearLayoutManager layoutManagerTomorrow = new LinearLayoutManager(this,
                    LinearLayoutManager.HORIZONTAL, false);
            rvWeatherForecastTomorrow.setLayoutManager(layoutManagerTomorrow);
            rvWeatherForecastTomorrow.setHasFixedSize(true);

            landingPresenter = new LandingImp(this, this);
            landingPresenter.loadWeatherData();
            pullToRefreshFunction();


        }
    }

    private void pullToRefreshFunction() {

        if (!connectionDetector.isConnected()) {

            pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    snackBar("No Internet Connection !");
                    //llPb.setVisibility(View.GONE);
                    pullToRefresh.setRefreshing(false);
                }
            });

        } else {
            // Pull to refresh
            pullToRefresh.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
                @Override
                public void onRefresh() {
                    snackBar("Data Refreshed !");
                    landingPresenter.loadWeatherData();
                    // tvOfflineMode.setVisibility(View.GONE);
                    pullToRefresh.setRefreshing(false);
                }
            });
        }
    }

    @Override
    public void showLandingData(List<WeatherModel> weatherModelsList) {

        tvCurrentStatus.setText(weatherModelsList.get(0).getMain());
        tvDegree.setText(weatherModelsList.get(0).getTempKf() + "");
        tvCity.setText(weatherModelsList.get(0).getCityName() + ","
                + " " + weatherModelsList.get(0).getCountry());

        WeatherAdapter weatherAdapter = new WeatherAdapter(weatherModelsList);
        rvWeatherForecast.setAdapter(weatherAdapter);


        WeatherAdapterTomorrow weatherAdapterTomorrow = new WeatherAdapterTomorrow(weatherModelsList);
        rvWeatherForecastTomorrow.setAdapter(weatherAdapterTomorrow);
    }


    @OnClick(R.id.cv_settings)
    public void onViewClicked() {
        Intent intent = new Intent(this, SettingMenuActivity.class);
        startActivity(intent);
    }


    private void snackBar(String message) {
        Snackbar snackbar = Snackbar
                .make(pullToRefresh, message, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBlack));
        snackbar.show();

    }


    @Override
    protected void onResume() {
        super.onResume();
        pullToRefreshFunction();
    }
}
