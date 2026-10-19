package pricing;
import model.ParkingSession;
public class VehicleTypePricing implements PricingStrategy {
    @Override
    public double calculate(ParkingSession session) {
        double hours = Math.ceil(session.getMinutes() / 60.0);
        int size = session.getVehicle().getSize();
        double multiplier;
        if (size == 1) {
            multiplier = 0.6;
        } else if (size == 2) {
            multiplier = 0.8;
        } else {
            multiplier = 1.0;
        }
        return 700 * hours * multiplier;
    }
}
