package model;
public class ParkingSession {
    private Vehicle vehicle;
    private int minutes;
    public ParkingSession(Vehicle vehicle, int minutes) {
        if (minutes < 0) {
            throw new IllegalArgumentException("Duration cannot be negative");
        }
        this.vehicle = vehicle;
        this.minutes = minutes;
    }
    public Vehicle getVehicle() {
        return vehicle;
    }
    public int getMinutes() {
        return minutes;
    }
}

