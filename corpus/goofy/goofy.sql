-- Same as line
SELECT l1.s, l2.s FROM LINE_TBL l1, LINE_TBL l2 WHERE l1.s = l2.s;

-- Parallel to line
SELECT l1.s, l2.s FROM LINE_TBL l1, LINE_TBL l2 WHERE l1.s ?|| l2.s;

-- Perpendicular to line
SELECT l1.s, l2.s FROM LINE_TBL l1, LINE_TBL l2 WHERE l1.s ?-| l2.s;


-- SELECT 2 !=-- confusing comment
--   a;

SELECT 2 ===/* confusing comment */ b;
