package br.com.saulo.cleancommerce.presenter.controller.product;

import br.com.saulo.cleancommerce.core.usecases.products.ProductsUseCase;
import br.com.saulo.cleancommerce.data.dto.CreateProductRequest;
import br.com.saulo.cleancommerce.data.dto.ProductResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
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
    public ResponseEntity<ProductResponse> createProduct(@Valid @RequestBody CreateProductRequest createProductRequest) {
        return ResponseEntity.ok(productsUseCase.createProduct(createProductRequest));
    }
}
