package progetto.mp.nome.cognome;

public class Product implements IProduct {

	private int code;
	private String name;
	private double price;

	public Product(int code, String name, double price) {
		this.code = code;
		this.name = name;
		this.price = price;
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
		return price;
	}

}
