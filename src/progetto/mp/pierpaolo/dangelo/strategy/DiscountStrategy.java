package progetto.mp.pierpaolo.dangelo.strategy;

@FunctionalInterface
public interface DiscountStrategy {
	double applyDiscount(double price);	
}
