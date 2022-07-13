package br.com.saulo.cleancommerce.core.usecases.products;

import br.com.saulo.cleancommerce.core.domain.Product;
import br.com.saulo.cleancommerce.data.entities.dto.CreateProductRequest;

import java.util.List;

public interface IProductRepository {
    List<Product> listAllProducts();
    void createProduct(CreateProductRequest createProductRequest);
}
