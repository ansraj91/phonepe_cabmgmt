package vehicle.state;

import vehicle.model.Vehicle;

public class VehicleStateIdle implements VehicleState {

    private static VehicleStateIdle instance = new VehicleStateIdle();
    private VehicleStateIdle(){}
    public static VehicleStateIdle instance() {
        return instance;
    }

    @Override
    public void updateState(Vehicle vehicle) {
        vehicle.setCurrentState(VehicleStateOnTrip.instance());
    }


}
