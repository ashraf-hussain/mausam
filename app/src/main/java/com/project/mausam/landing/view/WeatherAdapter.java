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

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class WeatherAdapter extends RecyclerView.Adapter<WeatherAdapter.ViewHolder> {


    private List<WeatherModel> weatherModelList;
    private Context context;


    public WeatherAdapter(List<WeatherModel> musicModelList) {
        this.weatherModelList = musicModelList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_forecast, parent, false);
        context = view.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, final int position) {
        final WeatherModel weatherModel = weatherModelList.get(position);
        try {


            holder.tvForecastTime.setText(weatherModel.getDtTxt());
            holder.tvForecastTemp.setText(weatherModel.getTempKf()+" ");
            holder.tvForecastAmPm.setText("Am");
            //Picasso.get().load(context.getDrawable(R.drawable.ic_keyboard_backspace_black_24dp)).into(holder.ivForecastPic);

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