package pricing;
import model.ParkingSession;
public interface PricingStrategy {
    double calculate(ParkingSession session);
}
