CREATE DATABASE IF NOT EXISTS anime_db;
USE anime_db;

DROP TABLE IF EXISTS watch_history_t;
DROP TABLE IF EXISTS series_episodes_t;
DROP TABLE IF EXISTS episodes_t;

-- Core Records
CREATE TABLE episodes_t (
    episode_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(70) NOT NULL,
    release_date DATE NOT NULL,
    synopsis TEXT,
    no_of_views INT DEFAULT 0,
    runtime TIME NOT NULL
);

-- Transaction Tables
CREATE TABLE series_episodes_t (
    series_id INT NOT NULL,
    episode_id INT NOT NULL,
    PRIMARY KEY (series_id, episode_id),
    FOREIGN KEY (series_id) REFERENCES series(series_id),
    FOREIGN KEY (episode_id) REFERENCES episodes_t(episode_id)
);

CREATE TABLE watch_history_t (
    watch_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    episode_id INT NOT NULL,
    is_done_watching BOOLEAN,
    start_watch_date DATE,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (episode_id) REFERENCES episodes_t(episode_id)
);

