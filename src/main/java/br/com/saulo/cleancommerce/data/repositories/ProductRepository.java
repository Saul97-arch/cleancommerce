package br.com.saulo.cleancommerce.data.repositories;

import br.com.saulo.cleancommerce.core.domain.Product;
import br.com.saulo.cleancommerce.core.usecases.products.IProductRepository;
import br.com.saulo.cleancommerce.data.entities.ProductData;
import org.springframework.context.annotation.Bean;
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
    public List<Product> listAllProducts() {
        return jpaProductRepository
                .findAll()
                .stream()
                .map(ProductData::toProduct)
                .collect(Collectors.toList());
    }

    @Override
    public void createProduct() {
        jpaProductRepository.save(new ProductData("MockNome", "MockDescricao", 2000.0));
    }
}
