CREATE TABLE users (
                       id BIGINT PRIMARY KEY,
                       user_identity VARCHAR(255) NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       nickname VARCHAR(255)
);
