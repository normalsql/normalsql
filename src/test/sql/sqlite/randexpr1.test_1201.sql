-- randexpr1.test
--
-- db eval {SELECT coalesce((select t1.f from t1 where case d-(select case cast(avg(coalesce((select case when not 11<>e*c then 11 else 13 end from t1 where  -t1.c not between t1.f and 11),t1.e)) AS integer) when case max( -d) when ((min(17))) then cast(avg(t1.a) AS integer) else min(e) end+cast(avg((t1.b)) AS integer) then cast(avg(t1.d) AS integer) else  -(max(c)) end from t1)-f | b*13+e when t1.b then (f) else t1.a end in (select d from t1 union select 17 from t1)),d) FROM t1 WHERE t1.e*c>13-+t1.f | (abs((select abs(min(b)-abs( - -count(distinct 17-d-13+ -e-(t1.d)*e)+max(t1.e)) | (~abs((max(c)))+cast(avg(17) AS integer)-count(*)*cast(avg(11) AS integer))) from t1))/abs(t1.f))}
SELECT coalesce((select t1.f
                 from t1
                 where case d - (select case cast(avg(coalesce((select case when not 11 <> e * c then 11 else 13 end
                                                                from t1
                                                                where -t1.c not between t1.f and 11), t1.e)) AS integer)
                                            when case max(-d)
                                                     when ((min(17))) then cast(avg(t1.a) AS integer)
                                                     else min(e) end + cast(avg((t1.b)) AS integer)
                                                then cast(avg(t1.d) AS integer)
                                            else -(max(c)) end
                                 from t1) -
                            f | b*13+e when t1.b then (f) else t1.a end in (select d from t1 union select 17 from t1)),
                d)
FROM t1
WHERE t1.e * c > 13 - +t1.f | (abs((select abs(min(b)-abs( - -count(distinct 17-d-13+ -e-(t1.d)*e)+max(t1.e)) | (~abs((max(c)))+cast(avg(17) AS integer)-count(*)*cast(avg(11) AS integer))) from t1))/abs(t1.f))