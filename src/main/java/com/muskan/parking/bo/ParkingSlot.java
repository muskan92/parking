package com.muskan.parking.bo;

import com.muskan.parking.constant.VehicleType;
import com.muskan.parking.exception.IncorrectParkingSlot;
import com.muskan.parking.exception.ParkingSlotNotAvailable;
import com.muskan.parking.exception.VehicleNotAvailable;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

@Data
public class ParkingSlot {

    Logger logger = LoggerFactory.getLogger(ParkingSlot.class);

    private int slotNumber;
    private int level;
    private Vehicle vehicle;
    private Date startTime;
    private Date endTime;
    private VehicleType type;

    public ParkingSlot(int slotNumber, int level, VehicleType type) {
        this.slotNumber = slotNumber;
        this.level = level;
        this.type = type;
    }

    public boolean isAvailable(){

        return vehicle==null;
    }

    public void park(Vehicle v) throws IncorrectParkingSlot, ParkingSlotNotAvailable {
        if(!isAvailable()){
            throw new ParkingSlotNotAvailable("This Parking slot is occupied ");
        }

        if(v.getVType().equals(this.type)){
            this.vehicle = v;
            this.startTime = new Date();
            logger.info(v.getVType()+" "+v.getNumber()+" is parked");
        }
        else {
            throw new IncorrectParkingSlot(v.getVType()+" is not compatible for "+this.type);
        }
    }

    public void unpark() throws VehicleNotAvailable {

        if(this.startTime!=null && !(isAvailable())) {
            this.endTime = new Date();
            this.vehicle = null;
        }
        else{
            throw new VehicleNotAvailable("Nothing to unpark");

        }
    }

    double getBill() throws VehicleNotAvailable{

        if(this.startTime != null && this.endTime != null){
            return ((this.endTime.getTime()-this.startTime.getTime())/(60*1000))*this.type.getRate();

        }
        else{
            throw new VehicleNotAvailable("Start time or end time is Null");
        }
    }

}
