package app;
import context.SpotSelectionContext;
import data.OccupancyProvider;
import data.SimulatedOccupancyProvider;
import model.ParkingSession;
import model.ParkingSpot;
import model.Vehicle;
import pricing.ParkingPriceCalculator;
import selector.StrategySelector;
import spot.*;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle("KZ-123", 3);
        ParkingSession session = new ParkingSession(vehicle, 180);
        OccupancyProvider sensor = new SimulatedOccupancyProvider(0.85);
        List<ParkingSpot> spots = List.of(
                new ParkingSpot("A1", 1, 10, 3, false),
                new ParkingSpot("A2", 1, 15, 3, true),
                new ParkingSpot("B5", 2, 40, 3, false),
                new ParkingSpot("B6", 2, 20, 2, true),
                new ParkingSpot("C2", 3, 25, 3, false)
        );
        ParkingPriceCalculator calculator = new ParkingPriceCalculator(sensor);
        StrategySelector selector = new StrategySelector(sensor);
        System.out.println("=== SMART GARAGE BEFORE REFACTORING ===");
        System.out.println("\n--- PRICING ---");
        System.out.println("Hourly: " + calculator.calculate(session, "hourly"));
        System.out.println("Vehicle: " + calculator.calculate(session, "vehicle"));
        System.out.println("Dynamic: " + calculator.calculate(session, "dynamic"));
        System.out.println("\n--- AUTOMATIC PRICING ---");
        String selectedPricing = selector.selectPricing(session);
        System.out.println("Selected: " + selectedPricing);
        System.out.println("Price: " + calculator.calculate(session, selectedPricing));
        System.out.println("\n--- SPOT STRATEGIES ---");
        SpotSelectionContext spotContext = new SpotSelectionContext(new NearestSpotStrategy());
        System.out.println("Nearest: " + spotContext.select(spots, vehicle).getId());
        spotContext.setStrategy(new FloorBalancedStrategy());
        System.out.println("Floor balanced: " + spotContext.select(spots, vehicle).getId());
        spotContext.setStrategy(new VehicleFitStrategy());
        System.out.println("Vehicle fit: " + spotContext.select(spots, vehicle).getId());
        System.out.println("\n--- AUTOMATIC SPOT ---");
        spotContext.setStrategy(selector.selectSpot(vehicle, sensor.getOccupancyRate()));
        System.out.println("Selected spot: " + spotContext.select(spots, vehicle).getId());
        System.out.println("\n=== DEMO COMPLETED ===");
    }
}
