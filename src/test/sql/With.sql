-- Copied 2023-08-06
-- http://www.h2database.com/html/advanced.html#recursive_queries

WITH RECURSIVE T(N) AS (
    SELECT 1
    UNION ALL
    SELECT N+1 FROM T WHERE N<10
)
SELECT * FROM T;
-- returns the values 1 .. 10

WITH RECURSIVE T(N) AS (
    SELECT 1
    UNION ALL
    SELECT N*2 FROM T WHERE N<10
)
SELECT * FROM T;
-- returns the values 1, 2, 4, 8, 16

CREATE TABLE FOLDER(ID INT PRIMARY KEY, NAME VARCHAR(255), PARENT INT);

INSERT INTO FOLDER VALUES(1, null, null), (2, 'src', 1),
                         (3, 'main', 2), (4, 'org', 3), (5, 'test', 2);

WITH LINK(ID, NAME, LEVEL) AS (
    SELECT ID, NAME, 0 FROM FOLDER WHERE PARENT IS NULL
    UNION ALL
    SELECT FOLDER.ID, COALESCE(LINK.NAME || '/', '') || FOLDER.NAME, LEVEL + 1
    FROM LINK INNER JOIN FOLDER ON LINK.ID = FOLDER.PARENT
)
SELECT NAME FROM LINK WHERE NAME IS NOT NULL ORDER BY ID;
-- src
-- src/main
-- src/main/org
-- src/test