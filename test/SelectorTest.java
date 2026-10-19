import model.*;
import selector.StrategySelector;
import pricing.*;
import spot.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SelectorTest {

    @Test
    void highOccupancyUsesDynamicPricing() {
        StrategySelector selector =
                new StrategySelector(() -> 0.9);

        ParkingSession session =
                new ParkingSession(new Vehicle("A", 1), 120);

        assertInstanceOf(
                DynamicPricing.class,
                selector.selectPricing(session)
        );
    }

    @Test
    void largeVehicleUsesVehiclePricing() {
        StrategySelector selector =
                new StrategySelector(() -> 0.4);

        ParkingSession session =
                new ParkingSession(new Vehicle("B", 3), 120);

        assertInstanceOf(
                VehicleTypePricing.class,
                selector.selectPricing(session)
        );
    }

    @Test
    void normalVehicleUsesHourlyPricing() {
        StrategySelector selector =
                new StrategySelector(() -> 0.4);

        ParkingSession session =
                new ParkingSession(new Vehicle("C", 1), 120);

        assertInstanceOf(
                HourlyPricing.class,
                selector.selectPricing(session)
        );
    }

    @Test
    void highOccupancyUsesVehicleFit() {
        StrategySelector selector =
                new StrategySelector(() -> 0.9);

        assertInstanceOf(
                VehicleFitStrategy.class,
                selector.selectSpot(new Vehicle("A", 1), 0.9)
        );
    }

    @Test
    void largeVehicleUsesNearestSpot() {
        StrategySelector selector =
                new StrategySelector(() -> 0.4);

        assertInstanceOf(
                NearestSpotStrategy.class,
                selector.selectSpot(new Vehicle("B", 3), 0.4)
        );
    }

    @Test
    void normalVehicleUsesFloorBalanced() {
        StrategySelector selector =
                new StrategySelector(() -> 0.4);

        assertInstanceOf(
                FloorBalancedStrategy.class,
                selector.selectSpot(new Vehicle("C", 1), 0.4)
        );
    }
}
