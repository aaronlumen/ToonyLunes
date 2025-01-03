package com.example.obdracing;

import android.bluetooth.BluetoothDevice;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class ObdManager {

    private static ObdManager instance;
    private InputStream in;
    private OutputStream out;
    private boolean isConnected = false;

    private BluetoothConnection btConnection;
    private WifiConnection wifiConnection;

    private ObdManager() {}

    public static ObdManager getInstance() {
        if (instance == null) {
            instance = new ObdManager();
        }
        return instance;
    }

    public boolean connectBluetooth(BluetoothDevice device) {
        btConnection = new BluetoothConnection();
        boolean success = btConnection.connect(device);
        if (success) {
            try {
                in = btConnection.getInputStream();
                out = btConnection.getOutputStream();
                isConnected = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return success;
    }

    public boolean connectWifi(String ip, int port) {
        wifiConnection = new WifiConnection(ip, port);
        boolean success = wifiConnection.connect();
        if (success) {
            try {
                in = wifiConnection.getInputStream();
                out = wifiConnection.getOutputStream();
                isConnected = true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return success;
    }

    public boolean isConnected() {
        return isConnected;
    }

    public void disconnect() {
        isConnected = false;
        if (btConnection != null) btConnection.disconnect();
        if (wifiConnection != null) wifiConnection.disconnect();
    }

    public void sendObdCommand(String command) throws IOException {
        if (!isConnected) return;
        out.write((command + "\r").getBytes());
        out.flush();
    }

    public String readObdResponse() throws IOException {
        if (!isConnected) return "";
        StringBuilder sb = new StringBuilder();
        byte[] buffer = new byte[128];
        int bytesRead = in.read(buffer);
        if (bytesRead > 0) {
            sb.append(new String(buffer, 0, bytesRead));
        }
        return sb.toString();
    }
}
