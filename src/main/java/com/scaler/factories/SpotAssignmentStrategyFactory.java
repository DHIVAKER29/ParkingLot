package com.scaler.factories;

import com.scaler.models.ParkingSpotAssignmentStrategy;
import com.scaler.strategy.NearestSpotAssignmentStrategy;
import com.scaler.strategy.RandomSpotAssignmentStrategy;
import com.scaler.strategy.SpotAssignmentStrategy;
import com.scaler.strategy.VipSpotAssignmentStrategy;

public class SpotAssignmentStrategyFactory {
    public static SpotAssignmentStrategy getSpotAssignmentStrategy(ParkingSpotAssignmentStrategy parkingSpotAssignmentStrategy) {
        if(parkingSpotAssignmentStrategy.equals(ParkingSpotAssignmentStrategy.NEAREST)){
            return new NearestSpotAssignmentStrategy();
        }
        else if(parkingSpotAssignmentStrategy.equals(ParkingSpotAssignmentStrategy.VIP)){
            return new VipSpotAssignmentStrategy();
        }
        else if(parkingSpotAssignmentStrategy.equals(ParkingSpotAssignmentStrategy.RANDOM)){
            return new RandomSpotAssignmentStrategy();
        }
        return null;
    }
}
