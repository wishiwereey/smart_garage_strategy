package pricing;
import data.OccupancyProvider;
import model.ParkingSession;
public class DynamicPricing implements PricingStrategy {
    private OccupancyProvider sensor;
    public DynamicPricing(OccupancyProvider sensor) {
        this.sensor = sensor;
    }
    @Override
    public double calculate(ParkingSession session) {
        double hours = Math.ceil(session.getMinutes() / 60.0);
        double occupancy = sensor.getOccupancyRate();
        double multiplier;
        if (occupancy >= 0.8) {
            multiplier = 1.6;
        } else if (occupancy >= 0.5) {
            multiplier = 1.2;
        } else {
            multiplier = 1.0;
        }
        return hours * 500 * multiplier;
    }
}
