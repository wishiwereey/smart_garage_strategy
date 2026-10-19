package spot;
import model.ParkingSpot;
import model.Vehicle;
import java.util.List;
public interface SpotSelectionStrategy {
    ParkingSpot select(List<ParkingSpot> spots, Vehicle vehicle);
}
