package com.muskan.parking.constant;

import lombok.Data;

public enum VehicleType {

    CAR(100),
    BIKE(50),
    BUS(200);

    int rate;

    VehicleType(int rate) {
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }
}
