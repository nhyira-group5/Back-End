CREATE DATABASE IF NOT EXISTS vitalisDB;
USE vitalisDB;

DROP TRIGGER IF EXISTS cria_rotina;

DROP DATABASE IF EXISTS vitalisDB;
CREATE DATABASE vitalisDB;
USE vitalisDB;

CREATE TABLE midia (
    id_midia INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    caminho VARCHAR(500) NOT NULL,
    extensao VARCHAR(10) NOT NULL
);

CREATE TABLE meta (
	id_meta INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100)
);

CREATE TABLE endereco (
    id_endereco INT PRIMARY KEY AUTO_INCREMENT,
    logradouro VARCHAR(100) NOT NULL,
    numero VARCHAR(100),
    bairro VARCHAR(100) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    estado CHAR(2) NOT NULL,
    complemento VARCHAR(100),
    cep CHAR(8)
);

CREATE TABLE usuario (
    id_usuario INT PRIMARY KEY AUTO_INCREMENT,
    -- tipo VARCHAR(45) CHECK (tipo IN ('USUARIO', 'PERSONAL', 'ADMIN')), 
    tipo TINYINT NOT NULL, 
    nickname VARCHAR(20) NOT NULL,
    cpf CHAR(11) NOT NULL,
    nome VARCHAR(200) NOT NULL,
    dt_nasc DATE NOT NULL,
    sexo CHAR(3),
    email VARCHAR(100) NOT NULL,
    email_recuperacao VARCHAR(100),
    senha VARCHAR(100) NOT NULL,
    midia_id INT,
    personal_id INT,
    endereco_id INT,
    FOREIGN KEY (endereco_id) REFERENCES endereco(id_endereco) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (midia_id) REFERENCES midia(id_midia) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (personal_id) REFERENCES usuario(id_usuario) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE contrato (
	id_contrato INT AUTO_INCREMENT,
    usuario_id INT,
    personal_id INT,
	afiliacao TINYINT NOT NULL CHECK (afiliacao IN (0, 1, 2)), -- 0 para finalizado, 1 para ativo e 2 para pendente
    inicio_contrato DATE,
    fim_contrato DATE,
	PRIMARY KEY (id_contrato, usuario_id, personal_id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id_usuario) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (personal_id) REFERENCES usuario(id_usuario) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE chat (
    id_chat INT AUTO_INCREMENT,
	usuario_id INT,
    personal_id INT,
    ativo TINYINT,
    PRIMARY KEY (id_chat, usuario_id, personal_id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id_usuario) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (personal_id) REFERENCES usuario(id_usuario) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE mensagem (
    id_mensagem INT AUTO_INCREMENT,
    chat_id INT,
    remetente_id INT,
    destinatario_id INT,
    assunto VARCHAR(255),
    data_hora DATETIME NOT NULL,
    PRIMARY KEY (id_mensagem, remetente_id, destinatario_id, chat_id),
    FOREIGN KEY (chat_id) REFERENCES chat(id_chat) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (remetente_id) REFERENCES usuario(id_usuario) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (destinatario_id) REFERENCES usuario(id_usuario) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE especialidade (
    id_especialidade INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE especialidade_por_personal (
    id_especialidade_personal INT AUTO_INCREMENT,
	personal_id INT,
    especialidade_id INT,
    PRIMARY KEY (id_especialidade_personal, personal_id, especialidade_id),
    FOREIGN KEY (personal_id) REFERENCES usuario(id_usuario) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (especialidade_id) REFERENCES especialidade(id_especialidade) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE especialidade_por_meta (
	id_especialidade_meta INT AUTO_INCREMENT,
    especialidade_id INT,
    meta_id INT,
    PRIMARY KEY (id_especialidade_meta, especialidade_id, meta_id),
    FOREIGN KEY (especialidade_id) REFERENCES especialidade(id_especialidade) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (meta_id) REFERENCES meta(id_meta) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE tag (
	id_tag INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE exercicio (
    id_exercicio INT PRIMARY KEY AUTO_INCREMENT,
    midia_id INT,
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(250),
    FOREIGN KEY (midia_id) REFERENCES midia(id_midia) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE tag_exercicio (
	id_tag_exercicio INT AUTO_INCREMENT,
    tag_id INT,
    exercicio_id INT,
    PRIMARY KEY (id_tag_exercicio, tag_id, exercicio_id),
    FOREIGN KEY (tag_id) REFERENCES tag(id_tag) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (exercicio_id) REFERENCES exercicio(id_exercicio) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE rotina_usuario (
    id_rotina_usuario INT AUTO_INCREMENT,
    usuario_id INT,
    meta_id INT,
    PRIMARY KEY (id_rotina_usuario, usuario_id, meta_id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id_usuario) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (meta_id) REFERENCES meta(id_meta) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE rotina_mensal (
	id_rotina_mensal INT AUTO_INCREMENT,
    rotina_usuario_id INT,
    mes INT CHECK (mes IN (1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)),
    ano INT,
    concluido TINYINT,
    PRIMARY KEY (id_rotina_mensal, rotina_usuario_id),
    FOREIGN KEY (rotina_usuario_id) REFERENCES rotina_usuario(id_rotina_usuario) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE rotina_semanal (
	id_rotina_semanal INT AUTO_INCREMENT,
    rotina_mensal_id INT,
    num_semana INT CHECK (num_semana IN (1, 2, 3, 4, 5)) NOT NULL,
    concluido TINYINT,
    PRIMARY KEY (id_rotina_semanal, rotina_mensal_id),
    FOREIGN KEY (rotina_mensal_id) REFERENCES rotina_mensal(id_rotina_mensal) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE rotina_diaria (
    id_rotina_diaria INT AUTO_INCREMENT,
    rotina_semanal_id INT,
    dia INT CHECK (dia IN (1, 2, 3, 4, 5, 6, 7)),
    concluido TINYINT,
    PRIMARY KEY (id_rotina_diaria, rotina_semanal_id),
    FOREIGN KEY (rotina_semanal_id) REFERENCES rotina_semanal(id_rotina_semanal) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE treino (
    id_treino INT AUTO_INCREMENT,
    exercicio_id INT,
    rotina_diaria_id INT,
    concluido TINYINT,
    repeticao INT NOT NULL,
    serie INT NOT NULL,
    tempo TIME NOT NULL,
    PRIMARY KEY (id_treino, exercicio_id, rotina_diaria_id),
    FOREIGN KEY (exercicio_id) REFERENCES exercicio(id_exercicio) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (rotina_diaria_id) REFERENCES rotina_diaria(id_rotina_diaria) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE dieta (
    id_dieta INT AUTO_INCREMENT,
    meta_id INT,
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(500) NOT NULL,
    PRIMARY KEY (id_dieta, meta_id),
    FOREIGN KEY (meta_id) REFERENCES meta(id_meta) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE refeicao (
    id_refeicao INT PRIMARY KEY AUTO_INCREMENT,
    midia_id INT,
    nome VARCHAR(100) NOT NULL,
    preparo VARCHAR(5001),
    FOREIGN KEY(midia_id) REFERENCES midia(id_midia) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE refeicao_diaria (
	id_refeicao_diaria INT AUTO_INCREMENT,
    refeicao_id INT,
    rotina_diaria_id INT,
    concluido TINYINT,
    PRIMARY KEY (id_refeicao_diaria, refeicao_id, rotina_diaria_id),
    FOREIGN KEY (refeicao_id) REFERENCES refeicao(id_refeicao) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (rotina_diaria_id) REFERENCES rotina_diaria(id_rotina_diaria) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE refeicao_por_dieta (
	id_refeicao_dieta INT AUTO_INCREMENT,
    refeicao_id INT,
    dieta_id INT,
    PRIMARY KEY (id_refeicao_dieta, refeicao_id, dieta_id),
    FOREIGN KEY (refeicao_id) REFERENCES refeicao(id_refeicao) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (dieta_id) REFERENCES dieta(id_dieta) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE alimento (
    id_alimento INT PRIMARY KEY AUTO_INCREMENT,
	midia_id INT,
    nome VARCHAR(100) NOT NULL,
    carboidrato FLOAT NOT NULL,
    proteina FLOAT NOT NULL,
    gordura FLOAT NOT NULL,
    FOREIGN KEY(midia_id) REFERENCES midia(id_midia) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE metrica (
	id_metrica INT PRIMARY KEY AUTO_INCREMENT,
    metrica VARCHAR(100)
);

CREATE TABLE alimento_por_refeicao (
    id_alimento_refeicao INT AUTO_INCREMENT,
    refeicao_id INT,
    alimento_id INT,
    metrica_id INT,
    qtd_alimento INT NOT NULL,
    PRIMARY KEY (id_alimento_refeicao, refeicao_id, alimento_id),
    FOREIGN KEY (refeicao_id) REFERENCES refeicao(id_refeicao) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (alimento_id) REFERENCES alimento(id_alimento) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (metrica_id) REFERENCES metrica(id_metrica) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE assinatura (
    id_assinatura INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    valor FLOAT NOT NULL
);

CREATE TABLE pagamento (
	id_pagamento INT AUTO_INCREMENT,
    usuario_id INT,
    assinatura_id INT,
    data_pagamento DATE NOT NULL,
    tipo VARCHAR(100) CHECK (tipo IN ('Cartão de débito', 'Cartão de crédito', 'PIX')) NOT NULL, 
    PRIMARY KEY (id_pagamento, usuario_id, assinatura_id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id_usuario) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (assinatura_id) REFERENCES assinatura(id_assinatura) ON DELETE CASCADE ON UPDATE CASCADE
);

/*
CREATE TABLE cartao (
	id_cartao INT AUTO_INCREMENT,
    numero CHAR(16) NOT NULL,
    nome_titular VARCHAR(200) NOT NULL,
    validade DATE NOT NULL,
    cvv CHAR(3) NOT NULL,
    bandeira VARCHAR(100) NOT NULL,
    usuario_id INT,
    PRIMARY KEY (id_cartao, numero, nome_titular, cvv, bandeira, usuario_id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id_usuario) ON DELETE CASCADE ON UPDATE CASCADE
);
*/

CREATE TABLE denuncia (
	id_denuncia INT AUTO_INCREMENT,
    denunciado_id INT,
    vitima_id INT,
    titulo VARCHAR(75) NOT NULL,
    assunto VARCHAR(255) NOT NULL,
    PRIMARY KEY (id_denuncia, denunciado_id, vitima_id),
    FOREIGN KEY (denunciado_id) REFERENCES usuario(id_usuario) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (vitima_id) REFERENCES usuario(id_usuario) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE ficha (
    id_ficha INT AUTO_INCREMENT,
    usuario_id INT,
    peso FLOAT,
    altura FLOAT,
	problema_cardiaco TINYINT,
	dor_peito_atividade TINYINT,
	dor_peito_ultimo_mes TINYINT,
	problema_osseo_articular TINYINT,
	medicamento_pressao_coracao TINYINT,
	impedimento_atividade TINYINT,
	PRIMARY KEY (id_ficha, usuario_id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id_usuario) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE mural (
    id_mural INT AUTO_INCREMENT,
    dt_postagem DATETIME NOT NULL,
    usuario_id INT,
    midia_id INT,
    PRIMARY KEY (id_mural),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id_usuario) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (midia_id) REFERENCES midia(id_midia) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE lembrete (
	id_lembrete INT AUTO_INCREMENT,
    usuario_id INT,
    conteudo VARCHAR(500) NOT NULL,
    data_lembrete DATETIME,
    PRIMARY KEY (id_lembrete, usuario_id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id_usuario) ON DELETE CASCADE ON UPDATE CASCADE
);

-- ---------------------------------------------------------------------------
-- TRIGGERS
-- ---------------------------------------------------------------------------
DELIMITER //

CREATE TRIGGER cria_rotina
AFTER INSERT ON rotina_usuario
FOR EACH ROW
BEGIN
    DECLARE rotina_mensal_id INT;
    DECLARE rotina_semanal_id INT;
    DECLARE rotina_diaria_id INT;
    DECLARE num_semana INT;
    DECLARE dia INT;
    DECLARE padrao INT DEFAULT 1;

    INSERT INTO rotina_mensal (rotina_usuario_id, mes, ano, concluido)
    VALUES 
    (NEW.id_rotina_usuario, MONTH(CURDATE()), YEAR(CURDATE()), 0);

    SET rotina_mensal_id = LAST_INSERT_ID();

    SET num_semana = 1;
    WHILE num_semana <= 5 DO
        INSERT INTO rotina_semanal (rotina_mensal_id, num_semana, concluido)
        VALUES 
        (rotina_mensal_id, num_semana, 0);

        SET rotina_semanal_id = LAST_INSERT_ID();


        SET dia = 1;
        WHILE dia <= 7 DO
            INSERT INTO rotina_diaria (rotina_semanal_id, dia, concluido)
            VALUES 
            (rotina_semanal_id, dia, 0);

            SET rotina_diaria_id = LAST_INSERT_ID();

			CASE NEW.meta_id
				WHEN 1 THEN
					INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo)
					VALUES
					(1, rotina_diaria_id, 0, 15, 3, '00:01:00'),
					(2, rotina_diaria_id, 0, 12, 3, '00:02:00'),
					(3, rotina_diaria_id, 0, 10, 3, '00:01:30'),
					(4, rotina_diaria_id, 0, 20, 3, '00:01:00'),
					(5, rotina_diaria_id, 0, 8, 3, '00:02:00'),
					(6, rotina_diaria_id, 0, 15, 3, '00:01:30'),
					(7, rotina_diaria_id, 0, 12, 3, '00:01:00');
                WHEN 2 THEN
					INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo)
					VALUES
					(8, rotina_diaria_id, 0, 15, 3, '00:01:00'),
					(9, rotina_diaria_id, 0, 12, 3, '00:02:00'),
					(10, rotina_diaria_id, 0, 10, 3, '00:01:30'),
					(11, rotina_diaria_id, 0, 20, 3, '00:01:00'),
					(12, rotina_diaria_id, 0, 8, 3, '00:02:00'),
					(13, rotina_diaria_id, 0, 15, 3, '00:01:30'),
					(14, rotina_diaria_id, 0, 12, 3, '00:01:00');
            END CASE;

            CASE NEW.meta_id
				WHEN 1 THEN
					CASE padrao
						WHEN 1 THEN
							INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
							(rotina_diaria_id, 1, 0),
							(rotina_diaria_id, 2, 0),
							(rotina_diaria_id, 3, 0);
						WHEN 2 THEN
							INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
							(rotina_diaria_id, 1, 0),
							(rotina_diaria_id, 2, 0),
							(rotina_diaria_id, 4, 0);
						WHEN 3 THEN
							INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
							(rotina_diaria_id, 1, 0),
							(rotina_diaria_id, 2, 0),
							(rotina_diaria_id, 5, 0);
						WHEN 4 THEN
							INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
							(rotina_diaria_id, 2, 0),
							(rotina_diaria_id, 4, 0),
							(rotina_diaria_id, 5, 0);
						WHEN 5 THEN
							INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
							(rotina_diaria_id, 5, 0),
							(rotina_diaria_id, 4, 0),
							(rotina_diaria_id, 3, 0);
					END CASE;
                WHEN 2 THEN
					CASE padrao
						WHEN 1 THEN
							INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
							(rotina_diaria_id, 5, 0),
							(rotina_diaria_id, 2, 0),
							(rotina_diaria_id, 3, 0);
						WHEN 2 THEN
							INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
							(rotina_diaria_id, 3, 0),
							(rotina_diaria_id, 2, 0),
							(rotina_diaria_id, 4, 0);
						WHEN 3 THEN
							INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
							(rotina_diaria_id, 5, 0),
							(rotina_diaria_id, 2, 0),
							(rotina_diaria_id, 5, 0);
						WHEN 4 THEN
							INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
							(rotina_diaria_id, 2, 0),
							(rotina_diaria_id, 4, 0),
							(rotina_diaria_id, 5, 0);
						WHEN 5 THEN
							INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
							(rotina_diaria_id, 5, 0),
							(rotina_diaria_id, 4, 0),
							(rotina_diaria_id, 3, 0);
					END CASE;
            END CASE;

            SET padrao = padrao + 1;
            IF padrao > 3 THEN
                SET padrao = 1;
            END IF;

            SET dia = dia + 1;
        END WHILE;

        SET num_semana = num_semana + 1;
    END WHILE;

END //

DELIMITER ;