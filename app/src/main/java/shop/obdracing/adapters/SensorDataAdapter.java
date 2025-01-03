package com.example.obdracing.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.obdracing.R;
import com.example.obdracing.models.SensorData;

import android.widget.TextView;

import java.util.List;

public class SensorDataAdapter extends RecyclerView.Adapter<SensorDataAdapter.SensorViewHolder> {

    private Context context;
    private List<SensorData> sensorDataList;

    public SensorDataAdapter(Context context, List<SensorData> sensorDataList) {
        this.context = context;
        this.sensorDataList = sensorDataList;
    }

    @NonNull
    @Override
    public SensorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_sensor_data, parent, false);
        return new SensorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SensorViewHolder holder, int position) {
        SensorData data = sensorDataList.get(position);
        holder.sensorName.setText(data.getSensorName());
        holder.sensorValue.setText(data.getSensorValue());
        holder.sensorUnit.setText(data.getSensorUnit());
    }

    @Override
    public int getItemCount() {
        return sensorDataList.size();
    }

    class SensorViewHolder extends RecyclerView.ViewHolder {
        TextView sensorName, sensorValue, sensorUnit;

        public SensorViewHolder(@NonNull View itemView) {
            super(itemView);
            sensorName = itemView.findViewById(R.id.sensor_name);
            sensorValue = itemView.findViewById(R.id.sensor_value);
            sensorUnit = itemView.findViewById(R.id.sensor_unit);
        }
    }
}