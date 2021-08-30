package vehicle.model;

import vehicle.state.VehicleStateIdle;
import vehicle.type.VehicleType;
import vehicle.state.VehicleState;

public class Vehicle {
    public Vehicle() {
        if(currentState ==null){
            this.currentState = VehicleStateIdle.instance();
        }
    }

    public String getVehicleNumber() {
        return vehicleNumber;
    }

    public void setVehicleNumber(String vehicleNumber) {
        this.vehicleNumber = vehicleNumber;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public VehicleType getType() {
        return type;
    }

    public void setType(VehicleType type) {
        this.type = type;
    }

    public VehicleState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(VehicleState vehicleState) {
        this.currentState = vehicleState;
    }

    public Vehicle(String vehicleNumber, Long cityId, VehicleType type, VehicleState currentState) {
        this.vehicleNumber = vehicleNumber;

        this.cityId = cityId;
        this.type = type;
        this.currentState = currentState;
        if(currentState ==null){
            this.currentState = VehicleStateIdle.instance();
        }

    }
    public void update() {
        currentState.updateState(this);
    }

    private String vehicleNumber;
    private Long cityId;
    private VehicleType type;
    private VehicleState currentState;
}
