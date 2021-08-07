package com.parkinglot;

import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy extends ParkingBoy {
    public SmartParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    public ParkingTicket park(Car car) {
        return getParkingLots().stream()
                .filter(parkingLot -> !parkingLot.isParkingLotFull())
                .min(Comparator.comparing(ParkingLot::getCurrentParkedCarsCount))
                .orElseThrow(NoAvailablePositionException::new)
                .park(car);
    }
}
