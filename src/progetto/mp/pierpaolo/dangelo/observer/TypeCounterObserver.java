package progetto.mp.pierpaolo.dangelo.observer;

import progetto.mp.pierpaolo.dangelo.composite.KitProduct;
import progetto.mp.pierpaolo.dangelo.composite.ProductType;
import progetto.mp.pierpaolo.dangelo.visitor.TypeCounterVisitor;

public class TypeCounterObserver implements IObserver {

  private TypeCounterVisitor visitor;
  private KitProduct kitProduct;

  public TypeCounterObserver(ProductType productType, KitProduct kitProduct) {
    visitor = new TypeCounterVisitor(productType);
    this.kitProduct = kitProduct;
    update();
    kitProduct.addIObserver(this);
  }

  @Override
  public void update() {
    kitProduct.accept(visitor);
  }

  public int getNumProduct() {
    return visitor.getCounter();
  }
}
