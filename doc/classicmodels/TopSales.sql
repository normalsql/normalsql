WITH topsales AS (
    SELECT
        salesRepEmployeeNumber employeeNumber,
        SUM(quantityOrdered * priceEach) sales
    FROM
        orders
            INNER JOIN
        orderdetails USING (orderNumber)
            INNER JOIN
        customers USING (customerNumber)
    WHERE
            YEAR(shippedDate) = 2003
      AND status = 'Shipped'
    GROUP BY salesRepEmployeeNumber
    ORDER BY sales DESC
    LIMIT 5
)
SELECT
    a.employeeNumber,
    a.firstName,
    a.lastName,
    b.sales
FROM
    employees a
        JOIN
    topsales b USING (employeeNumber);
