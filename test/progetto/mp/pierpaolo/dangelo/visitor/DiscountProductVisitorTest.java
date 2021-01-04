package progetto.mp.pierpaolo.dangelo.visitor;

import static org.junit.Assert.*;

import org.junit.Before;

import static org.assertj.core.api.Assertions.*;

import org.junit.Test;

import progetto.mp.pierpaolo.dangelo.composite.AbstractProduct;
import progetto.mp.pierpaolo.dangelo.composite.BaseProduct;
import progetto.mp.pierpaolo.dangelo.composite.KitProduct;
import progetto.mp.pierpaolo.dangelo.composite.ProductType;
import progetto.mp.pierpaolo.dangelo.strategy.DiscountStrategyFactory;

public class DiscountProductVisitorTest {
  private AbstractProduct kitproduct;
  private DiscountProductVisitor visitor;
  private double discount;

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
  }

  @Test
  public void discountStrategy() {
    visitor =
        new DiscountProductVisitor(
            3, ProductType.TYPE1, DiscountStrategyFactory.newPercentageDiscount(10.00));
    kitproduct.accept(visitor);
    discount = visitor.getDiscountStrategy().applyDiscount(50);
    assertThat(discount).isEqualTo(45.00);
  }
  
  @Test
  public void noDiscount() {
    visitor =
        new DiscountProductVisitor(
            3, ProductType.TYPE2, DiscountStrategyFactory.newPercentageDiscount(10.00));
    kitproduct.accept(visitor);
    discount = visitor.getDiscountStrategy().applyDiscount(50);
    assertThat(discount).isEqualTo(50.00);
  }
  
  
}
