CREATE DATABASE IF NOT EXISTS anime_db;
USE anime_db;

DROP TABLE IF EXISTS users;
CREATE TABLE users(
  -- user_id INT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(50) PRIMARY KEY UNIQUE NOT NULL,
  date_of_birth DATE NOT NULL,
  country VARCHAR(50),
  top_genre VARCHAR(50),
  date_user_created DATE DEFAULT CURRENT_DATE
);

DROP TABLE IF EXISTS series;
CREATE TABLE series (
	series_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(200) NOT NULL,
    genre VARCHAR(100) NOT NULL,
    release_year INT,
    total_episode_count INT,
    status_of_series VARCHAR(10)
);

DROP TABLE IF EXISTS episodes;
CREATE TABLE episodes (
    episode_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(70) NOT NULL,
    release_date DATE NOT NULL,
    synopsis VARCHAR(100),
    no_of_views INT DEFAULT 0,
    series_id INT
    runtime INT NOT NULL
    FOREIGN KEY (series_id) REFERENCES series(series_id)
);
DROP TABLE IF EXISTS actors;
CREATE TABLE actors (
    actors_id INT NOT NULL AUTO_INCREMENT,
    last_name VARCHAR(50),
    first_name VARCHAR(50),
    gender VARCHAR(10),
    date_of_birth DATE,
    place_of_birth VARCHAR(100),
    agency VARCHAR(100),
    PRIMARY KEY (actors_id)
);
DROP TABLE IF EXISTS likedEpisode;
CREATE TABLE likedEpisode(
  like_id INT PRIMARY KEY AUTO_INCREMENT,
  username VARCHAR(50),
  episode_id INT,
  date_added DATE DEFAULT CURRENT_DATE,
  FOREIGN KEY (username) REFERENCES Users(username),
  FOREIGN KEY (episode_id) REFERENCES Episodes(episode_id) --episode_id variable name depends on what variable name will be used in the episodes.sql file
);

--DROP TABLE IF EXISTS seriesEpisodes;
--CREATE TABLE series_episodes_t (
--    series_id INT NOT NULL,
--    episode_id INT NOT NULL,
--    PRIMARY KEY (series_id, episode_id),
--    FOREIGN KEY (series_id) REFERENCES series(series_id),
--    FOREIGN KEY (episode_id) REFERENCES episodes_t(episode_id)
--);

DROP TABLE IF EXISTS watchHistory;
CREATE TABLE watchHistory (
    watch_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    episode_id INT NOT NULL,
    watch_date DATE DEFAULT CURRENT_DATE,
    FOREIGN KEY (username) REFERENCES users(username),
    FOREIGN KEY (episode_id) REFERENCES episodes_t(episode_id)
);

DROP TABLE IF EXISTS actorSeries;
CREATE TABLE actorSeries (
    act_id INT NOT NULL AUTO_INCREMENT,
    actors_id INT NOT NULL,
    series_id INT NOT NULL,
    character_name VARCHAR(100),
    PRIMARY KEY (act_id),
    FOREIGN KEY (actors_id) REFERENCES actors(actors_id),
    FOREIGN KEY (series_id) REFERENCES series(series_id)
);


DROP TABLE IF EXISTS episodeReviews;
CREATE TABLE episodeReviews (
    review_id INT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL,
    episode_id INT NOT NULL,
    comment TEXT,
    FOREIGN KEY (username) REFERENCES users(username),
    FOREIGN KEY (episode_id) REFERENCES episodes(episode_id)
);
