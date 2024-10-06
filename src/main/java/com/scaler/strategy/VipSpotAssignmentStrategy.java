package com.scaler.strategy;

import com.scaler.models.Gate;
import com.scaler.models.ParkingSpot;
import com.scaler.models.Vehicle;

public class VipSpotAssignmentStrategy implements SpotAssignmentStrategy{
    @Override
    public ParkingSpot assignSpot(Gate gate, Vehicle vehicle) {
        return null;
    }
}
