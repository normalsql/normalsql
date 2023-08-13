SELECT "id", "make", "model", "year"
FROM public.AUTOMOBILES
WHERE "style" = 'coupe'
  AND "odometer" < 100000;
