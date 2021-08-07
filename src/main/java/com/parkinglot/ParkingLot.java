package com.parkinglot;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ParkingLot {
    private Map<ParkingTicket, Car> parkedPosition = new HashMap<>();
    private final int PARKING_LOT_CAPACITY = 10;

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
        Car fetchedCar = parkedPosition.get(parkingTicket);
        isUnrecognizedParkingTicket(fetchedCar);
        removeCarFromParkingLot(parkingTicket);
        return fetchedCar;
    }

    private void isUnrecognizedParkingTicket(Car car) {
        if(car==null) {
            throw new UnrecognizedParkingTicketException();
        }
    }

    private void removeCarFromParkingLot(ParkingTicket parkingTicket) {
        parkedPosition.remove(parkingTicket);
    }

    public boolean isParkingLotFull() {
        return parkedPosition.size() >= PARKING_LOT_CAPACITY;
    }
}
