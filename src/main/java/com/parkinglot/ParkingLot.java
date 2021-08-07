package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private Map<ParkingTicket, Car> parkedPosition = new HashMap<>();
    private final int PARKING_LOT_CAPACITY = 10;

    public ParkingTicket park(Car car) {
        if (parkedPosition.size() >= PARKING_LOT_CAPACITY) {
            return null;
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

    public static void main(String[] args) {
        try{
            ParkingLot parkingLot = new ParkingLot();
            //when
            ParkingTicket wrongParkingTicket = new ParkingTicket();
            Car actualCar = parkingLot.fetchCar(wrongParkingTicket);
        } catch (UnrecognizedParkingTicketException exc)
        {
            System.out.println(exc.getMessage());
        }
    }
}
