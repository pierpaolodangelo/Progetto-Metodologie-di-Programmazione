package progetto.mp.pierpaolo.dangelo.composite;

import java.util.Iterator;

import progetto.mp.pierpaolo.dangelo.observer.AbstractSubject;
import progetto.mp.pierpaolo.dangelo.visitor.ProductVisitor;

public abstract class AbstractProduct extends AbstractSubject {

  private int code;
  private String name;

  public AbstractProduct(int code, String name) {
    this.code = code;
    this.name = name;
  }

  public int getCode() {
    return code;
  }

  public String getName() {
    return name;
  }

  public abstract Iterator<AbstractProduct> getProducts();

  public abstract void addProduct(AbstractProduct product);

  public abstract void removeProduct(AbstractProduct product);

  public void addAllProducts(Iterator<AbstractProduct> products) {
    products.forEachRemaining(this::addProduct);
  }

  public void removeAllProducts(Iterator<AbstractProduct> products) {
    products.forEachRemaining(this::removeProduct);
  }

  public abstract double getPrice();

  public abstract void accept(ProductVisitor visitor);
}
