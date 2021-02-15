package progetto.mp.pierpaolo.dangelo.observer;

import progetto.mp.pierpaolo.dangelo.composite.AbstractProduct;

public abstract class AbstractProductObserver implements IObserver {

  private AbstractProduct product;

  public AbstractProductObserver(AbstractProduct product) {
    this.product = product;
    this.product.addIObserver(this);
  }

  public AbstractProduct getProduct() {
    return product;
  }
}
