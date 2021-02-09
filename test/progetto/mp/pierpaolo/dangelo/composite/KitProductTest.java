package progetto.mp.pierpaolo.dangelo.composite;

import static org.assertj.core.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import progetto.mp.pierpaolo.dangelo.strategy.DiscountStrategyFactory;

public class KitProductTest {

  @Test
  public void kitProductNoDiscount() {
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
    assertThat(kitproduct.getPrice()).isEqualTo(30.00);
  }

  @Test
  public void kitProductDiscount() {
    KitProduct kitproduct =
        new KitProduct(
            0,
            "Kit1",
            DiscountStrategyFactory.newPercentageDiscount(20.00),
            new BaseProduct(1, "Prodotto1", ProductType.TYPE1, 5.00),
            new BaseProduct(2, "Prodotto2", ProductType.TYPE1, 10.00));
    assertThat(kitproduct.getPrice()).isEqualTo(12.00d);
  }

  @Test
  public void addProductTest() {
    KitProduct kitproduct = new KitProduct(0, "Kit1");
    BaseProduct product = new BaseProduct(1, "Prodotto1", ProductType.TYPE1, 5.00);
    kitproduct.addProduct(product);
    assertThat(kitproduct.getProducts()).toIterable().containsExactly(product);
  }

  @Test
  public void addAllProductsTest() {
    KitProduct kitproduct = new KitProduct(0, "Kit1");
    List<AbstractProduct> products = new ArrayList<>();
    products.add(new BaseProduct(4, "Prodotto3", ProductType.TYPE1, 5.00));
    products.add(new BaseProduct(5, "Prodotto4", ProductType.TYPE1, 10.00));
    kitproduct.addAllProducts(products.iterator());
    assertThat(kitproduct.getProducts()).toIterable().containsExactlyElementsOf(products);
  }

  @Test
  public void removeProductTest() {
    KitProduct kitproduct = new KitProduct(0, "Kit1");
    BaseProduct product = new BaseProduct(1, "Prodotto1", ProductType.TYPE1, 5.00);
    kitproduct.addProduct(product);
    kitproduct.removeProduct(product);
    assertThat(kitproduct.getProducts()).toIterable().doesNotContain(product);
  }

  @Test
  public void removeAllProductTest() {
    KitProduct kitproduct = new KitProduct(0, "Kit1");
    List<AbstractProduct> products = new ArrayList<>();
    products.add(new BaseProduct(4, "Prodotto3", ProductType.TYPE1, 5.00));
    products.add(new BaseProduct(5, "Prodotto4", ProductType.TYPE1, 10.00));
    kitproduct.addAllProducts(products.iterator());
    kitproduct.removeAllProducts(products.iterator());
    assertThat(kitproduct.getProducts()).toIterable().doesNotContainAnyElementsOf(products);
  }
}
