package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private Map<ParkingTicket, Car> parkedPosition = new HashMap<>();

    public ParkingTicket park(Car car) {
        ParkingTicket parkingTicket = new ParkingTicket();
        parkedPosition.putIfAbsent(parkingTicket, car);
        return parkingTicket;
    }

    public Car fetchCar(ParkingTicket parkingTicket) {
        return parkedPosition.get(parkingTicket);
    }
}
