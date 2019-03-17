package com.project.mausam.settings;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import com.project.mausam.R;
import com.project.mausam.common.BaseActivity;
import com.project.mausam.landing.view.LandingActivity;
import com.project.mausam.start.SettingsActivity;

import butterknife.BindView;
import butterknife.OnClick;

import static com.project.mausam.start.SettingsActivity.MY_DATA;
import static com.project.mausam.start.SettingsActivity.unit;
import static com.project.mausam.start.SettingsActivity.zip;

public class SettingMenuActivity extends BaseActivity {
    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.tv_zip_code)
    TextView tvZipCode;
    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.tv_unit)
    TextView tvUnit;
    SharedPreferences sharedPreferences;
    @BindView(R.id.switch_unit)
    Switch switchUnit;

    @Override
    protected int getLayout() {
        return R.layout.activity_setting_list;
    }

    @Override
    protected void init() {
        this.getSupportActionBar().hide();
        toolbarTitle.setText(getString(R.string.settings));

        sharedPreferences = getSharedPreferences(MY_DATA, MODE_PRIVATE);
        String unitCheck = sharedPreferences.getString(SettingsActivity.unit, "imperial");
        String zipCodeDataCheck = sharedPreferences.getString(zip, "");


        if (unitCheck.equalsIgnoreCase("metric")) {
            switchUnit.setChecked(false);
        } else {
            switchUnit.setChecked(true);
        }


        tvZipCode.setText(zipCodeDataCheck);

        if (unitCheck.equalsIgnoreCase("metric")) {
            tvUnit.setText(getString(R.string.degree) + R.string.celsius);
        } else {
            tvUnit.setText(getString(R.string.degree) + R.string.fahrenheit);
        }


        switchUnit.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {
                    save("imperial");
                    Intent intent = new Intent(getApplication(), LandingActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    save("metric");
                    Intent intent = new Intent(getApplication(), LandingActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        });

    }


    @OnClick({R.id.toolbar, R.id.ll_zip})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.toolbar:
                onBackPressed();
                break;
            case R.id.ll_zip:

                final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
                alertDialogBuilder.setTitle(R.string.settings).setMessage(R.string.change_zip_code)
                        .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int which) {
                                Intent intent = new Intent(SettingMenuActivity.this, SettingsActivity.class);
                                startActivity(intent);
                                finish();
                            }
                        }).setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        alertDialogBuilder.setCancelable(true);

                    }
                }).show();

                break;
        }

    }


    void save(String value) {
        sharedPreferences = getSharedPreferences(MY_DATA,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(unit, value);
        editor.commit();

    }

}
