package com.example.obdracing.models;

public class ObdCommandItem {
    private String command;
    private String description;

    public ObdCommandItem(String command, String description) {
        this.command = command;
        this.description = description;
    }

    public String getCommand() {
        return command;
    }

    public String getDescription() {
        return description;
    }
}
