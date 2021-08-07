package com.parkinglot;

public class StandardParkingBoy {
    private ParkingLot parkingLot = new ParkingLot();

    public ParkingTicket park(Car car){
        return parkingLot.park(car);
    }

    public Car fetchCar(ParkingTicket parkingTicket) {
        return parkingLot.fetchCar(parkingTicket);
    }
}
