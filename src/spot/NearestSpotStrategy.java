package spot;
import model.ParkingSpot;
import model.Vehicle;
import java.util.List;
public class NearestSpotStrategy implements SpotSelectionStrategy {
    @Override
    public ParkingSpot select(List<ParkingSpot> spots, Vehicle vehicle) {
        ParkingSpot best = null;
        for (ParkingSpot spot : spots) {
            if (spot.fits(vehicle)) {
                if (best == null ||
                        spot.getDistance() < best.getDistance()) {
                    best = spot;
                }
            }
        }
        if (best == null) {
            throw new IllegalStateException("No available spot");
        }
        return best;
    }
}
