package br.com.saulo.cleancommerce.core.usecases.products;

import br.com.saulo.cleancommerce.core.domain.Product;
import br.com.saulo.cleancommerce.data.entities.dto.ProductResponse;
import br.com.saulo.cleancommerce.data.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpServerErrorException;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;

@Component
public class ProductsUseCase {

    @Autowired
    private ProductRepository repository;

    public ProductsUseCase() {
    }

    @Async
    public List<ProductResponse> listAllProducts() {
        List<Product> products = repository.listAllProducts();
        return products.stream().map(ProductResponse::from).collect(Collectors.toList());
    }

    public void createProduct() {
        repository.createProduct();
    }
}
