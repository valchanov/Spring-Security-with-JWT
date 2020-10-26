INSERT INTO roles (name)
VALUES ('ROLE_ADMIN'),
       ('ROLE_USER');

INSERT INTO users (name, email, password, role_id)
VALUES ('user', 'test@gmail.com', 'pass', 1)
;