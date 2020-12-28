package progetto.mp.pierpaolo.dangelo;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class ProductKitTest {
	IProduct product;

	@Before
	public void before() {
		product = new ProductsKit(0, "Prodotto0", new Product(1, "Prodotto1", 5.00), new Product(2, "Prodotto2", 10.00),
				new ProductsKit(0, "Kit2", new Product(1, "Prodotto1", 5.00), new Product(2, "Prodotto2", 10.00)));
	}

	@Test
	public void getPriceTest() {
		assertEquals(30.00, product.getPrice(), 0.10);
	}

}
