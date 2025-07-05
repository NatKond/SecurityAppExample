CREATE TABLE IF NOT EXISTS local_users
(
    id              INT PRIMARY KEY AUTO_INCREMENT,
    name            VARCHAR(255) NOT NULL,
    surname         VARCHAR(255) NOT NULL,
    email           VARCHAR(255) NOT NULL,
    password        VARCHAR(255) NOT NULL,
    post_address    VARCHAR(255)
    Role            ENUM('CLIENT', 'ADMINISTRATOR')
);

CREATE TABLE IF NOT EXISTS tickets
(
    id              INT PRIMARY KEY AUTO_INCREMENT,
    title           VARCHAR(255) NOT NULL,
    price           VARCHAR(255) NOT NULL,
    local_user_id   INT NOT NULL
);