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
('The Job of a High School Host!', '2006-04-11', 'Haruhi has no problems adjusting to her new duties as a Host; Haruhi takes an interest in her newest client; with a formal dance coming up Haruhi needs to learn how to dance.', 3500, '00:23:22'),
('Beware the Physical Exam!', '2006-04-18', 'It's physical exam time at Ouran Academy which could spell trouble for someone who spends her days in disguise. Meanwhile, Tamaki launches the Host Club on diversionary tactics, and Kyoya has a backup plan.', 4000, '00:23:22'),
('Attack of the Lady Manager!', '2006-04-25', 'Tamaki is jealous of the relationship between the twins and Haruhi. Meanwhile, a young woman obsessed with dating sims declares herself the Host Club's new manager and her first order of business is to give all of the Hosts a "dark side."', 4600, '00:23:22'),
('The Twins Fight!', '2006-05-02', 'Haruhi divulges her secret for telling Hikaru and Kaoru apart, as the rivalry between the twins begins to spiral out of control and is causing a dip in the Club's profits.', 5200, '00:23:22'),
('The Grade School Host Is Naughty Type', '2006-05-09', 'Elementary student, Shiro Takaoji, has an aspiration of becoming a Host ventures into Music Room 3. He learns how to make people happy.', 5750, '00:23:22'),
('Jungle Pool SOS', '2006-05-16', 'The Hosts are forced to cut their vacation short when Honey goes missing. Haruhi expresses her interest in going to a real beach.', 6430, '00:23:22'),
('The Sun, The Sea, and the Host Club!', '2006-05-23', 'Haruhi proves fearless when faced with challenges set by the twins, but some see his bravery as recklessness.', 7230, '00:23:22'),
('A Challenge From Lobelia Girls Academy', '2006-05-30', 'The Zuka Club comes for a visit and tries to steal Haruhi from the boys; the Hosts make a plan to keep Haruhi at Ouran.', 9800, '00:23:22'),
('A Day in the Life of the Fujioka Family', '2006-06-06', 'Tamaki investigates what Haruhi's aggregate commoner dwelling is like. He finds there is plenty of room for trouble.', 8452, '00:23:22');

    
