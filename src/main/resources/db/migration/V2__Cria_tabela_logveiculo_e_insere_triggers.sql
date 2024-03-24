CREATE TABLE log_veiculo (
                              id BIGINT AUTO_INCREMENT PRIMARY KEY,
                              veiculo_id BIGINT NOT NULL,
                              operacao VARCHAR(10) NOT NULL,
                              data_modificacao TIMESTAMP(6) DEFAULT CURRENT_TIMESTAMP(6),
                              dados_veiculo JSON

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
                       'operacao', 'UPDATE',
                       'dadosVeiculo', JSON_OBJECT(
                               'id', CASE WHEN NEW.id != OLD.id THEN NEW.id ELSE NULL END,
                               'veiculo', CASE WHEN NEW.veiculo != OLD.veiculo THEN NEW.veiculo ELSE NULL END,
                               'tipo', CASE WHEN NEW.tipo != OLD.tipo THEN NEW.tipo ELSE NULL END,
                               'marca', CASE WHEN NEW.marca != OLD.marca THEN NEW.marca ELSE NULL END,
                               'ano', CASE WHEN NEW.ano != OLD.ano THEN NEW.ano ELSE NULL END,
                               'descricao', CASE WHEN NEW.descricao != OLD.descricao THEN NEW.descricao ELSE NULL END,
                               'capacidade_tanque', CASE
                                                        WHEN NEW.capacidade_tanque != OLD.capacidade_tanque THEN NEW.capacidade_tanque
                                                        ELSE NULL END,
                               'consumo', CASE WHEN NEW.consumo != OLD.consumo THEN NEW.consumo ELSE NULL END,
                               'autonomia', CASE WHEN NEW.autonomia != OLD.autonomia THEN NEW.autonomia ELSE NULL END,
                               'checkup', CASE WHEN NEW.checkup != OLD.checkup THEN NEW.checkup ELSE NULL END,
                               'updated', CASE WHEN NEW.updated != OLD.updated THEN NEW.updated ELSE NULL END
                                       )
               )
           );
END;

-- Trigger para inserção

CREATE TRIGGER after_veiculo_insert
    AFTER INSERT ON veiculo
    FOR EACH ROW
BEGIN
    INSERT INTO log_veiculo (veiculo_id, operacao, data_modificacao, dados_veiculo)
    VALUES (
               NEW.id,
               'INSERT',
               NOW(),
               JSON_OBJECT(
                       'operacao', 'INSERT',
                       'dadosVeiculo', JSON_OBJECT(
                               'id', NEW.id,
                               'veiculo', NEW.veiculo,
                               'tipo', NEW.tipo,
                               'marca', NEW.marca,
                               'ano', NEW.ano,
                               'descricao', NEW.descricao,
                               'capacidade_tanque', NEW.capacidade_tanque,
                               'consumo', NEW.consumo,
                               'autonomia', NEW.autonomia,
                               'checkup', NEW.checkup,
                               'created', NEW.created,
                               'updated', NEW.updated
                                       )
               )
           );
END;

-- Trigger para delete

CREATE TRIGGER after_veiculo_delete
    AFTER DELETE ON veiculo
    FOR EACH ROW
BEGIN
    INSERT INTO log_veiculo (veiculo_id, operacao, data_modificacao, dados_veiculo)
    VALUES (
               OLD.id,
               'DELETE',
               NOW(),
               JSON_OBJECT(
                    'operacao', 'DELETE',
                    'dadosVeiculo', JSON_OBJECT(
                               'id', OLD.id,
                               'veiculo', OLD.veiculo,
                               'tipo', OLD.tipo,
                               'marca', OLD.marca,
                               'ano', OLD.ano,
                               'descricao', OLD.descricao,
                               'capacidade_tanque', OLD.capacidade_tanque,
                               'consumo', OLD.consumo,
                               'autonomia', OLD.autonomia,
                               'checkup', OLD.checkup,
                               'created', OLD.created,
                               'updated', OLD.updated
                                   )
               )
           );
END;

CREATE TABLE config_log_processamento (
                                          chave VARCHAR(255) NOT NULL,
                                          valor TIMESTAMP(6) NOT NULL,
                                          PRIMARY KEY (chave)
);