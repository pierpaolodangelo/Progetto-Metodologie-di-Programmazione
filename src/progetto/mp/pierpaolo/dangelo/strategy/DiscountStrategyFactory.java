package progetto.mp.pierpaolo.dangelo.strategy;

import java.util.function.Predicate;

public class DiscountStrategyFactory {

  public static final DiscountStrategy NO_DISCOUNT = d -> d;

  private DiscountStrategyFactory() {}
  // TODO Testare
  public static DiscountStrategy newAbsoluteDiscount(double abs) {
    sanityCheck(abs, d -> d >= 0);
    return d -> d >= abs ? d - abs : 0;
  }

  public static DiscountStrategy newPercentageDiscount(double percentage) {
    sanityCheck(percentage, d -> d >= 0 && d <= 100);
    return d -> d - (d * percentage / 100);
  }
  // TODO testare eccezione ROBE xD
  private static void sanityCheck(double value, Predicate<Double> condition) {
    if (!condition.test(value)) {
      throw new IllegalArgumentException("value: " + value + " is not a valid argument");
    }
  }
}
