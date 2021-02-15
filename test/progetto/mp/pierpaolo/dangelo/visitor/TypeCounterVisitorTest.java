package progetto.mp.pierpaolo.dangelo.visitor;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

import progetto.mp.pierpaolo.dangelo.composite.BaseProduct;
import progetto.mp.pierpaolo.dangelo.composite.KitProduct;
import progetto.mp.pierpaolo.dangelo.composite.ProductType;

public class TypeCounterVisitorTest {
  private KitProduct kitproduct;
  private TypeCounterVisitor visitor;

  @Before
  public void before() {

    kitproduct =
        new KitProduct(
            0,
            "Kit1",
            new BaseProduct(1, "Prodotto1", ProductType.TYPE1, 7.00),
            new BaseProduct(2, "Prodotto2", ProductType.TYPE1, 9.00),
            new BaseProduct(1, "Prodotto3", ProductType.TYPE2, 7.00),
            new BaseProduct(2, "Prodotto4", ProductType.TYPE1, 12.00),
            new KitProduct(
                3,
                "Kit2",
                new BaseProduct(4, "Prodotto5", ProductType.TYPE1, 5.00),
                new BaseProduct(5, "Prodotto6", ProductType.TYPE3, 10.00)));

    visitor = new TypeCounterVisitor(ProductType.TYPE1);
  }

  @Test
  public void counterTest() {
    kitproduct.accept(visitor);
    assertThat(visitor.getCounter()).isEqualTo(4);
  }
}
