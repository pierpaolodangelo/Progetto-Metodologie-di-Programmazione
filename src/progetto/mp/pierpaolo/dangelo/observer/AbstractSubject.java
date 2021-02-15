package progetto.mp.pierpaolo.dangelo.observer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractSubject {

  private List<IObserver> observers = new ArrayList<>();

  public void addIObserver(IObserver observer) {
    observers.add(observer);
  }

  public void addAllIObserver(Iterator<IObserver> observers) {
    observers.forEachRemaining(this::addIObserver);
  }

  public void removeIObserver(IObserver observer) {
    observers.remove(observer);
  }

  public void removeAllIObserver(Iterator<IObserver> observers) {
    observers.forEachRemaining(this::removeIObserver);
  }

  public void notifyObservers() {
    observers.forEach(IObserver::update);
  }
}
