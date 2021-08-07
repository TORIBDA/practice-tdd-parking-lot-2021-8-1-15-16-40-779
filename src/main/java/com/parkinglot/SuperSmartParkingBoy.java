package com.parkinglot;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public class SuperSmartParkingBoy extends ParkingBoy {

    public SuperSmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    public SuperSmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public ParkingTicket park(Car car) {
        return getParkingLots().stream()
                .filter(parkingLot -> !parkingLot.isParkingLotFull())
                .max(Comparator.comparing(ParkingLot::computeAvailablePosition))
                .orElseThrow(NoAvailablePositionException::new)
                .park(car);
    }
}
