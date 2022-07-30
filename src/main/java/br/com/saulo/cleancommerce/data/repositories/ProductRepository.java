package br.com.saulo.cleancommerce.data.repositories;

import br.com.saulo.cleancommerce.core.domain.Product;
import br.com.saulo.cleancommerce.core.usecases.products.IProductRepository;
import br.com.saulo.cleancommerce.data.entities.ProductData;
import br.com.saulo.cleancommerce.data.dto.CreateProductRequest;
import br.com.saulo.cleancommerce.data.dto.ProductResponse;
import br.com.saulo.cleancommerce.core.domain.exceptions.ProductNotFoundException;
import br.com.saulo.cleancommerce.data.repositories.jpaRepositories.JPAProductRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
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
    public Product findByName(String name) throws ProductNotFoundException {
        ProductData productData = jpaProductRepository.findByName(name);
        if (productData == null) {
            throw new ProductNotFoundException("Product no found!");
        }
        return productData.fromProductData();
    }

    @Override
    public Product findById(Long productId) throws ProductNotFoundException {
        Optional<ProductData> productData = jpaProductRepository.findById(productId);
        if (productData.isPresent()) {
            return Product.from(productData.get());
        }

        throw new ProductNotFoundException("Product not foun!");
    }
}
