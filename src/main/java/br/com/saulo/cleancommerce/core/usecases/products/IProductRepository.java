package br.com.saulo.cleancommerce.core.usecases.products;

import br.com.saulo.cleancommerce.core.domain.Product;
import br.com.saulo.cleancommerce.data.entities.dto.CreateProductRequest;
import br.com.saulo.cleancommerce.data.entities.dto.ProductResponse;

import java.util.List;

public interface IProductRepository {
    List<Product> listAllProducts();
    ProductResponse createProduct(CreateProductRequest createProductRequest);
}
