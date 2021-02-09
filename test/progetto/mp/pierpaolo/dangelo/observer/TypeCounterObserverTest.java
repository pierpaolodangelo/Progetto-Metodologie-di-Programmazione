package progetto.mp.pierpaolo.dangelo.observer;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

import progetto.mp.pierpaolo.dangelo.composite.BaseProduct;
import progetto.mp.pierpaolo.dangelo.composite.KitProduct;
import progetto.mp.pierpaolo.dangelo.composite.ProductType;

public class TypeCounterObserverTest {

  @Test
  public void test() {
    KitProduct kitproduct =
        new KitProduct(
            0,
            "Kit1",
            new BaseProduct(1, "Prodotto1", ProductType.TYPE1, 5.00),
            new BaseProduct(2, "Prodotto2", ProductType.TYPE1, 10.00),
            new KitProduct(
                3,
                "Kit2",
                new BaseProduct(4, "Prodotto3", ProductType.TYPE1, 5.00),
                new BaseProduct(5, "Prodotto4", ProductType.TYPE1, 10.00)));
    
    TypeCounterObserver observer = new TypeCounterObserver(ProductType.TYPE1, kitproduct);
    
    assertThat(observer.getNumProduct()).isEqualTo(4);
  }
}
