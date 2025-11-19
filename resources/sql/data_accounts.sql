CREATE TABLE Users(
  user_id INT AUTO_INCREMENT,
  username VARCHAR(50) PRIMARY KEY,
  password VARCHAR(255) NOT NULL,
  date_of_birth DATE NOT NULL,
  country VARCHAR(50),
  top_genre VARCHAR(50),
  date_user_created DATE DEFAULT CURRENT_DATE
);

-- INSERTS

INSERT INTO Users (username, password, date_of_birth, country, top_genre)
VALUES 

('thistlezhi', 'zh15p4ssword', '2006-08-21', 'Malaysia', 'Action'),
('cerizz_', 'xLuvr8', '2014-06-16', 'Philippines', 'Comedy'),
('_zafibleh', 'm3oW!', '2005-10-28', 'Singapore', 'Romance'),
('icep0p', 'xD_rul3z', '2006-07-27', 'Philippines', 'Horror'),
('Mother_Coco', 'AFDG2028.switz!', '2004-12-18', 'Philippines', 'Ecchi'),
('IJ.Orts', '1ts_IJcUhh', '2005-06-29', 'Malaysia', 'Supernatural'),
('Azii95', 'k4tch0W!', '2005-08-14', 'Malaysia', 'Fantasy'),
('YSA', 'gl@ssy!!', '2012-08-10', 'Singapore', 'Drama'),
('SMHMyHead', 'r4ndOm_p@ssw0rd!', '2006-10-06', 'Malaysia', 'Adventure'),
('RYAN12345801', 's3cret!S4NT4', '2007-12-05', 'Philippines', 'Sci-Fi');





