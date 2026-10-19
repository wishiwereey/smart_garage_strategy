package selector;
import data.OccupancyProvider;
import model.ParkingSession;
import model.Vehicle;
import pricing.*;
import spot.*;
public class StrategySelector {
    private OccupancyProvider sensor;
    public StrategySelector(OccupancyProvider sensor) {
        this.sensor = sensor;
    }
    public PricingStrategy selectPricing(ParkingSession session) {
        if (sensor.getOccupancyRate() >= 0.8) {
            return new DynamicPricing(sensor);
        }
        if (session.getVehicle().getSize() == 3) {
            return new VehicleTypePricing();
        }
        return new HourlyPricing();
    }
    public SpotSelectionStrategy selectSpot(Vehicle vehicle, double occupancy) {
        if (occupancy >= 0.8) {
            return new VehicleFitStrategy();
        }
        if (vehicle.getSize() == 3) {
            return new NearestSpotStrategy();
        }
        return new FloorBalancedStrategy();
    }
}