CREATE TABLE local_users
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(255) NULL,
    surname     VARCHAR(255) NULL,
    email       VARCHAR(255),
    password    VARCHAR(255),
    postAddress VARCHAR(255)
);

CREATE TABLE tickets
(
    id    INT PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(255) NULL,
    price VARCHAR(255) NULL
);