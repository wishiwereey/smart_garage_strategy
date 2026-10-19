package model;
public class Vehicle {
    private String plate;
    private int size;
    public Vehicle(String plate, int size) {
        if (size < 1 || size > 3) {
            throw new IllegalArgumentException("Invalid vehicle size");
        }
        this.plate = plate;
        this.size = size;
    }
    public String getPlate() {
        return plate;
    }
    public int getSize() {
        return size;
    }
}

