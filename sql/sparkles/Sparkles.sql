SELECT 
id,
name,
-- rtrim( name ) as lucious,
-- convert( whoops, int ) as whoops,
( year * 10 ) + quarter as lastATP
 FROM TEST 