package br.com.saulo.cleancommerce.presenter.services;

import br.com.saulo.cleancommerce.core.domain.Product;
import br.com.saulo.cleancommerce.data.entities.dto.CreateProductRequest;
import br.com.saulo.cleancommerce.data.entities.dto.ProductResponse;
import br.com.saulo.cleancommerce.data.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductsService {

    @Autowired
    ProductRepository repository;

    @Async
    public List<ProductResponse> listAllProducts() {
        List<Product> products = repository.listAllProducts();
        return products.stream().map(ProductResponse::from).collect(Collectors.toList());
    }

    public ProductResponse createProduct(CreateProductRequest createProductRequest) {
        return repository.persist(createProductRequest);
    }
}
