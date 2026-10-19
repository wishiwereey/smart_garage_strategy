package spot;
import model.ParkingSpot;
import model.Vehicle;
import java.util.List;
public class FloorBalancedStrategy implements SpotSelectionStrategy {
    @Override
    public ParkingSpot select(List<ParkingSpot> spots, Vehicle vehicle) {
        ParkingSpot best = null;
        int minimumOccupied = Integer.MAX_VALUE;
        for (ParkingSpot candidate : spots) {
            if (!candidate.fits(vehicle)) {
                continue;
            }
            int occupiedCount = 0;
            for (ParkingSpot spot : spots) {
                if (spot.getFloor() == candidate.getFloor()
                        && spot.isOccupied()) {
                    occupiedCount++;
                }
            }
            if (best == null ||
                    occupiedCount < minimumOccupied ||
                    (occupiedCount == minimumOccupied &&
                            candidate.getDistance() < best.getDistance())) {
                best = candidate;
                minimumOccupied = occupiedCount;
            }
        }
        if (best == null) {
            throw new IllegalStateException("No available spot");
        }
        return best;
    }
}
