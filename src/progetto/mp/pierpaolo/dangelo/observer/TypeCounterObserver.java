package progetto.mp.pierpaolo.dangelo.observer;

import java.util.ArrayList;
import java.util.List;

import progetto.mp.pierpaolo.dangelo.composite.AbstractProduct;
import progetto.mp.pierpaolo.dangelo.composite.ProductType;
import progetto.mp.pierpaolo.dangelo.visitor.TypeCounterVisitor;

public class TypeCounterObserver implements IObserver {

  private ProductType productType;
  private int counter = -1;
  private AbstractProduct product;
  private List<Integer> storico;

  public TypeCounterObserver(ProductType productType, AbstractProduct product) {
    this.storico = new ArrayList<Integer>();
    this.product = product;
    this.productType = productType;
    product.addIObserver(this);
    update();
  }

  @Override
  public void update() {
    TypeCounterVisitor visitor = new TypeCounterVisitor(productType);
    product.accept(visitor);
    if (visitor.getCounter() != counter) {
      counter = visitor.getCounter();
      storico.add(counter);
    }
  }

  public List<Integer> getStorico() {
    return storico;
  }
}
