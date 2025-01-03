package com.example.obdracing.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.obdracing.R;
import com.example.obdracing.adapters.CommandSelectionAdapter;
import com.example.obdracing.models.ObdCommandItem;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class SensorSelectionFragment extends Fragment {

    private RecyclerView recyclerView;
    private CommandSelectionAdapter adapter;
    private List<ObdCommandItem> allPossibleCommands;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_sensor_selection, container, false);

        recyclerView = view.findViewById(R.id.recycler_command_selection);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // Example commands
        allPossibleCommands = new ArrayList<>();
        allPossibleCommands.add(new ObdCommandItem("010C","Engine RPM"));
        allPossibleCommands.add(new ObdCommandItem("010D","Vehicle Speed"));
        allPossibleCommands.add(new ObdCommandItem("0105","Engine Coolant Temp"));
        allPossibleCommands.add(new ObdCommandItem("0111","Throttle Position"));
        // ... more as desired

        adapter = new CommandSelectionAdapter(allPossibleCommands);
        recyclerView.setAdapter(adapter);

        return view;
    }
}