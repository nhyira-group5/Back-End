
CREATE DATABASE IF NOT EXISTS vitalisDB;

USE vitalisDB;

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

CREATE TABLE especialidade (
    id_especialidade INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL
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
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(550)
);

CREATE TABLE tag_exercicio (
	id_tag_exercicio INT AUTO_INCREMENT,
    tag_id INT,
    exercicio_id INT,
    PRIMARY KEY (id_tag_exercicio, tag_id, exercicio_id),
    FOREIGN KEY (tag_id) REFERENCES tag(id_tag) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (exercicio_id) REFERENCES exercicio(id_exercicio) ON DELETE CASCADE ON UPDATE CASCADE
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
    nome VARCHAR(100) NOT NULL,
    preparo VARCHAR(5001)
);

CREATE TABLE alimento (
    id_alimento INT PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(100) NOT NULL,
    carboidrato FLOAT NOT NULL,
    proteina FLOAT NOT NULL,
    gordura FLOAT NOT NULL
);

CREATE TABLE midia (
    id_midia INT AUTO_INCREMENT,
    exercicio_id INT,
    alimento_id INT,
    refeicao_id INT,
    nome VARCHAR(200) NOT NULL,
    caminho VARCHAR(1000) NOT NULL,
    extensao VARCHAR(10) NOT NULL,
    tipo VARCHAR(20) NOT NULL CHECK (tipo IN ('Imagem', 'Video')),
    PRIMARY KEY (id_midia),
    FOREIGN KEY (exercicio_id) REFERENCES exercicio(id_exercicio) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (alimento_id) REFERENCES alimento(id_alimento) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (refeicao_id) REFERENCES refeicao(id_refeicao) ON DELETE CASCADE ON UPDATE CASCADE
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
    pontos INT,
    personal_id INT,
    midia_id INT,
    endereco_id INT,
    FOREIGN KEY (endereco_id) REFERENCES endereco(id_endereco) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (midia_id) REFERENCES midia(id_midia) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (personal_id) REFERENCES usuario(id_usuario) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE especialidade_por_personal (
    id_especialidade_personal INT AUTO_INCREMENT,
	personal_id INT,
    especialidade_id INT,
    PRIMARY KEY (id_especialidade_personal, personal_id, especialidade_id),
    FOREIGN KEY (personal_id) REFERENCES usuario(id_usuario) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (especialidade_id) REFERENCES especialidade(id_especialidade) ON DELETE CASCADE ON UPDATE CASCADE
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

CREATE TABLE rotina_usuario (
    id_rotina_usuario INT AUTO_INCREMENT,
    usuario_id INT,
    meta_id INT,
    rotina_alternativa TINYINT NOT NULL,
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

CREATE TABLE lembrete (
	id_lembrete INT AUTO_INCREMENT,
    usuario_id INT,
    conteudo VARCHAR(500) NOT NULL,
    data_lembrete DATETIME,
    PRIMARY KEY (id_lembrete, usuario_id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id_usuario) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE mural (
    id_mural INT AUTO_INCREMENT,
    dt_postagem DATETIME NOT NULL,
    usuario_id INT,
    midia_id INT,
    PRIMARY KEY (id_mural, usuario_id, midia_id),
    FOREIGN KEY (usuario_id) REFERENCES usuario(id_usuario) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (midia_id) REFERENCES midia(id_midia) ON DELETE CASCADE ON UPDATE CASCADE
);



-- ---------------------------------------------------------------------------
-- TRIGGERS & PROCEDURES
-- ---------------------------------------------------------------------------
DELIMITER //
-- ROTINA PADRÂO PÓS CRIAÇÂO DO PERFIL
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
	-- Cria a rotina mensal do usuário
    INSERT INTO rotina_mensal (rotina_usuario_id, mes, ano, concluido)
    VALUES
    (NEW.id_rotina_usuario, MONTH(CURDATE()), YEAR(CURDATE()), 0);
    SET rotina_mensal_id = LAST_INSERT_ID();
	-- Cria as rotinas semanais do usuário para a rotina mensal
    SET num_semana = 1;
    WHILE num_semana <= 5 DO
        INSERT INTO rotina_semanal (rotina_mensal_id, num_semana, concluido)
        VALUES
        (rotina_mensal_id, num_semana, 0);
        SET rotina_semanal_id = LAST_INSERT_ID();
        -- Para cada semana, criar sua rotina por dia
        SET dia = 1;
        WHILE dia <= 7 DO
            INSERT INTO rotina_diaria (rotina_semanal_id, dia, concluido)
            VALUES
            (rotina_semanal_id, dia, 0);
            SET rotina_diaria_id = LAST_INSERT_ID();

            -- Caso a caso a meta seja X... Monte o treino para a rotina diária
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
			-- Caso a caso a meta seja X... Monte as refeições para a rotina diária para cada dia
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

            -- Padrão serve para variar as rotinas diárias
            SET padrao = padrao + 1;
            IF padrao > 3 THEN
                SET padrao = 1;
            END IF;
            -- Para cada repetição, aumenta o dia
            SET dia = dia + 1;
        END WHILE;

        -- Para cada repetição, aumenta a semana
        SET num_semana = num_semana + 1;
    END WHILE;
END //
DELIMITER ;
-- ----------------------------------------- --




USE vitalisDB;

DELETE FROM lembrete WHERE id_lembrete > 0;
DELETE FROM mural WHERE id_mural > 0;
DELETE FROM ficha WHERE id_ficha > 0;
DELETE FROM denuncia WHERE id_denuncia > 0;
DELETE FROM pagamento WHERE id_pagamento > 0;
DELETE FROM assinatura WHERE id_assinatura > 0;
DELETE FROM alimento_por_refeicao WHERE id_alimento_refeicao > 0;
DELETE FROM alimento WHERE id_alimento > 0;
DELETE FROM refeicao_por_dieta WHERE id_refeicao_dieta > 0;
DELETE FROM refeicao_diaria WHERE id_refeicao_diaria > 0;
DELETE FROM refeicao WHERE id_refeicao > 0;
DELETE FROM dieta WHERE id_dieta > 0;
DELETE FROM treino WHERE id_treino > 0;
DELETE FROM rotina_diaria WHERE id_rotina_diaria > 0;
DELETE FROM tag_exercicio WHERE id_tag_exercicio > 0;
DELETE FROM exercicio WHERE id_exercicio > 0;
DELETE FROM tag WHERE id_tag > 0;
DELETE FROM rotina_semanal WHERE id_rotina_semanal > 0;
DELETE FROM rotina_mensal WHERE id_rotina_mensal > 0;
DELETE FROM rotina_usuario WHERE id_rotina_usuario > 0;
DELETE FROM especialidade_por_personal WHERE id_especialidade_personal > 0;
DELETE FROM especialidade_por_meta WHERE id_especialidade_meta > 0;
DELETE FROM especialidade WHERE id_especialidade > 0;
DELETE FROM mensagem WHERE id_mensagem > 0;
DELETE FROM chat WHERE id_chat > 0;
DELETE FROM endereco WHERE id_endereco > 0;
DELETE FROM contrato WHERE id_contrato > 0;
DELETE FROM usuario WHERE id_usuario > 0;
DELETE FROM meta WHERE id_meta > 0;
DELETE FROM midia WHERE id_midia > 0;

SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE lembrete;
TRUNCATE TABLE mural;
TRUNCATE TABLE ficha;
TRUNCATE TABLE denuncia;
TRUNCATE TABLE pagamento;
TRUNCATE TABLE assinatura;
TRUNCATE TABLE alimento_por_refeicao;
TRUNCATE TABLE alimento;
TRUNCATE TABLE refeicao_por_dieta;
TRUNCATE TABLE refeicao_diaria;
TRUNCATE TABLE refeicao;
TRUNCATE TABLE dieta;
TRUNCATE TABLE treino;
TRUNCATE TABLE rotina_diaria;
TRUNCATE TABLE tag_exercicio;
TRUNCATE TABLE exercicio;
TRUNCATE TABLE tag;
TRUNCATE TABLE rotina_semanal;
TRUNCATE TABLE rotina_mensal;
TRUNCATE TABLE rotina_usuario;
TRUNCATE TABLE especialidade_por_personal;
TRUNCATE TABLE especialidade_por_meta;
TRUNCATE TABLE especialidade;
TRUNCATE TABLE mensagem;
TRUNCATE TABLE chat;
TRUNCATE TABLE endereco;
TRUNCATE TABLE contrato;
TRUNCATE TABLE usuario;
TRUNCATE TABLE meta;
TRUNCATE TABLE midia;
SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO especialidade (nome) VALUES
('Emagrecimento'),
('Alta Intensidade (HIIT)'),
('Força e Resistência'),
('Flexibilidade e Mobilidade'),
('Resistência Cardiovascular'),
('Peso Corporal'),
('Alongamento e Relaxamento');

INSERT INTO meta (nome) VALUES
('Emagrecimento'),
('Ganho de Massa Muscular'),
('Flexibilidade');
-- ('Melhoria da Resistência Cardiovascular');

-- Inserção de correspondências entre especialidades e metas
INSERT INTO especialidade_por_meta (especialidade_id, meta_id) VALUES
(1, 1),   -- Emagrecimento -> Emagrecimento
(1, 3),   -- Emagrecimento -> Flexibilidade
(2, 1),   -- Alta Intensidade (HIIT)
(2, 3),   -- Alta Intensidade (HIIT)
(3, 2),   -- Força e Resistência
(3, 3),   -- Força e Resistência
(4, 1),   -- Flexibilidade e Mobilidade
(4, 2),   -- Flexibilidade e Mobilidade
(4, 3),   -- Flexibilidade e Mobilidade
(6, 1),   -- Peso Corporal
(6, 2),   -- Peso Corporal
(6, 3),   -- Peso Corporal
(7, 1),   -- Alongamento e Relaxamento
(7, 2),   -- Alongamento e Relaxamento
(7, 3);   -- Alongamento e Relaxamento

INSERT INTO metrica (metrica) VALUES
('gramas'), -- 1
('quilogramas'), -- 2
('miligramas'), -- 3
('litros'), -- 4
('mililitros'), -- 5
('unidade'), -- 6
('xícaras'), -- 7
('colheres de sopa'), -- 8
('colheres de chá'), -- 9
('scoop'); -- 10

INSERT INTO refeicao (nome, preparo) VALUES
('Virada Paulista', 'Cozinhe o arroz e o feijão em panelas separadas, temperando a gosto. Grelhe a carne de porco e a linguiça até dourarem bem. Refogue a couve em alho e azeite até murchar. Para o torresmo, corte a barriga de porco em pedaços pequenos e frite até ficar crocante. Frite o ovo em óleo ou manteiga até a gema firmar. Prepare a farofa dourando a farinha em manteiga com cebola. Sirva todos os ingredientes no prato, organizados de forma que cada componente complemente o outro.'), -- 1
('Salada de Frango Grelhado', 'Tempere o peito de frango com sal, pimenta e ervas a gosto. Grelhe-o em fogo médio até estar cozido por dentro e dourado por fora, cerca de 6-8 minutos de cada lado. Enquanto o frango cozinha, prepare a salada misturando folhas verdes (alface, rúcula, espinafre) e legumes grelhados (como abobrinha e pimentão). Corte o frango em tiras e disponha sobre a salada. Finalize com um molho feito com iogurte natural desnatado, suco de limão, sal e ervas, e sirva.'), -- 2
('Salmão com Quinoa', 'Tempere o filé de salmão com sal, pimenta e ervas como endro ou tomilho. Asse em forno pré-aquecido a 180°C por cerca de 15-20 minutos, ou até que esteja cozido e suculento. Enquanto isso, cozinhe a quinoa em água com uma pitada de sal até que os grãos estejam macios e a água tenha sido absorvida. Corte o abacate em fatias finas e prepare uma omelete de claras levemente temperada. Sirva o salmão sobre a quinoa, acompanhado do abacate e da omelete, criando uma refeição equilibrada e nutritiva.'), -- 3
('Frango e batata doce', 'Tempere o frango com alho, limão, sal e pimenta, e asse em forno a 200°C por 25-30 minutos, ou até dourar e ficar suculento. Asse a batata doce com casca em forno médio até que esteja macia, então corte em rodelas. Cozinhe o arroz branco em água com sal até ficar macio e o feijão preto em uma panela de pressão até que esteja cremoso e bem temperado. No prato, disponha o frango assado, as rodelas de batata doce, uma porção de arroz branco e o feijão preto, criando uma refeição completa e rica em proteínas e carboidratos.'), -- 4
('Carne com aveia de flocos', 'Tempere a carne vermelha com sal, pimenta e alho. Grelhe em fogo alto até alcançar o ponto desejado, selando bem ambos os lados. Em uma panela, cozinhe a aveia em flocos com água e uma pitada de sal, mexendo até obter uma consistência cremosa. Cozinhe o ovo até que a gema esteja firme, cerca de 8-10 minutos, e toste levemente o amendoim em uma frigideira. No prato, sirva a carne grelhada ao lado da aveia cremosa, o ovo cozido e finalize com o amendoim tostado, garantindo uma refeição nutritiva e satisfatória.'), -- 5
('Omelete de legumes', 'Bata os ovos com um garfo, adicione sal e pimenta a gosto. Refogue os legumes em uma frigideira antiaderente com um pouco de azeite. Despeje os ovos batidos na frigideira e cozinhe até dourar. Sirva com queijo branco ralado. Utilize ervas frescas como cebolinha ou salsa para dar um toque especial.'), -- 6;
('Frango com Legumes Salteados', 'Cozinhe o peito de frango em uma panela com água e sal até ficar macio. Desfie o frango. Em uma frigideira, refogue os legumes com um pouco de azeite, sal e pimenta. Adicione o frango desfiado e misture bem. Utilize um mix de temperos para dar mais sabor aos legumes.'), -- 7;
('Iogurte Grego com Frutas e Granola', 'Em um pote, coloque o iogurte grego, as frutas vermelhas picadas e a granola. Misture bem. Adicione sementes de chia para aumentar o teor de fibras.'), -- 8;
('Frango com Arroz Integral e Salada', 'Cozinhe o arroz integral. Grelhe o peito de frango temperado com sal, pimenta e azeite. Prepare uma salada com folhas verdes, tomate, pepino e tempero a gosto. Sirva o frango sobre o arroz e acompanhe com a salada.'), -- 9;
('Lentilha com Legumes e Pão Integral', 'Cozinhe a lentilha com os legumes em água e sal. Sirva com pão integral. Tempere a lentilha com cominho e páprica doce.'), -- 10;
('Aveia com Frutas e Castanhas', 'Cozinhe a aveia em leite ou água. Adicione as frutas picadas e as castanhas. Utilize frutas da estação.'), -- 11;
('Macarrão Integral com Molho de Tomate e Frango', 'Cozinhe o macarrão integral. Prepare o molho de tomate com cebola, alho, tomate picado e temperos a gosto. Grelhe o frango e adicione ao molho. Utilize ervas frescas como manjericão para dar um toque especial ao molho.'), -- 12;
('Peixe Assado com Batatas Assadas e Espinafre', 'Tempere o peixe com limão, sal e pimenta. Asse o peixe e as batatas em forno preaquecido. Cozinhe o espinafre no vapor. Regue o peixe com azeite e limão antes de servir.'), -- 13;
('Tapioca Recheada', 'Prepare a tapioca em uma frigideira antiaderente. Recheie com queijo, como o do tipo cottage, e peito de peru. Utilize outros recheios como frango desfiado ou legumes salteados.'), -- 14;
('Strogonoff de frango com legumes e macarrão integral', 'Prepare o strogonoff com frango desfiado, legumes e creme de leite light. Sirva com macarrão integral. Utilize ervas frescas como cebolinha ou salsa para dar um toque especial.'), -- 15;
('Carne Magra com Purê de Batata Doce e Brócolis', 'Assar a carne magra no forno. Cozinhe a batata doce e amasse com um garfo. Cozinhe o brócolis no vapor. Tempere a carne com alho, alecrim e azeite.'), -- 16;
('Pão Integral com Ovo Mexido e Abacate', 'Prepare o ovo mexido. Amasse o abacate e tempere com sal e limão. Sirva com o pão integral. Utilize outros tipos de pão, como pão de forma integral ou torrada.'), -- 17;
('Lentilhas com legumes e arroz integral', 'Cozinhe a lentilha e os legumes. Sirva com arroz integral. Utilize legumes coloridos para deixar mais nutritiva e saborosa.'), -- 18;
('Overnight Oats com frutas vermelhas', 'Misture todos os ingredientes em um pote e leve à geladeira por pelo menos 2 horas.'), -- 19;
('Feijoada Light com Acompanhamentos', 'Prepare a feijoada com cortes magros de carne. Sirva com couve refogada e laranja. Utilize temperos como louro, alho e pimenta do reino.'), -- 20;
('Wraps de frango com salada', 'Tortilla de trigo integral, frango grelhado, salada, legumes. Utilize legumes coloridos como pimentão, cenoura e brócolis.'), -- 21;
('Risoto de Abóbora com Frango Desfiado', 'Refogue a cebola e o alho, adicione o arroz e o caldo de legumes. Cozinhe até o arroz estar macio. Adicione a abóbora e o frango desfiado.'), -- 22;
('Salada Caprese com Pão Integral', 'Corte o tomate, a mussarela e o manjericão em rodelas. Monte a salada e sirva com pão integral. Utilize um azeite extra virgem de boa qualidade para temperar a salada.'), -- 23;
('Omelete de Claras', 'Bata as claras e cozinhe com espinafre em uma frigideira antiaderente.'), -- 24
('Smoothie Verde', 'Bata todos os ingredientes no liquidificador até ficar homogêneo.'), -- 25
('Panqueca de Banana', 'Bata todos os ingredientes e cozinhe em uma frigideira antiaderente.'), -- 26
('Salada de Atum', 'Misture o atum com a alface e o tomate picado.'), -- 27
('Salada com Quinoa', 'Cozinhe a quinoa e misture com os legumes cozidos.'), -- 28
('Smoothie Protéico', 'No liquidificador, adicione 1 scoop de whey protein, 1 banana congelada, 1 colher de sopa de manteiga de amendoim, 200 ml de leite ou água, e 1 colher de chá de mel ou adoçante a gosto. Bata todos os ingredientes até obter uma mistura homogênea. Sirva imediatamente.'), -- 29
('Carne Moída com Batata Doce', 'Descasque e corte 2 batatas doces em cubos e cozinhe em água com uma pitada de sal até ficarem macias. Enquanto isso, aqueça uma frigideira com um fio de azeite e refogue 1 cebola picada até dourar. Adicione 400g de carne moída e cozinhe até ficar bem dourada. Tempere com sal, pimenta, alho em pó e ervas a gosto. Junte as batatas doces cozidas à carne, misture bem e sirva.'), -- 30
('Bife com Salada', 'Tempere 2 bifes de sua preferência (como alcatra ou contrafilé) com sal, pimenta e alho a gosto. Aqueça uma frigideira com um fio de azeite em fogo alto e grelhe os bifes por 3 a 4 minutos de cada lado, ou até atingir o ponto desejado. Para a salada, misture folhas verdes, tomate, pepino fatiado e cebola roxa em rodelas. Tempere com azeite, suco de limão (se quiser), sal e pimenta a gosto. Sirva o bife acompanhado da salada fresca.'), -- 31
('Tilápia com Legumes', 'Tempere 2 filés de tilápia com sal, pimenta e suco de limão. Aqueça uma frigideira antiaderente com um fio de azeite em fogo médio e grelhe os filés por 3 a 4 minutos de cada lado, até que fiquem cozidos por completo. Enquanto isso, corte em pedaços pequenos 1 cenoura, 1 abobrinha e 1 pimentão. Refogue os legumes em uma frigideira separada com azeite, sal e pimenta até ficarem al dente. Sirva a tilápia acompanhada dos legumes refogados.'); -- 32

-- Inserção de alimentos da virada paulista
INSERT INTO alimento (nome, carboidrato, proteina, gordura) VALUES
('Arroz Branco', 45.0, 4.0, 0.5), -- 1
('Feijão Carioca', 30.0, 7.0, 1.5), -- 2
('Carne de Porco', 0.0, 20.0, 5.0), -- 3
('Linguiça', 1.0, 18.0, 10.0), -- 4
('Couve', 5.0, 2.0, 0.5), -- 5
('Torresmo', 2.0, 5.0, 10.0), -- 6
('Ovo', 1.0, 7.0, 5.0),	 -- 7					-- Vai ser usado também na refeição Carne com aveia de flocos"
('Farofa', 20.0, 2.0, 10.0); -- 8

-- Inserção de alimentos da Salada de Frango Grelhado
INSERT INTO alimento (nome, carboidrato, proteina, gordura) VALUES
('Salada de Folhas Verdes', 2.0, 1.0, 0.2), -- 9
('Frango', 0.0, 30.0, 3.5),	-- 10				-- Vai ser usado também na refeição "Frango e batata doce"
('Legumes', 10.0, 2.0, 0.5), -- 11
('Iogurte Natural', 4.0, 8.0, 0.2); -- 12

-- Inserção de alimentos de Salmão com Quinoa
INSERT INTO alimento (nome, carboidrato, proteina, gordura) VALUES
('Salmão', 0.0, 25.0, 12.0), -- 13
('Quinoa', 20.0, 4.0, 1.5), -- 14
('Abacate', 1.0, 2.0, 14.0), -- 15
('Omelete de Claras', 1.0, 15.0, 3.0); -- 16

-- Inserção de alimentos de Frango e batata doce
INSERT INTO alimento (nome, carboidrato, proteina, gordura) VALUES
('Batata Doce', 30.0, 2.0, 0.5), -- 17
('Feijão Preto', 30.0, 7.0, 1.5); -- 18

-- Inserção de alimentos de Carne com aveia de flocos
INSERT INTO alimento (nome, carboidrato, proteina, gordura) VALUES
('Carne de vaca', 0.0, 25.0, 10.0), -- 19
('Aveia em Flocos', 66.0, 16.9, 7.0), -- 20
('Amendoim', 16.0, 26.0, 49.0); -- 21

-- Omelete com Legumes
INSERT INTO alimento (nome, carboidrato, proteina, gordura) VALUES
('Brócolis', 31.5, 12.6, 1.8), -- 22
('Queijo Branco', 3.2, 17.4, 15.2), -- 23
('Espinafre', 3.6, 2.9, 0.4); -- 24

-- Iogurte Grego com Frutas e Granola
INSERT INTO alimento (nome, carboidrato, proteina, gordura) VALUES
('Iogurte Grego', 3.6, 8.7, 10.0), -- 25
('Frutas Vermelhas', 10.0, 1.0, 0.3), -- 26
('Granola', 64.0, 10.0, 10.0); -- 27

-- Frango com Arroz Integral e Salada
INSERT INTO alimento (nome, carboidrato, proteina, gordura) VALUES
('Arroz Integral', 23.0, 2.7, 10.0); -- 28

-- Lentilha com Legumes e Pão Integral
INSERT INTO alimento (nome, carboidrato, proteina, gordura) VALUES
('Lentilha', 20.0, 9.0, 0.4), -- 29
('Pão Integral', 43.0, 8.0, 4.0); -- 30

-- Aveia com Frutas e Castanhas
INSERT INTO alimento (nome, carboidrato, proteina, gordura) VALUES
('Frutas', 14.0, 0.7, 0.2), -- 31
('Castanha', 15.0, 5.0, 13.0); -- 32

-- Macarrão Integral com Molho de Tomate e Frango
INSERT INTO alimento (nome, carboidrato, proteina, gordura) VALUES
('Macarrão Integral', 25.0, 5.0, 1.5), -- 33
('Molho de tomate', 8.0, 1.0, 0.5); -- 34

-- Tapioca Recheada
INSERT INTO alimento (nome, carboidrato, proteina, gordura) VALUES
('Queijo Cottage', 3.4, 11.0, 4.3), -- 35
('Peito de Peru', 0.0, 3.0, 1.0); -- 36

-- Overnight Oats com frutas vermelhas
INSERT INTO alimento (nome, carboidrato, proteina, gordura) VALUES
('Semente de Chia', 42.0, 17.0, 31); -- 37

INSERT INTO alimento (nome, carboidrato, proteina, gordura) VALUES
('Laranja', 11.0, 0.9, 1.0); -- 38

-- Wraps de frango com salada
INSERT INTO alimento (nome, carboidrato, proteina, gordura) VALUES
('Tortilha de Trigo Integral', 45.0, 7.0, 3.0); -- 39

INSERT INTO alimento (nome, carboidrato, proteina, gordura) VALUES
('Abóbora', 7.0, 1.0, 0.1); -- 40

-- Salada Caprese com Pão Integral
INSERT INTO alimento (nome, carboidrato, proteina, gordura) VALUES
('Tomate', 3.9, 0.9, 0.2), -- 41
('Manjericão', 2.7, 3.2, 0.6), -- 42
('Queijo Mussarela', 2.2, 22.0, 22.0); -- 43

INSERT INTO alimento (nome, carboidrato, proteina, gordura) VALUES
('Tapioca', 88.0, 0.3, 0.2); -- 44

-- Smoothie Verde
INSERT INTO alimento (nome, carboidrato, proteina, gordura) VALUES
('Banana', 3.9, 0.9, 0.2), -- 45
('Água de coco', 23.0, 1.1, 0.3); -- 46

INSERT INTO alimento (nome, carboidrato, proteina, gordura) VALUES
('Lata de Atum', 0.0, 25.0, 1.0); -- 47

INSERT INTO alimento (nome, carboidrato, proteina, gordura) VALUES
('Tilápia', 0.0, 26.0, 3.0), -- 48
('Whey', 5.0, 25.0, 1.0), -- 49
('Cenoura', 5.9, 0.55, 0.2), -- 50
('Cebola Roxa', 9.3, 1.1, 0.1), -- 51
('Pepino', 11.1, 1.4, 0.3), -- 52
('Carne Moída', 0.0, 20.0, 20.0), -- 53
('Leite', 4.7, 3.2, 3.5), -- 54
('Mel', 82.0, 0.3, 0.0), -- 55
('Manteiga de Amendoim', 20.0, 25.0, 50.0); -- 56

-- Inserção de alimentos por refeição
INSERT INTO alimento_por_refeicao (refeicao_id, alimento_id, metrica_id, qtd_alimento) VALUES
(1, 1, 1, 75),    -- Virada Paulista: Arroz Branco - 1 unidade
(1, 2, 1, 100),    -- Virada Paulista: Feijão Carioca - 1 unidade
(1, 3, 1, 150),  -- Virada Paulista: Carne de Porco - 150 gramas
(1, 4, 1, 75),    -- Virada Paulista: Linguiça - 1 unidade
(1, 5, 1, 100),  -- Virada Paulista: Couve - 100 gramas
(1, 6, 1, 50),  -- Virada Paulista: Torresmo - 100 gramas
(1, 7, 6, 1),    -- Virada Paulista: Ovo - 1 unidade
(1, 8, 1, 50),   -- Virada Paulista: Farofa - 50 gramas

(2, 9, 1, 100),  -- Salada de Frango Grelhado: Salada de Folhas Verdes - 100 gramas
(2, 10, 1, 150), -- Salada de Frango Grelhado: Frango - 150 gramas
(2, 11, 1, 100), -- Salada de Frango Grelhado: Legumes - 100 gramas
(2, 12, 1, 150), -- Salada de Frango Grelhado: Iogurte Natural - 150 gramas

(3, 13, 1, 150), -- Salmão com Quinoa: Salmão Assado - 150 gramas
(3, 14, 1, 100), -- Salmão com Quinoa: Quinoa - 100 gramas
(3, 15, 6, 1),   -- Salmão com Quinoa: Abacate - 1 unidade
(3, 16, 6, 1),   -- Salmão com Quinoa: Omelete de Claras - 1 unidade

(4, 10, 1, 200), -- Frango e batata doce: Frango - 200 gramas
(4, 17, 1, 200), -- Frango e batata doce: Batata Doce - 200 gramas
(4, 2, 1, 120),    -- Frango e batata doce: Feijão Preto - 1 unidade

(5, 18, 1, 200), -- Carne com aveia de flocos: Carne de vaca - 200 gramas
(5, 20, 1, 50),   -- Carne com aveia de flocos: Aveia em Flocos - 50 gramas
(5, 8, 1, 30),   -- Carne com aveia de flocos: Amendoim - 30 gramas

-- Omelete de legumes
(6, 7, 1, 2), -- ovo
(6, 22, 7, 1), -- brocolis
(6, 23, 1, 100), -- queijo branco

-- Frango com Legumes Salteados
(7, 10, 1, 150), -- Frango
(7, 22, 1, 100), -- brocolis
(7, 11, 1, 100), -- legumes

-- Iogurte Grego com Frutas e Granola
(8, 25, 1, 150), -- iogurte grego
(8, 26, 1, 100), -- frutas vermelhas
(8, 27, 1, 30), -- granola

-- Frango com Arroz Integral e Salada
(9, 10, 1, 150), -- frango
(9, 28, 1, 100), -- arroz integral
(9, 9, 1, 150), -- salada pae

-- Lentilha com Legumes e Pão Integral
(10, 11, 1, 100), -- legumes
(10, 29, 1, 100), -- lentilha
(10, 30, 1, 100), -- pao integral

-- Aveia com Frutas e Castanhas
(11, 7, 1, 50), -- Aveia em flocos
(11, 31, 1, 100), -- frutas
(11, 32, 1, 30), -- castanhas

-- Macarrão Integral com Molho de Tomate e Frango
(12, 10, 1, 100), -- Frango
(12, 33, 1, 100), -- Macarrão Integral
(12, 34, 1, 150), -- Molho de tomate

-- Peixe Assado com Batatas Assadas e Espinafre
(13, 13, 1, 150), -- peixe
(13, 17, 1, 150), -- batata doce
(13, 24, 1, 100), -- espinafre

-- Tapioca Recheada
(14, 44, 6, 1), -- Tapioca
(14, 35, 1, 50), -- Queijo Cottage
(14, 36, 1, 30), -- Peito de Peru

-- Strogonoff de frango com legumes e macarrão integral
(15, 10, 1, 150), -- frango
(15, 11, 1, 170), -- legumes
(15, 33, 1, 100), -- macarrao integral

-- Carne Magra com Purê de Batata Doce e Brócolis
(16, 18, 1, 150), -- carne vermelha
(16, 17, 1, 150), -- batata doce
(16, 22, 1, 100), -- Brocolis

-- Pão Integral com Ovo Mexido e Abacate
(17, 30, 1, 50), -- Pao integral
(17, 7, 6, 1), -- ovo
(17, 15, 1, 50), -- abacate

-- Lentilhas com legumes e arroz integral
(18, 29, 1, 100), -- lentilha
(18, 22, 1, 50), -- Brocolis
(18, 11, 1, 50), -- legumes
(18, 28, 1, 100), -- arroz integral

-- Overnight Oats com frutas vermelhas
(19, 7, 1, 50), -- Aveia em flocos
(19, 12, 1, 150), -- Iogurte natural
(19, 26, 1, 100), -- frutas vermelhas
(19, 37, 8, 1), -- chia

-- Feijoada Light com Acompanhamentos
(20, 18, 1, 10), -- feijão preto
(20, 3, 1, 200), -- carne de porco
(20, 38, 6, 1), -- laranja
(20, 5, 1, 100), -- couve

-- Wraps de frango com salada
(21, 39, 6, 1), -- tortilha
(21, 10, 1, 100), -- frango
(21, 9, 1, 150), -- salada
(21, 11, 1, 150), -- Legumes

-- Risoto de Abóbora com Frango Desfiado
(22, 28, 1, 100), -- arroz integral
(22, 40, 1, 150), -- abobora
(22, 10, 1, 100), -- frango

-- Salada Caprese com Pão Integral
(23, 30, 6, 1), -- pao integral
(23, 41, 1, 100), -- tomate
(23, 42, 1, 30), -- manjericão
(23, 43, 1, 110), -- mussarela

-- Omelete de Claras
(24, 7, 6, 3), -- ovo
(24, 24, 7, 1), -- espinafre

-- Smoothie Verde
(25, 45, 6, 1), -- banana
(25, 24, 7, 1), -- espinafre
(25, 46, 5, 200), -- agua de coco

-- Panqueca de Banana
(26, 45, 6, 1), -- banana
(26, 20, 7, 2), -- aveia
(26, 7, 6, 1), -- ovo

-- Salada de atum
(27, 46, 6, 1), -- lata de atum
(27, 9, 7, 2), -- salada
(27, 41, 6, 1), -- tomate

-- Salada com Quinoa
(28, 14, 7, 2), -- quinoa
(28, 11, 7, 1), -- legumes

-- Smoothie Protéico
(29, 49, 10, 1), -- Whey
(29, 45, 6, 1), -- Banana
(29, 56, 8, 1), -- Manteiga de amendoim
(29, 54, 5, 200), -- Leite
(29, 55, 9, 1), -- mel

-- Carne Moída com Batata Doce
(30, 53, 1, 400), -- Carne Moida
(30, 50, 1, 2), -- Cenoura
(30, 17, 6, 2), -- Batata Doce
(30, 51, 6, 1), -- Cebola Roxa

-- Bife com Salada
(31, 18, 1, 250), -- Carne Vermelha
(31, 9, 1, 120), -- salada de folhas verdes
(31, 52, 1, 50), -- pepino
(31, 51, 1, 50), -- cebola roxa

-- Tilápia com Legumes
(32, 48, 1, 225), -- Tilápia
(32, 50, 6, 1), -- Cenoura
(32, 11, 1, 125); -- Legumes

INSERT INTO dieta(nome, descricao, meta_id)
VALUES
('Emagrecimento', 'Uma dieta de emagrecimento bem-sucedida deve ser equilibrada, sustentável e personalizada para atender às necessidades individuais. O objetivo principal é criar um déficit calórico, onde a quantidade de calorias ingeridas é menor do que a quantidade de calorias gastas.', 1),
('Ganho de massa', 'Uma dieta para ganho de massa muscular precisa fornecer um excedente calórico, ou seja, consumir mais calorias do que se gasta, junto com uma quantidade adequada de proteínas, carboidratos e gorduras saudáveis para promover a construção muscular. Além disso, a alimentação deve ser distribuída ao longo do dia para manter um fornecimento constante de nutrientes', 2),
('Flexível', 'A dieta flexível é uma abordagem versátil e personalizada para alcançar seus objetivos de saúde e fitness, seja o emagrecimento ou o ganho de massa muscular. Baseada no conceito de "IIFYM" (If It Fits Your Macros), essa dieta permite que você coma uma variedade de alimentos, desde que se enquadrem nas suas metas diárias de macronutrientes: proteínas, carboidratos e gorduras.', 3);


-- INSERIR AS DEMAIS REFEIÇÕES
INSERT INTO refeicao_por_dieta (refeicao_id, dieta_id)
VALUES
(2, 1),
(3, 1),
(4, 1),
(5, 1),
(6, 1),
(7, 1),
(8, 1),
(9, 1),
(10, 1),
(11, 1),
(12, 1),
(13, 1),
(14, 1),
(15, 1),
(16, 1),
(17, 1),
(18, 1),
(19, 1),
(20, 1),
(21, 1),
(24, 1),
(25, 1),
(26, 1),
(27, 1),
(28, 1),
(30, 1),
(31, 1),
(32, 1),

(1, 2),
(2, 2),
(3, 2),
(4, 2),
(5, 2),
(6, 2),
(7, 2),
(8, 2),
(9, 2),
(10, 2),
(11, 2),
(12, 2),
(13, 2),
(14, 2),
(15, 2),
(16, 2),
(18, 2),
(19, 2),
(20, 2),
(21, 2),
(22, 2),
(23, 2),
(25, 2),
(26, 2),
(27, 2),
(29, 2),
(30, 2),
(31, 2),
(32, 2),

(1, 3),
(2, 3),
(3, 3),
(4, 3),
(5, 3),
(6, 3),
(7, 3),
(8, 3),
(9, 3),
(10, 3),
(11, 3),
(12, 3),
(13, 3),
(14, 3),
(15, 3),
(16, 3),
(17, 3),
(18, 3),
(19, 3),
(20, 3),
(21, 3),
(22, 3),
(23, 3),
(24, 3),
(25, 3),
(26, 3),
(27, 3),
(28, 3),
(29, 3),
(30, 3),
(31, 3),
(32, 3);

INSERT INTO tag (nome) VALUES
('Peitoral'), -- 1
('Pernas'), -- 2
('Costas'), -- 3
('Ombros'), -- 4
('Braços'), -- 5
('Abdômen'), -- 6
('Glúteos'), -- 7
('Trapézio'), -- 8
('Deltoides'), -- 9
('Bíceps'), -- 10
('Tríceps'), -- 11
('Quadríceps'), -- 12
('Isquiotibiais'), -- 13
('Lombar'), -- 14
('Oblíquos'), -- 15
('Coração'), -- 16
('Panturrilha'), -- 17
('Pulmões'), -- 18
('Romboides'), -- 19
('Antebraços'), -- 20
('Flexores do Quadril'); -- 21

INSERT INTO exercicio (nome, descricao) VALUES
('Flexão de Braço', 'A flexão de braço é um exercício de peso corporal que fortalece o peitoral, deltoides e tríceps. Com as mãos no chão e corpo alinhado, você se abaixa até quase tocar o peito no chão e retorna. Melhora a força do core e a estabilidade dos ombros.'), -- 1
('Agachamento Livre', 'O agachamento livre fortalece pernas e glúteos. Com os pés na largura dos ombros, você desce como se fosse sentar, mantendo a coluna ereta. É excelente para quadríceps, isquiotibiais e glúteos, além de melhorar a mobilidade dos quadris e joelhos.'), -- 2
('Remada Alta', 'A remada alta trabalha as costas e ombros. Com uma barra ou halteres, puxe o peso em direção ao peito com os cotovelos elevados. Foca nos músculos trapézios, rombóides e deltoides, contribuindo para uma melhor postura e força da parte superior do corpo.'), -- 3
('Abdominal Crunch', 'O abdominal crunch foca no reto abdominal. Deitado com joelhos dobrados, eleve o tronco em direção aos joelhos, contraindo o abdômen. É um excelente exercício para melhorar a definição da região abdominal e fortalecer o core.'), -- 4
('Levantamento Terra', 'O levantamento terra é um exercício composto que trabalha vários grupos musculares. Com a barra no chão, levante-a até a altura dos quadris, mantendo a coluna neutra. Foca em glúteos, costas e pernas, melhorando a força total e a estabilidade.'), -- 5
('Flexão Lateral do Tronco', 'A flexão lateral do tronco fortalece os oblíquos. De pé, com um peso em uma mão, incline-se lateralmente em direção ao chão e retorne. Este movimento melhora a estabilidade lateral do core e ajuda na definição da cintura.'), -- 6
('Rosca Direta', 'A rosca direta desenvolve os bíceps. Com um par de halteres ou uma barra, flexione os cotovelos para levantar o peso até a altura dos ombros, mantendo os braços estáveis. É fundamental para aumentar a força e o volume dos bíceps.'), -- 7
('Elevação Frontal', 'A elevação frontal trabalha o deltoide anterior. Com halteres nas mãos, levante os braços à frente até a altura dos ombros. Esse exercício fortalece os ombros e é ótimo para melhorar a definição da parte anterior dos deltoides.'), -- 8
('Prancha Abdominal', 'A prancha abdominal é um exercício estático que fortalece o core. Com os cotovelos e pés no chão, mantenha o corpo reto como uma tábua. É eficaz para trabalhar o abdômen, costas e melhorar a estabilidade da coluna e a postura.'), -- 9
('Desenvolvimento de Ombros', 'O desenvolvimento de ombros fortalece o deltoide lateral. Sentado ou em pé, empurre halteres ou barra acima da cabeça, com os cotovelos alinhados. Trabalha a parte superior do corpo, melhorando a força e estabilidade dos ombros.'), -- 10
('Leg Press', 'O leg press é um exercício para as pernas. Sentado no aparelho, empurre a plataforma com os pés, estendendo os joelhos. Foca nos quadríceps, isquiotibiais e glúteos, sendo excelente para aumentar a força das pernas e melhorar a performance atlética.'), -- 11
('Crucifixo com Halteres', 'O crucifixo com halteres é ótimo para o peitoral. Deitado no banco, com halteres nas mãos, abra os braços em forma de arco e volte ao centro. Este exercício foca na porção esternal do peitoral, promovendo força e definição muscular.'), -- 12
('Tríceps Pulley', 'O tríceps pulley é realizado em uma polia, onde você estende os cotovelos para baixo, isolando os tríceps. É excelente para aumentar a força e a definição da parte posterior dos braços, sendo um dos melhores exercícios para tríceps.'), -- 13
('Prancha Lateral', 'A prancha lateral é um exercício estático que fortalece os músculos oblíquos e o core. De lado, apoie-se em um cotovelo e nos pés, mantendo o corpo em linha reta. É eficaz para melhorar a estabilidade lateral e definir a cintura.'), -- 14
('Flexão de Pernas', 'A flexão de pernas trabalha os isquiotibiais e glúteos. No aparelho específico, flexione os joelhos para aproximar os calcanhares dos glúteos. Este exercício é fundamental para o equilíbrio muscular das pernas e a prevenção de lesões.'), -- 15
('Puxada Frontal', 'A puxada frontal foca no latíssimo do dorso. Sentado no aparelho, puxe a barra em direção ao peito, mantendo os cotovelos para baixo. Fortalece as costas e melhora a postura, sendo excelente para o desenvolvimento da parte superior do dorso.'), -- 16
('Elevação Lateral', 'A elevação lateral trabalha o deltoide médio. Com halteres nas mãos, levante os braços lateralmente até a altura dos ombros. É um exercício chave para aumentar a largura dos ombros e melhorar a simetria muscular.'), -- 17
('Extensão de Tríceps', 'A extensão de tríceps fortalece a parte posterior dos braços. Deitado no banco, com halteres ou barra, estenda os cotovelos, mantendo os braços estáveis. Trabalha intensamente os tríceps, melhorando a força e a definição muscular.'), -- 18
('Prancha Superman', 'A prancha Superman fortalece o core, lombar e glúteos. Deitado de bruços, levante os braços e pernas simultaneamente, como se estivesse voando. Este exercício melhora a estabilidade do core e fortalece a região lombar.'), -- 19
('Flexão de Braço Inclinada', 'A flexão de braço inclinada é uma variação que foca no peitoral superior. Com as mãos elevadas em um banco ou plataforma, realize a flexão. Este exercício aumenta a ativação do peitoral superior, sendo ótimo para a definição da parte alta do peitoral.'), -- 20;
('Burpees', 'O burpee é um exercício completo que combina agachamento, prancha, flexão e salto. Comece em pé, agache-se, coloque as mãos no chão e pule para a posição de prancha. Faça uma flexão, volte ao agachamento e termine com um salto explosivo. É ótimo para melhorar a força, resistência e coordenação.'), -- 21
('Jumping Jacks', 'Jumping Jacks são um exercício aeróbico que envolve pular, abrindo as pernas e levantando os braços acima da cabeça, seguido por fechar as pernas e baixar os braços. Este exercício simples e dinâmico melhora a resistência cardiovascular, coordenação e mobilidade articular.'), -- 22
('Afundo', 'O afundo é um exercício de força que foca no fortalecimento das pernas e glúteos. Dê um passo à frente e abaixe o corpo até que o joelho de trás quase toque o chão, mantendo o tronco ereto. Alterne as pernas para trabalhar ambos os lados, melhorando a estabilidade e o equilíbrio.'), -- 23
('Polichinelos', 'O polichinelo é um exercício cardiovascular que envolve pular, abrindo e fechando as pernas enquanto levanta e abaixa os braços. É uma maneira eficaz de aumentar a frequência cardíaca, queimar calorias e melhorar a resistência cardiovascular e a coordenação motora.'), -- 24
('Pular Corda', 'Pular corda é um exercício aeróbico intenso que envolve pular repetidamente uma corda giratória, mantendo um ritmo constante. Este exercício melhora a resistência cardiovascular, coordenação, agilidade e tonifica os músculos das pernas e core.'), -- 25
('Ponte (Glúteos)', 'A ponte para glúteos é um exercício focado no fortalecimento da cadeia posterior. Deite-se de costas, dobre os joelhos e levante os quadris em direção ao teto, contraindo os glúteos no topo do movimento. Ajuda a melhorar a força dos glúteos, isquiotibiais e a estabilidade do core.'), -- 26
('Sprint', 'O sprint é uma corrida em alta velocidade por uma curta distância. Este exercício explosivo melhora a força e potência muscular, além de aumentar a capacidade cardiovascular e queimar calorias rapidamente. É ideal para desenvolver velocidade e resistência anaeróbica.'), -- 27
('High Knees', 'High knees é um exercício cardiovascular onde você corre no lugar, levantando os joelhos em direção ao peito o mais alto possível. Este movimento dinâmico aumenta a frequência cardíaca, trabalha o core e fortalece os músculos das pernas, sendo ótimo para melhorar a resistência e agilidade.'), -- 28
('Skaters', 'Skaters são um exercício pliométrico que envolve saltos laterais, aterrissando em um pé enquanto o outro é levado para trás, imitando o movimento de um patinador. Este exercício trabalha o equilíbrio, coordenação, força das pernas e glúteos, além de ser um excelente exercício cardiovascular.'), -- 29
('Pullover', 'O pullover é um exercício que foca na parte superior do corpo, especialmente no peitoral e dorsais. Deitado com as costas apoiadas em um banco, levante um peso acima da cabeça e traga-o até o nível dos quadris. Ajuda a expandir a caixa torácica e fortalece os músculos das costas e peito.'), -- 30
('Pulldown', 'O pulldown é um exercício de força realizado em uma máquina de cabo. Puxe a barra em direção ao peito enquanto mantém os cotovelos para baixo, depois solte devagar. Este movimento foca no latíssimo do dorso, ajudando a desenvolver a largura das costas e melhorar a postura.'), -- 31
('Mountain Climbers', 'Mountain climbers são um exercício dinâmico de corpo inteiro que envolve alternar os joelhos em direção ao peito enquanto está na posição de prancha. Este movimento rápido e intenso melhora a resistência cardiovascular, força do core e agilidade, além de queimar calorias.'), -- 32;
('Bicicleta Abdominal', ' Deitado de costas, leve os joelhos em direção ao peito, tocando cotovelo oposto no joelho, alternando os lados.'), -- 33;
('Abdominal Infra', 'Deite-se de costas, eleve as pernas juntas até a posição vertical, depois abaixe devagar. '), -- 34;
('Elevação de Panturrilha', 'Em pé, levante os calcanhares, ficando na ponta dos pés, depois abaixe devagar.'), -- 35;
('Corrida (Cardio)', 'Corra em um ritmo confortável para manter o movimento constante..'), -- 36;
('Prancha com Toque no Ombro', ' Em posição de prancha, toque alternadamente o ombro oposto com a mão. '), -- 37;
('Flexão com Abertura', 'Após a flexão, mova o braço para fora, apoiando o corpo em uma prancha lateral.'), -- 38;
('Agachamento Isométrico', 'Segure a posição de agachamento por 30 segundos.'), -- 39;
('Abdominal V-Sit', 'Sentado, levante as pernas e mantenha o corpo em formato de "V", mantendo o equilíbrio.'), -- 40;
('Corrida no lugar', 'Corra no lugar levantando os joelhos o mais alto possível.'), -- 41;
('Elevação de Quadril', 'Deite-se de costas, dobre os joelhos e levante o quadril.'), -- 42;
('Supino Reto', 'Deite-se em um banco plano, com os pés firmemente apoiados no chão. Segure a barra com as mãos afastadas um pouco além da largura dos ombros. Desça a barra de forma controlada até o peito, mantendo os cotovelos ligeiramente dobrados. Empurre a barra para cima, estendendo completamente os braços. Mantenha os ombros estabilizados e o core contraído durante todo o movimento.'), -- 43
('Stiff', 'Fique em pé com os pés na largura dos ombros, segurando uma barra ou halteres com as palmas voltadas para o corpo. Mantenha a coluna neutra e os joelhos ligeiramente flexionados. Desça o peso lentamente, flexionando os quadris e levando o glúteo para trás, até sentir um alongamento nos isquiotibiais. Retorne à posição inicial contraindo os glúteos e mantendo o core ativado.'), -- 44
('Panturrilha no Smith', 'Posicione-se na máquina Smith com os ombros sob a barra e as pontas dos pés apoiadas em uma plataforma elevada. Com as pernas estendidas, levante os calcanhares o máximo possível, contrate as panturrilhas no topo do movimento e, em seguida, desça lentamente os calcanhares para a posição inicial, sentindo o alongamento nas panturrilhas.'), -- 45
('Remada Curvada', 'Segure uma barra com as palmas voltadas para baixo e os pés na largura dos ombros. Flexione ligeiramente os joelhos e incline o tronco para frente, mantendo a coluna neutra. Puxe a barra em direção ao abdômen, mantendo os cotovelos próximos ao corpo. Contraia as escápulas no topo do movimento e, em seguida, desça a barra de forma controlada.'), -- 46
('Encolhimento de Ombros', 'Fique em pé com os pés na largura dos ombros, segurando halteres ao lado do corpo com os braços estendidos. Encolha os ombros em direção às orelhas, elevando-os o máximo possível. Segure a contração por um momento e, em seguida, desça os ombros de volta à posição inicial de forma controlada.'), -- 47
('Abdominal Oblíquo', 'Deite-se de lado em um colchonete, com as pernas estendidas e uma mão atrás da cabeça. Levante o tronco em direção ao quadril, contraindo os músculos oblíquos. Segure a contração por um momento no topo do movimento e, em seguida, desça lentamente de volta à posição inicial. Repita do outro lado.'), -- 48
('Supino Inclinado', 'Deite-se em um banco inclinado, segurando uma barra com as mãos afastadas na largura dos ombros. Abaixe a barra até a parte superior do peito, controlando o movimento. Empurre a barra para cima até que os braços estejam completamente estendidos, mantendo o core ativado e os ombros estabilizados.'), -- 49
('Rosca Martelo', 'Fique em pé com os pés na largura dos ombros, segurando halteres ao lado do corpo com as palmas voltadas para dentro. Flexione os cotovelos para levantar os halteres em direção aos ombros, mantendo os cotovelos próximos ao corpo. Segure a contração no topo e, em seguida, desça os halteres de forma controlada.'), -- 50
('Tríceps Testa', 'Deite-se em um banco plano, segurando uma barra EZ com as mãos na largura dos ombros. Com os braços estendidos acima da cabeça, dobre os cotovelos para descer a barra em direção à testa, mantendo os cotovelos fixos. Empurre a barra de volta à posição inicial, estendendo completamente os braços.'), -- 51
('Agachamento no Hack', 'Posicione-se na máquina Hack, com os ombros sob as almofadas e os pés na plataforma, na largura dos ombros. Desça o corpo dobrando os joelhos e quadris até que as coxas estejam paralelas ao chão. Empurre a plataforma de volta à posição inicial, estendendo completamente as pernas.'), -- 52
('Extensão de Pernas', 'Sente-se na máquina de extensão de pernas, ajustando o encosto para que suas costas fiquem totalmente apoiadas. Coloque os pés sob as almofadas e segure as alças laterais. Estenda as pernas até que estejam completamente retas, contraindo o quadríceps no topo do movimento. Retorne à posição inicial de forma controlada.'), -- 53
('Abdominal Bicicleta', 'Deite-se de costas em um colchonete, com as mãos atrás da cabeça e as pernas elevadas. Traga um joelho em direção ao peito enquanto torce o tronco para que o cotovelo oposto se mova em direção ao joelho. Alterne as pernas e repita o movimento, simulando o pedalar de uma bicicleta, mantendo o core contraído.'), -- 54
('Barra Fixa', 'Segure a barra com as mãos afastadas na largura dos ombros, com as palmas voltadas para frente. Suspenda o corpo até que o queixo ultrapasse a altura da barra, puxando as escápulas para baixo e para trás. Desça de forma controlada até que os braços estejam completamente estendidos. Mantenha o core ativado e os pés juntos durante todo o movimento.'), -- 55
('Remada Unilateral', 'Coloque um joelho e uma mão em um banco para suporte, mantendo o tronco paralelo ao chão. Segure um halter com a outra mão, com o braço estendido. Puxe o halter em direção ao abdômen, mantendo o cotovelo próximo ao corpo. Contraia as escápulas no topo do movimento e, em seguida, desça o halter de forma controlada. Repita do outro lado.'), -- 56
('Crucifixo Invertido', 'Sente-se em um banco ou fique em pé com o tronco inclinado para frente, segurando um par de halteres com os braços estendidos para baixo e as palmas voltadas uma para a outra. Abra os braços para os lados em um movimento de arco, até que os cotovelos estejam alinhados com os ombros. Contraia as escápulas e, em seguida, desça os halteres de forma controlada.'), -- 57
('Rosca Concentrada', 'Sente-se em um banco com as pernas afastadas. Segure um halter com uma mão e apoie o cotovelo na parte interna da coxa. Flexione o cotovelo para levantar o halter em direção ao ombro, mantendo a contração máxima no topo. Desça o halter de forma controlada até a posição inicial. Repita com o outro braço.'), -- 58
('Abdominal Canivete', 'Deite-se de costas em um colchonete, com os braços estendidos acima da cabeça e as pernas estendidas. Simultaneamente, levante os braços e as pernas para que se encontrem no meio, formando um "V" com o corpo. Contraia o abdômen no topo do movimento e, em seguida, desça de volta à posição inicial de forma controlada.'); -- 59

-- Associações para o exercício 1 (Flexão de Braço)
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(4, 1), -- Peitoral
(9, 1), -- Deltoides
(11, 1); -- Tríceps

-- Associações para o exercício 2 (Agachamento Livre)
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(2, 2), -- Pernas
(7, 2); -- Glúteos

-- Associações para o exercício 3 (Remada Alta)
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(3, 3), -- Costas
(9, 3); -- Deltoides

-- Associações para o exercício 4 (Abdominal Crunch)
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(6, 4); -- Abdômen

-- Associações para o exercício 5 (Levantamento Terra)
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(2, 5), -- Pernas
(3, 5), -- Costas
(7, 5); -- Glúteos

-- Associações para o exercício 6 (Flexão Lateral do Tronco)
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(15, 6); -- Oblíquos

-- Associações para o exercício 7 (Rosca Direta)
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(5, 7); -- Braços

-- Associações para o exercício 8 (Elevação Frontal)
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(4, 8), -- Ombros
(9, 8); -- Deltoides

-- Associações para o exercício 9 (Prancha Abdominal)
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(6, 9), -- Abdômen
(14, 9); -- Lombar

-- Associações para o exercício 10 (Desenvolvimento de Ombros)
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(4, 10), -- Ombros
(9, 10); -- Deltoides

-- Associações para o exercício 11 (Leg Press)
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(2, 11), -- Pernas
(12, 11); -- Quadríceps

-- Associações para o exercício 12 (Crucifixo com Halteres)
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(1, 12); -- Peitoral

-- Associações para o exercício 13 (Tríceps Pulley)
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(11, 13), -- Tríceps
(5, 13); -- Braços

-- Associações para o exercício 14 (Prancha Lateral)
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(6, 14), -- Abdômen
(15, 14); -- Oblíquos

-- Associações para o exercício 15 (Flexão de Pernas)
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(2, 15), -- Pernas
(13, 15); -- Isquiotibiais

-- Associações para o exercício 16 (Puxada Frontal)
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(3, 16), -- Costas
(9, 16); -- Deltoides

-- Associações para o exercício 17 (Elevação Lateral)
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(4, 17), -- Ombros
(9, 17); -- Deltoides

-- Associações para o exercício 18 (Extensão de Tríceps)
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(11, 18), -- Tríceps
(5, 18); -- Braços

-- Associações para o exercício 19 (Prancha Superman)
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(6, 19), -- Abdômen
(14, 19); -- Lombar

-- Associações para o exercício 20 (Flexão de Braço Inclinada)
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(1, 20), -- Peitoral
(9, 20); -- Deltoides

-- Associações para o exercício 21 (Burpees)
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(1, 21), -- Peitoral
(2, 21), -- Pernas
(4, 21); -- Ombro

-- Associações para o exercício 22 (Jumping Jacks)
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(1, 22), -- Peitoral
(4, 22); -- Ombro

-- Associações para o exercício 23 (Afundo)
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(12, 23), -- Quadrícipes
(7, 23); -- Gluteos

-- Associações para o exercício 24 (Polichinelo)
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(2, 24), -- Pernas
(4, 24); -- Ombro

-- Associações para o exercício 25 (Pular Corda)
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(2, 25), -- Pernas
(4, 25); -- Ombro

-- Associações para o exercício 26 (Ponte Gluteos)
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(12, 26), -- Quadrícipes
(7, 26); -- Gluteos

-- Associações para o exercício 27 (Sprint)
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(2, 27), -- Pernas
(16, 27); -- Coração

-- Associações para o exercício 28 (High Knees)
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(2, 28); -- Pernas

-- Associações para o exercício 29 (Skaters)
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(2, 29); -- Pernas

-- Associações para o exercício 30 (Pullover)
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(3, 30), -- Costas
(4, 30); -- Ombro

-- Associações para o exercício 31 (Pulldown)
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(3, 31), -- Costas
(4, 31); -- Ombro

-- Associações para o exercício 32 (Mountain Climbers)
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(3, 32), -- Costas
(4, 32); -- Ombro

-- Associações para o exercício 34 (Abdominal Infra)
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(6, 34); -- Abdomen

-- Associações para o exercício 35 (Elevação de Panturrilha)
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(17, 35), -- Panturilha
(7, 35); -- Gluteos

-- Associações para o exercício 36 (Corrida (Cardio))
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(16, 36), -- coracao
(18, 36), -- pulmão
(2, 36); -- perna

-- Associações para o exercício 37 (Prancha com Toque no Ombro)
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(4, 37), -- ombro
(1, 37); -- peito

-- Associações para o exercício 38 (Flexão com Abertura)
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(4, 38), -- ombro
(1, 38); -- peito

-- Associações para o exercício 39 (Agachamento Isométrico)
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(7, 39), -- gluteos
(12, 39); -- quadriceps

-- Associações para o exercício 40 (Abdominal V-Sit)
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(6, 39); -- abdomen

-- Associações para o exercício 41 (Corrida no lugar)
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(1, 41), -- perna
(16, 41); -- coracao

-- Associações para o exercício 42 (Elevacao de quadril)
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(7, 42), -- gluteos
(6, 42); -- abdomen

-- Associações para o exercício 43 (Supino Reto )
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(1, 43), -- Peitoral
(4, 43), -- Ombro
(11, 43); -- Tríceps

-- Associações para o exercício 44 (Stiff)
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(13, 44), -- Isquiotibiais
(14, 44), -- lombar
(7, 44); -- gluteos

-- Associações para o exercício 45 (panturrilha no smith)
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(17, 45); -- gluteos

-- Associações para o exercício 46 (Remada Curvada)
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(3, 46), -- costas
(14, 46), -- lombar
(10, 46); -- biceps

-- Associações para o exercício 47 (Encolhimento de Ombros)
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(8, 47), -- trapezio
(19, 47); -- romboides

-- Associações para o exercício 48 (Abdominal Oblíquo)
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(15, 48), -- obliquo
(6, 48); -- abdomen

-- Associações para o exercício 49 (Supino Inclinado)
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(1, 49), -- Peitoral
(4, 49), -- Ombro
(11, 49); -- Tríceps

-- Associações para o exercício 50 (Rosca martelo)
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(10, 50); -- biceps

-- Associações para o exercício 51 (triceps testa)
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(11, 51); -- Tríceps

-- Associações para o exercício 52 (agachamento no hack)
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(13, 52), -- isquitobiais
(7, 52), -- gluteos
(12, 52); -- quadriceps

-- Associações para o exercício 53 (Extensão de Perna)
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(12, 53); -- quadriceps

-- Associações para o exercício 54 (abdominal bicicleta)
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(6, 54), -- abdomen
(15, 54); -- obliquos

-- Associações para o exercício 55 (barra fixa)
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(3, 55), -- costa
(10, 55), -- biceps
(20, 55); -- antibraco

-- Associações para o exercício 56 (remana unilateral)
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(3, 56), -- costas
(14, 56), -- lombar
(10, 56); -- biceps

-- Associações para o exercício 57 (crucifixo invertido)
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(4, 57), -- ombro
(8, 57), -- trapezio
(19, 57); -- romboides

-- Associações para o exercício 58 (Rosca Concentrada)
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(10, 58); -- biceps

-- Associações para o exercício 59 (abdominal canivete)
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(6, 59), -- abdomen
(21, 59); -- Flexores do quadril

INSERT INTO assinatura (nome, valor) VALUES
('Viva Vitalis', 49.99);

INSERT INTO assinatura (nome, valor) VALUES
('Viva Vitalis', 0.10);

INSERT INTO endereco (logradouro, numero, bairro, cidade, estado, complemento, cep)
VALUES
('Avenida Wilson Carvalho', 10, 'Zerão', 'Macapá', 'AP', null, 68903025),
('Rua Verdum', 112, 'Vila Nasser', 'Campo Grande', 'MS', null, 79117360),
('Rua Alzira Gomes Queiros', 6, 'Jardim Eldorado', 'Ourinhos', 'SP', null, 19914550);

-- SENHA -> Daniel@23133 (todos)
INSERT INTO usuario (tipo, nickname, cpf, nome, dt_nasc, sexo, email, email_recuperacao, senha, personal_id, endereco_id, pontos)
VALUES
(0, 'ylu1Gi@@', '56438153036', 'Luigi Vicchietti', '2005-01-17', 'M', 'luigi@gmail.com', 'padrao@gmail', '$2a$10$Ix.qCm5U71fFzjkd2/z3T.gKtgr9NzUzpqVOqAXU8uAcvv3ftooWS', null, null, 0),
(1, 'marC@SSilV4', '92865867013', 'Marcos Silva Oliveira Pinto Santos', '1980-12-05', 'M', 'marcos@gmail.com', 'padrao@gmail', '$2a$10$Ix.qCm5U71fFzjkd2/z3T.gKtgr9NzUzpqVOqAXU8uAcvv3ftooWS', null, 1, 0);

INSERT INTO ficha (usuario_id, peso, altura, problema_cardiaco, dor_peito_atividade, dor_peito_ultimo_mes, problema_osseo_articular, medicamento_pressao_coracao, impedimento_atividade)
VALUES
(1, 58.60, 1.85, 0, 0, 0, 0, 0, 0);

INSERT INTO rotina_usuario (usuario_id, meta_id, rotina_alternativa) VALUES
(1, 2, 0);

-- SENHA -> Daniel@23133 (todos)
INSERT INTO usuario (tipo, nickname, cpf, nome, dt_nasc, sexo, email, email_recuperacao, senha, personal_id, endereco_id, pontos)
VALUES
(0, 'w1llSal4d@', '95931984070', 'Will Dantas', '2004-03-31', 'M', 'will@gmail.com', 'padrao@gmail', '$2a$10$Ix.qCm5U71fFzjkd2/z3T.gKtgr9NzUzpqVOqAXU8uAcvv3ftooWS', 2, null, 0),
(1, 'roberTT4F@', '63515811095', 'Roberta Ferreira', '1985-08-25', 'F', 'roberta@gmail.com', null, '$2a$10$Ix.qCm5U71fFzjkd2/z3T.gKtgr9NzUzpqVOqAXU8uAcvv3ftooWS', null, 2, 0),
(1, 'pedR0G@', '47767654036', 'Pedro Gomes', '1978-06-17', 'M', 'pedro@gmail.com', null, '$2a$10$Ix.qCm5U71fFzjkd2/z3T.gKtgr9NzUzpqVOqAXU8uAcvv3ftooWS', null, 3, 0);
-- (2, 'admin1Nhyir@', '29896637032', 'Poliana Micheline Milit�o', '1999-07-18', 'F', 'admin@gmail.com', 'padrao@gmail', '$2a$10$Ix.qCm5U71fFzjkd2/z3T.gKtgr9NzUzpqVOqAXU8uAcvv3ftooWS', null, null);

INSERT INTO ficha (usuario_id, peso, altura, problema_cardiaco, dor_peito_atividade, dor_peito_ultimo_mes, problema_osseo_articular, medicamento_pressao_coracao, impedimento_atividade)
VALUES
(3, 88.30, 1.81, 0, 0, 0, 1, 0, 1);

INSERT INTO rotina_usuario (usuario_id, meta_id, rotina_alternativa) VALUES
(3, 1, 0);

-- Inserção de contrato para o usuário com id_usuario 3 e personal_id 2
INSERT INTO contrato (usuario_id, personal_id, afiliacao, inicio_contrato, fim_contrato) VALUES
(3, 2, 1, '2024-01-01', '2024-12-31');

INSERT INTO pagamento (assinatura_id, usuario_id, data_pagamento, tipo)
VALUES
(1, 2, '2024-01-01', 'PIX');

INSERT INTO chat (usuario_id, personal_id, ativo)
VALUES
(3, 2, 1);

INSERT INTO mensagem (chat_id, destinatario_id, remetente_id, assunto, data_hora)
VALUES
(1, 2, 3, 'Bom dia! Tudo bem com você? Gostaria que conversar mais sobre minha rotina...', '2024-01-01 12:30:37'),
(1, 3, 2, 'Claro! Podemos nos encontrar no parque do Zerão? Podemos começar a treinar juntos!', '2024-01-01 12:30:59'),
(1, 2, 3, 'Legal! Compareço sim, pode ser as 17h? Outra coisa, pode me dar dicas no dia a dia? Meio que estou precisando kkkk', '2024-01-01 12:31:37'),
(1, 3, 2, 'Claro!', '2024-01-01 12:35:37');

-- Atribuição de usuários personais a especialidades
INSERT INTO especialidade_por_personal (personal_id, especialidade_id) VALUES
(2, 1),  -- Marcos Silva Oliveira Pinto Santos é especializado em Emagrecimento
(2, 3),  -- Marcos Silva Oliveira Pinto Santos é especializado em Resistência Cardiovascular
(4, 2),  -- Roberta Ferreira é especializada em Alta Intensidade (HIIT)
(4, 4),  -- Roberta Ferreira é especializada em Flexibilidade e Mobilidade
(5, 5),  -- Pedro Gomes é especializado em Resistência Cardiovascular
(5, 3);  -- Pedro Gomes é especializado em Força e Resistência

INSERT INTO midia (nome, caminho, extensao, tipo, exercicio_id, alimento_id, refeicao_id)
VALUES
-- Imagem dos ingredientes e refeições PT.1
('Amendoim', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114337/alimentos-refeicoes/vt8bw3xzrhi7kl4zsgqn.jpg', 'JPG', 'Imagem', null, 21, null), -- 1
('Abacate', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114337/alimentos-refeicoes/uwtjrjjhi8jdzybyywum.jpg', 'JPG', 'Imagem', null, 15, null), -- 2
('Virada Paulista', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114337/alimentos-refeicoes/a2fog1ceujlidytlq8mb.jpg', 'JPG', 'Imagem', null, null, 1), -- 3
('Iogurte Natural', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114337/alimentos-refeicoes/a3c8smqnoit2zlnkmolr.jpg', 'JPG', 'Imagem', null, 12, null), -- 4
('Torresmo', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114337/alimentos-refeicoes/aovf0uyvjadxwhemqa9q.jpg', 'JPG', 'Imagem', null, 6, null), -- 5
('Salada de Frango Grelhado', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114336/alimentos-refeicoes/yjvjplvgmbm9iiphcazw.jpg', 'JPG', 'Imagem', null, null, 2), -- 6
('Salmão com Quinoa', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114336/alimentos-refeicoes/dwtji5jplzfbwyz87s1y.jpg', 'JPG', 'Imagem', null, null, 3), -- 7
('Salmão Assado', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114336/alimentos-refeicoes/ce2cadaqxklgvnif9fgb.jpg', 'JPG', 'Imagem', null, 13, null), -- 8
('Farofa', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114334/alimentos-refeicoes/h1tmwmeywou5tybswnxl.jpg', 'JPG', 'Imagem', null, 8, null), -- 9
('Salada de Folhas Verdes', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114336/alimentos-refeicoes/aho0uljh4s5bnurbhdyx.jpg', 'JPG', 'Imagem', null, 9, null), -- 10
('Ovo', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114336/alimentos-refeicoes/ltzbrusehrpnr7hrart8.jpg', 'JPG', 'Imagem', null, 7, null), -- 11
('Linguiça', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114336/alimentos-refeicoes/hzgq0nexjgmos5dsu9fe.jpg', 'JPG', 'Imagem', null, 4, null), -- 12
('Omelete de Claras', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114336/alimentos-refeicoes/lsiko15fxeljqxknyctk.jpg', 'JPG', 'Imagem', null, 16, null), -- 13
('Frango e Batata Doce', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114336/alimentos-refeicoes/fyarzkwh5evynkfsne3m.png', 'PNG', 'Imagem', null, null, 4), -- 14
('Quinoa Cozida', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114336/alimentos-refeicoes/bflbinmmdvgbicthxm4g.jpg', 'JPG', 'Imagem', null, 14, null), -- 15
('Legumes', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114336/alimentos-refeicoes/eqvtmlcdxxealazxybnb.jpg', 'JPG', 'Imagem', null, 11, null), -- 16
('Couve', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114335/alimentos-refeicoes/ojwatdeipahfpzs2ybdc.jpg', 'JPG', 'Imagem', null, 5, null), -- 17
('Carne de vaca', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114335/alimentos-refeicoes/jywwig4axaeknweo2lwb.jpg', 'JPG', 'Imagem', null, 19, null), -- 18
('Carne de porco', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114335/alimentos-refeicoes/mqoe5ulczacdmt6yf009.jpg', 'JPG', 'Imagem', null, 3, null), -- 19
('Feijão Preto', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114335/alimentos-refeicoes/t7rcph08fvnvf3x4gezr.jpg', 'JPG', 'Imagem', null, 18, null), -- 20
('Frango', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114335/alimentos-refeicoes/zjk4sr6ks3cvo8yl81r9.jpg', 'JPG', 'Imagem', null, 10, null), -- 21
('Carne com Aveia de Flocos', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114335/alimentos-refeicoes/ow44exnjgm1ssi8taiav.png', 'PNG', 'Imagem', null, null, 5), -- 22
('Batata Doce', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114334/alimentos-refeicoes/usewobqvvx4udhfrjfai.jpg', 'JPG', 'Imagem', null, 17, null), -- 23
('Arroz Branco', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114334/alimentos-refeicoes/qrl878lcfpb6mr6sjrxy.png', 'PNG', 'Imagem', null, 1, null), -- 24
('Feijão Carioca', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114334/alimentos-refeicoes/wvpq35wxctpzhlwf6qlv.jpg', 'JPG', 'Imagem', null, 2, null), -- 25
('Aveia em Flocos', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114334/alimentos-refeicoes/ntlpyde4xobqrsajhwnz.jpg', 'JPG', 'Imagem', null, 20, null), -- 26

-- Imagens dos EX PT.1
('Flexão de Braço', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114562/exercicios/hicln1lwqn2c3vyoaj9a.png', 'PNG', 'Imagem', 1, null, null), -- 27
('Agachamento Livre', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114561/exercicios/pgvfsvdimamebrlrq8lo.png', 'PNG', 'Imagem', 2, null, null), -- 28
('Prancha Abdominal', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114561/exercicios/ivw5kvvgd3i0fezhp3hm.jpg', 'JPG', 'Imagem', 9, null, null), -- 29
('Tríceps Pulley', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114561/exercicios/llwqzomppzetvz6uuh8q.jpg', 'JPG', 'Imagem', 13, null, null), -- 30
('Leg Press', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114561/exercicios/v8grdjhkjz4omtdmo4ex.jpg', 'JPG', 'Imagem', 11, null, null), -- 31
('Rosca Direta', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114561/exercicios/cjnipso8b9ulpandg5bv.jpg', 'JPG', 'Imagem', 7, null, null), -- 32
('Remada Alta', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114561/exercicios/mmqse4o4ovnqag8wedjx.jpg', 'JPG', 'Imagem', 3, null, null), -- 33
('Levantamento Terra', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114559/exercicios/bzwkb7omeomketkbmiec.jpg', 'JPG', 'Imagem', 5, null, null), -- 34
('Crucifíxo com Halteres', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114560/exercicios/yutkonjrjmfv7d8iypts.jpg', 'JPG', 'Imagem', 12, null, null), -- 35
('Elevação Frontal', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114560/exercicios/frbkgulpowtszrsv7jvt.jpg', 'JPG', 'Imagem', 8, null, null), -- 36
('Abdominal Crunch', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114560/exercicios/u2fq2gpzi5whbvbaksd1.jpg', 'JPG', 'Imagem', 4, null, null), -- 37
('Puxada Frontal', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114560/exercicios/hadejd8zdzyzfjczbcyv.jpg', 'JPG', 'Imagem', 16, null, null), -- 38
('Desenvolvimento de Ombros', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114559/exercicios/uz4heaadlg5kbg7kfw6x.jpg', 'JPG', 'Imagem', 10, null, null), -- 39
('Prancha Lateral', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114559/exercicios/mjmi8zvimuknb9klj93j.jpg', 'JPG', 'Imagem', 14, null, null), -- 40
('Flexão de Pernas', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114559/exercicios/d8sg5iugt4g33jxjjmxz.webp', 'WEBP', 'Imagem', 15, null, null), -- 41
('Extensão de Tríceps', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114559/exercicios/qa1qcjchjowfvixjfrji.jpg', 'JPG', 'Imagem', 18, null, null), -- 42
('Elevação Lateral', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114559/exercicios/osnce7yjg5yv7crt5hur.jpg', 'JPG', 'Imagem', 17, null, null), -- 43
('Flexão Lateral do Tronco', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718121243/exercicios/uni6orw0bcgl3stezl6m.jpg', 'JPG', 'Imagem', 6, null, null), -- 44
('Prancha Superman', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718121529/exercicios/tcpfhxtr5ts5uk6tug3m.png', 'PNG', 'Imagem', 19, null, null), -- 45
('Flexão de Braço Inclinada', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718122137/exercicios/tpkblqnusy1iljk6bas5.webp', 'WEBP', 'Imagem', 20, null, null), -- 46

-- Fotos do Mural
('foto-mural-1', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718126019/mural/cw64tvqvz5zdxds2fcbg.jpg', 'JPG', 'Imagem', null, null, null), -- 47
('foto-mural-2', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718126019/mural/ehz6bscm88abrqts5thg.jpg', 'JPG', 'Imagem', null, null, null), -- 48
--
('foto-mural-3', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718126494/mural/pwdze5jkmp2g59djagci.jpg', 'JPG', 'Imagem', null, null, null), -- 49
('foto-mural-4', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718126494/mural/k5svjihaojkf1bo7ld3m.jpg', 'JPG', 'Imagem', null, null, null), -- 50
('foto-mural-5', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718126494/mural/hmggcwuqr57hgoqagytp.jpg', 'JPG', 'Imagem', null, null, null), -- 51

-- Videos dos EX
-- Inserir
('Flexão de Braço', 'https://youtu.be/dHgoYiCraCw?si=-4m8_YAUEmOk54AN', 'MP4', 'Video', 1, null, null), -- 52
('Agachamento Livre', 'https://youtube.com/shorts/AmpX2jRn9fs?si=p4g9qRmq4zHTlpxd', 'MP4', 'Video', 2, null, null), -- 53
('Prancha Abdominal', 'https://youtu.be/qNRqGqESAWU?si=cYDtHu7at2jAu3lq', 'MP4', 'Video', 9, null, null), -- 54
('Tríceps Pulley', 'https://youtu.be/dTqDKC0D6P4?si=-ty-asq4KWNL1YPz', 'MP4', 'Video', 13, null, null), -- 55
('Leg Press', 'https://youtu.be/nY8UsiAqwds?si=0Uq3dcLI7n-IihCV', 'MP4', 'Video', 11, null, null), -- 56
('Rosca Direta', 'https://youtu.be/Q8TqfD8E7BU?si=f48mTBdc5B7B3ASZ', 'MP4', 'Video', 7, null, null), -- 57
('Remada Alta', 'https://www.youtube.com/shorts/PByRUvDMFBw', 'MP4', 'Video', 3, null, null), -- 58
('Levantamento Terra', 'https://youtu.be/50AkPBZwACQ?si=9TV4P-Wcv_G1qYlK', 'MP4', 'Video', 5, null, null), -- 59
('Crucifíxo com Halteres', 'https://www.youtube.com/shorts/hV21YJFt6MI', 'MP4', 'Video', 12, null, null), -- 60
('Elevação Frontal', 'https://www.youtube.com/shorts/GqZRmCow0rw', 'MP4', 'Video', 8, null, null), -- 61
('Abdominal Crunch', 'https://www.youtube.com/shorts/t91ou1ZoaE0', 'MP4', 'Video', 4, null, null), -- 62
('Puxada Frontal', 'https://youtu.be/9FFLBDWXSZA?si=tp3agcjBs3e1kY5J', 'MP4', 'Video', 16, null, null), -- 63
('Desenvolvimento de Ombros', 'https://youtu.be/L-iQfHVeuVg?si=USnpz5u8a0cLuwq9', 'MP4', 'Video', 10, null, null), -- 64
('Prancha Lateral', 'https://youtu.be/wxyzL0mIow0?si=ATHAfo_u3pwh9pPN', 'MP4', 'Video', 14, null, null), -- 65
('Flexão de Pernas', 'https://youtu.be/yiPqK5WMcNw?si=u4ceq6RTLoxHMcTS', 'MP4', 'Video', 15, null, null), -- 66
('Extensão de Tríceps', 'https://www.youtube.com/watch?v=wrPq0OH8mK4', 'MP4', 'Video', 18, null, null), -- 67
('Elevação Lateral', 'https://youtu.be/IwWvZ0rlNXs?si=-8-KvUpzK_H4307_', 'MP4', 'Video', 17, null, null), -- 68
('Flexão Lateral do Tronco', 'https://www.youtube.com/shorts/IlVa8zk9AGM', 'MP4', 'Video', 6, null, null), -- 69
('Prancha Superman', 'https://www.youtube.com/shorts/PVUOn-EJAiI', 'MP4', 'Video', 19, null, null), -- 70
('Flexão de Braço Inclinada', 'https://www.youtube.com/shorts/so5nuzZWwmI', 'MP4', 'Video', 20, null, null), -- 71
('Burpees', 'https://youtube.com/shorts/EiIWIEaIZe0?si=p-FXR8ypjXb0qp7-', 'MP4', 'Video', 21, null, null), -- 72
('Jumping Jacks', 'https://www.youtube.com/watch?v=PBHUfBzxczU', 'MP4', 'Video', 22, null, null), -- 73
('Afundo', 'https://youtube.com/shorts/CR_fLvBMiwY?si=jo3Is25Q0lXsMFKr', 'MP4', 'Video', 23, null, null), -- 74
('Polichinelos', 'https://youtu.be/YJbkVbNNops?si=mYAfC_BMWe8UQ0J2', 'MP4', 'Video', 24, null, null), -- 75
('Pular Corda', 'https://www.youtube.com/shorts/ihm2nvsgKSM', 'MP4', 'Video', 25, null, null), -- 76
('Ponte (Gluteos)', 'https://www.youtube.com/watch?v=Pplko_LUxDI', 'MP4', 'Video', 26, null, null), -- 77
('Sprint', 'https://www.youtube.com/shorts/ViHzzCrHN2w', 'MP4', 'Video', 27, null, null), -- 78
('High Knees', 'https://www.youtube.com/watch?v=DfjpR6dzLVg', 'MP4', 'Video', 28, null, null), -- 79
('Skaters', 'https://www.youtube.com/watch?v=Xz27DudBfSs', 'MP4', 'Video', 29, null, null), -- 80
('Pullover', 'https://www.youtube.com/shorts/RtVTd4dXlZQ', 'MP4', 'Video', 30, null, null), -- 81
('Pulldown', 'https://www.youtube.com/shorts/Lgr9JqdRp3M', 'MP4', 'Video', 31, null, null), -- 82
('Mountain Climbers', 'https://www.youtube.com/watch?v=kLh-uczlPLg', 'MP4', 'Video', 32, null, null), -- 83
('Bicicleta Abdominal', 'https://www.youtube.com/watch?v=apmprS8H1MY', 'MP4', 'Video', 33, null, null), -- 84
('Abdominal Infra', 'https://youtu.be/9EYiA8gbnRA?si=lNmzsiaaUgPH1x5X', 'MP4', 'Video', 34, null, null), -- 85
('Elevação de Panturrilha', 'https://youtu.be/cGoe-feFYD4?si=P4BsElc-_W3VmGgM', 'MP4', 'Video', 35, null, null), -- 86
('Corrida (Cardio)', 'https://www.youtube.com/shorts/SjBIJ_ZAWpU', 'MP4', 'Video', 36, null, null), -- 87
('Prancha com Toque no Ombro', 'https://www.youtube.com/watch?v=xdyHJmuwO6w', 'MP4', 'Video', 37, null, null), -- 88
('Flexão com Abertura', 'https://www.youtube.com/shorts/2f7rFGVAqLw', 'MP4', 'Video', 38, null, null), -- 89
('Agachamento Isométrico', 'https://www.youtube.com/shorts/yWpXrGznyfU', 'MP4', 'Video', 39, null, null), -- 90
('Abdominal V-Sit', 'https://www.youtube.com/shorts/z6tcgCz5lLs', 'MP4', 'Video', 40, null, null), -- 91
('Corrida no Lugar', 'https://youtu.be/qNRqGqESAWU?si=E06zCaiSBafTrY-M', 'MP4', 'Video', 41, null, null), -- 92
('Elevação de Quadril', 'https://youtu.be/HkE86qAJzZU?si=Ecql5UyfD8-Kxm-O', 'MP4', 'Video', 42, null, null), -- 93

-- Imagens dos EX PT.2
    -- Inserir
('Burpees', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1725841225/exercicios/jpsbnmfjp4ieev8pfqlo.jpg', 'JPG', 'Imagem', 21, null, null), -- 94
('Jumping Jacks', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1728159492/exercicios/tmbr1yheuadfvut3dfjx.jpg', 'JPG', 'Imagem', 22, null, null), -- 95
('Afundo', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1725841922/exercicios/pax33hmoelym2q9ewxuv.jpg', 'JPG', 'Imagem', 23, null, null), -- 96
('Polichinelos', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1725840453/exercicios/ivrgnutd7z6dc2k2ul7e.jpg', 'JPG', 'Imagem', 24, null, null), -- 97
('Pular Corda', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1728159663/exercicios/zbfdpkxvpkbanb8zteww.jpg', 'JPG', 'Imagem', 25, null, null), -- 98
('Ponte (Gluteos)', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1728159591/exercicios/gyqp0o1thx5axvhdjump.jpg', 'JPG', 'Imagem', 26, null, null), -- 99
('Sprint', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1728159237/exercicios/epfedxltpuknfue6vhzj.jpg', 'JPG', 'Imagem', 27, null, null), -- 100
('High Knees', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1728159452/exercicios/oedasaxawgmet75nhtsm.jpg', 'JPG', 'Imagem', 28, null, null), -- 101
('Skaters', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1728159192/exercicios/ltzmdfqymrnfnxyjtniu.jpg', 'JPG', 'Imagem', 29, null, null), -- 102
('Pullover', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1728159127/exercicios/ehxkqmshhzkk15qkqegu.jpg', 'JPG', 'Imagem', 30, null, null), -- 103
('Pulldown', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1728159703/exercicios/kpiiu1r3djpdpshd5zo4.jpg', 'JPG', 'Imagem', 31, null, null), -- 104
('Mountain Climbers', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1728159535/exercicios/yxb3qg7xwewazrbdcxo7.jpg', 'JPG', 'Imagem', 32, null, null), -- 105
('Bicicleta Abdominal', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1728159313/exercicios/mdn4yxhepbrymckf07tv.jpg', 'JPG', 'Imagem', 33, null, null), -- 106
('Abdominal Infra', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1725841833/exercicios/sacfobeb48ym3akjlyyb.jpg', 'JPG', 'Imagem', 34, null, null), -- 107
('Elevação de Panturrilha', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1728159367/exercicios/a9lydtfrgu0nvlls5hcs.jpg', 'JPG', 'Imagem', 35, null, null), -- 108
('Corrida (Cardio)', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1725841416/exercicios/phkc74yrknwycpxtugam.jpg', 'JPG', 'Imagem', 36, null, null), -- 109
('Prancha com Toque no Ombro', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1728159623/exercicios/idsxgr9yzkcfcxgsufkf.jpg', 'JPG', 'Imagem', 37, null, null), -- 110
('Flexão com Abertura', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1728159411/exercicios/rl3qnixzhqinlkhzr4k6.jpg', 'JPG', 'Imagem', 38, null, null), -- 111
('Agachamento Isométrico', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1728159824/exercicios/delhrtedjvoirkuhh2zr.jpg', 'JPG', 'Imagem', 39, null, null), -- 112
('Abdominal V-Sit', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1728159744/exercicios/dbdk1emcpbrawezjucsw.jpg', 'JPG', 'Imagem', 40, null, null), -- 113
('Corrida no Lugar', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1725841377/exercicios/wlmlabnkalb8irlwefxa.jpg', 'JPG', 'Imagem', 41, null, null), -- 114
('Elevação de Quadril', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1725841659/exercicios/rj7gp0iwk3bqk9fulwry.jpg', 'JPG', 'Imagem', 42, null, null), -- 115

-- Imagem dos ingredientes e refeições PT.2
('Omelete de legumes', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1725843518/alimentos-refeicoes/vdpznurtw1bynvbj5t2y.jpg', 'JPG', 'Imagem', null, null, 6), -- 116
('Frango com Legumes Salteados', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1725844366/alimentos-refeicoes/auvjnesqbzey3naowvnz.jpg', 'JPG', 'Imagem', null, null, 7), -- 117
('Iogurte Grego com Frutas e Granolas', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1725843068/alimentos-refeicoes/bkdlxotd3hfxoxgweayc.jpg', 'JPG', 'Imagem', null, null, 8), -- 118
('Frango com Arroz Integral e Salada', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1725843906/alimentos-refeicoes/xuduwuadjfq1qld3pbjx.jpg', 'JPG', 'Imagem', null, null, 9), -- 119
('Lentilha com Legumes e Pão Integral', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1728162184/alimentos-refeicoes/i8grd1ezdhpxmhhmzfa7.png', 'PNG', 'Imagem', null, null, 10), -- 120
('Aveia com Frutas e Castanhas', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1728160226/alimentos-refeicoes/xjejw2ig5ejjrxwbjdq6.jpg', 'JPG', 'Imagem', null, null, 11), -- 121
('Macarrão Integral com Molho de Tomate e Frango', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1725843343/alimentos-refeicoes/l4o6jwuvzsadweut0xob.jpg', 'JPG', 'Imagem', null, null, 12), -- 122
('Peixe Assado com Batatas Assadas e Espinafre', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1728162280/alimentos-refeicoes/bckmrkbiimhhxfovixav.png', 'PNG', 'Imagem', null, null, 13), -- 123
('Tapioca Recheada', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1725842457/alimentos-refeicoes/wu2kdyfxrmvwwjkiras5.jpg', 'JPG', 'Imagem', null, null, 14), -- 124
('Strogonoff de Frango com Legumes e Macarrão Integral', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1728160579/alimentos-refeicoes/jegfg51hfv2c8il6ixix.jpg', 'JPG', 'Imagem', null, null, 15), -- 125
('Carne Magra com Purê de Batata Doce e Brocolis', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1728160682/alimentos-refeicoes/y2btwxa0ycgts0afkubq.jpg', 'JPG', 'Imagem', null, null, 16), -- 126
('Pão Integral com Ovo Mexido e Abacate', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1725842938/alimentos-refeicoes/ljlqoktzhphzwjrcdbds.jpg', 'JPG', 'Imagem', null, null, 17), -- 127
('Lentilhas com Legumes e Arroz Integral', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1728161062/alimentos-refeicoes/re2spzh7tc7rgb1f7wjn.jpg', 'JPG', 'Imagem', null, null, 18), -- 128
('Overnight Oats com Frutas Vermelhas', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1728161306/alimentos-refeicoes/yg2f0pauw2iykuye1xnz.jpg', 'JPG', 'Imagem', null, null, 19), -- 129
('Feijoada Light com Acompanhamentos', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1728160847/alimentos-refeicoes/wku4u3cqtio8st4g3s7f.jpg', 'JPG', 'Imagem', null, null, 20), -- 130
('Wraps de Frango com Salada', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1728160138/alimentos-refeicoes/hol64pqirtxoa5huyxl3.jpg', 'JPG', 'Imagem', null, null, 21), -- 131
('Risoto de Abobora com Frango Desfiado', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1728160398/alimentos-refeicoes/acqolj0aamlc9zsj5yre.jpg', 'JPG', 'Imagem', null, null, 22), -- 132
('Salada Caprese com Pão Integral', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1728160459/alimentos-refeicoes/u5yz3cglam32pbrsmqu2.jpg', 'JPG', 'Imagem', null, null, 23), -- 133
('Omelete de Claras', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1725843427/alimentos-refeicoes/crktkxbinghbnsxz2g8k.jpg', 'JPG', 'Imagem', null, null, 24), -- 134
('Smoothie Verde', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1725842385/alimentos-refeicoes/vuwfupklremsgm4ehjf0.jpg', 'JPG', 'Imagem', null, null, 25), -- 135
('Panqueca de Banana', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1725843634/alimentos-refeicoes/e7opb5pkkmq6vyobc6sk.jpg', 'JPG', 'Imagem', null, null, 26), -- 136
('Salada de Atum', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1725843848/alimentos-refeicoes/vgfpaht8slaub12vybjh.jpg', 'JPG', 'Imagem', null, null, 27), -- 137
('Salada com Quinoa', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1728160500/alimentos-refeicoes/uirtcy3vi2lcu0aclxzp.jpg', 'JPG', 'Imagem', null, null, 28), -- 138

('Brocolis', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1728160276/alimentos-refeicoes/p2kuhkf0lkutw8bslycw.jpg', 'JPG', 'Imagem', null, 22, null), -- 139
('Queijo Branco', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1728160317/alimentos-refeicoes/cvgbwmhxs8gjai2knhsa.jpg', 'JPG', 'Imagem', null, 23, null), -- 140
('Espinafre', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1728160802/alimentos-refeicoes/uaptmowfdrvnli4y6zhf.jpg', 'JPG', 'Imagem', null, 24, null), -- 141
('Iogurte Grego', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1728160989/alimentos-refeicoes/g5f6qmqv79dzu1fkftdr.jpg', 'JPG', 'Imagem', null, 25, null), -- 142
('Frutas Vermelhas', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1728160883/alimentos-refeicoes/nv8enrkzfvnjrjnv3xhz.jpg', 'JPG', 'Imagem', null, 26, null), -- 143
('Granola', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1728160951/alimentos-refeicoes/ipetakx9vgp2bmfcuxek.jpg', 'JPG', 'Imagem', null, 27, null), -- 144
('Arroz Integral', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1728160189/alimentos-refeicoes/zsww38lkhlgwnuaenyl6.jpg', 'JPG', 'Imagem', null, 28, null), -- 145
('Lentilha', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1728161126/alimentos-refeicoes/prqdqtf5vhh2kty8mpcb.jpg', 'JPG', 'Imagem', null, 29, null), -- 146
('Pão Integral', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1728161449/alimentos-refeicoes/hb4jm6u3zd1zeg6aftop.jpg', 'JPG', 'Imagem', null, 30, null), -- 147
('Frutas', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1728160921/alimentos-refeicoes/gygq6ed0vy0vx4svn9xv.jpg', 'JPG', 'Imagem', null, 31, null), -- 148
('Castanha', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1728160723/alimentos-refeicoes/xtx0ayu1yoyyp80kyizb.jpg', 'JPG', 'Imagem', null, 32, null), -- 149
('Macarrão Integral', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1728161179/alimentos-refeicoes/afjscrciyzx4ramqx5nr.jpg', 'JPG', 'Imagem', null, 33, null), -- 150
('Mollho de Tomate', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1728161244/alimentos-refeicoes/ysobarj6ukmovf5bzglp.jpg', 'JPG', 'Imagem', null, 34, null), -- 151
('Queijo Cottage', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1728160359/alimentos-refeicoes/kosith5a1vb5ls037em2.jpg', 'JPG', 'Imagem', null, 35, null), -- 152
('Peito de Peru', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1728161518/alimentos-refeicoes/re14das44alc78myofbc.jpg', 'JPG', 'Imagem', null, 36, null), -- 153
('Semente de Chia', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1728160542/alimentos-refeicoes/quqjjhaddymtxp19zhwf.jpg', 'JPG', 'Imagem', null, 37, null), -- 154
('Laranja', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1728161024/alimentos-refeicoes/otra7ukhcn33viptly4v.jpg', 'JPG', 'Imagem', null, 38, null), -- 155
('Tortilha de Trigo Integral', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1728160051/alimentos-refeicoes/fqz2wacez61hrbbuma7p.jpg', 'JPG', 'Imagem', null, 39, null), -- 156
('Abobora', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1728161611/alimentos-refeicoes/se2xzijzlepyiif1twtm.jpg', 'JPG', 'Imagem', null, 40, null), -- 157
('Tomate', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1728160007/alimentos-refeicoes/bszj6acnaj4zemr8fjmw.jpg', 'JPG', 'Imagem', null, 41, null), -- 158
('Manjericão', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1728161214/alimentos-refeicoes/kpsdf48hxcoizxvocluj.jpg', 'JPG', 'Imagem', null, 42, null), -- 159
('Queijo Mussarela', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1728161275/alimentos-refeicoes/sanzwh9lyjiywfzggnjd.jpg', 'JPG', 'Imagem', null, 43, null), -- 160
('Tapioca', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1728160635/alimentos-refeicoes/gyodp7da9rz7k8occzxp.jpg', 'JPG', 'Imagem', null, 44, null), -- 161
('Banana', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1725843980/alimentos-refeicoes/bibriiq0qbbtwlfmxrzm.jpg', 'JPG', 'Imagem', null, 45, null), -- 162
('Água de Coco', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1728161647/alimentos-refeicoes/sjz3z65evwjjvsa5wyfz.jpg', 'JPG', 'Imagem', null, 46, null), -- 163

-- Imagens dos EX PT.3
    -- Inserir
('Supino Reto', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1725841024/exercicios/cccfmch0errvpqkbmmhk.jpg', 'JPG', 'Imagem', 43, null, null), -- 164
('Stiff', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1725840952/exercicios/tnkclokbzjv5vmjfaan7.jpg', 'JPG', 'Imagem', 44, null, null), -- 165
('Panturrilha no Smith', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1725840380/exercicios/egakeop3kgp4ditysod7.jpg', 'JPG', 'Imagem', 45, null, null), -- 166
('Remada Curvada', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1725840670/exercicios/o0en4u14hro3aavaioo2.jpg', 'JPG', 'Imagem', 46, null, null), -- 167
('Encolhimento de Ombros', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1725841698/exercicios/ncd6kmtkhdqfclfysjwz.jpg', 'JPG', 'Imagem', 47, null, null), -- 168
('Abdominal Oblíquo', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1725841857/exercicios/spzle8rtxoh84azi5f66.jpg', 'JPG', 'Imagem', 48, null, null), -- 169
('Supino Inclinado', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1725840999/exercicios/b65klkftbq8zqnai3bc1.jpg', 'JPG', 'Imagem', 49, null, null), -- 170
('Rosca Martelo', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1725840851/exercicios/lsgm8maumbjbrcargh59.jpg', 'JPG', 'Imagem', 50, null, null), -- 171
('Tríceps Testa', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1725841127/exercicios/rkuxadyryjoxyd97skf1.jpg', 'JPG', 'Imagem', 51, null, null), -- 172
('Agachamento no Hack', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1725841190/exercicios/phuwhosa9qw1sklp55pn.jpg', 'JPG', 'Imagem', 52, null, null), -- 173
('Extensão de Pernas', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1725839995/exercicios/vjvuamggd7hlmz6bga5y.jpg', 'JPG', 'Imagem', 53, null, null), -- 174
('Abdominal Bicicleta', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1725841737/exercicios/vosrgg4u7vbtl0u7u7ef.jpg', 'JPG', 'Imagem', 54, null, null), -- 175
('Barra Fixa', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1725841322/exercicios/hqmesw9gjbe8xgur3its.jpg', 'JPG', 'Imagem', 55, null, null), -- 176
('Remada Unilateral', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1725840731/exercicios/z8xxnaqwz8nxashfdudg.jpg', 'JPG', 'Imagem', 56, null, null), -- 177
('Crucifixo Invertido', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1725841501/exercicios/nf67t7yrfknobeso9aq8.jpg', 'JPG', 'Imagem', 57, null, null), -- 178
('Rosca Concentrada', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1725840798/exercicios/iiy05rneojyqv9wkhajt.jpg', 'JPG', 'Imagem', 58, null, null), -- 179
('Abdominal Canivete', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1725841767/exercicios/ubbibdfobs2eugnkgzjg.jpg', 'JPG', 'Imagem', 59, null, null), -- 180

-- Videos dos EX PT.2
-- Inserir
('Supino Reto', 'https://youtu.be/EZMYCLKuGow?si=jnaC_IAEfxxGKVxi', 'MP4', 'Video', 43, null, null), -- 181
('Stiff', 'https://youtu.be/u1E3_u2gJYE?si=JtssxBne9_tWU0YO', 'MP4', 'Video', 44, null, null), -- 182
('Panturrilha no Smith', 'https://youtu.be/7XCu5iGBWxM?si=1yMjzAzpwXA-F6yF', 'MP4', 'Video', 45, null, null), -- 183
('Remada Curvada', 'https://youtu.be/TfxJMertfsw?si=kZJd3170ZAdUUpDt', 'MP4', 'Video', 46, null, null), -- 184
('Encolhimento de Ombros', 'https://youtu.be/RhGjwIUe16E?si=10hwVmlEuinqKsHv', 'MP4', 'Video', 47, null, null), -- 185
('Abdominal Oblíquo', 'https://youtube.com/shorts/wWtaDcMwU20?si=AKB7NpCL5OnVLvNZ', 'MP4', 'Video', 48, null, null), -- 186
('Supino Inclinado', 'https://youtu.be/WP1VLAt8hbM?si=iamvtMBCDuZZI9ia', 'MP4', 'Video', 49, null, null), -- 187
('Rosca Martelo', 'https://youtu.be/0qkQy8V2FC0?si=vz1PqTpYCTrN9wB6', 'MP4', 'Video', 50, null, null), -- 188
('Tríceps Testa', 'https://youtu.be/0M-1walYH4Y?si=7ne3f_a1D4KZMg6K', 'MP4', 'Video', 51, null, null), -- 189
('Agachamento no Hack', 'https://youtu.be/5Ix3fjf4w9o?si=M8vOMvvzmhDDKOSd', 'MP4', 'Video', 52, null, null), -- 190
('Extensão de Pernas', 'https://youtu.be/5ryXSEu-Itc?si=g5-scTku4W9heo5I', 'MP4', 'Video', 53, null, null), -- 191
('Abdominal Bicicleta', 'https://youtu.be/apmprS8H1MY?si=e_tiwTnw4ZvAsGQK', 'MP4', 'Video', 54, null, null), -- 192
('Barra Fixa', 'https://youtu.be/thg6cGXSlvY?si=vxWqZPmLealNRmem', 'MP4', 'Video', 55, null, null), -- 193
('Remada Unilateral', 'https://youtu.be/m4h4jT9patY?si=WLrqRcUJJypk7Zd7', 'MP4', 'Video', 56, null, null), -- 194
('Crucifixo Invertido', 'https://youtu.be/5HDkxzxe400?si=gV6kd-T0LoNTRl72', 'MP4', 'Video', 57, null, null), -- 195
('Rosca Concentrada', 'https://youtu.be/EEpvOQAAtRo?si=Wiv6b69aC_GlRrRL', 'MP4', 'Video', 58, null, null), -- 196
('Abdominal Canivete', 'https://youtu.be/yFKPFCnpFJs?si=v1cPSSfc4TJTLQym', 'MP4', 'Video', 59, null, null), -- 197

-- Imagem dos ingredientes e refeições PT.3
('Smoothie Protéico', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1725842279/alimentos-refeicoes/teho3mzmz2kl4wfefsxq.jpg', 'JPG', 'Imagem', null, null, 29), -- 198
('Carne Moida com Batata Doce', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1725844146/alimentos-refeicoes/cykddweboj4mcv2jvit7.jpg', 'JPG', 'Imagem', null, null, 30), -- 199
('Bife com Salada', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1725844046/alimentos-refeicoes/aydhgtgtlhxdq0xin9yg.jpg', 'JPG', 'Imagem', null, null, 31), -- 200
('Tilápia com Legumes', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1725842546/alimentos-refeicoes/besnhfx6pxfujkam3r7r.jpg', 'JPG', 'Imagem', null, null, 32), -- 201

('Tilápia', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1728159964/alimentos-refeicoes/ehvjvfkuqvrmloxwkmaz.jpg', 'JPG', 'Imagem', null, 47, null), -- 202
('Whey', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1728159903/alimentos-refeicoes/wsa6ichylsdntqyaxds1.jpg', 'JPG', 'Imagem', null, 48, null), -- 203
('Cenoura', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1725844227/alimentos-refeicoes/ccrollgp73bnbnzq7khm.jpg', 'JPG', 'Imagem', null, 49, null), -- 204
('Cebola Roxa', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1728160759/alimentos-refeicoes/zfobvztgcj3yykv8ytlq.jpg', 'JPG', 'Imagem', null, 50, null), -- 205
('Pepino', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1728161552/alimentos-refeicoes/ihcawasbs3wwq01kwwoy.jpg', 'JPG', 'Imagem', null, 51, null), -- 206

('Luigi Vicchietti (ylu1Gi@@)', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1725825401/foto-perfil/y55dehzw8onaa7gdhf5x.jpg', 'JPG', 'Imagem', null, null, null), -- 207
('Marcos Silva Oliveira Pinto Santos (marC@SSilV4)', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1725826712/foto-perfil/kybkqcdagiwkrzm4g2ht.jpg', 'JPG', 'Imagem', null, null, null), -- 208
('Will Dantas (w1llSal4d@)', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1725827057/foto-perfil/t2oc9mv6paoc0rw1zrq9.jpg', 'JPG', 'Imagem', null, null, null), -- 209
('Roberta Ferreira (roberTT4F@)', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1725826825/foto-perfil/cacxy5jgerqh7x9qvtoc.jpg', 'JPG', 'Imagem', null, null, null), -- 210
('Pedro Gomes (pedR0G@)', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1725826881/foto-perfil/ifshby1rebpupgqklczf.webp', 'WEBP', 'Imagem', null, null, null); -- 211


INSERT INTO mural (usuario_id, dt_postagem, midia_id)
VALUES
(1, '2024-01-01 19:31:00', 47), -- 1
(1,'2024-03-26 11:42:50', 48), -- 2

(3, '2024-01-08 11:42:50', 49), -- 3
(3, '2024-04-08 11:42:50', 50), -- 4
(3, '2024-07-05 09:22:54', 51); -- 5

-- Atualizar usuário Luigi Vicchietti com id_usuario = 1
UPDATE usuario
SET midia_id = 207
WHERE id_usuario = 1;

-- Atualizar usuário Marcos Silva Oliveira Pinto Santos com id_usuario = 2
UPDATE usuario
SET midia_id = 208
WHERE id_usuario = 2;

-- Atualizar usuário Will Dantas com id_usuario = 3
UPDATE usuario
SET midia_id = 209
WHERE id_usuario = 3;

-- Atualizar usuário Roberta Ferreira com id_usuario = 4
UPDATE usuario
SET midia_id = 210
WHERE id_usuario = 4;

-- Atualizar usuário Pedro Gomes com id_usuario = 5
UPDATE usuario
SET midia_id = 211
WHERE id_usuario = 5;
