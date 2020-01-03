
-- Cria a base de dados avaliacao
CREATE DATABASE avaliacao;

-- Cria o usuario phcruz com a senha phcruz
CREATE USER 'phcruz'@'localhost' IDENTIFIED BY 'phcruz';

-- Concede os privilégios ao usuario phcruz
GRANT ALL PRIVILEGES ON * . * TO 'phcruz'@'localhost';