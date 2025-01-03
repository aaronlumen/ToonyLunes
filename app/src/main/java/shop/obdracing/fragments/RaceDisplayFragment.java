package shop.surina.obdracing.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import shop.surina.obdracing.ObdCommandTask;
import shop.surina.obdracing.ObdManager;
import shop.surina.obdracing.R;
import shop.surina.obdracing.adapters.SensorDataAdapter;
import shop.surina.obdracing.models.SensorData;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class RaceDisplayFragment extends Fragment implements ObdCommandTask.SensorDataListener {

    private RecyclerView recyclerView;
    private SensorDataAdapter sensorDataAdapter;
    private List<SensorData> sensorDataList;
    private ObdCommandTask obdCommandTask;

    // A demonstration set of commands to poll
    private List<String> selectedCommands;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_race_display, container, false);

        recyclerView = view.findViewById(R.id.recycler_race_display);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2));

        sensorDataList = new ArrayList<>();
        sensorDataAdapter = new SensorDataAdapter(getContext(), sensorDataList);
        recyclerView.setAdapter(sensorDataAdapter);

        // Example commands that user might have selected
        selectedCommands = new ArrayList<>();
        selectedCommands.add("010C"); // Engine RPM
        selectedCommands.add("010D"); // Vehicle Speed
        selectedCommands.add("0105"); // Coolant Temperature
        selectedCommands.add("0111"); // Throttle Position

        // Create placeholders in sensorDataList
        for (String cmd : selectedCommands) {
            sensorDataList.add(new SensorData(cmd, "--", ""));
        }

        // Start background OBD polling if connected
        if (ObdManager.getInstance().isConnected()) {
            obdCommandTask = new ObdCommandTask(selectedCommands, this);
            obdCommandTask.execute();
        }

        return view;
    }

    @Override
    public void onNewSensorData(String command, String rawResult) {
        // Simple example: match command text
        for (SensorData sd : sensorDataList) {
            if (sd.getSensorName().equals(command)) {
                sd.setSensorValue(rawResult.trim());
            }
        }
        sensorDataAdapter.notifyDataSetChanged();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (obdCommandTask != null) {
            obdCommandTask.stopTask();
        }
    }
}