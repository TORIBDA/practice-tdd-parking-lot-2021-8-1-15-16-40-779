package com.parkinglot;

import java.util.LinkedList;
import java.util.List;

public abstract class ParkingBoy {
    private List<ParkingLot> parkingLots = new LinkedList<>();

    public ParkingBoy(ParkingLot parkingLot) {
        this.parkingLots.add(parkingLot);
    }

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public abstract ParkingTicket park(Car car);

    public Car fetchCar(ParkingTicket parkingTicket) {
        return findParkingLotBasedOnParkingTicket(parkingTicket).fetchCar(parkingTicket);
    }

    private ParkingLot findParkingLotBasedOnParkingTicket(ParkingTicket parkingTicket)
    {
        return parkingLots.stream()
                .filter(parkingLot -> !parkingLot.isUnrecognizedParkingTicket(parkingTicket))
                .findFirst()
                .orElseThrow(UnrecognizedParkingTicketException::new);
    }

    protected List<ParkingLot> getParkingLots() {
        return parkingLots;
    }
}
