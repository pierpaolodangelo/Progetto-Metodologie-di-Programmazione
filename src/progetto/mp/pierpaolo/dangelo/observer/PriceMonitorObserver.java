package progetto.mp.pierpaolo.dangelo.observer;

import progetto.mp.pierpaolo.dangelo.composite.AbstractProduct;

public class PriceMonitorObserver extends AbstractProductObserver {

  private static final double PRECISIONE_DI_MACCHINA = Math.pow(2, -53);

  private double currentPrice;
  private double lowestPrice;
  private double highestPrice;
  private double avaragePrice;
  private int counter;
  private double tolerance;

  public PriceMonitorObserver(AbstractProduct product) {
    this(product, PRECISIONE_DI_MACCHINA);
  }

  public PriceMonitorObserver(AbstractProduct product, double tolerance) {
    super(product);
    this.currentPrice = getProduct().getPrice();
    this.lowestPrice = getProduct().getPrice();
    this.highestPrice = getProduct().getPrice();
    this.tolerance = tolerance;
    updateAverage(getProduct().getPrice());
  }

  @Override
  public void update() {
    if (Math.abs(currentPrice - getProduct().getPrice()) > tolerance) {
      this.lowestPrice = Math.min(getProduct().getPrice(), this.lowestPrice);
      this.highestPrice = Math.max(getProduct().getPrice(), this.highestPrice);
      updateAverage(getProduct().getPrice());
    }
  }

  private void updateAverage(double currentPrice) {
    avaragePrice = (counter * avaragePrice + currentPrice) / ++counter;
  }

  public double getLowestPrice() {
    return lowestPrice;
  }

  public double getHighestPrice() {
    return highestPrice;
  }

  public double getAvaragePrice() {
    return avaragePrice;
  }
}
