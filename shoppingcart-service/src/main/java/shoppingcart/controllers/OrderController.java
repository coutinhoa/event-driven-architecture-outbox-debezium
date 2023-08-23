package shoppingcart.controllers;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shoppingcart.dto.ShoppingCartDTO;
import shoppingcart.entities.ShoppingCart;
import shoppingcart.services.OrderService;
import shoppingcart.services.ShoppingCartService;

import javax.transaction.Transactional;
import java.util.List;

@Slf4j
@Transactional
@RestController
@RequestMapping("/create-order")
@CrossOrigin(origins = "*", allowedHeaders = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class OrderController {
    private final OrderService orderService;

    OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<ShoppingCart> createCart(@RequestBody ShoppingCart shoppingCart) {
        ShoppingCart createdCart = orderService.addOrder(shoppingCart);
        return new ResponseEntity<>(createdCart, HttpStatus.CREATED);
    }

}
