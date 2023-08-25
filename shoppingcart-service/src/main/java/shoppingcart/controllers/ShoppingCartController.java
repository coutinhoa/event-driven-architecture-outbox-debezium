package shoppingcart.controllers;

import org.springframework.http.HttpStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import shoppingcart.dto.ShoppingCartDTO;
import shoppingcart.entities.ShoppingCart;
import shoppingcart.services.ShoppingCartService;

import java.util.List;

@Slf4j
@Transactional
@RestController
@RequestMapping("/shopping-cart")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class ShoppingCartController {
    private final ShoppingCartService shoppingCartService;
    private final KafkaTemplate<String, ShoppingCartDTO> kafkaTemplate;

    ShoppingCartController(KafkaTemplate<String, ShoppingCartDTO> kafkaTemplate, ShoppingCartService shoppingCartService) {
        this.kafkaTemplate = kafkaTemplate;
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping
    public ResponseEntity<List<ShoppingCart>> getCart(@RequestParam(name = "name", required = false, defaultValue = "") String name) {
        List<ShoppingCart> shoppingCart = shoppingCartService.getAll(name);
        return ResponseEntity.ok(shoppingCart);
    }

    @PostMapping("/purchase")
    public ResponseEntity<ShoppingCart> createCart(@RequestBody ShoppingCartDTO shoppingCart) {
        ShoppingCart createdCart = shoppingCartService.addOrder(shoppingCart);
        return new ResponseEntity<>(createdCart, HttpStatus.CREATED);
    }
}
