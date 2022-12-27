-- https://www.mysqltutorial.org/mysql-cte/

WITH customers_in_usa AS (
    SELECT
        customerName, state
    FROM
        customers
    WHERE
            country = 'USA'
) SELECT
    customerName
FROM
    customers_in_usa
WHERE
        state = 'CA'
ORDER BY customerName;