import model.*;
import spot.*;
import context.SpotSelectionContext;
import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class SpotStrategyTest {

    private Vehicle vehicle = new Vehicle("TEST", 1);

    private List<ParkingSpot> spots = List.of(
            new ParkingSpot("A1", 1, 5, 3, false),
            new ParkingSpot("B1", 2, 20, 1, false),
            new ParkingSpot("A2", 1, 10, 2, true)
    );

    @Test
    void testNearest() {
        assertEquals("A1",
                new NearestSpotStrategy()
                        .select(spots, vehicle).getId());
    }

    @Test
    void testFloorBalanced() {
        assertEquals("B1",
                new FloorBalancedStrategy()
                        .select(spots, vehicle).getId());
    }

    @Test
    void testVehicleFit() {
        assertEquals("B1",
                new VehicleFitStrategy()
                        .select(spots, vehicle).getId());
    }

    @Test
    void testRuntimeSwitch() {
        SpotSelectionContext context =
                new SpotSelectionContext(new NearestSpotStrategy());

        assertEquals("A1", context.select(spots, vehicle).getId());

        context.setStrategy(new VehicleFitStrategy());

        assertEquals("B1", context.select(spots, vehicle).getId());
    }

    @Test
    void testNoAvailableSpot() {
        List<ParkingSpot> full = List.of(
                new ParkingSpot("X1", 1, 5, 1, true)
        );

        assertThrows(IllegalStateException.class, () ->
                new NearestSpotStrategy().select(full, vehicle));
    }
}
