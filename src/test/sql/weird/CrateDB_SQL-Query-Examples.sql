/*
 Copied from https://cratedb.com/product/sql-query-examples 2023-10-21

 */

 */
/* Based on device data, this query returns the average
 * of the battery level for every hour for each device_id
 */
WITH avg_metrics AS (
    SELECT device_id,
       DATE_BIN('1 hour'::INTERVAL, time, 0) AS period,
       AVG(battery_level) AS avg_battery_level
    FROM devices.readings
    GROUP BY 1, 2
    ORDER BY 1, 2
)
SELECT period,
       t.device_id,
       manufacturer,
       avg_battery_level
FROM avg_metrics t, devices.info i
WHERE t.device_id = i.device_id
      AND model = 'mustang'
LIMIT 10;



/* Based on reports from IoT devices, this query returns
 * the voltage corresponding to the maximum
 * global active power for each meter_id
 */

SELECT meter_id,
       MAX_BY("Voltage", "Global_active_power") AS voltage_max_global_power
FROM iot.power_consumption
GROUP BY 1
LIMIT 10;




/* Based on the location of the International Space Station,
 * this query returns the 10 closest capital cities from
 * the last known position
 */
SELECT city as "City Name",
       country as "Country",
       DISTANCE(i.position, c.location)::LONG / 1000 AS "Distance [km]"
FROM demo.iss i
CROSS JOIN demo.world_cities c
WHERE capital = 'primary'
      AND ts = (SELECT MAX(ts) FROM demo.iss)
ORDER BY 3 ASC
LIMIT 10;


/*
 * Based on system event logs, this query calculates:
 * - a filter for specific messages using a full-text index
 * - the number of entries per minute
 * - the average scoring ratio for each matched row
 */
SELECT DATE_TRUNC('minute', receivedat) AS event_time,
       COUNT(*) AS entries,
       AVG(_score) AS avg_score
FROM "syslog"."systemevents"
WHERE MATCH(message, 'authentication failure')
USING most_fields WITH (analyzer = 'whitespace')
   AND MATCH(syslogtag, 'sshd')
GROUP BY 1
ORDER BY 1 DESC
LIMIT 10;






