CREATE DATABASE IF NOT EXISTS ProjetoExpoeetepaBD
  DEFAULT CHARACTER SET utf8mb4
  DEFAULT COLLATE utf8mb4_general_ci;

  USE ProjetoExpoeetepaBD;

CREATE TABLE estacoes (
    id  VARCHAR(32) NOT NULL PRIMARY KEY,
    data_hora DATETIME NOT NULL,
    nome VARCHAR(32) NOT NULL,
    temperaturaAr DECIMAL(5,2) NOT NULL,
    umidadeAr DECIMAL(5,2) NOT NULL,
    pressaoAr DECIMAL(6,2) NOT NULL
);

CREATE TABLE irrigadores (
    id             INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    plantio        VARCHAR(32) NOT NULL,
    id_estacao     VARCHAR(32) NOT NULL,
    nome_estacao VARCHAR(32) NOT NULL,
    umidade_solo    DECIMAL(5,2),
    acao_atual      ENUM('ligado','desligado'),
    tempo_restante  INT,
    ciclo_dias INT,
    limiar_umidade  INT,
    INDEX (id_estacao),
    FOREIGN KEY (id_estacao) REFERENCES estacoes(id)
);

CREATE USER 'joao-admin'@'172.19.0.2' IDENTIFIED BY 'joao-admin1234';
GRANT SELECT, INSERT ON ProjetoExpoeetepaBD.* TO 'joao-admin'@'172.19.0.2' WITH GRANT OPTION;
FLUSH PRIVILEGES;
