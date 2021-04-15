package ru.netology.products_dao.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.netology.products_dao.models.Order;

import java.io.*;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class ProductsRepository {

    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final String sqlScript;

    public ProductsRepository(NamedParameterJdbcTemplate namedParameterJdbcTemplate, @Value("${sql.scripts.name}") String scriptFileName) {
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
        sqlScript = getSqlScript(scriptFileName);
    }

    public List<Order> getOrdersByName(String name) {
        return namedParameterJdbcTemplate
                .query(sqlScript, Map.of("name", name), (resultSet, i) -> new Order(resultSet.getString("product_name"),
                                resultSet.getInt("amount"),
                                resultSet.getString("date")));
    }

    private String getSqlScript(String scriptFileName) {
        String sqlScript;
        try (InputStream is = new ClassPathResource(scriptFileName).getInputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is))) {
            sqlScript = bufferedReader.lines().collect(Collectors.joining("\n"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return sqlScript;
    }
}
