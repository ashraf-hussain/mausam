package com.project.mausam.landing.view;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
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
    String str = null;
    String inputPattern;
    String outputPattern;
    SimpleDateFormat in;
    SimpleDateFormat out;


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
        in = new SimpleDateFormat(inputPattern);
        out = new SimpleDateFormat(outputPattern);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final WeatherModel weatherModel = weatherModelList.get(position);
        try {
                date = in.parse(weatherModel.getDtTxt());
                str = out.format(date);

            holder.tvForecastTemp.setText(weatherModel.getTempKf()+" ");
            holder.tvForecastTime.setText(str);
            if (weatherModel.getMain().equalsIgnoreCase("clear sky")) {
                holder.ivForecastPic.setBackgroundResource(R.drawable.ic_warm);
            } else if (weatherModel.getMain().equalsIgnoreCase("scattered clouds")) {
                holder.ivForecastPic.setBackgroundResource(R.drawable.ic_partly_cloudy_blue);
            }else if (weatherModel.getMain().equalsIgnoreCase("Rain")) {
                holder.ivForecastPic.setBackgroundResource(R.drawable.ic_rain);
            }else if (weatherModel.getMain().equalsIgnoreCase("broken clouds")) {
                holder.ivForecastPic.setBackgroundResource(R.drawable.ic_partly_cloudy);
            }else if (weatherModel.getMain().equalsIgnoreCase("few clouds")) {
                holder.ivForecastPic.setBackgroundResource(R.drawable.ic_sun);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        holder.cvForecast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "In Progress !", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(context, MusicPlayerDetailActivity.class);
//                intent.putExtra(AppConstant.MUSIC_DETAIL_DATA, weatherModel);
//                context.startActivity(intent);

            }
        });

    }


    @Override
    public int getItemCount() {
        return weatherModelList == null ? 0 : weatherModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_forecast_time)
        TextView tvForecastTime;
        @BindView(R.id.tv_forecast_am_pm)
        TextView tvForecastAmPm;
        @BindView(R.id.iv_forecast_pic)
        ImageView ivForecastPic;
        @BindView(R.id.tv_forecast_temp)
        TextView tvForecastTemp;
        @BindView(R.id.cv_forecast)
        CardView cvForecast;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }
}