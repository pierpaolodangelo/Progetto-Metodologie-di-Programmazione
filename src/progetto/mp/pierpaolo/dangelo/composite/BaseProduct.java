package progetto.mp.pierpaolo.dangelo.composite;

import java.util.Iterator;

import progetto.mp.pierpaolo.dangelo.visitor.ProductVisitor;

public class BaseProduct extends AbstractProduct {

  private double price;
  private ProductType type;

  public BaseProduct(int code, String name, ProductType type, double price) {
    super(code, name);
    this.price = price;
    this.type = type;
  }

  @Override
  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
    notifyObservers();
  }

  public ProductType getType() {
    return type;
  }

  @Override
  public void accept(ProductVisitor visitor) {
    visitor.visit(this);
  }

  @Override
  public Iterator<AbstractProduct> getProducts() {
    throw new UnsupportedOperationException();
  }

  @Override
  public void addProduct(AbstractProduct product) {
    throw new UnsupportedOperationException();
  }

  @Override
  public void removeProduct(AbstractProduct product) {
    throw new UnsupportedOperationException();
  }
}
