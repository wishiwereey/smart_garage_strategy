package context;
import pricing.PricingStrategy;
import model.ParkingSession;
import java.util.Objects;
public class PricingContext {
    private PricingStrategy strategy;
    public PricingContext(PricingStrategy strategy) {
        setStrategy(strategy);
    }
    public void setStrategy(PricingStrategy strategy) {
        this.strategy = Objects.requireNonNull(strategy);
    }
    public double calculate(ParkingSession session) {
        return strategy.calculate(session);
    }
}
