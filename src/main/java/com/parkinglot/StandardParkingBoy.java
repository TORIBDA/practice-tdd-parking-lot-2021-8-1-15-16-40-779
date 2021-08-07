package com.parkinglot;

import java.util.LinkedList;
import java.util.List;

public class StandardParkingBoy {
    private List<ParkingLot> parkingLots = new LinkedList<>();

    public StandardParkingBoy(ParkingLot parkingLot) {
        this.parkingLots.add(parkingLot);
    }

    public StandardParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingTicket park(Car car){
        return parkingLots.stream().filter(parkingLot -> !parkingLot.isParkingLotFull())
                .findFirst()
                .orElseThrow(NoAvailablePositionException::new).park(car);
    }

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
}
