package app;
import model.*;
import data.*;
import pricing.*;
import spot.*;
import context.*;
import selector.*;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle("KZ-123", 3);
        ParkingSession session = new ParkingSession(vehicle, 180);
        OccupancyProvider sensor = new SimulatedOccupancyProvider(0.85);
        List<ParkingSpot> spots = List.of(
                new ParkingSpot("A1", 1, 10, 3, false),
                new ParkingSpot("A2", 1, 30, 1, false),
                new ParkingSpot("B5", 2, 40, 3, false),
                new ParkingSpot("B6", 2, 20, 2, true),
                new ParkingSpot("C2", 3, 25, 3, false));
        System.out.println("=== SMART GARAGE ===");
        System.out.println("Vehicle: " + vehicle.getPlate());
        System.out.println("Duration: " + session.getMinutes());
        System.out.println("Occupancy: " + sensor.getOccupancyRate() * 100 + "%");
        System.out.println("\n--- PRICING STRATEGIES ---");
        PricingStrategy[] pricingStrategies = {
                new HourlyPricing(),
                new VehicleTypePricing(),
                new DynamicPricing(sensor)
        };
        PricingContext pricingContext = new PricingContext(pricingStrategies[0]);
        for (PricingStrategy strategy : pricingStrategies) {
            pricingContext.setStrategy(strategy);
            System.out.println(strategy.getClass().getSimpleName() + ": " + pricingContext.calculate(session) + " KZT");
        }
        System.out.println("\n--- SPOT STRATEGIES ---");
        SpotSelectionStrategy[] spotStrategies = {
                new NearestSpotStrategy(),
                new FloorBalancedStrategy(),
                new VehicleFitStrategy()
        };
        SpotSelectionContext spotContext = new SpotSelectionContext(spotStrategies[0]);
        for (SpotSelectionStrategy strategy : spotStrategies) {
            spotContext.setStrategy(strategy);
            ParkingSpot selected = spotContext.select(spots, vehicle);
            System.out.println(strategy.getClass().getSimpleName() + ": " + selected.getId());
        }
        System.out.println("\n--- AUTOMATIC SELECTION ---");
        StrategySelector selector = new StrategySelector(sensor);
        PricingStrategy autoPricing = selector.selectPricing(session);
        SpotSelectionStrategy autoSpot = selector.selectSpot(vehicle, sensor.getOccupancyRate());
        pricingContext.setStrategy(autoPricing);
        spotContext.setStrategy(autoSpot);
        System.out.println("Selected pricing: " + autoPricing.getClass().getSimpleName());
        System.out.println("Automatic price: " + pricingContext.calculate(session) + " KZT");
        System.out.println("Selected spot: " + spotContext.select(spots, vehicle).getId());
        System.out.println("\n--- RUNTIME SWITCHING ---");
        pricingContext.setStrategy(new HourlyPricing());
        System.out.println("After switching pricing: " + pricingContext.calculate(session) + " KZT");
        spotContext.setStrategy(new NearestSpotStrategy());
        System.out.println("After switching spot: " + spotContext.select(spots, vehicle).getId());
        System.out.println("\n=== DEMO COMPLETED ===");
    }
}
