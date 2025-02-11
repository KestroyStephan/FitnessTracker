package com.example.personalfitnesstracker;

public class StepData {
    private long timestamp;
    private int steps;

    public StepData(long timestamp, int steps) {
        this.timestamp = timestamp;
        this.steps = steps;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public int getSteps() {
        return steps;
    }
}

