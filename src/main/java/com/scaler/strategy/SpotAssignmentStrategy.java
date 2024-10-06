package com.scaler.strategy;

import com.scaler.models.Gate;
import com.scaler.models.ParkingSpot;
import com.scaler.models.Vehicle;
import com.scaler.models.VehicleType;

public interface SpotAssignmentStrategy {
    ParkingSpot assignSpot(Gate gate, VehicleType vehicleType);
}
