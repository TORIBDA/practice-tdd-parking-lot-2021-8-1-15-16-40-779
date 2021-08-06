package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private Map<ParkingTicket, Car> parkedPosition = new HashMap<>();

    public ParkingTicket park(Car car) {
        if (parkedPosition.size() >= 10) {
            return null;
        } else {
            ParkingTicket parkingTicket = new ParkingTicket();
            parkedPosition.putIfAbsent(parkingTicket, car);
            return parkingTicket;
        }
    }

    public Car fetchCar(ParkingTicket parkingTicket) {
        Car fetchedCar = parkedPosition.get(parkingTicket);
        removeCarFromParkingLot(parkingTicket);
        return fetchedCar;
    }

    private void removeCarFromParkingLot(ParkingTicket parkingTicket) {
        parkedPosition.remove(parkingTicket);
    }
}
