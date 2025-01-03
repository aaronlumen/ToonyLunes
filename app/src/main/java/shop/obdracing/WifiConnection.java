package com.example.obdracing;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class WifiConnection {

    private Socket socket;
    private String ipAddress;
    private int port;

    public WifiConnection(String ipAddress, int port) {
        this.ipAddress = ipAddress;
        this.port = port;
    }

    public boolean connect() {
        try {
            socket = new Socket(ipAddress, port);
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