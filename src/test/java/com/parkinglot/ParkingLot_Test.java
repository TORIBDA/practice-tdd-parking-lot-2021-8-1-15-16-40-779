package com.parkinglot;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ParkingLot_Test {
    @Test
    public void should_return_ticket_when_park_car_given_parking_lot_has_open_spaces() throws Exception {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        //when
        ParkingTicket parkingTicket = parkingLot.park(car);

        //then
        assertNotNull(parkingTicket);
    }

    @Test
    public void should_return_car_when_fetch_car_given_parking_lot_has_parked_car() throws Exception {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        //when
        ParkingTicket parkingTicket = parkingLot.park(car);
        Car bobCar = parkingLot.fetchCar(parkingTicket);

        //then
        assertEquals(car,bobCar);
    }
}
