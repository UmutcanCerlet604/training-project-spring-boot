package trainingproject.northwind.business.abstracts;

import trainingproject.northwind.entities.concretes.Product;

import java.util.List;

public interface ProductService {
    List<Product> getAll();
}
