package progetto.mp.pierpaolo.dangelo.observer;

import progetto.mp.pierpaolo.dangelo.composite.AbstractProduct;

public class PriceMonitorObserver implements IObserver {

	private AbstractProduct product;
	private double lowestPrice;
	private double highestPrice;
	private double avaragePrice;
	private int counter;

	public PriceMonitorObserver(AbstractProduct product) {
		this.product = product;
		this.lowestPrice = product.getPrice();
		this.highestPrice = product.getPrice();
		updateAverage(product.getPrice());
	}

	@Override
	public void update() {
		this.lowestPrice = Math.min(product.getPrice(), this.lowestPrice);
		this.highestPrice = Math.max(product.getPrice(), this.highestPrice);
		updateAverage(product.getPrice());
	}

	private void updateAverage(double currentPrice) {
		avaragePrice = (avaragePrice + currentPrice) / ++counter;
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
