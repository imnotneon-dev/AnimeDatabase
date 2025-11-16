CREATE TABLE EpisodeReview (
    review_id INT PRIMARY KEY AUTO_INCREMENT,
    user_id INT NOT NULL,
    episode_id INT NOT NULL,
    user_review TEXT NOT NULL,
    date_reviewed DATE DEFAULT CURRENT_DATE,
    
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (episode_id) REFERENCES Episodes(episode_id)
);
