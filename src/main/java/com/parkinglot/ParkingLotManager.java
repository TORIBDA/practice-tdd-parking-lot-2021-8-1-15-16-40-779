package com.parkinglot;

import java.util.LinkedList;
import java.util.List;

public class ParkingLotManager extends StandardParkingBoy{
    List<ParkingBoy> parkingBoys = new LinkedList<>();

    public ParkingLotManager(ParkingLot parkingLot) {
        super(parkingLot);
    }

    public ParkingLotManager(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    public ParkingLotManager(List<ParkingLot> parkingLots, List<ParkingBoy> parkingBoys) {
        super(parkingLots);
        this.parkingBoys = parkingBoys;
    }

    public ParkingLotManager(ParkingLot parkingLots, List<ParkingBoy> parkingBoys) {
        super(parkingLots);
        this.parkingBoys = parkingBoys;
    }

    public ParkingTicket askParkingBoyToPark(ParkingBoy parkingBoy, Car car) {
        return parkingBoys.stream()
                .filter(parkingBoyValue -> parkingBoyValue.equals(parkingBoy))
                .findFirst()
                .orElse(null)
                .park(car);
    }
}
