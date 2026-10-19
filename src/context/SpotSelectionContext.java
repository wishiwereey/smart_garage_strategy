package context;
import spot.SpotSelectionStrategy;
import model.ParkingSpot;
import model.Vehicle;
import java.util.List;
import java.util.Objects;
public class SpotSelectionContext {
    private SpotSelectionStrategy strategy;
    public SpotSelectionContext(SpotSelectionStrategy strategy) {
        setStrategy(strategy);
    }
    public void setStrategy(SpotSelectionStrategy strategy) {
        this.strategy = Objects.requireNonNull(strategy);
    }
    public ParkingSpot select(List<ParkingSpot> spots, Vehicle vehicle) {
        return strategy.select(spots, vehicle);
    }
}

