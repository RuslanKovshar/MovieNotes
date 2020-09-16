CREATE TABLE movie_notes
(
    id           BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    release_date BIGINT,
    title        VARCHAR(255),
    user_id      BIGINT,
    FOREIGN KEY (user_id)
        REFERENCES users (id) ON DELETE CASCADE
) ENGINE = InnoDB;
