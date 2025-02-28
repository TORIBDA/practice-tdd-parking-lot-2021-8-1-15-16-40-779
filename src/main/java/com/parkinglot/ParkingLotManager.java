package com.parkinglot;

import java.util.LinkedList;
import java.util.List;

public class ParkingLotManager extends StandardParkingBoy {

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

    public ParkingLotManager(ParkingLot parkingLot, List<ParkingBoy> parkingBoys) {
        super(parkingLot);
        this.parkingBoys = parkingBoys;
    }

    public ParkingTicket askParkingBoyToPark(ParkingBoy parkingBoy, Car car) {
        return parkingBoys.stream()
                .filter(parkingBoyValue -> parkingBoyValue.equals(parkingBoy))
                .findFirst()
                .orElse(null)
                .park(car);
    }

    public Car askParkingBoyToFetchCar(ParkingBoy parkingBoy, ParkingTicket parkingTicket) {
        return parkingBoys.stream()
                .filter(parkingBoyValue -> parkingBoyValue.equals(parkingBoy))
                .findFirst()
                .orElse(null)
                .fetchCar(parkingTicket);
    }

    public void addParkingBoy(ParkingBoy parkingBoy) {
        parkingBoys.add(parkingBoy);
    }

    public List<ParkingBoy> getParkingBoys() {
        return parkingBoys;
    }
}
