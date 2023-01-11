SELECT id, make, model, year
FROM automobiles
WHERE style = 'coupe'
  AND odometer < 100000;