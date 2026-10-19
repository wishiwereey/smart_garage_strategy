package pricing;
import data.OccupancyProvider;
import model.ParkingSession;
public class ParkingPriceCalculator {
    private final OccupancyProvider occupancyProvider;
    public ParkingPriceCalculator(OccupancyProvider occupancyProvider) {
        this.occupancyProvider = occupancyProvider;
    }
    public double calculate(ParkingSession session, String type) {
        double hours = Math.ceil(session.getMinutes() / 60.0);
        if (type.equals("hourly")) {
            return hours * 500;
        } else if (type.equals("vehicle")) {
            int size = session.getVehicle().getSize();
            double multiplier;
            if (size == 1) {
                multiplier = 0.6;
            } else if (size == 2) {
                multiplier = 0.8;
            } else {
                multiplier = 1.0;
            }
            return hours * 700 * multiplier;
        } else if (type.equals("dynamic")) {
            double occupancy = occupancyProvider.getOccupancyRate();
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
        throw new IllegalArgumentException("Unknown pricing type: " + type);
    }
}
