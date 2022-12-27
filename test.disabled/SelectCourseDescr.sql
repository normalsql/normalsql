SELECT
    id, lastATP, course_title as title, desc, revision
FROM Course c
WHERE abbrev = 'ENGL'
  AND number = 100
ORDER BY lastATP desc;