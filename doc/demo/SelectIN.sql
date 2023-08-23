SELECT *
FROM VALUES (1, 'apple'), (2, 'banana'), (3, 'cherry'), (4, 'daikon') t("count", "fruit")
WHERE "count" IN ( 1, 4 );