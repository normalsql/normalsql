-- https://learnsql.com/blog/sql-nested-select/
SELECT AVG(number_of_students)
FROM classes as c
WHERE teacher_id IN (
    SELECT id
    FROM teachers
    WHERE tada > 1 AND subject = 'English' OR subject = 'History');