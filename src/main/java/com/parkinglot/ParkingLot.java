package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private Map<ParkingTicket, Car> parkedPosition = new HashMap<>();

    private final int PARKING_LOT_CAPACITY;
    private final int DEFAULT_PARKING_LOT_CAPACITY = 10;

    public ParkingLot(){
        PARKING_LOT_CAPACITY = DEFAULT_PARKING_LOT_CAPACITY;
    }

    public ParkingLot(int parkingLotCapacity) {
        this.PARKING_LOT_CAPACITY = parkingLotCapacity;
    }

    public ParkingTicket park(Car car) {
        if (isParkingLotFull()) {
            throw new NoAvailablePositionException();
        } else {
            ParkingTicket parkingTicket = new ParkingTicket();
            parkedPosition.putIfAbsent(parkingTicket, car);
            return parkingTicket;
        }
    }

    public Car fetchCar(ParkingTicket parkingTicket) {
        if (isUnrecognizedParkingTicket(parkingTicket)) {
            throw new UnrecognizedParkingTicketException();
        }
        Car fetchedCar = parkedPosition.get(parkingTicket);
        removeCarFromParkingLot(parkingTicket);
        return fetchedCar;
    }

    public boolean isUnrecognizedParkingTicket(ParkingTicket parkingTicket) {
        return !parkedPosition.containsKey(parkingTicket);
    }

    private void removeCarFromParkingLot(ParkingTicket parkingTicket) {
        parkedPosition.remove(parkingTicket);
    }

    public boolean isParkingLotFull() {
        return parkedPosition.size() >= PARKING_LOT_CAPACITY;
    }

    public int getCurrentParkedCarsCount() {
        return parkedPosition.size();
    }

    public float computeAvailablePosition() {
        return (float)(PARKING_LOT_CAPACITY - getCurrentParkedCarsCount()) / PARKING_LOT_CAPACITY;
    }
}
