package vehicle.state;

import vehicle.model.Vehicle;

import javax.xml.validation.ValidatorHandler;

public class VehicleStateOnTrip implements VehicleState {
    private static VehicleStateOnTrip instance = new VehicleStateOnTrip();
    private VehicleStateOnTrip(){}
    public static VehicleStateOnTrip instance() {
        return instance;
    }

    @Override
    public void updateState(Vehicle vehicle) {
        vehicle.setCurrentState(VehicleStateIdle.instance());
    }

}
