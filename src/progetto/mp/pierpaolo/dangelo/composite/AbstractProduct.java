package progetto.mp.pierpaolo.dangelo.composite;
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

	public abstract double getPrice();

	public abstract void accept(ProductVisitor visitor);
}
