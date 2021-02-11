package progetto.mp.pierpaolo.dangelo.observer;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

import progetto.mp.pierpaolo.dangelo.composite.BaseProduct;
import progetto.mp.pierpaolo.dangelo.composite.ProductType;

public class PriceMonitorObserverTest {

  private BaseProduct product;
  private PriceMonitorObserver observer;

  @Before
  public void before() {
    product = new BaseProduct(0, "Prod1", ProductType.TYPE2, 10.00);
    observer = new PriceMonitorObserver(product);
  }

  @Test
  public void noUpdateTest() {
    assertThat(observer)
        .hasFieldOrPropertyWithValue("lowestPrice", 10.00)
        .hasFieldOrPropertyWithValue("highestPrice", 10.00)
        .hasFieldOrPropertyWithValue("avaragePrice", 10.00)
        .hasFieldOrPropertyWithValue("counter", 1);
  }

  @Test
  public void noChangeTest() {
    double previousAvaragePrice = observer.getAvaragePrice();
    product.setPrice(10.00);
    assertThat(previousAvaragePrice).isEqualTo(observer.getAvaragePrice());
  }

  @Test
  public void updateTest() {
    product.setPrice(15.00);
    product.setPrice(7.00);
    product.setPrice(20.00);
    assertThat(observer)
        .hasFieldOrPropertyWithValue("lowestPrice", 7.00)
        .hasFieldOrPropertyWithValue("highestPrice", 20.00)
        .hasFieldOrPropertyWithValue("avaragePrice", 13.00)
        .hasFieldOrPropertyWithValue("counter", 4);
  }
}
