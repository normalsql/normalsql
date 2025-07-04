-- https://www.sqlite.org/lang_update.html

-- PostgreSQL dialect
UPDATE inventory
   SET quantity = quantity - daily.amt
  FROM (SELECT sum(quantity) AS amt, itemId FROM sales GROUP BY 2) AS daily
 WHERE inventory.itemId = daily.itemId;

-- SQL Server dialect
UPDATE inventory
   SET quantity = quantity - daily.amt
  FROM inventory,
       (SELECT sum(quantity) AS amt, itemId FROM sales GROUP BY 2) AS daily
 WHERE inventory.itemId = daily.itemId;

-- MySQL dialect
UPDATE inventory JOIN
       (SELECT sum(quantity) AS amt, itemId FROM sales GROUP BY 2) AS daily
       USING( itemId )
   SET inventory.quantity = inventory.quantity - daily.amt;
