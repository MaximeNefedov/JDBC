select orders.product_name, orders.amount, orders.date from customers
join orders on customers.id = orders.customer_id where upper(customers.name) = upper(:name);