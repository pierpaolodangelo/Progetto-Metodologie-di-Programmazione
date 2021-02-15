package progetto.mp.pierpaolo.dangelo.visitor;

import progetto.mp.pierpaolo.dangelo.composite.BaseProduct;
import progetto.mp.pierpaolo.dangelo.composite.KitProduct;
import progetto.mp.pierpaolo.dangelo.composite.ProductType;
import progetto.mp.pierpaolo.dangelo.strategy.DiscountStrategy;
import progetto.mp.pierpaolo.dangelo.strategy.DiscountStrategyFactory;

public class DiscountProductVisitor implements ProductVisitor {

  private int numProduct;
  private ProductType type;
  private DiscountStrategy discountStrategy;

  public DiscountProductVisitor(
      int numProduct, ProductType type, DiscountStrategy discountStrategy) {
    this.numProduct = numProduct;
    this.type = type;
    this.discountStrategy = discountStrategy;
  }

  public DiscountStrategy getDiscountStrategy() {
    return numProduct <= 0 ? discountStrategy : DiscountStrategyFactory.NO_DISCOUNT;
  }

  @Override
  public void visit(BaseProduct product) {
    if (product.getType() == type) {
      --numProduct;
    }
  }

  @Override
  public void visit(KitProduct productskit) {
    productskit.getProducts().forEachRemaining(p -> p.accept(this));
  }
}
