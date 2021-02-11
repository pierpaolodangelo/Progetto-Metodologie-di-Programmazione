package progetto.mp.pierpaolo.dangelo.observer;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

import progetto.mp.pierpaolo.dangelo.composite.BaseProduct;
import progetto.mp.pierpaolo.dangelo.composite.KitProduct;
import progetto.mp.pierpaolo.dangelo.composite.ProductType;

public class TypeCounterObserverTest {
  private TypeCounterObserver observer;
  private KitProduct kitproduct;

  @Before
  public void before() {
    kitproduct =
        new KitProduct(
            0,
            "Kit1",
            new BaseProduct(1, "Prodotto1", ProductType.TYPE1, 5.00),
            new BaseProduct(2, "Prodotto2", ProductType.TYPE2, 10.00),
            new KitProduct(
                3,
                "Kit2",
                new BaseProduct(4, "Prodotto3", ProductType.TYPE1, 5.00),
                new BaseProduct(5, "Prodotto4", ProductType.TYPE1, 10.00)));

    observer = new TypeCounterObserver(ProductType.TYPE1, kitproduct);
  }

  @Test
  public void observerInitializationTest() {
    assertThat(observer.getStorico()).hasSize(1).contains(3, atIndex(0));
  }

  @Test
  public void addProductTest() {
    int previousSize = observer.getStorico().size();
    kitproduct.addProduct(new BaseProduct(2, "Prodotto2", ProductType.TYPE1, 10.00));
    assertThat(observer.getStorico()).hasSize(previousSize + 1).contains(4, atIndex(1));
  }

  @Test
  public void addProductWrongTypeTest() {
    int currentSize = observer.getStorico().size();
    kitproduct.addProduct(new BaseProduct(2, "Prodotto2", ProductType.TYPE2, 10.00));
    assertThat(observer.getStorico()).hasSize(currentSize);
  }

}
