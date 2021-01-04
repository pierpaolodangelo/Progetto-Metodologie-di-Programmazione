package progetto.mp.pierpaolo.dangelo.observer;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import progetto.mp.pierpaolo.dangelo.composite.ProductType;

public class TypeObserver {

  private Map<ProductType, List<IObserver>> observers = new HashMap<>();

  public void attachIObserver(ProductType productType, IObserver observer) {
    observers.get(productType).add(observer);
  }

  public void attachAllIObserver(Map<ProductType, IObserver> observers) {
    observers.forEach(this::attachIObserver);
  }

  public void detachIObserver(ProductType productType, IObserver observer) {
    observers.get(productType).remove(observer);
  }

  public void notifyObservers(ProductType productType) {
    observers.get(productType).forEach(IObserver::update);
  }
}
