CREATE TABLE Users(
  user_id INT AUTO_INCREMENT,
  username VARCHAR(50) PRIMARY KEY,
  password VARCHAR(255) NOT NULL,
  date_of_birth DATE NOT NULL,
  country VARCHAR(50),
  top_genre VARCHAR(50),
  date_user_created DATE DEFAULT CURRENT_DATE
);


