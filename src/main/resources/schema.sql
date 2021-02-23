DROP TABLE IF EXISTS roles;
DROP TABLE IF EXISTS users;

CREATE TABLE roles
(
    id   INT AUTO_INCREMENT PRIMARY KEY,
    name ENUM('ROLE_ADMIN', 'ROLE_USER') NOT NULL,
    created_by INT,
    last_modified_by INT,
    created_date DATETIME(6),
    last_modified_date DATETIME(6)
);

CREATE TABLE users
(
    id       INT AUTO_INCREMENT PRIMARY KEY,
    name     varchar(50)  NOT NULL,
    email    varchar(50)  NOT NULL,
    password varchar(100) NOT NULL,
    role_id  INT          NOT NULL,
    created_by INT,
    last_modified_by INT,
    created_date DATETIME(6),
    last_modified_date DATETIME(6)
);