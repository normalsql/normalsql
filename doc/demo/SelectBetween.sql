SELECT *
FROM VALUES (1, 'apple'), (2, 'banana'), (3, 'cherry'), (4, 'daikon') t("count", "fruit")
WHERE "count" BETWEEN 2 and 3;