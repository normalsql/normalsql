SELECT *
FROM VALUES (1, TRUE), (2, FALSE), (3, NULL) t("id", "status")
WHERE "status" = TRUE;