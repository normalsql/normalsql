SELECT *
FROM VALUES (1, 'apple pie'), (2, 'apple fritter'), (3, 'cherry pie'), (4, 'cookies') t("id", "treat")
WHERE "treat" LIKE '%pie';