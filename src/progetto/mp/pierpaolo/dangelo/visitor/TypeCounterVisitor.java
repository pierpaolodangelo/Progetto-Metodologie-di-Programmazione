package progetto.mp.pierpaolo.dangelo.visitor;

import progetto.mp.pierpaolo.dangelo.composite.BaseProduct;
import progetto.mp.pierpaolo.dangelo.composite.KitProduct;
import progetto.mp.pierpaolo.dangelo.composite.ProductType;

public class TypeCounterVisitor implements ProductVisitor {

  private int counter;
  private ProductType productType;

  public TypeCounterVisitor(ProductType productType) {
    counter = 0;
    this.productType = productType;
  }

  @Override
  public void visit(BaseProduct product) {
    if (product.getType() == productType) {
      ++counter;
    }
  }

  @Override
  public void visit(KitProduct productskit) {
    productskit.getProducts().forEachRemaining(d -> d.accept(this));
  }

  public int getCounter() {
    return counter;
  }
}
