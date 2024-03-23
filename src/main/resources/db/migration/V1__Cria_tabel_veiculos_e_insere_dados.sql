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
                         created TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         updated TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

INSERT INTO veiculo (tipo, veiculo, marca, ano, descricao, capacidade_tanque, consumo, checkup, created)
VALUES
('CARRO', 'Corolla', 'Toyota', 2021, 'Veículo potente e elegante', 50, 12, false, NOW()),
('MOTO', 'NMax', 'Yamaha', 2019, 'Bis potente, 160', 7, 40, false, NOW()),
('ONIBUS', 'Mercedes Benz', 'Mercedes', 2018, 'Descrição do veículo Modelo O', 350, 2, false, NOW());


CREATE TABLE log_veiculo (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              veiculo_id BIGINT NOT NULL,
                              operacao VARCHAR(10) NOT NULL,
                              data_modificacao TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                              dados_veiculo JSON,
                              FOREIGN KEY (veiculo_id) REFERENCES veiculo(id)
);

-- ##Trigger para atualizar log_veiculos toda vez que houver update em veiculo


CREATE TRIGGER after_veiculo_update
    AFTER UPDATE ON veiculo FOR EACH ROW
BEGIN
    INSERT INTO log_veiculo (veiculo_id, operacao, data_modificacao, dados_veiculo)
    VALUES (
               NEW.id,
               'UPDATE',
               NOW(),
               JSON_OBJECT(
                       'veiculo', CASE WHEN NEW.veiculo != OLD.veiculo THEN NEW.veiculo ELSE NULL END, -- Assuming 'nome' should be 'veiculo'
                       'tipo', CASE WHEN NEW.tipo != OLD.tipo THEN NEW.tipo ELSE NULL END,
                       'marca', CASE WHEN NEW.marca != OLD.marca THEN NEW.marca ELSE NULL END,
                       'ano', CASE WHEN NEW.ano != OLD.ano THEN NEW.ano ELSE NULL END,
                       'descricao', CASE WHEN NEW.descricao != OLD.descricao THEN NEW.descricao ELSE NULL END,
                       'capacidade_tanque', CASE WHEN NEW.capacidade_tanque != OLD.capacidade_tanque THEN NEW.capacidade_tanque ELSE NULL END,
                       'consumo', CASE WHEN NEW.consumo != OLD.consumo THEN NEW.consumo ELSE NULL END,
                       'autonomia', CASE WHEN NEW.autonomia != OLD.autonomia THEN NEW.autonomia ELSE NULL END,
                       'checkup', CASE WHEN NEW.checkup != OLD.checkup THEN NEW.checkup ELSE NULL END,
                       'updated', CASE WHEN NEW.updated != OLD.updated THEN NEW.updated ELSE NULL END
               )
           );
END;

