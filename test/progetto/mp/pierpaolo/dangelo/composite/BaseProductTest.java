package progetto.mp.pierpaolo.dangelo.composite;

import static org.assertj.core.api.Assertions.*;

import org.junit.Before;
import org.junit.Test;

public class BaseProductTest {
  private AbstractProduct product;

  @Before
  public void before() {
    product = new BaseProduct(0, "Prodotto1", ProductType.TYPE1, 5.00);
  }

  @Test
  public void getProductsTest() {
    assertThatThrownBy(() -> product.getProducts())
        .isInstanceOf(UnsupportedOperationException.class);
  }

  @Test
  public void addProductTest() {
    AbstractProduct newProduct = new BaseProduct(0, "Prodotto2", ProductType.TYPE3, 10.00);
    assertThatThrownBy(() -> product.addProduct(newProduct))
        .isInstanceOf(UnsupportedOperationException.class);
  }

  @Test
  public void removeProductsTest() {
    AbstractProduct newProduct = new BaseProduct(0, "Prodotto2", ProductType.TYPE3, 10.00);

    assertThatThrownBy(() -> product.removeProduct(newProduct))
        .isInstanceOf(UnsupportedOperationException.class);
  }
}
