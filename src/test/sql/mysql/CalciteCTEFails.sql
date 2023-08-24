with t1(x, y) as (select * from (values (1, 10), (2, 20), (cast(null as integer), 30)) as t),
     t2(x, y) as (select * from (values (1,100), (cast(null as integer), 200)) as t)
select * from t1 full join t2 on t1.x = t2.x;