package com.scaler.service;

/* STEPS :
        1. Get the Vehicle details from the DB.
        2. Get the gate details from the DB.
        3. Create a Ticket object.
        4. Assign the parking spot.
        5. return the ticket object.
         */

import com.scaler.exceptions.InvalidGateException;
import com.scaler.factories.SpotAssignmentStrategyFactory;
import com.scaler.models.*;
import com.scaler.reposiitory.GateRepository;
import com.scaler.reposiitory.ParkingLotRepository;
import com.scaler.reposiitory.TicketRepository;
import com.scaler.reposiitory.VehicleRepository;
import com.scaler.strategy.SpotAssignmentStrategy;

import java.util.Date;
import java.util.Optional;

public class TicketService {
    private GateRepository gateRepository;
    private VehicleRepository vehicleRepository;
    private ParkingLotRepository parkingLotRepository;
    private TicketRepository ticketRepository;

    public TicketService(GateRepository gateRepository, VehicleRepository vehicleRepository, ParkingLotRepository parkingLotRepository, TicketRepository ticketRepository) {
        this.gateRepository = gateRepository;
        this.vehicleRepository = vehicleRepository;
        this.parkingLotRepository = parkingLotRepository;
        this.ticketRepository = ticketRepository;
    }

    public Ticket issueTicket(String vehicleNumber, VehicleType vehicleType, String vehicleOwnername, Long gateId) throws InvalidGateException {
        Ticket ticket = new Ticket();
        ticket.setEntryTime(new Date());

        Optional<Gate> optionalGate = gateRepository.findGateById(gateId);

        if (optionalGate.isEmpty()) {
            throw new InvalidGateException("Gate with id: " + gateId + " doesn't exist");
        }

        Gate gate = optionalGate.get();
        ticket.setGate(gate);
        ticket.setGeneratedBy(gate.getOperator());

        Optional<Vehicle> optionalVehicle = vehicleRepository.findByVehicleNumber(vehicleNumber);
        Vehicle savedVehicle = null;

        if (optionalVehicle.isEmpty()) {
            //This vehicle is coming for the very first time in Parking Lot,
            //Create a Vehicle and save it to the DB.
            Vehicle vehicle = new Vehicle();
            vehicle.setNumber(vehicleNumber);
            vehicle.setVehicleType(vehicleType);
            vehicle.setOwnerName(vehicleOwnername);

            savedVehicle = vehicleRepository.save(vehicle);
        } else {
            savedVehicle = optionalVehicle.get();
        }

        ticket.setVehicle(savedVehicle);

        //Assign the Parking Spot.
        ParkingSpotAssignmentStrategy parkingSpotAssignmentStrategyType =
                parkingLotRepository.findByGateId(gateId).get().getParkingSpotAssignmentStrategy();

        SpotAssignmentStrategy spotAssignmentStrategy = SpotAssignmentStrategyFactory.
                getSpotAssignmentStrategy(parkingSpotAssignmentStrategyType);

        ParkingSpot parkingSpot = spotAssignmentStrategy.assignSpot(gate, vehicleType);
        ticket.setParkingSpot(parkingSpot);

        //Save the ticket to DB.
        return ticketRepository.save(ticket);
    }
}
