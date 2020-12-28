package progetto.mp.nome.cognome;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProductsKit implements IProduct {

	private int code;
	private String name;
	private List<IProduct> products;

	public ProductsKit(int code, String name, IProduct... products) {
		this.code = code;
		this.name = name;
		this.products = new ArrayList<>(Arrays.asList(products));
	}

	@Override
	public int getCode() {
		return code;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public double getPrice() {
		return products.stream().mapToDouble(p -> p.getPrice()).sum();
	}

}
