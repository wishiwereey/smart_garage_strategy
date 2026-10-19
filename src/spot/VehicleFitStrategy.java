package spot;
import model.ParkingSpot;
import model.Vehicle;
import java.util.List;
public class VehicleFitStrategy implements SpotSelectionStrategy {
    @Override
    public ParkingSpot select(List<ParkingSpot> spots, Vehicle vehicle) {
        ParkingSpot best = null;
        int smallestDifference = Integer.MAX_VALUE;
        for (ParkingSpot spot : spots) {
            if (!spot.fits(vehicle)) {
                continue;
            }
            int difference = spot.getCapacity() - vehicle.getSize();
            if (best == null || difference < smallestDifference || (difference == smallestDifference && spot.getDistance() < best.getDistance())) {
                best = spot;
                smallestDifference = difference;
            }
        }
        if (best == null) {
            throw new IllegalStateException("No available spot");
        }
        return best;
    }
}
