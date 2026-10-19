package data;
public class SimulatedOccupancyProvider implements OccupancyProvider {
    private double rate;
    public SimulatedOccupancyProvider(double rate) {
        if (rate < 0 || rate > 1) {
            throw new IllegalArgumentException("Invalid occupancy");
        }
        this.rate = rate;
    }
    @Override
    public double getOccupancyRate() {
        return rate;
    }
}
