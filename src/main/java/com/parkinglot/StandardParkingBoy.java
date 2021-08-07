package com.parkinglot;

import java.util.LinkedList;
import java.util.List;

public class StandardParkingBoy extends ParkingBoy {

    public StandardParkingBoy(ParkingLot parkingLot) {
        super(parkingLot);
    }

    public StandardParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    public ParkingTicket park(Car car){
        return getParkingLots().stream().filter(parkingLot -> !parkingLot.isParkingLotFull())
                .findFirst()
                .orElseThrow(NoAvailablePositionException::new).park(car);
    }
}
