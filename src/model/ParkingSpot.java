package model;
public class ParkingSpot {
    private String id;
    private int floor;
    private int distance;
    private int capacity;
    private boolean occupied;
    public ParkingSpot(String id, int floor, int distance, int capacity, boolean occupied) {
        this.id = id;
        this.floor = floor;
        this.distance = distance;
        this.capacity = capacity;
        this.occupied = occupied;
    }
    public String getId() {
        return id;
    }
    public int getFloor() {
        return floor;
    }
    public int getDistance() {
        return distance;
    }
    public int getCapacity() {
        return capacity;
    }
    public boolean isOccupied() {
        return occupied;
    }
    public boolean fits(Vehicle vehicle) {
        return !occupied && capacity >= vehicle.getSize();
    }
}