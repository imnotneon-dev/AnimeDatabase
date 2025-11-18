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

('One Punch Man', 'Action, Comedy', '2015', '12', 'Complete'),
('Demon Slayer: Kimetsu no Yaiba Hashira Training Arc', 'Action, Supernatural', '2024', '8', 'Complete'),
('My Hero Academia Season 8', 'Action', '2025', '6', 'On-Going'),
('Spy x Family Season 3', 'Action, Comedy', '2025', '13', 'On-Going'),
('GACHIAKUTA', 'Action, Fantasy', '2025', '24', 'On-Going'),
('Tougen Anki: Dark Demon of Paradise', 'Action, Fantasy', '2025', '15', 'On-Going'),
('Kaguya-sama: Love Is War', 'Comedy, Romance', '2019', '12', 'Complete'),
('Paradise Kiss', 'Drama, Romance', '2005', '12', 'Complete'),
('Given', 'Boys Love, Drama', '2019', '11', 'Complete'),
('Elfen Lied', 'Action, Drama, Horror, Romance, Suspense', '2004', '13', 'Complete');


