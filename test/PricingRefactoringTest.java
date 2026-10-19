import data.OccupancyProvider;
import model.ParkingSession;
import model.Vehicle;
import org.junit.jupiter.api.Test;
import pricing.ParkingPriceCalculator;
import static org.junit.jupiter.api.Assertions.assertEquals;
public class PricingRefactoringTest {
    private final OccupancyProvider fakeProvider = () -> 0.85;
    @Test
    void hourlyPricing() {
        ParkingPriceCalculator calculator = new ParkingPriceCalculator(fakeProvider);
        ParkingSession session = new ParkingSession(new Vehicle("KZ-123", 3), 180);
        assertEquals(1500.0, calculator.calculate(session, "hourly"));
    }
    @Test
    void vehiclePricing() {
        ParkingPriceCalculator calculator = new ParkingPriceCalculator(fakeProvider);
        ParkingSession session = new ParkingSession(new Vehicle("KZ-123", 3), 180);
        assertEquals(2100.0, calculator.calculate(session, "vehicle"));
    }
    @Test
    void dynamicPricing() {
        ParkingPriceCalculator calculator = new ParkingPriceCalculator(fakeProvider);
        ParkingSession session = new ParkingSession(new Vehicle("KZ-123", 3), 180);
        assertEquals(2400.0, calculator.calculate(session, "dynamic"));
    }
}