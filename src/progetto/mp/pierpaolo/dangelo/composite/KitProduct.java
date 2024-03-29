package progetto.mp.pierpaolo.dangelo.composite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import progetto.mp.pierpaolo.dangelo.strategy.DiscountStrategy;
import progetto.mp.pierpaolo.dangelo.strategy.DiscountStrategyFactory;
import progetto.mp.pierpaolo.dangelo.visitor.ProductVisitor;

public class KitProduct extends AbstractProduct {

  private List<AbstractProduct> products;
  private DiscountStrategy discount;

  public KitProduct(int code, String name, AbstractProduct... products) {
    this(code, name, DiscountStrategyFactory.NO_DISCOUNT, products);
  }

  public KitProduct(int code, String name, DiscountStrategy discount, AbstractProduct... products) {
    super(code, name);
    this.discount = discount;
    this.products = new ArrayList<>(Arrays.asList(products));
  }

  @Override
  public Iterator<AbstractProduct> getProducts() {
    return products.iterator();
  }

  @Override
  public void addProduct(AbstractProduct product) {
    products.add(product);
    notifyObservers();
  }

  @Override
  public void removeProduct(AbstractProduct product) {
    if (products.remove(product)) {
      notifyObservers();
    }
  }

  @Override
  public double getPrice() {
    return discount.applyDiscount(products.stream().mapToDouble(AbstractProduct::getPrice).sum());
  }

  @Override
  public void accept(ProductVisitor visitor) {
    visitor.visit(this);
  }
}
