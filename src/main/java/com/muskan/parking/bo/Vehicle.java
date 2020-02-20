package com.muskan.parking.bo;

import com.muskan.parking.constant.VehicleType;
import lombok.Data;

@Data
public  abstract class Vehicle {

    private String number;
    private VehicleType vType;
    private String color;

    public Vehicle(VehicleType vType, String number, String color) {
        this.vType = vType;
        this.number = number;
        this.color = color;

    }
}
