CREATE TABLE movie_watchlist
(
    id           BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    title        VARCHAR(255),
    watched      BOOLEAN,
    user_id      BIGINT,
    FOREIGN KEY (user_id)
        REFERENCES users (id) ON DELETE CASCADE
) ENGINE = InnoDB;