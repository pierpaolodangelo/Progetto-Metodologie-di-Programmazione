package progetto.mp.pierpaolo.dangelo.visitor;

import progetto.mp.pierpaolo.dangelo.composite.BaseProduct;
import progetto.mp.pierpaolo.dangelo.composite.KitProduct;
import progetto.mp.pierpaolo.dangelo.composite.ProductType;

public class HiLoPriceVisitor implements ProductVisitor {

  private double maxPrice = -Double.MAX_VALUE;
  private double minPrice = Double.MAX_VALUE;
  private ProductType type;

  public HiLoPriceVisitor(ProductType type) {
    this.type = type;
  }

  @Override
  public void visit(BaseProduct product) {
    if (product.getType() == type) {
      maxPrice = Math.max(product.getPrice(), maxPrice);
      minPrice = Math.min(product.getPrice(), minPrice);
    }
  }

  @Override
  public void visit(KitProduct productskit) {
    productskit.getProducts().forEachRemaining(d -> d.accept(this));
  }

  public double getMaxPrice() {
    return maxPrice;
  }

  public double getMinPrice() {
    return minPrice;
  }
}
