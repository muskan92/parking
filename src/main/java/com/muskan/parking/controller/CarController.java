package com.muskan.parking.controller;


import com.muskan.parking.bo.Bill;
import com.muskan.parking.bo.Car;
import com.muskan.parking.bo.ParkingSlot;
import com.muskan.parking.bo.Vehicle;
import com.muskan.parking.constant.VehicleType;
import com.muskan.parking.exception.IncorrectParkingSlot;
import com.muskan.parking.exception.ParkingSlotNotAvailable;
import com.muskan.parking.exception.VehicleNotAvailable;
import com.muskan.parking.service.ParkingSlotService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/car")
public class CarController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

        @Autowired
        private ParkingSlotService parkingSlotService;

    @RequestMapping(path = "/park", method = {RequestMethod.POST})
    public ParkingSlot parkCar(@RequestBody Car car) throws IncorrectParkingSlot, ParkingSlotNotAvailable {
        ParkingSlot parkingSlot = new ParkingSlot();
        try {
            parkingSlot = parkingSlotService.getAvailableParkingSlotAndPark(car);
        }catch (IncorrectParkingSlot ex){
            parkingSlot.setStatus(ex.getMessage());
            logger.error(ex.getMessage());
        }catch (ParkingSlotNotAvailable ex1){
            parkingSlot.setStatus(ex1.getMessage());
            logger.error(ex1.getMessage());
        }
        return parkingSlot;

    }

    @RequestMapping(path = "/unparkAndGetBill/{licenseNumber}", method = {RequestMethod.GET})
    public Bill unparkCar(@PathVariable("licenseNumber") String licenceNumber) {
        Bill bill = new Bill();
        try {
             bill = parkingSlotService.unpark(licenceNumber);
     }catch (VehicleNotAvailable e){
            bill.setStatus(e.getMessage());
            logger.error(e.getMessage());
        }
        return bill;

    }

}
