CREATE TABLE veiculo (
                         id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         tipo VARCHAR(255) NOT NULL,
                         veiculo VARCHAR(255) NOT NULL,
                         marca VARCHAR(255) NOT NULL,
                         ano INTEGER NOT NULL,
                         descricao TEXT,
                         capacidade_tanque INTEGER,
                         consumo INTEGER,
                         autonomia DOUBLE,
                         checkup BOOLEAN,
                         created TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
                         updated TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6) ON UPDATE CURRENT_TIMESTAMP(6)
);



