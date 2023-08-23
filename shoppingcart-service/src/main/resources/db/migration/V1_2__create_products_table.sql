CREATE TABLE products(
    id bigserial primary key,
    quantity int,
    shopping_cart_id bigserial,
    product_id bigserial
);

INSERT INTO products (product_id, quantity, shopping_cart_id) values (2, 2, 1);
INSERT INTO products (product_id, quantity, shopping_cart_id) values (4, 4, 1);

