package ru.netology.products_dao.services;

import org.springframework.stereotype.Service;
import ru.netology.products_dao.models.Order;
import ru.netology.products_dao.repositories.ProductsRepository;

import java.util.List;

@Service
public class ProductsService {
    private final ProductsRepository productsRepository;

    public ProductsService(ProductsRepository productsRepository) {
        this.productsRepository = productsRepository;
    }

    public List<Order> getOrdersByName(String name) {
        List<Order> ordersByName = productsRepository.getOrdersByName(name);
        if (ordersByName.isEmpty()) {
            throw new IllegalArgumentException("Заказов для пользователя под именем " + name + " не найдено");
        }
        return ordersByName;
    }
}
