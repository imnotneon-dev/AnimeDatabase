CREATE DATABASE IF NOT EXISTS anime_db;
USE anime_db;

DROP TABLE IF EXISTS series;

CREATE TABLE series (
	series_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    genre VARCHAR(100) NOT NULL,
    release_year INT,
    total_episode_count INT,
    status_of_series VARCHAR(10)
);

INSERT INTO series (title, genre, release_year, total_episode_count, status_of_series)
VALUES

('One Punch Man', 'Comedy', '2015', '12', 'Complete'),
('Ouran High School Host Club', 'Comedy, Romance', '2006', '26', 'Complete'),
('Demon Slayer: Kimetsu no Yaiba Hashira Training Arc', 'Action', '2024', '8', 'Complete'),
('My Hero Academia Season 8', 'Action', '2025', '6', 'On-Going'),
('Spy x Family Season 3', 'Comedy', '2025', '13', 'On-Going'),
('GACHIAKUTA', 'Fantasy', '2025', '24', 'On-Going'),
('Tougen Anki: Dark Demon of Paradise', 'Fantasy', '2025', '15', 'On-Going'),
('Kaguya-sama: Love Is War', 'Romance', '2019', '12', 'Complete'),
('Paradise Kiss', 'Romance', '2005', '12', 'Complete'),
('Given', 'Boys Love', '2019', '11', 'Complete'),
('Elfen Lied', 'Action', '2004', '13', 'Complete');




SELECT
	s.series_id,
	s.title,
	COUNT(DISTINCT wh.user_id) AS weekly_viewers
FROM watch_history wh
JOIN series s ON wh.series_id = s.series_id
WHERE wh.watch_date >= CURDATE() - INTERVAL 7 DAY
GROUP BY s.series_id, s.title
ORDER BY weekly_viewers DESC
LIMIT 5;

