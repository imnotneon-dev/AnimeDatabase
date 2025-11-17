SELECT 
    fs.username,
    fs.series_id,
    s.series_title,
    s.series_description
FROM favorite_series fs
JOIN users u ON fs.username = u.username
JOIN series s ON fs.series_id = s.series_id
WHERE u.username = ? 
  AND fs.series_id = ?;
