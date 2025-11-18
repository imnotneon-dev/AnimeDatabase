CREATE TABLE LikedEpisode(
  username VARCHAR(50),
  episode_id INT,
  date_added DATE DEFAULT CURRENT_DATE,
  PRIMARY KEY (user_id, episode_id),
  FOREIGN KEY (user_id) REFERENCES Users(user_id),
  FOREIGN KEY (episode_id) REFERENCES Episodes(episode_id) --episode_id variable name depends on what variable name will be used in the episodes.sql file
);


