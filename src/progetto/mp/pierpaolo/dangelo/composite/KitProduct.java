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

	public Iterator<AbstractProduct> getProducts() {
		return products.iterator();
	}

	public void addProduct(AbstractProduct product) {
		products.add(product);
	}

	public void addAllProducts(Iterator<AbstractProduct> products) {
		products.forEachRemaining(this::addProduct);
	}

	@Override
	public double getPrice() {
		return discount.applyDiscount(products.stream().mapToDouble(AbstractProduct::getPrice).sum());
	}

	@Override
	public void accept(ProductVisitor visitor) {
		visitor.visit(this);
	}

	@Override
	public String toString() {
		return "KitProduct: " + getName() + "Products: " + products;
	}
}
