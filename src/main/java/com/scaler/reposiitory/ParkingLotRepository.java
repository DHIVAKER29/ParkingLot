package com.scaler.reposiitory;

import com.scaler.models.ParkingLot;

import java.util.Optional;

public class ParkingLotRepository {

    public Optional<ParkingLot> findByGateId(Long gateId) {
        return Optional.empty();
    }
}