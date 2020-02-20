package com.muskan.parking.bo;

import com.muskan.parking.constant.VehicleType;
import lombok.Data;

import java.util.Date;

@Data
public class Bill {

    Date parkStartTime;
    Date parkEndTime;
    Double billAmount;
    VehicleType type;
    String licenseNum;
    String status;

    public Bill(Date parkStartTime, Date parkEndTime, Double billAmount, VehicleType type, String licenseNum) {
        this.parkStartTime = parkStartTime;
        this.parkEndTime = parkEndTime;
        this.billAmount = billAmount;
        this.type = type;
        this.licenseNum = licenseNum;
    }

    public Bill() {

    }
}
