package com.project.mausam.landing.view;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.project.mausam.R;
import com.project.mausam.common.BaseActivity;
import com.project.mausam.landing.model.WeatherModel;
import com.project.mausam.landing.presenter.LandingImp;
import com.project.mausam.landing.presenter.LandingPresenter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LandingActivity extends BaseActivity
        implements NavigationView.OnNavigationItemSelectedListener, LandingView {

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


    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void init() {
        LinearLayoutManager horizontalLayoutManager = new LinearLayoutManager(this,
                LinearLayoutManager.HORIZONTAL, false);
        rvWeatherForecast.setLayoutManager(horizontalLayoutManager);
        rvWeatherForecast.setHasFixedSize(true);


        LandingPresenter landingPresenter = new LandingImp(this);
        landingPresenter.loadWeatherData();

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void showLandingData(List<WeatherModel> weatherModelsList) {

        WeatherAdapter weatherAdapter = new WeatherAdapter(weatherModelsList);
        rvWeatherForecast.setAdapter(weatherAdapter);
    }

}
