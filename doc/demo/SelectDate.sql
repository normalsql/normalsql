SELECT *
FROM VALUES
    ({d '2023-01-01'}, 'apple'),
    ({d '2023-02-02'}, 'banana'),
    ({d '2023-03-03'}, 'cherry'),
    ({d '2023-04-04'}, 'daikon')
    t("ripe", "fruit")
WHERE "ripe" >= '2023-03-03';