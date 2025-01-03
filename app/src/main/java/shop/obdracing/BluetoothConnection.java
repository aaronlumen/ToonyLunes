package com.example.obdracing;

import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothSocket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

public class BluetoothConnection {

    private BluetoothSocket socket;

    public boolean connect(BluetoothDevice device) {
        try {
            socket = device.createRfcommSocketToServiceRecord(
                    UUID.fromString("00001101-0000-1000-8000-00805F9B34FB"));
            socket.connect();
            return true;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void disconnect() {
        if (socket != null) {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public InputStream getInputStream() throws IOException {
        if (socket != null) {
            return socket.getInputStream();
        }
        return null;
    }

    public OutputStream getOutputStream() throws IOException {
        if (socket != null) {
            return socket.getOutputStream();
        }
        return null;
    }
}
