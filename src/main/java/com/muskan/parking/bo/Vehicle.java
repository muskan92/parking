package com.muskan.parking.bo;

import com.muskan.parking.constant.VehicleType;
import lombok.Data;

@Data
public  abstract class Vehicle {

    private String number;
    private VehicleType vType;

    public Vehicle(VehicleType vType, String number) {
        this.vType = vType;
        this.number = number;

    }
}
