package com.parkinglot;

import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingLot_Test {
    //<editor-fold desc="Parking Lot Region">
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

    //</editor-fold>
    //<editor-fold desc="Standard Parking Boy">
    @Test
    public void should_return_ticket_when_standard_parking_boy_park_car_given_parking_lot_has_open_spaces() throws Exception {
        //given
        ParkingLot parkingLot = new ParkingLot();
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLot);
        Car car = new Car();
        //when
        ParkingTicket parkingTicket = standardParkingBoy.park(car);
        //then
        assertNotNull(parkingTicket);
    }

    @Test
    public void should_return_car_when_standard_parking_boy_fetch_car_given_parking_lot_has_parked_car() throws Exception {
        //given
        ParkingLot parkingLot = new ParkingLot();
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLot);
        Car car = new Car();
        //when
        ParkingTicket parkingTicket = standardParkingBoy.park(car);
        Car actualCar = standardParkingBoy.fetchCar(parkingTicket);
        //then
        assertEquals(car, actualCar);
    }

    @Test
    public void should_return_correct_car_when_standard_parking_boy_fetch_car_two_times_given_parking_lot_has_two_parked_cars() throws Exception {
        //given
        ParkingLot parkingLot = new ParkingLot();
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLot);
        Car davidCar = new Car();
        Car randomCar = new Car();
        //when
        ParkingTicket davidParkingTicket = standardParkingBoy.park(davidCar);
        ParkingTicket randomParkingTicket = standardParkingBoy.park(randomCar);
        Car actualDavidCar = standardParkingBoy.fetchCar(davidParkingTicket);
        Car actualRandomCar = standardParkingBoy.fetchCar(randomParkingTicket);
        //then
        assertEquals(davidCar, actualDavidCar);
        assertEquals(randomCar, actualRandomCar);
    }

    @Test
    public void should_not_return_any_car_and_display_error_message_when_standard_parking_boy_fetch_car_given_parking_ticket_is_wrong() throws Exception {
        //given
        ParkingLot parkingLot = new ParkingLot();
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLot);
        //when
        ParkingTicket wrongParkingTicket = new ParkingTicket();
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> standardParkingBoy.fetchCar(wrongParkingTicket));
        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    public void should_not_return_any_car_and_display_error_message_when_standard_parking_boy_fetch_car_given_parking_ticket_is_used_already() throws Exception {
        //given
        ParkingLot parkingLot = new ParkingLot();
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLot);
        Car car = new Car();
        //when
        ParkingTicket parkingTicket = standardParkingBoy.park(car);
        standardParkingBoy.fetchCar(parkingTicket);
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> standardParkingBoy.fetchCar(parkingTicket));
        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    public void should_be_able_to_park_more_than_10_when_standard_parking_boy_park_more_than_10_cars_but_not_higher_than_20_given_2_parking_lot() throws Exception {
        //given
        List<ParkingLot> parkingLots = new LinkedList<>();
        parkingLots.add(new ParkingLot());
        parkingLots.add(new ParkingLot());
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLots);
        Car car = new Car();
        List<ParkingTicket> parkingTicket = new LinkedList<>();
        //when
        for (int count = 0; count < 20; count++) {
            parkingTicket.add(standardParkingBoy.park(car));
        }
        Exception exception = assertThrows(NoAvailablePositionException.class, () -> standardParkingBoy.park(car));
        //then
        assertEquals("No available position.", exception.getMessage());
    }

    //</editor-fold>
    //<editor-fold desc="Smart Parking Boy">
    @Test
    public void should_return_ticket_when_smart_parking_boy_park_car_given_parking_lot_has_open_spaces() throws Exception {
        //given
        ParkingLot parkingLot = new ParkingLot();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot);
        Car car = new Car();
        //when
        ParkingTicket parkingTicket = smartParkingBoy.park(car);
        //then
        assertNotNull(parkingTicket);
    }

    @Test
    public void should_return_car_when_smart_parking_boy_fetch_car_given_parking_lot_has_parked_car() throws Exception {
        //given
        ParkingLot parkingLot = new ParkingLot();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot);
        Car car = new Car();
        //when
        ParkingTicket parkingTicket = smartParkingBoy.park(car);
        Car actualCar = smartParkingBoy.fetchCar(parkingTicket);
        //then
        assertEquals(car, actualCar);
    }

    @Test
    public void should_return_correct_car_when_smart_parking_boy_fetch_car_two_times_given_parking_lot_has_two_parked_cars() throws Exception {
        //given
        ParkingLot parkingLot = new ParkingLot();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot);
        Car davidCar = new Car();
        Car randomCar = new Car();
        //when
        ParkingTicket davidParkingTicket = smartParkingBoy.park(davidCar);
        ParkingTicket randomParkingTicket = smartParkingBoy.park(randomCar);
        Car actualDavidCar = smartParkingBoy.fetchCar(davidParkingTicket);
        Car actualRandomCar = smartParkingBoy.fetchCar(randomParkingTicket);
        //then
        assertEquals(davidCar, actualDavidCar);
        assertEquals(randomCar, actualRandomCar);
    }

    @Test
    public void should_not_return_any_car_and_display_error_message_when_smart_parking_boy_fetch_car_given_parking_ticket_is_wrong() throws Exception {
        //given
        ParkingLot parkingLot = new ParkingLot();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot);
        //when
        ParkingTicket wrongParkingTicket = new ParkingTicket();
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> smartParkingBoy.fetchCar(wrongParkingTicket));
        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    public void should_not_return_any_car_and_display_error_message_when_smart_parking_boy_fetch_car_given_parking_ticket_is_used_already() throws Exception {
        //given
        ParkingLot parkingLot = new ParkingLot();
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLot);
        Car car = new Car();
        //when
        ParkingTicket parkingTicket = smartParkingBoy.park(car);
        smartParkingBoy.fetchCar(parkingTicket);
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> smartParkingBoy.fetchCar(parkingTicket));
        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    public void should_be_able_to_park_more_than_10_when_smart_parking_boy_park_more_than_10_cars_but_not_higher_than_20_given_2_parking_lot() throws Exception {
        //given
        List<ParkingLot> parkingLots = new LinkedList<>();
        parkingLots.add(new ParkingLot());
        parkingLots.add(new ParkingLot());
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car();
        List<ParkingTicket> parkingTicket = new LinkedList<>();
        //when
        for (int count = 0; count < 20; count++) {
            parkingTicket.add(smartParkingBoy.park(car));
        }
        Exception exception = assertThrows(NoAvailablePositionException.class, () -> smartParkingBoy.park(car));
        //then
        assertEquals("No available position.", exception.getMessage());
    }

    @Test
    public void should_park_cars_to_the_parking_lot_when_smart_parking_boy_park_car_given_parking_lot_has_more_empty_position() throws Exception {
        //given
        List<ParkingLot> parkingLots = new LinkedList<>();
        parkingLots.add(new ParkingLot());
        parkingLots.add(new ParkingLot());
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        Car car = new Car();
        List<ParkingTicket> parkingTicket = new LinkedList<>();
        //when
        for (int count = 0; count < 15; count++) {
            parkingTicket.add(smartParkingBoy.park(car));
        }
        //then
        assertEquals(8, parkingLots.get(0).getCurrentParkedCarsCount());
        assertEquals(7, parkingLots.get(1).getCurrentParkedCarsCount());
        assertTrue(parkingLots.get(0).getCurrentParkedCarsCount() > parkingLots.get(1).getCurrentParkedCarsCount());
    }

    //</editor-fold>
    //<editor-fold desc="Super Smart Parking Boy">
    @Test
    public void should_return_ticket_when_super_smart_parking_boy_park_car_given_parking_lot_has_open_spaces() throws Exception {
        //given
        ParkingLot parkingLot = new ParkingLot();
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLot);
        Car car = new Car();
        //when
        ParkingTicket parkingTicket = superSmartParkingBoy.park(car);
        //then
        assertNotNull(parkingTicket);
    }

    @Test
    public void should_return_car_when_super_smart_parking_boy_fetch_car_given_parking_lot_has_parked_car() throws Exception {
        //given
        ParkingLot parkingLot = new ParkingLot();
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLot);
        Car car = new Car();
        //when
        ParkingTicket parkingTicket = superSmartParkingBoy.park(car);
        Car actualCar = superSmartParkingBoy.fetchCar(parkingTicket);
        //then
        assertEquals(car, actualCar);
    }

    @Test
    public void should_return_correct_car_when_super_smart_parking_boy_fetch_car_two_times_given_parking_lot_has_two_parked_cars() throws Exception {
        //given
        ParkingLot parkingLot = new ParkingLot();
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLot);
        Car davidCar = new Car();
        Car randomCar = new Car();
        //when
        ParkingTicket davidParkingTicket = superSmartParkingBoy.park(davidCar);
        ParkingTicket randomParkingTicket = superSmartParkingBoy.park(randomCar);
        Car actualDavidCar = superSmartParkingBoy.fetchCar(davidParkingTicket);
        Car actualRandomCar = superSmartParkingBoy.fetchCar(randomParkingTicket);
        //then
        assertEquals(davidCar, actualDavidCar);
        assertEquals(randomCar, actualRandomCar);
    }

    @Test
    public void should_not_return_any_car_and_display_error_message_when_super_smart_parking_boy_fetch_car_given_parking_ticket_is_wrong() throws Exception {
        //given
        ParkingLot parkingLot = new ParkingLot();
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLot);
        //when
        ParkingTicket wrongParkingTicket = new ParkingTicket();
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> superSmartParkingBoy.fetchCar(wrongParkingTicket));
        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    public void should_not_return_any_car_and_display_error_message_when_super_smart_parking_boy_fetch_car_given_parking_ticket_is_used_already() throws Exception {
        //given
        ParkingLot parkingLot = new ParkingLot();
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLot);
        Car car = new Car();
        //when
        ParkingTicket parkingTicket = superSmartParkingBoy.park(car);
        superSmartParkingBoy.fetchCar(parkingTicket);
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> superSmartParkingBoy.fetchCar(parkingTicket));
        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    public void should_be_able_to_park_more_than_10_when_super_smart_parking_boy_park_more_than_10_cars_but_not_higher_than_20_given_2_parking_lot() throws Exception {
        //given
        List<ParkingLot> parkingLots = new LinkedList<>();
        parkingLots.add(new ParkingLot());
        parkingLots.add(new ParkingLot());
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        Car car = new Car();
        List<ParkingTicket> parkingTicket = new LinkedList<>();
        //when
        for (int count = 0; count < 20; count++) {
            parkingTicket.add(superSmartParkingBoy.park(car));
        }
        Exception exception = assertThrows(NoAvailablePositionException.class, () -> superSmartParkingBoy.park(car));
        //then
        assertEquals("No available position.", exception.getMessage());
    }

    @Test
    public void should_park_cars_to_the_parking_lot_when_super_smart_parking_boy_park_car_given_parking_lot_has_more_available_positions() throws Exception {
        //given
        List<ParkingLot> parkingLots = new LinkedList<>();
        parkingLots.add(new ParkingLot(10));
        parkingLots.add(new ParkingLot(100));
        SuperSmartParkingBoy superSmartParkingBoy = new SuperSmartParkingBoy(parkingLots);
        Car car = new Car();
        List<ParkingTicket> parkingTicket = new LinkedList<>();
        //when
        for (int count = 0; count < 10; count++) {
            parkingTicket.add(superSmartParkingBoy.park(car));
        }
        //then
        assertEquals(1, parkingLots.get(0).getCurrentParkedCarsCount());
        assertEquals(9, parkingLots.get(1).getCurrentParkedCarsCount());
        assertTrue(parkingLots.get(0).getCurrentParkedCarsCount() < parkingLots.get(1).getCurrentParkedCarsCount());
    }

    //</editor-fold>
    //<editor-fold desc="Parking Lot Manager">
    @Test
    public void should_return_ticket_when_parking_manager_park_car_given_parking_lot_has_open_spaces() throws Exception {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingLotManager parkingLotManager = new ParkingLotManager(parkingLot);
        Car car = new Car();
        //when
        ParkingTicket parkingTicket = parkingLotManager.park(car);
        //then
        assertNotNull(parkingTicket);
    }

    @Test
    public void should_return_car_when_parking_manager_fetch_car_given_parking_lot_has_parked_car() throws Exception {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingLotManager parkingLotManager = new ParkingLotManager(parkingLot);
        Car car = new Car();
        //when
        ParkingTicket parkingTicket = parkingLotManager.park(car);
        Car actualCar = parkingLotManager.fetchCar(parkingTicket);
        //then
        assertEquals(car, actualCar);
    }

    @Test
    public void should_return_correct_car_when_parking_manager_fetch_car_two_times_given_parking_lot_has_two_parked_cars() throws Exception {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingLotManager parkingLotManager = new ParkingLotManager(parkingLot);
        Car davidCar = new Car();
        Car randomCar = new Car();
        //when
        ParkingTicket davidParkingTicket = parkingLotManager.park(davidCar);
        ParkingTicket randomParkingTicket = parkingLotManager.park(randomCar);
        Car actualDavidCar = parkingLotManager.fetchCar(davidParkingTicket);
        Car actualRandomCar = parkingLotManager.fetchCar(randomParkingTicket);
        //then
        assertEquals(davidCar, actualDavidCar);
        assertEquals(randomCar, actualRandomCar);
    }

    @Test
    public void should_not_return_any_car_and_display_error_message_when_parking_manager_fetch_car_given_parking_ticket_is_wrong() throws Exception {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingLotManager parkingLotManager = new ParkingLotManager(parkingLot);
        //when
        ParkingTicket wrongParkingTicket = new ParkingTicket();
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> parkingLotManager.fetchCar(wrongParkingTicket));
        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    public void should_not_return_any_car_and_display_error_message_when_parking_lot_manager_fetch_car_given_parking_ticket_is_used_already() throws Exception {
        //given
        ParkingLot parkingLot = new ParkingLot();
        ParkingLotManager parkingLotManager = new ParkingLotManager(parkingLot);
        Car car = new Car();
        //when
        ParkingTicket parkingTicket = parkingLotManager.park(car);
        parkingLotManager.fetchCar(parkingTicket);
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> parkingLotManager.fetchCar(parkingTicket));
        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    public void should_be_able_to_park_more_than_10_when_parking_lot_manager_park_more_than_10_cars_but_not_higher_than_20_given_2_parking_lot() throws Exception {
        //given
        List<ParkingLot> parkingLots = new LinkedList<>();
        parkingLots.add(new ParkingLot());
        parkingLots.add(new ParkingLot());
        ParkingLotManager parkingLotManager = new ParkingLotManager(parkingLots);
        Car car = new Car();
        List<ParkingTicket> parkingTicket = new LinkedList<>();
        //when
        for (int count = 0; count < 20; count++) {
            parkingTicket.add(parkingLotManager.park(car));
        }
        Exception exception = assertThrows(NoAvailablePositionException.class, () -> parkingLotManager.park(car));
        //then
        assertEquals("No available position.", exception.getMessage());
    }

    @Test
    public void should_add_parking_boy_when_parking_lot_manager_add_parking_boy_given_parking_boy_existing() throws Exception {
        //given
        ParkingLot parkingLot = new ParkingLot();
        StandardParkingBoy expectedParkingBoy = new StandardParkingBoy(parkingLot);
        ParkingLotManager parkingLotManager = new ParkingLotManager(new ParkingLot());
        //when
        parkingLotManager.addParkingBoy(expectedParkingBoy);
        ParkingBoy actualParkingBoyAdded = parkingLotManager.getParkingBoys()
                .stream()
                .filter(parkingBoy -> parkingBoy.equals(expectedParkingBoy))
                .findFirst()
                .get();
        //then
        assertNotNull(actualParkingBoyAdded);
    }

    @Test
    public void should_park_cars_to_the_parking_lot_by_parking_boy_when_parking_manager_asked_parking_boy_to_park_car_given_parking_lot_has_available_positions() throws Exception {
        //given
        Car car = new Car();

        List<ParkingLot> parkingLots = new LinkedList<>();
        parkingLots.add(new ParkingLot(10));
        parkingLots.add(new ParkingLot(100));

        SuperSmartParkingBoy standardParkingBoy = new SuperSmartParkingBoy(parkingLots);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        StandardParkingBoy superSmartParkingBoy = new StandardParkingBoy(parkingLots);

        List<ParkingBoy> parkingBoys = new LinkedList<>();
        parkingBoys.add(superSmartParkingBoy);
        parkingBoys.add(smartParkingBoy);
        parkingBoys.add(standardParkingBoy);

        ParkingLotManager parkingLotManager = new ParkingLotManager(new ParkingLot(), parkingBoys);
        //when
        ParkingTicket parkingTicket = parkingLotManager.askParkingBoyToPark(smartParkingBoy, car);

        //then
        assertNotNull(parkingTicket);
    }

    @Test
    public void should_fetch_cars_by_parking_boy_when_parking_manager_asked_parking_boy_to_fetch_car_given_parking_boy_parking_lot_has_the_car() throws Exception {
        //given
        Car car = new Car();

        List<ParkingLot> parkingLots = new LinkedList<>();
        parkingLots.add(new ParkingLot(10));
        parkingLots.add(new ParkingLot(100));

        SuperSmartParkingBoy standardParkingBoy = new SuperSmartParkingBoy(parkingLots);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);
        StandardParkingBoy superSmartParkingBoy = new StandardParkingBoy(parkingLots);

        List<ParkingBoy> parkingBoys = new LinkedList<>();
        parkingBoys.add(superSmartParkingBoy);
        parkingBoys.add(smartParkingBoy);
        parkingBoys.add(standardParkingBoy);

        ParkingLotManager parkingLotManager = new ParkingLotManager(new ParkingLot(), parkingBoys);
        //when
        ParkingTicket parkingTicket = parkingLotManager.askParkingBoyToPark(smartParkingBoy, car);
        Car actualCar = parkingLotManager.askParkingBoyToFetchCar(smartParkingBoy, parkingTicket);

        //then
        assertNotNull(parkingTicket);
        assertEquals(car, actualCar);
    }

    @Test
    public void should_not_return_any_car_and_display_error_message_when_parking_manager_asked_parking_boy_fetch_car_given_parking_ticket_is_wrong() throws Exception {
        //given
        ParkingLot parkingLot = new ParkingLot();
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLot);
        ParkingLotManager parkingLotManager = new ParkingLotManager(new ParkingLot());
        parkingLotManager.addParkingBoy(standardParkingBoy);
        //when
        ParkingTicket wrongParkingTicket = new ParkingTicket();
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> parkingLotManager.askParkingBoyToFetchCar(standardParkingBoy, wrongParkingTicket));
        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    public void should_not_return_any_car_and_display_error_message_when_parking_lot_manager_asked_parking_boy_fetch_car_given_parking_ticket_is_used_already() throws Exception {
        //given
        ParkingLot parkingLot = new ParkingLot();
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLot);
        ParkingLotManager parkingLotManager = new ParkingLotManager(new ParkingLot());
        parkingLotManager.addParkingBoy(standardParkingBoy);
        Car car = new Car();
        //when
        ParkingTicket parkingTicket = parkingLotManager.askParkingBoyToPark(standardParkingBoy, car);
        parkingLotManager.askParkingBoyToFetchCar(standardParkingBoy, parkingTicket);
        Exception exception = assertThrows(UnrecognizedParkingTicketException.class, () -> parkingLotManager.askParkingBoyToFetchCar(standardParkingBoy, parkingTicket));
        //then
        assertEquals("Unrecognized parking ticket.", exception.getMessage());
    }

    @Test
    public void should_be_able_to_park_more_than_10_when_parking_lot_manager_ask_parking_boy_park_more_than_10_cars_but_not_higher_than_20_given_2_parking_lot() throws Exception {
        //given
        List<ParkingLot> parkingLots = new LinkedList<>();
        parkingLots.add(new ParkingLot());
        parkingLots.add(new ParkingLot());
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLots);
        ParkingLotManager parkingLotManager = new ParkingLotManager(new ParkingLot());
        parkingLotManager.addParkingBoy(standardParkingBoy);
        Car car = new Car();
        List<ParkingTicket> parkingTicket = new LinkedList<>();
        //when
        for (int count = 0; count < 20; count++) {
            parkingTicket.add(parkingLotManager.askParkingBoyToPark(standardParkingBoy, car));
        }
        Exception exception = assertThrows(NoAvailablePositionException.class, () -> parkingLotManager.askParkingBoyToPark(standardParkingBoy, car));
        //then
        assertEquals("No available position.", exception.getMessage());
    }
    //</editor-fold>
}