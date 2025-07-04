-- https://www.sqlite.org/lang_createtrigger.html

CREATE TRIGGER update_customer_address UPDATE OF address ON customers
  BEGIN
    UPDATE orders SET address = new.address WHERE customer_name = old.name;
  END;

UPDATE customers SET address = '1 Main St.' WHERE name = 'Jack Jones';

UPDATE orders SET address = '1 Main St.' WHERE customer_name = 'Jack Jones';

CREATE TABLE customer(
  cust_id INTEGER PRIMARY KEY,
  cust_name TEXT,
  cust_addr TEXT
);
CREATE VIEW customer_address AS
   SELECT cust_id, cust_addr FROM customer;
CREATE TRIGGER cust_addr_chng
INSTEAD OF UPDATE OF cust_addr ON customer_address
BEGIN
  UPDATE customer SET cust_addr=NEW.cust_addr
   WHERE cust_id=NEW.cust_id;
END;

UPDATE customer_address SET cust_addr=$new_address WHERE cust_id=$cust_id;
