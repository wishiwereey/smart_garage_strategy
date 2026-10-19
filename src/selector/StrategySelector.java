package selector;
import data.OccupancyProvider;
import model.ParkingSession;
import model.Vehicle;
import spot.*;
public class StrategySelector {
    private final OccupancyProvider occupancyProvider;
    public StrategySelector(OccupancyProvider occupancyProvider) {
        this.occupancyProvider = occupancyProvider;
    }
    public String selectPricing(ParkingSession session) {
        double occupancy = occupancyProvider.getOccupancyRate();
        if (occupancy >= 0.8) {
            return "dynamic";
        }
        if (session.getVehicle().getSize() == 3) {
            return "vehicle";
        }
        return "hourly";
    }
    public SpotSelectionStrategy selectSpot(
            Vehicle vehicle, double occupancy) {
        if (occupancy >= 0.8) {
            return new VehicleFitStrategy();
        }
        if (vehicle.getSize() == 3) {
            return new NearestSpotStrategy();
        }
        return new FloorBalancedStrategy();
    }
}
