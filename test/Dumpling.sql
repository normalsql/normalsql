SELECT 
id as super,
( id * 10 ) + 1 as duper,
(( id * 10 ) + 1 ) as duper2,
100 as gronk, 
id * 5  as duper,
count( id ) as tally
FROM Table