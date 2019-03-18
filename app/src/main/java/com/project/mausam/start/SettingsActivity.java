package com.project.mausam.start;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.project.mausam.R;
import com.project.mausam.common.BaseActivity;
import com.project.mausam.common.ConnectionDetector;
import com.project.mausam.landing.view.LandingActivity;

import butterknife.BindView;
import butterknife.OnClick;

public class SettingsActivity extends BaseActivity {
    @BindView(R.id.et_zip_code)
    EditText etZipCode;
    SharedPreferences sharedPreferences;
    ConnectionDetector connectionDetector;
    String zipCodeData;
    @BindView(R.id.ll_main)
    LinearLayout llMain;
    @BindView(R.id.tv_offline)
    TextView tvOffline;

    @Override
    protected int getLayout() {
        return R.layout.activity_settings;
    }

    public static final String MY_DATA = "my_data";
    public static final String zip = "zip";
    public static final String unit = "unit";

    @Override
    protected void init() {
//        this.getSupportActionBar().hide();
        connectionDetector = new ConnectionDetector(this);
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(etZipCode, InputMethodManager.SHOW_IMPLICIT);
        zipCodeData = etZipCode.getText().toString();

    }

    @OnClick({R.id.btn_save})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_save:
                if (!connectionDetector.isConnected()) {
                    tvOffline.setVisibility(View.VISIBLE);
                    snackBar(getString(R.string.no_internet_connection));

                } else if (zipCodeData.equalsIgnoreCase("") && etZipCode.length() < 5) {
                    etZipCode.setError(getString(R.string.zip_code_required));

                } else {
                    zipCodeData = etZipCode.getText().toString();
                    save(zipCodeData);

                    String zipCodeDataCheck = sharedPreferences.getString(zip, "");
                    Log.d("zip", zipCodeDataCheck);
                    Toast.makeText(this, zipCodeData, Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(this, LandingActivity.class);
                    startActivity(intent);
                    finish();
                }

                break;
        }
    }


    void save(String value) {
        sharedPreferences = getSharedPreferences(MY_DATA,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(zip, value);
        editor.commit();

    }


    public void snackBar(String message) {
        Snackbar snackbar = Snackbar
                .make(llMain, message, Snackbar.LENGTH_SHORT);
        View sbView = snackbar.getView();
        sbView.setBackgroundColor(ContextCompat.getColor(this, R.color.colorBlack));
        snackbar.show();

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        onBackPressed();
    }
}
