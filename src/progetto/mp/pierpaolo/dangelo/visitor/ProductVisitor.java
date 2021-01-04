package progetto.mp.pierpaolo.dangelo.visitor;

import progetto.mp.pierpaolo.dangelo.composite.BaseProduct;
import progetto.mp.pierpaolo.dangelo.composite.KitProduct;

public interface ProductVisitor {
  void visit(BaseProduct product);

  void visit(KitProduct productskit);
}
