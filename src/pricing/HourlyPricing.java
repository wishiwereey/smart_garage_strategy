package pricing;
import model.ParkingSession;
public class HourlyPricing implements PricingStrategy {
    @Override
    public double calculate(ParkingSession session) {
        double hours = Math.ceil(session.getMinutes() / 60.0);
        return hours * 500;
    }
}
