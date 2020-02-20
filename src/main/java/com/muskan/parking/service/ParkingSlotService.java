package com.muskan.parking.service;

import com.muskan.parking.bo.Bill;
import com.muskan.parking.bo.ParkingSlot;
import com.muskan.parking.bo.Vehicle;
import com.muskan.parking.constant.VehicleType;
import com.muskan.parking.exception.IncorrectParkingSlot;
import com.muskan.parking.exception.ParkingSlotNotAvailable;
import com.muskan.parking.exception.VehicleNotAvailable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;
import java.util.stream.IntStream;

@Service
public class ParkingSlotService {

    Map<String,ParkingSlot> licenseParkingSlotMap  = new HashMap<>();

    static List<ParkingSlot> parkingSlotList = new ArrayList<>();

    static {
        IntStream.range(1,11).forEach(i->{
            parkingSlotList.add(new ParkingSlot(i,1, VehicleType.CAR));
        });

        IntStream.range(11,16).forEach(i->{
            parkingSlotList.add(new ParkingSlot(i,1, VehicleType.BUS));
        });

        IntStream.range(16,26).forEach(i->{
            parkingSlotList.add(new ParkingSlot(i,1, VehicleType.BIKE));
        });

    }

    public ParkingSlot getAvailableParkingSlotAndPark(Vehicle vehicle) throws ParkingSlotNotAvailable, IncorrectParkingSlot {
        Optional<ParkingSlot> availablePs;
        availablePs = parkingSlotList.stream()
                        .filter(ps->ps.isAvailable() && ps.getType().equals(vehicle.getVType()))
                        .findFirst();
        if(availablePs.isPresent()){
            availablePs.get().park(vehicle);
            licenseParkingSlotMap.put(vehicle.getNumber(),availablePs.get());
            return availablePs.get();
        }else {
            throw new ParkingSlotNotAvailable("Parking not available");
        }
    }

    public Bill unpark(String vNumber) throws VehicleNotAvailable {

        ParkingSlot ps = licenseParkingSlotMap.get(vNumber);

        if(ps!= null) {
            Bill bill = ps.unparkAndGetBill();
            licenseParkingSlotMap.remove(vNumber);
            return bill;
        }else{
            throw new VehicleNotAvailable("No vehicle is parked with "+vNumber);
        }

    }

}
