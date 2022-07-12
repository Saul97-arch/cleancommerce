package br.com.saulo.cleancommerce.core.usecases.products;

import br.com.saulo.cleancommerce.data.entities.dto.ProductResponse;
import br.com.saulo.cleancommerce.data.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
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

    public CompletableFuture<List<ProductResponse>> listAllProducts() {
        // TODO verify if the assync happens here
        List<ProductResponse> productResponseList = repository
                .listAllProducts().stream().map(ProductResponse::from)
                .collect(Collectors.toList());
        CompletableFuture<List<ProductResponse>> objectCompletableFuture = new CompletableFuture<>();

        if (objectCompletableFuture.complete(productResponseList)) {
            return objectCompletableFuture;
        }

        throw new HttpServerErrorException(HttpStatus.NOT_FOUND);
    }

    public void tototo() {
        List<ProductResponse> collect = repository.listAllProducts().stream().map(ProductResponse::from).collect(Collectors.toList());
    }

    public void createProduct() {
        repository.createProduct();
    }
}
