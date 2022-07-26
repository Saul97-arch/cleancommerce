package br.com.saulo.cleancommerce.core.usecases.products;

import br.com.saulo.cleancommerce.core.domain.Product;
import br.com.saulo.cleancommerce.data.dto.CreateProductRequest;
import br.com.saulo.cleancommerce.data.dto.ProductResponse;
import br.com.saulo.cleancommerce.data.entities.exceptions.ProductNotFoundException;

import java.util.List;
import java.util.Optional;

public interface IProductRepository {
    List<Product> listAllProducts();
    ProductResponse persist(CreateProductRequest createProductRequest);

    Product findByName(String name) throws ProductNotFoundException;

    Product findById(Long productId) throws ProductNotFoundException;
}
