package pricing;
import data.OccupancyProvider;
import model.ParkingSession;
import java.util.Map;
public class ParkingPriceCalculator {
    private final Map<String, PricingStrategy> strategies;
    public ParkingPriceCalculator(OccupancyProvider occupancyProvider) {
        strategies = Map.of(
                "hourly", new HourlyPricing(),
                "vehicle", new VehicleTypePricing(),
                "dynamic", new DynamicPricing(occupancyProvider));
    }
    public double calculate(ParkingSession session, String type) {
        PricingStrategy strategy = strategies.get(type);
        if (strategy == null) {
            throw new IllegalArgumentException("Unknown pricing type: " + type);
        }
        return strategy.calculate(session);
    }
}