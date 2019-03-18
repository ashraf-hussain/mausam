package com.project.mausam.landing.view;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.project.mausam.R;
import com.project.mausam.landing.model.WeatherModel;
import com.squareup.picasso.Picasso;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {


    private List<WeatherModel> weatherModelList;
    private Context context;
    Date date = null;
    Date dateFormat = null;
    String str = null;
    String strDate = null;
    String inputPattern;
    String outputPattern;
    String outputPatternDate;
    SimpleDateFormat in;
    SimpleDateFormat out;
    SimpleDateFormat outDate;
    public boolean check;


    public WeatherAdapter(List<WeatherModel> musicModelList) {
        this.weatherModelList = musicModelList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_forecast, parent, false);
        context = view.getContext();


        //formatting as date and time
        inputPattern = "yyyy-MM-dd HH:mm:ss";
        outputPattern = "h:mm a";
        outputPatternDate = "yyyy-MM-dd";
        in = new SimpleDateFormat(inputPattern);
        out = new SimpleDateFormat(outputPattern);


        outDate = new SimpleDateFormat(outputPatternDate);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final WeatherModel weatherModel = weatherModelList.get(position);
        try {
            date = in.parse(weatherModel.getDtTxt());
            dateFormat = outDate.parse(weatherModel.getDtTxt());
            str = out.format(date);
            strDate = outDate.format(dateFormat);

           check = LandingActivity.todaysDate.equals(strDate);

            if (LandingActivity.todaysDate.equals(strDate)) {
                Log.d("today", "yes");

                int result = (int) Math.ceil(weatherModel.getTempKf());
                holder.llForecast.setVisibility(View.VISIBLE);
                holder.tvForecastTemp.setText(result+"");
                holder.tvForecastTime.setText(str);

                if (result > 50) {
                    holder.ivForecastPic.setBackgroundResource(R.drawable.ic_warm);
                    holder.tvForecastTemp.setTextColor(Color.parseColor("#FF9506"));
                    holder.tvForecastTime.setTextColor(Color.parseColor("#FF9506"));
                    holder.tvForecastDegree.setTextColor(Color.parseColor("#FF9506"));
                } else if (weatherModel.getMain().equalsIgnoreCase("scattered clouds")) {
                    holder.ivForecastPic.setBackgroundResource(R.drawable.ic_partly_cloudy_blue);
                    holder.tvForecastTemp.setTextColor(Color.parseColor("#03A9F5"));
                    holder.tvForecastTime.setTextColor(Color.parseColor("#03A9F5"));
                    holder.tvForecastDegree.setTextColor(Color.parseColor("#03A9F5"));
                } else if (weatherModel.getMain().equalsIgnoreCase("light rain")) {
                    holder.ivForecastPic.setBackgroundResource(R.drawable.ic_rain);
                } else if (weatherModel.getMain().equalsIgnoreCase("broken clouds")
                        || weatherModel.getMain().equalsIgnoreCase("few clouds")) {
                    holder.ivForecastPic.setBackgroundResource(R.drawable.ic_partly_cloudy);
                    holder.tvForecastTemp.setTextColor(Color.parseColor("#000000"));
                    holder.tvForecastTime.setTextColor(Color.parseColor("#000000"));
                    holder.tvForecastDegree.setTextColor(Color.parseColor("#000000"));
                } else if (weatherModel.getMain().equalsIgnoreCase("clear sky")) {
                    holder.tvForecastTemp.setTextColor(Color.parseColor("#000000"));
                    holder.tvForecastTime.setTextColor(Color.parseColor("#000000"));
                    holder.tvForecastDegree.setTextColor(Color.parseColor("#000000"));
                    holder.ivForecastPic.setBackgroundResource(R.drawable.ic_sun);
                } else if (result > 30) {
                    holder.tvForecastTemp.setTextColor(Color.parseColor("#000000"));
                    holder.tvForecastTime.setTextColor(Color.parseColor("#000000"));
                    holder.tvForecastDegree.setTextColor(Color.parseColor("#000000"));
                    holder.ivForecastPic.setBackgroundResource(R.drawable.ic_sun);
                } else {
                    holder.ivForecastPic.setBackgroundResource(R.drawable.ic_partly_cloudy);
                    holder.tvForecastTemp.setTextColor(Color.parseColor("#000000"));
                    holder.tvForecastTime.setTextColor(Color.parseColor("#000000"));
                    holder.tvForecastDegree.setTextColor(Color.parseColor("#000000"));
                }

            } else {
                holder.llForecast.setVisibility(View.GONE);
                Log.d("today", "no");

            }

        } catch (Exception e) {
            e.printStackTrace();
        }    }


    @Override
    public int getItemCount() {
        return weatherModelList == null ? 0 : weatherModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_forecast_time)
        TextView tvForecastTime;
        @BindView(R.id.tv_forecast_degree)
        TextView tvForecastDegree;
        @BindView(R.id.tv_forecast_am_pm)
        TextView tvForecastAmPm;
        @BindView(R.id.iv_forecast_pic)
        ImageView ivForecastPic;
        @BindView(R.id.tv_forecast_temp)
        TextView tvForecastTemp;
        @BindView(R.id.ll_forecast)
        LinearLayout llForecast;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}