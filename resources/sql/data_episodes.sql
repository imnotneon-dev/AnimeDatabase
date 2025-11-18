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
    watch_date DATE DEFAULT CURRENT_DATE,
    FOREIGN KEY (user_id) REFERENCES users(user_id),
    FOREIGN KEY (episode_id) REFERENCES episodes_t(episode_id)
);

-- INSERTS
INSERT INTO episodes_t (title, release_date, synopsis, no_of_views, runtime)
VALUES

('Starting Today, You Are a Host!', '2006-04-04', 'Haruhi Fujioka stumbles across the Host Club and accidentally breaks a vase which she is unable to pay for, so she must work as a new Host despite being a girl.', 3000, '00:23:22'),





