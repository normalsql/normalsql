SELECT
    a.customerNumber, customerName, SUM(priceEach * quantityOrdered) spend
FROM
    orders
        INNER JOIN
    orderdetails USING (orderNumber)
        INNER JOIN
    customers a USING (customerNumber)
GROUP BY customerName
HAVING spend > 60000
ORDER BY spend DESC;