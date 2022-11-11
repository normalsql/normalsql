SELECT
    id, course_title
FROM Course
WHERE number BETWEEN 100 AND 200
  AND 250 BETWEEN number AND 300
  AND 350 BETWEEN 301 AND number
  AND 450 BETWEEN number AND number