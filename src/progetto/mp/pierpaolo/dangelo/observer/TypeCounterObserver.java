package progetto.mp.pierpaolo.dangelo.observer;

import java.util.ArrayList;
import java.util.List;

import progetto.mp.pierpaolo.dangelo.composite.AbstractProduct;
import progetto.mp.pierpaolo.dangelo.composite.ProductType;
import progetto.mp.pierpaolo.dangelo.visitor.TypeCounterVisitor;

public class TypeCounterObserver extends AbstractProductObserver {

  private ProductType productType;
  private int counter = -1;
  private List<Integer> storico;

  public TypeCounterObserver(ProductType productType, AbstractProduct product) {
    super(product);
    this.storico = new ArrayList<Integer>();
    this.productType = productType;
    update();
  }

  @Override
  public void update() {
    TypeCounterVisitor visitor = new TypeCounterVisitor(productType);
    getProduct().accept(visitor);
    if (visitor.getCounter() != counter) {
      counter = visitor.getCounter();
      storico.add(counter);
    }
  }

  public List<Integer> getStorico() {
    return storico;
  }
}
