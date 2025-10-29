DROP TABLE IF EXISTS actors;

CREATE TABLE actors (
    actors_id INT NOT NULL,
    last_name VARCHAR(50),
    first_name VARCHAR(50),
    date_of_birth DATE,
    place_of_birth VARCHAR(100),
    agency VARCHAR(100),
)

INSERT INTO actors (actors_id, last_name, first_name, date_of_birth, place_of_birth, agency)
VALUES
(1, '-', '-', '-', '-', '-'),
(2, '-', '-', '-', '-', '-')