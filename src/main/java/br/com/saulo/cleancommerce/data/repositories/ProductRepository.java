package br.com.saulo.cleancommerce.data.repositories;

import br.com.saulo.cleancommerce.core.domain.Product;
import br.com.saulo.cleancommerce.core.usecases.products.IProductRepository;
import br.com.saulo.cleancommerce.data.entities.ProductData;
import br.com.saulo.cleancommerce.data.entities.dto.CreateProductRequest;
import br.com.saulo.cleancommerce.data.entities.dto.ProductResponse;
import br.com.saulo.cleancommerce.data.repositories.jpaRepositories.JPAProductRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductRepository implements IProductRepository {

    private final JPAProductRepository jpaProductRepository;

    public ProductRepository(JPAProductRepository jpaProductRepository) {
        this.jpaProductRepository = jpaProductRepository;
    }

    @Override
    @Async
    public List<Product> listAllProducts() {
        return jpaProductRepository
                .findAll()
                .stream()
                .map(ProductData::fromProductData)
                .collect(Collectors.toList());
    }

    @Override
    @Async
    public ProductResponse persist(CreateProductRequest createProductRequest) {
        ProductData save = jpaProductRepository.save(new ProductData(createProductRequest.getName(),
                createProductRequest.getDescription(),
                createProductRequest.getPrice()));

        return new ProductResponse(save.getId(), save.getName(), save.getDescription(), save.getPrice());
    }

    @Override
    public Product findByName(String name) {
        ProductData productData = jpaProductRepository.findByName(name);
        return ProductData.fromProductData(productData);
    }
}
