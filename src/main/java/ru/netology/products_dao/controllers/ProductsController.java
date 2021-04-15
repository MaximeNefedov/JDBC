package ru.netology.products_dao.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.netology.products_dao.models.Order;
import ru.netology.products_dao.services.ProductsService;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductsController {
    private final ProductsService productsService;

    public ProductsController(ProductsService productsService) {
        this.productsService = productsService;
    }

    @GetMapping("/fetch-product")
    public ResponseEntity<String> getOrdersByName(@RequestParam("name") String name) {
        List<Order> ordersByName = productsService.getOrdersByName(name);
        final var sb = new StringBuilder("Заказы клиента " + "\"" + name + "\": ");
        for (Order order : ordersByName) {
            sb.append(order).append(" ");
        }
        return new ResponseEntity<>(sb.toString(), HttpStatus.OK);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
}
