package shoppingcart.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shoppingcart.dto.ProductDTO;
import shoppingcart.entities.Product;
import shoppingcart.repositories.ProductRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductService {
    private final ProductRepository productRepository;
    @Autowired
    ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepository.findAll();
        return products.stream()
                .map(product -> new ProductDTO(product.getId(),
                        product.getProductId(),
                        product.getQuantity(),
                        product.getShopping_cart().getId()))
                .collect(Collectors.toList());
    }
}
