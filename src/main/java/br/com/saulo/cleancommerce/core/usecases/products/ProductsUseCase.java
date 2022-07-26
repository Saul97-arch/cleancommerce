package br.com.saulo.cleancommerce.core.usecases.products;

import br.com.saulo.cleancommerce.data.dto.CreateProductRequest;
import br.com.saulo.cleancommerce.data.dto.ProductResponse;
import br.com.saulo.cleancommerce.presenter.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ProductsUseCase {

    @Autowired
    private ProductsService service;

    public ProductsUseCase() {

    }

    @Async
    public List<ProductResponse> listAllProducts() {
        return service.listAllProducts();
    }

    public ProductResponse createProduct(CreateProductRequest createProductRequest) {
        return service.createProduct(createProductRequest);
    }
}
