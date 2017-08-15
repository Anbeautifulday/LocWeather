package cn.edu.cqupt.loc_whether;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.edu.cqupt.loc_whether.weatherbean.Forecast;

/**
 * Created by wentai on 17-8-15.
 */

public class ForecastAdapter extends RecyclerView.Adapter<ForecastAdapter.ViewHolder> {

    private List<Forecast> forecastList;

    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView data;
        TextView type;
        TextView high;
        TextView low;
        TextView sunrise;
        TextView sunset;
        TextView fx;
        TextView fl;
        TextView notice;

        public ViewHolder(View itemView) {
            super(itemView);
            data = itemView.findViewById(R.id.show_data);
            type = itemView.findViewById(R.id.show_type);
            high = itemView.findViewById(R.id.show_high);
            low = itemView.findViewById(R.id.show_low);
            sunrise = itemView.findViewById(R.id.show_sunrise);
            sunset = itemView.findViewById(R.id.show_sunset);
            fx = itemView.findViewById(R.id.show_fx);
            fl = itemView.findViewById(R.id.show_fl);
            notice = itemView.findViewById(R.id.show_notice);
        }
    }

    public ForecastAdapter(List<Forecast> forecastList) {
        this.forecastList = forecastList;
        forecastList.get(0).setDate("今天");
        forecastList.get(1).setDate("明天");
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_forecast, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Forecast forecast = forecastList.get(position);
        holder.data.setText(forecast.getDate());
        holder.type.setText(forecast.getType());
        holder.high.setText(forecast.getHigh());
        holder.low.setText(forecast.getLow());
        holder.sunrise.setText(forecast.getSunrise());
        holder.sunset.setText(forecast.getSunset());
        holder.fx.setText(forecast.getFx());
        holder.fl.setText(forecast.getFl());
        holder.notice.setText(forecast.getNotice());
    }

    @Override
    public int getItemCount() {
        return forecastList.size();
    }
}
