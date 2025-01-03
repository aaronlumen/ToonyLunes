package com.example.obdracing.fragments;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.obdracing.ObdManager;
import com.example.obdracing.R;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DashboardFragment extends Fragment {

    private TextView txtConnectionStatus;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dashboard, container, false);

        txtConnectionStatus = view.findViewById(R.id.txt_connection_status);

        // Check if OBD is connected
        if (ObdManager.getInstance().isConnected()) {
            txtConnectionStatus.setText("OBD Connected");
        } else {
            txtConnectionStatus.setText("Not Connected");
        }
        return view;
    }
}
