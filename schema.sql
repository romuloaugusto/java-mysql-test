CREATE TABLE `log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` timestamp(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3),
  `request` varchar(255) NOT NULL DEFAULT '',
  `ip` varchar(45) NOT NULL DEFAULT '',
  `status` int(5) NOT NULL,
  `useragent` varchar(255) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=116485 DEFAULT CHARSET=utf8;


CREATE TABLE `block` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `details` varchar(255) DEFAULT NULL,
  `ip` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;


---- SQL


--(1) Write MySQL query to find IPs that mode more than a certain number of requests for a given time period.
--Ex: Write SQL to find IPs that made more than 100 requests starting from 2017-01-01.13:00:00 to 2017-01-01.14:00:00.
SELECT ip, count(id) AS count
FROM log
WHERE date BETWEEN '2017-01-01.15:00:00' AND '2017-01-01.16:00:00'
GROUP BY ip
HAVING count > 200
    
-- (2) Write MySQL query to find requests made by a given IP.
SELECT * FROM log WHERE ip LIKE '192.168.11.231'

