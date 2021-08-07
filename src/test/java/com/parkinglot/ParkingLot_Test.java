package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

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
        assertEquals(car, bobCar);
    }

    @Test
    public void should_return_correct_car_when_fetch_car_two_times_given_parking_lot_has_two_parked_cars() throws Exception {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car davidCar = new Car();
        Car randomCar = new Car();
        //when
        ParkingTicket davidParkingTicket = parkingLot.park(davidCar);
        ParkingTicket randomParkingTicket = parkingLot.park(randomCar);
        Car actualDavidCar = parkingLot.fetchCar(davidParkingTicket);
        Car actualRandomCar = parkingLot.fetchCar(randomParkingTicket);

        //then
        assertEquals(davidCar, actualDavidCar);
        assertEquals(randomCar, actualRandomCar);
    }

    @Test
    public void should_not_return_any_car_and_display_error_message_when_fetch_car_given_parking_ticket_is_wrong() throws Exception {
        //given
        ParkingLot parkingLot = new ParkingLot();
        //when
        ParkingTicket wrongParkingTicket = new ParkingTicket();
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> parkingLot.fetchCar(wrongParkingTicket));
        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    public void should_not_return_any_car_and_display_error_message_when_fetch_car_given_parking_ticket_is_used_already() throws Exception {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        //when
        ParkingTicket parkingTicket = parkingLot.park(car);
        parkingLot.fetchCar(parkingTicket);
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> parkingLot.fetchCar(parkingTicket));

        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    public void should_return_nothing_and_display_error_message_when_fetch_car_given_parking_lot_is_full() throws Exception {
        //given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car();
        List<ParkingTicket> parkingTicket = new LinkedList<>();
        //when
        for (int count = 0; count < 10; count++) {
            parkingTicket.add(parkingLot.park(car));
        }
        Exception exception = assertThrows(NoAvailablePositionException.class, () -> parkingLot.park(car));
        //then
        assertEquals("No available position.", exception.getMessage());
    }
}
