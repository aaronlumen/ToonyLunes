package com.example.obdracing.models;

public class SensorData {
    private String sensorName;
    private String sensorValue;
    private String sensorUnit;

    public SensorData(String sensorName, String sensorValue, String sensorUnit) {
        this.sensorName = sensorName;
        this.sensorValue = sensorValue;
        this.sensorUnit = sensorUnit;
    }

    public String getSensorName() {
        return sensorName;
    }

    public String getSensorValue() {
        return sensorValue;
    }

    public String getSensorUnit() {
        return sensorUnit;
    }

    public void setSensorValue(String sensorValue) {
        this.sensorValue = sensorValue;
    }

    public void setSensorUnit(String sensorUnit) {
        this.sensorUnit = sensorUnit;
    }
}
