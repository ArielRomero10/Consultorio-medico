CREATE TABLE IF NOT EXISTS usuario (
    password VARCHAR(255) NOT NULL,
    username VARCHAR(255) NOT NULL UNIQUE
);

-- Inserta dos usuarios
INSERT INTO usuario ( password, username) VALUES ( '321', 'Secre');
INSERT INTO usuario ( password, username) VALUES ( '321', 'doctor');
