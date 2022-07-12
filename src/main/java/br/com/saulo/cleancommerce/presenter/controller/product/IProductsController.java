package br.com.saulo.cleancommerce.presenter.controller.product;

import br.com.saulo.cleancommerce.data.entities.dto.ProductResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/product")
public interface IProductsController {
    @GetMapping("/list")
    CompletableFuture<List<ProductResponse>> listAllProducts();

    @PostMapping("/create")
    void createProduct();
}
