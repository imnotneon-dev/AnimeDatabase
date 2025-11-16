CREATE TABLE FavoriteSeries (
    user_id INT,
    series_id INT,
    added_date DATE DEFAULT CURRENT_DATE,
    PRIMARY KEY (user_id, series_id),
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (series_id) REFERENCES Series(series_id)
);