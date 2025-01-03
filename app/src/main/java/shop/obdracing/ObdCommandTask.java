package com.example.obdracing;

import android.os.AsyncTask;
import java.io.IOException;
import java.util.List;

public class ObdCommandTask extends AsyncTask<Void, String, Void> {

    private List<String> commands;
    private ObdManager obdManager;
    private SensorDataListener listener;
    private boolean running = true;

    public interface SensorDataListener {
        void onNewSensorData(String command, String rawResult);
    }

    public ObdCommandTask(List<String> commands, SensorDataListener listener) {
        this.commands = commands;
        this.listener = listener;
        this.obdManager = ObdManager.getInstance();
    }

    @Override
    protected Void doInBackground(Void... voids) {
        while (running && obdManager.isConnected()) {
            try {
                for (String cmd : commands) {
                    obdManager.sendObdCommand(cmd);
                    Thread.sleep(200);  // small pause
                    String response = obdManager.readObdResponse();
                    publishProgress(cmd, response);
                    Thread.sleep(300);
                }
            } catch (IOException | InterruptedException e) {
                e.printStackTrace();
                running = false;
            }
        }
        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        String cmd = values[0];
        String resp = values[1];
        if (listener != null) {
            listener.onNewSensorData(cmd, resp);
        }
    }

    public void stopTask() {
        running = false;
    }
}