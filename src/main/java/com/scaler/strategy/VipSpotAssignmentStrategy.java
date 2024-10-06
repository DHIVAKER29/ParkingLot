package com.scaler.strategy;

import com.scaler.models.Gate;
import com.scaler.models.ParkingSpot;
import com.scaler.models.Vehicle;
import com.scaler.models.VehicleType;

public class VipSpotAssignmentStrategy implements SpotAssignmentStrategy{
    @Override
    public ParkingSpot assignSpot(Gate gate, VehicleType vehicleType) {
        return null;
    }
}
