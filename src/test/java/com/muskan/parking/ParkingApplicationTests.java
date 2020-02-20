package com.muskan.parking;

import com.muskan.parking.bo.Car;
import com.muskan.parking.bo.ParkingSlot;
import com.muskan.parking.bo.Vehicle;
import com.muskan.parking.constant.VehicleType;
import com.muskan.parking.exception.IncorrectParkingSlot;
import com.muskan.parking.exception.ParkingSlotNotAvailable;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

class ParkingApplicationTests {

	void tryPark(List<ParkingSlot> parkingSlotListLevel1, Vehicle car) {

		for(ParkingSlot p : parkingSlotListLevel1){
			if(p.isAvailable()){
				try {
					p.park(car);
					break;
				}catch (IncorrectParkingSlot e){
					System.out.println(e.getMessage());
					break;
				}catch (ParkingSlotNotAvailable ps){
					System.out.println(ps.getMessage());
				}
			}

		}
	}

	@Test
	void parkingTest() {

		List<ParkingSlot> parkingSlotListLevel1 = new ArrayList<>();

		IntStream.range(1,10).forEach(i->{
			parkingSlotListLevel1.add(new ParkingSlot(i,1,VehicleType.CAR));
		});

		Vehicle car =  new Car(VehicleType.CAR,"BR-1234","Red");
		Vehicle car1 = new Car(VehicleType.CAR,"BR-123411","White");
		Vehicle car2 = new Car(VehicleType.CAR,"BR-123422","Black");
		Vehicle car3 = new Car(VehicleType.CAR,"BR-123433","Gray");

		tryPark(parkingSlotListLevel1,car);
		tryPark(parkingSlotListLevel1,car1);
		tryPark(parkingSlotListLevel1,car2);
		tryPark(parkingSlotListLevel1,car3);

		try {
			parkingSlotListLevel1.get(1).park(car1);
		} catch (IncorrectParkingSlot incorrectParkingSlot) {
			incorrectParkingSlot.printStackTrace();
		} catch (ParkingSlotNotAvailable parkingSlotNotAvailable) {
			parkingSlotNotAvailable.printStackTrace();
		}


	}

}
