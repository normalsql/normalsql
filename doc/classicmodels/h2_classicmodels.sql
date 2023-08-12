SET MODE STRICT;
SET SCHEMA PUBLIC;

ALTER TABLE IF EXISTS customers
    DROP CONSTRAINT IF EXISTS customers_employees_fk;

ALTER TABLE IF EXISTS employees
    DROP CONSTRAINT IF EXISTS customers_employees_fk;

ALTER TABLE IF EXISTS employees
    DROP CONSTRAINT IF EXISTS employees_offices_fk;

ALTER TABLE IF EXISTS orders
    DROP CONSTRAINT IF EXISTS orders_customers_fk;

ALTER TABLE IF EXISTS orderdetails
    DROP CONSTRAINT IF EXISTS orderdetails_orders_fk;

ALTER TABLE IF EXISTS orderdetails
    DROP CONSTRAINT IF EXISTS orderdetails_products_fk;

ALTER TABLE IF EXISTS payments
    DROP CONSTRAINT IF EXISTS payments_customers_fk;

ALTER TABLE IF EXISTS products
    DROP CONSTRAINT IF EXISTS products_productlines_fk;

DROP TABLE IF EXISTS customers;
DROP TABLE IF EXISTS employees;
DROP TABLE IF EXISTS offices;
DROP TABLE IF EXISTS orders;
DROP TABLE IF EXISTS orderdetails;
DROP TABLE IF EXISTS payments;
DROP TABLE IF EXISTS productlines;
DROP TABLE IF EXISTS products;

CREATE TABLE customers
(
    customerNumber         integer     NOT NULL PRIMARY KEY,
    customerName           varchar(50) NOT NULL,
    contactLastName        varchar(50) NOT NULL,
    contactFirstName       varchar(50) NOT NULL,
    phone                  varchar(50) NOT NULL,
    addressLine1           varchar(50) NOT NULL,
    addressLine2           varchar(50)    DEFAULT NULL,
    city                   varchar(50) NOT NULL,
    state                  varchar(50)    DEFAULT NULL,
    postalCode             varchar(15)    DEFAULT NULL,
    country                varchar(50) NOT NULL,
    salesRepEmployeeNumber integer        DEFAULT NULL,
    creditLimit            numeric(10, 2) DEFAULT NULL
--     offices KEY salesRepEmployeeNumber (salesRepEmployeeNumber),
);

CREATE TABLE employees
(
    employeeNumber integer      NOT NULL PRIMARY KEY,
    lastName       varchar(50)  NOT NULL,
    firstName      varchar(50)  NOT NULL,
    extension      varchar(10)  NOT NULL,
    email          varchar(100) NOT NULL,
    officeCode     varchar(10)  NOT NULL,
    reportsTo      integer DEFAULT NULL,
    jobTitle       varchar(50)  NOT NULL
--     KEY              reportsTo (reportsTo),
--     KEY              officeCode (officeCode),
);

CREATE TABLE offices
(
    officeCode   varchar(10) NOT NULL PRIMARY KEY,
    city         varchar(50) NOT NULL,
    phone        varchar(50) NOT NULL,
    addressLine1 varchar(50) NOT NULL,
    addressLine2 varchar(50) DEFAULT NULL,
    state        varchar(50) DEFAULT NULL,
    country      varchar(50) NOT NULL,
    postalCode   varchar(15) NOT NULL,
    territory    varchar(10) NOT NULL
);

CREATE TABLE orders
(
    orderNumber    integer     NOT NULL PRIMARY KEY,
    orderDate      date        NOT NULL,
    requiredDate   date        NOT NULL,
    shippedDate    date DEFAULT NULL,
    status         varchar(15) NOT NULL,
    comments       text,
    customerNumber integer     NOT NULL
--                           KEY customerNumber (customerNumber),
);

CREATE TABLE orderdetails
(
    orderNumber     integer        NOT NULL,
    productCode     varchar(15)    NOT NULL,
    quantityOrdered integer        NOT NULL,
    priceEach       numeric(10, 2) NOT NULL,
    orderLineNumber smallint       NOT NULL,
    PRIMARY KEY (orderNumber, productCode)
--                                 KEY productCode (productCode),
);

CREATE TABLE payments
(
    customerNumber integer        NOT NULL,
    checkNumber    varchar(50)    NOT NULL,
    paymentDate    date           NOT NULL,
    amount         numeric(10, 2) NOT NULL,
    PRIMARY KEY (customerNumber, checkNumber)
);

CREATE TABLE productlines
(
    productLine     varchar(50) NOT NULL PRIMARY KEY,
    textDescription varchar(4000) DEFAULT NULL,
    htmlDescription mediumtext,
    image           mediumblob
);

CREATE TABLE products
(
    productCode        varchar(15)    NOT NULL PRIMARY KEY,
    productName        varchar(70)    NOT NULL,
    productLine        varchar(50)    NOT NULL,
    productScale       varchar(10)    NOT NULL,
    productVendor      varchar(50)    NOT NULL,
    productDescription text           NOT NULL,
    quantityInStock    smallint       NOT NULL,
    buyPrice           numeric(10, 2) NOT NULL,
    MSRP               numeric(10, 2) NOT NULL
--                             KEY productLine (productLine),
);

ALTER TABLE customers
    ADD CONSTRAINT customers_employees_fk
        FOREIGN KEY (salesRepEmployeeNumber) REFERENCES employees (employeeNumber);

ALTER TABLE employees
    ADD CONSTRAINT employees_employees_fk
        FOREIGN KEY (reportsTo) REFERENCES employees (employeeNumber);

ALTER TABLE employees
    ADD CONSTRAINT employees_offices_fk
        FOREIGN KEY (officeCode) REFERENCES offices (officeCode);

ALTER TABLE orders
    ADD CONSTRAINT orders_customers_fk
        FOREIGN KEY (customerNumber) REFERENCES customers (customerNumber);

ALTER TABLE orderdetails
    ADD CONSTRAINT orderdetails_orders_fk
        FOREIGN KEY (orderNumber) REFERENCES orders (orderNumber);

ALTER TABLE orderdetails
    ADD CONSTRAINT orderdetails_products_fk
        FOREIGN KEY (productCode) REFERENCES products (productCode);

ALTER TABLE payments
    ADD CONSTRAINT payments_customers_fk
        FOREIGN KEY (customerNumber) REFERENCES customers (customerNumber);

ALTER TABLE products
    ADD CONSTRAINT products_productlines_fk
        FOREIGN KEY (productLine) REFERENCES productlines (productLine);


