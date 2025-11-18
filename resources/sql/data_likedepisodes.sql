CREATE TABLE LikedEpisode(
  user_id INT,
  episode_id INT,
  date_added DATE DEFAULT CURRENT_DATE,
  PRIMARY KEY (user_id, episode_id),
  FOREIGN KEY (user_id) REFERENCES Users(user_id),
  FOREIGN KEY (episode_id) REFERENCES Episodes(episode_id) --episode_id variable name depends on what variable name will be used in the episodes.sql file
);



-- Delete function
DELETE FROM LikedEpisode
WHERE user_id = ? AND episode_id = ?;


-- Select based on the account
SELECT 
    le.episode_id,
    e.title,
    le.date_added
FROM LikedEpisode le
JOIN Episodes e ON le.episode_id = e.episode_id
WHERE le.user_id = ?
ORDER BY le.date_added DESC;

