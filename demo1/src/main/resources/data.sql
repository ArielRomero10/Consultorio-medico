DROP TABLE IF EXISTS "USUARIO";
CREATE TABLE "USUARIO" (id BIGINT AUTO_INCREMENT PRIMARY KEY, username VARCHAR(255), password VARCHAR(255));

INSERT INTO USUARIO (username, password) VALUES ('user1', 'password1');
INSERT INTO USUARIO (username, password) VALUES ('user2', 'password2');
