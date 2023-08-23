CREATE TABLE products(
    id bigserial primary key,
    quantity int,
    order_id bigserial,
    product_id bigserial
);



INSERT INTO products (product_id, quantity, order_id) values (5, 3, 1);
INSERT INTO products (product_id, quantity, order_id) values (7, 2, 1);
INSERT INTO products (product_id, quantity, order_id) values (4, 4, 1);
INSERT INTO products (product_id, quantity, order_id) values (1, 1, 1);
INSERT INTO products (product_id, quantity, order_id) values (4, 2, 1);
INSERT INTO products (product_id, quantity, order_id) values (5, 3, 2);
INSERT INTO products (product_id, quantity, order_id) values (3, 3, 2);
INSERT INTO products (product_id, quantity, order_id) values (5, 3, 2);
INSERT INTO products (product_id, quantity, order_id) values (7, 2, 2);
INSERT INTO products (product_id, quantity, order_id) values (4, 4, 3);
INSERT INTO products (product_id, quantity, order_id) values (1, 1, 3);
INSERT INTO products (product_id, quantity, order_id) values (2, 2, 3);
INSERT INTO products (product_id, quantity, order_id) values (6, 3, 3);
INSERT INTO products (product_id, quantity, order_id) values (3, 3, 3);
INSERT INTO products (product_id, quantity, order_id) values (5, 3, 3);
INSERT INTO products (product_id, quantity, order_id) values (7, 2, 3);
INSERT INTO products (product_id, quantity, order_id) values (4, 4, 4);
INSERT INTO products (product_id, quantity, order_id) values (1, 1, 4);
INSERT INTO products (product_id, quantity, order_id) values (7, 2, 4);
INSERT INTO products (product_id, quantity, order_id) values (5, 3, 4);
INSERT INTO products (product_id, quantity, order_id) values (3, 3, 4);
INSERT INTO products (product_id, quantity, order_id) values (2, 3, 4);
INSERT INTO products (product_id, quantity, order_id) values (7, 2, 5);
INSERT INTO products (product_id, quantity, order_id) values (4, 4, 5);
INSERT INTO products (product_id, quantity, order_id) values (1, 1, 6);
INSERT INTO products (product_id, quantity, order_id) values (4, 2, 7);
INSERT INTO products (product_id, quantity, order_id) values (1, 3, 7);
INSERT INTO products (product_id, quantity, order_id) values (3, 3, 7);
INSERT INTO products (product_id, quantity, order_id) values (5, 3, 7);


