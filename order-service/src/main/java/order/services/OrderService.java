package order.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import order.dto.OrderDTO;
import order.dto.ProductDTO;
import order.entities.Order;
import order.entities.Product;
import order.repositories.OrderRepository;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrderService {
    private final OrderRepository orderRepository;

    @Autowired
    private ObjectMapper objectMapper; // Jackson's ObjectMapper for deserialization

    @Autowired
    OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> getAll() {
        return orderRepository.findAll();
    }

    public void deleteOrder(Long userId){
        System.out.println(("service delete"));
        orderRepository.deleteOrderByUserId(userId);
    }

    public void deleteOrderById(Long orderId){
        System.out.println(("order deleted"));
        orderRepository.deleteById(orderId);
    }
    public Order createOrderWithProducts(OrderDTO orderRequest) {
        Order order = new Order();
        order.setTotalPrice(orderRequest.getTotalPrice());
        order.setUserId(orderRequest.getUserId());

        List<Product> products = new ArrayList<>();
        for (ProductDTO productDTO : orderRequest.getProducts()) {
            Product product = new Product();
            product.setQuantity(productDTO.getQuantity());
            product.setProductId(productDTO.getProductId());
            product.setOrder(order);
            products.add(product);
        }

        order.setProducts(products);

        return orderRepository.save(order);
    }
}
