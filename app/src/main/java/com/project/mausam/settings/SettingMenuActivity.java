package com.project.mausam.settings;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.project.mausam.R;
import com.project.mausam.common.BaseActivity;
import com.project.mausam.start.SettingsActivity;

import butterknife.BindView;
import butterknife.OnClick;

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

    @Override
    protected int getLayout() {
        return R.layout.activity_setting_list;
    }

    @Override
    protected void init() {
        this.getSupportActionBar().hide();
        toolbarTitle.setText(getString(R.string.settings));

        sharedPreferences = getSharedPreferences(SettingsActivity.MY_DATA, MODE_PRIVATE);

        String zipCodeDataCheck = sharedPreferences.getString(SettingsActivity.zip, "");
        tvZipCode.setText(zipCodeDataCheck);
        tvUnit.setText("Celsius"+" "+getString(R.string.degree));

    }


    @OnClick({R.id.toolbar, R.id.ll_zip, R.id.ll_unit})
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
            case R.id.ll_unit:
                Toast.makeText(this, "Working", Toast.LENGTH_SHORT).show();
                break;

        }


    }
}
