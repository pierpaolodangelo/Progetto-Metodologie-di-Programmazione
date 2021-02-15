package progetto.mp.pierpaolo.dangelo.strategy;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

public class DiscountStrategyTest {
  private static final double PRICE = 100;

  @Test
  public void noDiscountTest() {
    assertThat(DiscountStrategyFactory.NO_DISCOUNT.applyDiscount(PRICE)).isEqualTo(100);
  }

  @Test
  public void absoluteDiscountTest() {
    assertThat(DiscountStrategyFactory.newAbsoluteDiscount(20).applyDiscount(PRICE)).isEqualTo(80);
  }

  @Test
  public void absoluteDiscountTestAbsGrtPrice() {
    assertThat(DiscountStrategyFactory.newAbsoluteDiscount(120).applyDiscount(PRICE)).isEqualTo(0);
  }

  @Test
  public void percentageDiscountTest() {
    assertThat(DiscountStrategyFactory.newPercentageDiscount(30).applyDiscount(PRICE))
        .isEqualTo(70);
  }

  @Test
  public void absoluteSanityCheckTest() {
    assertThatThrownBy(() -> DiscountStrategyFactory.newAbsoluteDiscount(-20).applyDiscount(PRICE))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void percentageSanityCheckTestPercentageGrtOneHudred() {
    assertThatThrownBy(
            () -> DiscountStrategyFactory.newPercentageDiscount(120).applyDiscount(PRICE))
        .isInstanceOf(IllegalArgumentException.class);
  }

  @Test
  public void percentageSanityCheckTestPercentageLessZero() {
    assertThatThrownBy(
            () -> DiscountStrategyFactory.newPercentageDiscount(-10).applyDiscount(PRICE))
        .isInstanceOf(IllegalArgumentException.class);
  }
}
