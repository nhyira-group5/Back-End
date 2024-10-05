USE master;

IF EXISTS (SELECT 1 FROM sys.databases WHERE name = 'vitalisDB')
BEGIN
    ALTER DATABASE vitalisDB SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
    DROP DATABASE vitalisDB;
   	CREATE DATABASE vitalisDB;
END
GO

IF EXISTS (
    SELECT * 
    FROM sys.triggers 
    WHERE object_id = OBJECT_ID(N'[dbo].[cria_rotina]')
)
BEGIN
    DROP TRIGGER [dbo].[cria_rotina];
END
GO

IF OBJECT_ID(N'[dbo].[atualizar_rotinas_expiradas]', N'P') IS NOT NULL
BEGIN
    DROP PROCEDURE [dbo].[atualizar_rotinas_expiradas];
END
GO

IF OBJECT_ID(N'[dbo].[atualiza_rotinas_mensais]', N'P') IS NOT NULL
BEGIN
    DROP PROCEDURE [dbo].[atualiza_rotinas_mensais];
END
GO

USE vitalisDB;
GO

CREATE TABLE meta (
    id_meta INT IDENTITY(1,1) PRIMARY KEY,
    nome VARCHAR(100)
);

CREATE TABLE endereco (
    id_endereco INT IDENTITY(1,1) PRIMARY KEY,
    logradouro VARCHAR(100) NOT NULL,
    numero VARCHAR(100),
    bairro VARCHAR(100) NOT NULL,
    cidade VARCHAR(100) NOT NULL,
    estado CHAR(2) NOT NULL,
    complemento VARCHAR(100),
    cep CHAR(8)
);

CREATE TABLE especialidade (
    id_especialidade INT IDENTITY(1,1) PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE especialidade_por_meta (
    id_especialidade_meta INT IDENTITY(1,1) PRIMARY KEY,
    especialidade_id INT,
    meta_id INT,
    FOREIGN KEY (especialidade_id) REFERENCES especialidade(id_especialidade),
    FOREIGN KEY (meta_id) REFERENCES meta(id_meta)
);

CREATE TABLE tag (
    id_tag INT IDENTITY(1,1) PRIMARY KEY,
    nome VARCHAR(100) NOT NULL
);

CREATE TABLE exercicio (
    id_exercicio INT IDENTITY(1,1) PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(550)
);

CREATE TABLE tag_exercicio (
    id_tag_exercicio INT IDENTITY(1,1) PRIMARY KEY,
    tag_id INT,
    exercicio_id INT,
    FOREIGN KEY (tag_id) REFERENCES tag(id_tag),
    FOREIGN KEY (exercicio_id) REFERENCES exercicio(id_exercicio)
);

CREATE TABLE dieta (
    id_dieta INT IDENTITY(1,1) PRIMARY KEY,
    meta_id INT,
    nome VARCHAR(100) NOT NULL,
    descricao VARCHAR(500) NOT NULL,
    FOREIGN KEY (meta_id) REFERENCES meta(id_meta)
);

CREATE TABLE refeicao (
    id_refeicao INT IDENTITY(1,1) PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    preparo VARCHAR(MAX)
);

CREATE TABLE alimento (
    id_alimento INT IDENTITY(1,1) PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    carboidrato FLOAT NOT NULL,
    proteina FLOAT NOT NULL,
    gordura FLOAT NOT NULL
);

CREATE TABLE midia (
    id_midia INT IDENTITY(1,1) PRIMARY KEY,
    exercicio_id INT,
    alimento_id INT,
    refeicao_id INT,
    nome VARCHAR(200) NOT NULL,
    caminho VARCHAR(1000) NOT NULL,
    extensao VARCHAR(10) NOT NULL,
    tipo VARCHAR(20) CHECK (tipo IN ('Imagem', 'Video')),
    FOREIGN KEY (exercicio_id) REFERENCES exercicio(id_exercicio),
    FOREIGN KEY (alimento_id) REFERENCES alimento(id_alimento),
    FOREIGN KEY (refeicao_id) REFERENCES refeicao(id_refeicao)
);

CREATE TABLE usuario (
    id_usuario INT IDENTITY(1,1) PRIMARY KEY,
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
    FOREIGN KEY (endereco_id) REFERENCES endereco(id_endereco),
    FOREIGN KEY (midia_id) REFERENCES midia(id_midia),
    FOREIGN KEY (personal_id) REFERENCES usuario(id_usuario)
);

CREATE TABLE especialidade_por_personal (
    id_especialidade_personal INT IDENTITY(1,1) PRIMARY KEY,
    personal_id INT,
    especialidade_id INT,
    FOREIGN KEY (personal_id) REFERENCES usuario(id_usuario),
    FOREIGN KEY (especialidade_id) REFERENCES especialidade(id_especialidade)
);

CREATE TABLE contrato (
    id_contrato INT IDENTITY(1,1) PRIMARY KEY,
    usuario_id INT,
    personal_id INT,
    afiliacao TINYINT NOT NULL CHECK (afiliacao IN (0, 1, 2)), -- 0 para finalizado, 1 para ativo e 2 para pendente
    inicio_contrato DATE,
    fim_contrato DATE,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id_usuario),
    FOREIGN KEY (personal_id) REFERENCES usuario(id_usuario)
);

CREATE TABLE chat (
    id_chat INT IDENTITY(1,1) PRIMARY KEY,
    usuario_id INT,
    personal_id INT,
    ativo TINYINT,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id_usuario),
    FOREIGN KEY (personal_id) REFERENCES usuario(id_usuario)
);

CREATE TABLE mensagem (
    id_mensagem INT IDENTITY(1,1) PRIMARY KEY,
    chat_id INT,
    remetente_id INT,
    destinatario_id INT,
    assunto VARCHAR(255),
    data_hora DATETIME NOT NULL,
    FOREIGN KEY (chat_id) REFERENCES chat(id_chat),
    FOREIGN KEY (remetente_id) REFERENCES usuario(id_usuario),
    FOREIGN KEY (destinatario_id) REFERENCES usuario(id_usuario)
);

CREATE TABLE rotina_usuario (
    id_rotina_usuario INT IDENTITY(1,1) PRIMARY KEY,
    usuario_id INT,
    meta_id INT,
    rotina_alternativa INT NOT NULL,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id_usuario),
    FOREIGN KEY (meta_id) REFERENCES meta(id_meta)
);

CREATE TABLE rotina_mensal (
    id_rotina_mensal INT IDENTITY(1,1) PRIMARY KEY,
    rotina_usuario_id INT,
    mes INT CHECK (mes IN (1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12)),
    ano INT,
    concluido TINYINT,
    FOREIGN KEY (rotina_usuario_id) REFERENCES rotina_usuario(id_rotina_usuario)
);

CREATE TABLE rotina_semanal (
    id_rotina_semanal INT IDENTITY(1,1) PRIMARY KEY,
    rotina_mensal_id INT,
    num_semana INT CHECK (num_semana IN (1, 2, 3, 4, 5)) NOT NULL,
    concluido TINYINT,
    FOREIGN KEY (rotina_mensal_id) REFERENCES rotina_mensal(id_rotina_mensal)
);

CREATE TABLE rotina_diaria (
    id_rotina_diaria INT IDENTITY(1,1) PRIMARY KEY,
    rotina_semanal_id INT,
    dia INT CHECK (dia IN (1, 2, 3, 4, 5, 6, 7)),
    concluido TINYINT,
    FOREIGN KEY (rotina_semanal_id) REFERENCES rotina_semanal(id_rotina_semanal)
);

CREATE TABLE treino (
    id_treino INT IDENTITY(1,1) PRIMARY KEY,
    exercicio_id INT,
    rotina_diaria_id INT,
    concluido TINYINT,
    repeticao INT NOT NULL,
    serie INT NOT NULL,
    tempo TIME NOT NULL,
    FOREIGN KEY (exercicio_id) REFERENCES exercicio(id_exercicio),
    FOREIGN KEY (rotina_diaria_id) REFERENCES rotina_diaria(id_rotina_diaria)
);

CREATE TABLE refeicao_diaria (
    id_refeicao_diaria INT IDENTITY(1,1) PRIMARY KEY,
    refeicao_id INT,
    rotina_diaria_id INT,
    concluido TINYINT,
    FOREIGN KEY (refeicao_id) REFERENCES refeicao(id_refeicao),
    FOREIGN KEY (rotina_diaria_id) REFERENCES rotina_diaria(id_rotina_diaria)
);

CREATE TABLE refeicao_por_dieta (
    id_refeicao_dieta INT IDENTITY(1,1) PRIMARY KEY,
    refeicao_id INT,
    dieta_id INT,
    FOREIGN KEY (refeicao_id) REFERENCES refeicao(id_refeicao),
    FOREIGN KEY (dieta_id) REFERENCES dieta(id_dieta)
);

CREATE TABLE metrica (
    id_metrica INT IDENTITY(1,1) PRIMARY KEY,
    metrica VARCHAR(100)
);

CREATE TABLE alimento_por_refeicao (
    id_alimento_refeicao INT IDENTITY(1,1) PRIMARY KEY,
    refeicao_id INT,
    alimento_id INT,
    metrica_id INT,
    qtd_alimento INT NOT NULL,
    FOREIGN KEY (refeicao_id) REFERENCES refeicao(id_refeicao),
    FOREIGN KEY (alimento_id) REFERENCES alimento(id_alimento),
    FOREIGN KEY (metrica_id) REFERENCES metrica(id_metrica)
);

CREATE TABLE assinatura (
    id_assinatura INT IDENTITY(1,1) PRIMARY KEY,
    nome VARCHAR(100) NOT NULL,
    valor FLOAT NOT NULL
);

CREATE TABLE pagamento (
    id_pagamento INT IDENTITY(1,1) PRIMARY KEY,
    usuario_id INT,
    assinatura_id INT,
    data_pagamento DATE NOT NULL,
    tipo VARCHAR(100) CHECK (tipo IN ('Cartão de débito', 'Cartão de crédito', 'PIX')) NOT NULL, 
    FOREIGN KEY (usuario_id) REFERENCES usuario(id_usuario),
    FOREIGN KEY (assinatura_id) REFERENCES assinatura(id_assinatura)
);

/*
CREATE TABLE cartao (
    id_cartao INT IDENTITY(1,1) PRIMARY KEY,
    numero CHAR(16) NOT NULL,
    nome_titular VARCHAR(200) NOT NULL,
    validade DATE NOT NULL,
    cvv CHAR(3) NOT NULL,
    bandeira VARCHAR(100) NOT NULL,
    usuario_id INT,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id_usuario)
);
*/

CREATE TABLE denuncia (
    id_denuncia INT IDENTITY(1,1) PRIMARY KEY,
    denunciado_id INT,
    vitima_id INT,
    titulo VARCHAR(75) NOT NULL,
    assunto VARCHAR(255) NOT NULL,
    FOREIGN KEY (denunciado_id) REFERENCES usuario(id_usuario),
    FOREIGN KEY (vitima_id) REFERENCES usuario(id_usuario)
);

CREATE TABLE ficha (
    id_ficha INT IDENTITY(1,1) PRIMARY KEY,
    usuario_id INT,
    peso FLOAT,
    altura FLOAT,
    problema_cardiaco TINYINT,
    dor_peito_atividade TINYINT,
    dor_peito_ultimo_mes TINYINT,
    problema_osseo_articular TINYINT,
    medicamento_pressao_coracao TINYINT,
    impedimento_atividade TINYINT,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id_usuario)
);

CREATE TABLE lembrete (
    id_lembrete INT IDENTITY(1,1) PRIMARY KEY,
    usuario_id INT,
    conteudo VARCHAR(500) NOT NULL,
    data_lembrete DATETIME,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id_usuario)
);

CREATE TABLE mural (
    id_mural INT IDENTITY(1,1) PRIMARY KEY,
    dt_postagem DATETIME NOT NULL,
    usuario_id INT,
    midia_id INT,
    FOREIGN KEY (usuario_id) REFERENCES usuario(id_usuario),
    FOREIGN KEY (midia_id) REFERENCES midia(id_midia)
);