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

INSERT INTO veiculo (tipo, veiculo, marca, ano, descricao, capacidade_tanque, consumo, checkup, created)
VALUES
('CARRO', 'Corolla', 'Toyota', 2021, 'Veículo potente e elegante', 50, 12, false, NOW()),
('MOTO', 'NMax', 'Yamaha', 2019, 'Bis potente, 160', 7, 40, false, NOW()),
('ONIBUS', 'Mercedes Benz', 'Mercedes', 2018, 'Descrição do veículo Modelo O', 350, 2, false, NOW());

