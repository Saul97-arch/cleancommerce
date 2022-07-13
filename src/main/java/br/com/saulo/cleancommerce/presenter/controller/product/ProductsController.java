package br.com.saulo.cleancommerce.presenter.controller.product;

import br.com.saulo.cleancommerce.core.usecases.products.ProductsUseCase;
import br.com.saulo.cleancommerce.data.entities.dto.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class ProductsController implements IProductsController {
    @Autowired
    private ProductsUseCase productsUseCase;

    public ProductsController() {
    }

    // Fazer post no discourse sobre
    @Override
    public CompletableFuture<ResponseEntity<List<ProductResponse>>> listAllProducts() {
        return CompletableFuture.supplyAsync(() -> ResponseEntity.ok(productsUseCase.listAllProducts()));
    }

    @Override
    public void createProduct() {
        productsUseCase.createProduct();
    }
}
