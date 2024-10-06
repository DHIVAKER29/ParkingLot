package com.scaler.strategy;

import com.scaler.models.Gate;
import com.scaler.models.ParkingSpot;
import com.scaler.models.Vehicle;

public interface SpotAssignmentStrategy {
    ParkingSpot assignSpot(Gate gate, Vehicle vehicle);
}
