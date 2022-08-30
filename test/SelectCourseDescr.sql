SELECT
    id, lastATP, course_title, description, revision
FROM Course
WHERE department_abbrev = 'ENGL'
  AND course_number = 100
ORDER BY lastATP desc;