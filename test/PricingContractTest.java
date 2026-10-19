import model.*;
import pricing.*;
import context.PricingContext;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.Arguments;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.*;
public class PricingContractTest {
    private ParkingSession session =
            new ParkingSession(new Vehicle("TEST", 3), 180);
    @Test
    void testHourlyPricing() {
        assertEquals(1500.0,
                new HourlyPricing().calculate(session));
    }

    @Test
    void testVehicleTypePricing() {
        assertEquals(2100.0,
                new VehicleTypePricing().calculate(session));
    }

    @Test
    void testDynamicPricing() {
        assertEquals(2400.0,
                new DynamicPricing(() -> 0.85).calculate(session));
    }

    static Stream<Arguments> strategies() {
        return Stream.of(
                Arguments.of(new HourlyPricing()),
                Arguments.of(new VehicleTypePricing()),
                Arguments.of(new DynamicPricing(() -> 0.85))
        );
    }

    @ParameterizedTest
    @MethodSource("strategies")
    void priceNeverNegative(PricingStrategy strategy) {
        assertTrue(strategy.calculate(session) >= 0);
    }

    @Test
    void testRuntimeSwitch() {
        PricingContext context =
                new PricingContext(new HourlyPricing());

        assertEquals(1500.0, context.calculate(session));

        context.setStrategy(new VehicleTypePricing());

        assertEquals(2100.0, context.calculate(session));
    }
}

