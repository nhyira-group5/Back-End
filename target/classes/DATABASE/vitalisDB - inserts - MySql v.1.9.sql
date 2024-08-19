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

INSERT INTO midia (nome, caminho, extensao)
VALUES
('Amendoim', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114337/alimentos-refeicoes/vt8bw3xzrhi7kl4zsgqn.jpg', 'JPG'), -- 1
('Abacate', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114337/alimentos-refeicoes/uwtjrjjhi8jdzybyywum.jpg', 'JPG'), -- 2
('Virada Paulista', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114337/alimentos-refeicoes/a2fog1ceujlidytlq8mb.jpg', 'JPG'), -- 3
('Iogurte Natrural', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114337/alimentos-refeicoes/a3c8smqnoit2zlnkmolr.jpg', 'JPG'), -- 4
('Torresmo', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114337/alimentos-refeicoes/aovf0uyvjadxwhemqa9q.jpg', 'JPG'), -- 5
('Salada de Frango Grelhado', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114336/alimentos-refeicoes/yjvjplvgmbm9iiphcazw.jpg', 'JPG'), -- 6
('Salmão com Quinoa', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114336/alimentos-refeicoes/dwtji5jplzfbwyz87s1y.jpg', 'JPG'), -- 7
('Salmão', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114336/alimentos-refeicoes/ce2cadaqxklgvnif9fgb.jpg', 'JPG'), -- 8
('Farofa', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114334/alimentos-refeicoes/h1tmwmeywou5tybswnxl.jpg', 'JPG'), -- 9
('Salada de Folhas Verdes', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114336/alimentos-refeicoes/aho0uljh4s5bnurbhdyx.jpg', 'JPG'), -- 10
('Ovo', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114336/alimentos-refeicoes/ltzbrusehrpnr7hrart8.jpg', 'JPG'), -- 11
('Linguiça', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114336/alimentos-refeicoes/hzgq0nexjgmos5dsu9fe.jpg', 'JPG'), -- 12
('Omelete de Claras', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114336/alimentos-refeicoes/lsiko15fxeljqxknyctk.jpg', 'JPG'), -- 13
('Frango com Batata Doce', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114336/alimentos-refeicoes/fyarzkwh5evynkfsne3m.png', 'PNG'), -- 14
('Quinoa Cozida', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114336/alimentos-refeicoes/bflbinmmdvgbicthxm4g.jpg', 'JPG'), -- 15
('Legumes', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114336/alimentos-refeicoes/eqvtmlcdxxealazxybnb.jpg', 'JPG'), -- 16
('Couve', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114335/alimentos-refeicoes/ojwatdeipahfpzs2ybdc.jpg', 'JPG'), -- 17
('Carne de vaca', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114335/alimentos-refeicoes/jywwig4axaeknweo2lwb.jpg', 'JPG'), -- 18
('Carne de porco', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114335/alimentos-refeicoes/mqoe5ulczacdmt6yf009.jpg', 'JPG'), -- 19
('Feijão Preto', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114335/alimentos-refeicoes/t7rcph08fvnvf3x4gezr.jpg', 'JPG'), -- 20
('Frango', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114335/alimentos-refeicoes/zjk4sr6ks3cvo8yl81r9.jpg', 'JPG'), -- 21
('Carne com Aveia de Flocos', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114335/alimentos-refeicoes/ow44exnjgm1ssi8taiav.png', 'PNG'), -- 22
('Batata Doce', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114334/alimentos-refeicoes/usewobqvvx4udhfrjfai.jpg', 'JPG'), -- 23
('Arroz Branco', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114334/alimentos-refeicoes/qrl878lcfpb6mr6sjrxy.png', 'PNG'), -- 24
('Feijão Carioca', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114334/alimentos-refeicoes/wvpq35wxctpzhlwf6qlv.jpg', 'JPG'), -- 25
('Aveia em Flocos', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114334/alimentos-refeicoes/ntlpyde4xobqrsajhwnz.jpg', 'JPG'), -- 26

('Flexão de Braço', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114562/exercicios/hicln1lwqn2c3vyoaj9a.png', 'PNG'), -- 27
('Agachamento Livre', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114561/exercicios/pgvfsvdimamebrlrq8lo.png', 'PNG'), -- 28
('Prancha Abdominal', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114561/exercicios/ivw5kvvgd3i0fezhp3hm.jpg', 'JPG'), -- 29
('Tríceps Pulley', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114561/exercicios/llwqzomppzetvz6uuh8q.jpg', 'JPG'), -- 30
('Leg Press', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114561/exercicios/v8grdjhkjz4omtdmo4ex.jpg', 'JPG'), -- 31
('Rosca Direta', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114561/exercicios/cjnipso8b9ulpandg5bv.jpg', 'JPG'), -- 32
('Remada Alta', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114561/exercicios/mmqse4o4ovnqag8wedjx.jpg', 'JPG'), -- 33
('Levantamento Terra', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114559/exercicios/bzwkb7omeomketkbmiec.jpg', 'JPG'), -- 34
('Crucifíxo com Halteres', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114560/exercicios/yutkonjrjmfv7d8iypts.jpg', 'JPG'), -- 35
('Elevação Frontal', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114560/exercicios/frbkgulpowtszrsv7jvt.jpg', 'JPG'), -- 36
('Abdominal Crunch', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114560/exercicios/u2fq2gpzi5whbvbaksd1.jpg', 'JPG'), -- 37
('Puxada Frontal', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114560/exercicios/hadejd8zdzyzfjczbcyv.jpg', 'JPG'), -- 38
('Desenvolvimento de Ombros', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114559/exercicios/uz4heaadlg5kbg7kfw6x.jpg', 'JPG'), -- 39
('Prancha Lateral', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114559/exercicios/mjmi8zvimuknb9klj93j.jpg', 'JPG'), -- 40
('Flexão de Pernas', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114559/exercicios/d8sg5iugt4g33jxjjmxz.webp', 'WEBP'), -- 41
('Extensão de Tríceps', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114559/exercicios/qa1qcjchjowfvixjfrji.jpg', 'JPG'), -- 42
('Elevação Lateral', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718114559/exercicios/osnce7yjg5yv7crt5hur.jpg', 'JPG'), -- 43
('Flexão Lateral', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718121243/exercicios/uni6orw0bcgl3stezl6m.jpg', 'JPG'), -- 44
('Prancha Superman', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718121529/exercicios/tcpfhxtr5ts5uk6tug3m.png', 'PNG'), -- 45
('Flexão de Braço Inclinada', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718122137/exercicios/tpkblqnusy1iljk6bas5.webp', 'WEBP'), -- 46

('foto-mural-1', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718126019/mural/cw64tvqvz5zdxds2fcbg.jpg', 'JPG'), -- 47
('foto-mural-2', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718126019/mural/ehz6bscm88abrqts5thg.jpg', 'JPG'), -- 48

('foto-mural-3', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718126494/mural/pwdze5jkmp2g59djagci.jpg', 'JPG'), -- 49
('foto-mural-4', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718126494/mural/k5svjihaojkf1bo7ld3m.jpg', 'JPG'), -- 50
('foto-mural-5', 'https://res.cloudinary.com/dpzjmq6x5/image/upload/v1718126494/mural/hmggcwuqr57hgoqagytp.jpg', 'JPG'); -- 51
--
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
('Melhoria da Resistência Cardiovascular'),
('Redução do Estresse'),
('Aumento da Flexibilidade'),
('Melhoria da Postura'),
('Redução da Pressão Arterial');

-- Inserção de correspondências entre especialidades e metas
INSERT INTO especialidade_por_meta (especialidade_id, meta_id) VALUES
(1, 1),   -- Emagrecimento -> Emagrecimento
(2, 1),   -- Alta Intensidade (HIIT) -> Emagrecimento
(3, 2),   -- Força e Resistência -> Ganho de Massa Muscular
(4, 4),   -- Flexibilidade e Mobilidade -> Melhoria da Postura
(5, 3),   -- Resistência Cardiovascular -> Melhoria da Resistência Cardiovascular
(6, 6),   -- Peso Corporal -> Emagrecimento
(6, 2),   -- Peso Corporal -> Ganho de massa muscular
(7, 7);   -- Alongamento e Relaxamento -> Redução do Estresse

INSERT INTO metrica (metrica) VALUES
('gramas'),
('quilogramas'),
('miligramas'),
('litros'),
('mililitros'),
('unidade'),
('xícaras'),
('colheres de sopa'),
('colheres de chá');

-- Inserção de refeições
INSERT INTO refeicao (midia_id, nome, preparo) VALUES
(3, 'Virada Paulista', 'Refeição típica da culinária paulista, composta por arroz, feijão, carne de porco, linguiça, couve refogada, torresmo, ovo frito e farofa.'),
(6, 'Salada de Frango Grelhado', 'Refeição leve e saudável, composta por salada de folhas verdes, peito de frango grelhado, legumes grelhados e iogurte natural desnatado.'),
(7, 'Salmão com Quinoa', 'Refeição nutritiva e pouco calórica, composta por salmão assado, quinoa cozida, abacate e omelete de claras.'),
(13, 'Frango e batata doce', 'Refeição calórica e rica em proteínas para estimular o crescimento muscular, incluindo frango assado, batata doce assada, arroz branco e feijão preto.'),
(22, 'Carne com aveia de flocos', 'Refeição balanceada com carboidratos e proteínas para fornecer energia e promover o ganho de massa muscular, incluindo carne vermelha grelhada, aveia em flocos, ovo cozido e amendoim.');

-- Inserção de alimentos da virada paulista
INSERT INTO alimento (midia_id, nome, carboidrato, proteina, gordura) VALUES
(23, 'Arroz Branco', 45.0, 4.0, 0.5),
(25, 'Feijão Carioca', 30.0, 7.0, 1.5),
(19, 'Carne de Porco', 0.0, 20.0, 5.0),
(12, 'Linguiça', 1.0, 18.0, 10.0),
(17, 'Couve', 5.0, 2.0, 0.5),
(5, 'Torresmo', 2.0, 5.0, 10.0),
(11, 'Ovo', 1.0, 7.0, 5.0),						-- Vai ser usado também na refeição Carne com aveia de flocos"
(9, 'Farofa', 20.0, 2.0, 10.0);

-- Inserção de alimentos da Salada de Frango Grelhado
INSERT INTO alimento (midia_id, nome, carboidrato, proteina, gordura) VALUES
(10, 'Salada de Folhas Verdes', 2.0, 1.0, 0.2),
(21, 'Frango', 0.0, 30.0, 3.5),					-- Vai ser usado também na refeição "Frango e batata doce"
(16, 'Legumes', 10.0, 2.0, 0.5),
(4, 'Iogurte Natural', 4.0, 8.0, 0.2);

-- Inserção de alimentos de Salmão com Quinoa
INSERT INTO alimento (midia_id, nome, carboidrato, proteina, gordura) VALUES
(8, 'Salmão Assado', 0.0, 25.0, 12.0),
(15, 'Quinoa Cozida', 20.0, 4.0, 1.5),
(2, 'Abacate', 1.0, 2.0, 14.0),
(13, 'Omelete de Claras', 1.0, 15.0, 3.0);

-- Inserção de alimentos de Frango e batata doce
INSERT INTO alimento (midia_id, nome, carboidrato, proteina, gordura) VALUES
(23, 'Batata Doce', 30.0, 2.0, 0.5),
(20, 'Feijão Preto', 30.0, 7.0, 1.5);

-- Inserção de alimentos de Carne com aveia de flocos
INSERT INTO alimento (midia_id, nome, carboidrato, proteina, gordura) VALUES
(18, 'Carne de vaca', 0.0, 25.0, 10.0),
(26, 'Aveia em Flocos', 66.0, 16.9, 7.0),
(1, 'Amendoim', 16.0, 26.0, 49.0);

-- Inserção de alimentos por refeição
INSERT INTO alimento_por_refeicao (refeicao_id, alimento_id, metrica_id, qtd_alimento) VALUES
(1, 1, 6, 1),    -- Virada Paulista: Arroz Branco - 1 unidade
(1, 2, 6, 1),    -- Virada Paulista: Feijão Carioca - 1 unidade
(1, 3, 1, 150),  -- Virada Paulista: Carne de Porco - 150 gramas
(1, 4, 6, 1),    -- Virada Paulista: Linguiça - 1 unidade
(1, 5, 1, 100),  -- Virada Paulista: Couve - 100 gramas
(1, 6, 1, 100),  -- Virada Paulista: Torresmo - 100 gramas
(1, 7, 6, 1),    -- Virada Paulista: Ovo - 1 unidade
(1, 8, 1, 50),   -- Virada Paulista: Farofa - 50 gramas

(2, 9, 1, 100),  -- Salada de Frango Grelhado: Salada de Folhas Verdes - 100 gramas
(2, 10, 1, 150), -- Salada de Frango Grelhado: Frango - 150 gramas
(2, 11, 1, 100), -- Salada de Frango Grelhado: Legumes - 100 gramas
(2, 12, 1, 150), -- Salada de Frango Grelhado: Iogurte Natural - 150 gramas

(3, 13, 1, 150), -- Salmão com Quinoa: Salmão Assado - 150 gramas
(3, 14, 1, 100), -- Salmão com Quinoa: Quinoa Cozida - 100 gramas
(3, 15, 6, 1),   -- Salmão com Quinoa: Abacate - 1 unidade
(3, 16, 6, 1),   -- Salmão com Quinoa: Omelete de Claras - 1 unidade

(4, 10, 1, 200), -- Frango e batata doce: Frango - 200 gramas
(4, 17, 1, 200), -- Frango e batata doce: Batata Doce - 200 gramas
(4, 2, 6, 1),    -- Frango e batata doce: Feijão Preto - 1 unidade

(5, 18, 1, 200), -- Carne com aveia de flocos: Carne de vaca - 200 gramas
(5, 7, 1, 50),   -- Carne com aveia de flocos: Aveia em Flocos - 50 gramas
(5, 8, 1, 30);   -- Carne com aveia de flocos: Amendoim - 30 gramas

INSERT INTO dieta(nome, descricao, meta_id)
VALUES
('Emagrecimento', 'Uma dieta de emagrecimento bem-sucedida deve ser equilibrada, sustentável e personalizada para atender às necessidades individuais. O objetivo principal é criar um déficit calórico, onde a quantidade de calorias ingeridas é menor do que a quantidade de calorias gastas.', 1),
('Ganho de massa', 'Uma dieta para ganho de massa muscular precisa fornecer um excedente calórico, ou seja, consumir mais calorias do que se gasta, junto com uma quantidade adequada de proteínas, carboidratos e gorduras saudáveis para promover a construção muscular. Além disso, a alimentação deve ser distribuída ao longo do dia para manter um fornecimento constante de nutrientes', 2);

INSERT INTO refeicao_por_dieta (refeicao_id, dieta_id)
VALUES
(2, 1),
(3, 1),
(4, 1),
(5, 1),

(1, 2),
(2, 2),
(3, 2),
(4, 2),
(5, 2);

INSERT INTO tag (nome) VALUES
('Peitoral'),
('Pernas'),
('Costas'),
('Ombros'),
('Braços'),
('Abdômen'),
('Glúteos'),
('Trapézio'),
('Deltoides'),
('Bíceps'),
('Tríceps'),
('Quadríceps'),
('Isquiotibiais'),
('Lombar'),
('Oblíquos');
    
INSERT INTO exercicio (midia_id, nome, descricao) VALUES
(27, 'Flexão de Braço', 'Exercício que trabalha os músculos peitorais, deltoides e tríceps.'),
(28, 'Agachamento Livre', 'Exercício para fortalecimento dos músculos das pernas e glúteos.'),
(33, 'Remada Alta', 'Exercício para o desenvolvimento dos músculos das costas e dos ombros.'),
(37, 'Abdominal Crunch', 'Exercício para fortalecimento dos músculos abdominais.'),
(34, 'Levantamento Terra', 'Exercício composto que trabalha vários grupos musculares, incluindo os glúteos, costa e pernas.'),
(44, 'Flexão Lateral do Tronco', 'Exercício para fortalecer os músculos oblíquos.'),
(32, 'Rosca Direta', 'Exercício para o desenvolvimento dos músculos dos braços, especialmente os bíceps.'),
(36, 'Elevação Frontal', 'Exercício para o desenvolvimento dos músculos dos ombros, principalmente o deltoide anterior.'),
(29, 'Prancha Abdominal', 'Exercício estático para fortalecer os músculos abdominais e lombares.'),
(39, 'Desenvolvimento de Ombros', 'Exercício para o desenvolvimento dos músculos dos ombros, especialmente o deltoide lateral.'),
(31, 'Leg Press', 'Exercício para fortalecimento dos músculos das pernas, especialmente quadríceps e glúteos.'),
(35, 'Crucifixo com Halteres', 'Exercício para o desenvolvimento dos músculos do peitoral, especialmente a porção esternal.'),
(30, 'Tríceps Pulley', 'Exercício para o desenvolvimento dos músculos tríceps braquial, situados na parte posterior do braço.'),
(40, 'Prancha Lateral', 'Exercício estático para fortalecer os músculos abdominais e oblíquos.'),
(41, 'Flexão de Pernas', 'Exercício para fortalecimento dos músculos isquiotibiais e glúteos.'),
(38, 'Puxada Frontal', 'Exercício para o desenvolvimento dos músculos das costas, especialmente os latíssimos do dorso.'),
(43, 'Elevação Lateral', 'Exercício para o desenvolvimento dos músculos dos ombros, principalmente o deltoide médio.'),
(42, 'Extensão de Tríceps', 'Exercício para o desenvolvimento dos músculos tríceps braquial, situados na parte posterior do braço.'),
(45, 'Prancha Superman', 'Exercício para fortalecer os músculos do core, lombar e glúteos.'),
(46, 'Flexão de Braço Inclinada', 'Variação da flexão de braço que trabalha mais intensamente os músculos do peitoral superior.');

-- Associações para o exercício 1 (Flexão de Braço)
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(1, 1), -- Peitoral
(9, 1), -- Deltoides
(10, 1); -- Tríceps

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
(10, 13), -- Tríceps
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
(10, 18), -- Tríceps
(5, 18); -- Braços

-- Associações para o exercício 19 (Prancha Superman)
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(6, 19), -- Abdômen
(14, 19); -- Lombar

-- Associações para o exercício 20 (Flexão de Braço Inclinada)
INSERT INTO tag_exercicio (tag_id, exercicio_id) VALUES
(1, 20), -- Peitoral
(9, 20); -- Deltoides

INSERT INTO assinatura (nome, valor) VALUES
('Viva Vitalis', 49.99);

INSERT INTO endereco (logradouro, numero, bairro, cidade, estado, complemento, cep)
VALUES
('Avenida Wilson Carvalho', 10, 'Zerão', 'Macapá', 'AP', null, 68903025),
('Rua Verdum', 112, 'Vila Nasser', 'Campo Grande', 'MS', null, 79117360),
('Rua Alzira Gomes Queiros', 6, 'Jardim Eldorado', 'Ourinhos', 'SP', null, 19914550);

-- SENHA -> Daniel@23133 (todos)
INSERT INTO usuario (tipo, nickname, cpf, nome, dt_nasc, sexo, email, email_recuperacao, senha, midia_id, personal_id, endereco_id) 
VALUES 
(0, 'ylu1Gi@@', '56438153036', 'Luigi Vicchietti', '2005-01-17', 'M', 'luigi@gmail.com', 'padrao@gmail', '$2a$10$Ix.qCm5U71fFzjkd2/z3T.gKtgr9NzUzpqVOqAXU8uAcvv3ftooWS', null, null, null),
(1, 'marC@SSilV4', '92865867013', 'Marcos Silva Oliveira Pinto Santos', '1980-12-05', 'M', 'marcos@gmail.com', 'padrao@gmail', '$2a$10$Ix.qCm5U71fFzjkd2/z3T.gKtgr9NzUzpqVOqAXU8uAcvv3ftooWS', null, null, 1);

INSERT INTO ficha (usuario_id, peso, altura, problema_cardiaco, dor_peito_atividade, dor_peito_ultimo_mes, problema_osseo_articular, medicamento_pressao_coracao, impedimento_atividade) 
VALUES
(1, 58.60, 1.85, 0, 0, 0, 0, 0, 0);

INSERT INTO rotina_usuario (usuario_id, meta_id) VALUES
(1, 2);

-- SENHA -> Daniel@23133 (todos)
INSERT INTO usuario (tipo, nickname, cpf, nome, dt_nasc, sexo, email, email_recuperacao, senha, midia_id, personal_id, endereco_id) 
VALUES 
(0, 'w1llSal4d@', '95931984070', 'Will Dantas', '2004-03-31', 'M', 'will@gmail.com', 'padrao@gmail', '$2a$10$Ix.qCm5U71fFzjkd2/z3T.gKtgr9NzUzpqVOqAXU8uAcvv3ftooWS', null, 2, null),
(1, 'roberTT4F@', '63515811095', 'Roberta Ferreira', '1985-08-25', 'F', 'roberta@gmail.com', null, '$2a$10$Ix.qCm5U71fFzjkd2/z3T.gKtgr9NzUzpqVOqAXU8uAcvv3ftooWS', null, null, 2),
(1, 'pedR0G@', '47767654036', 'Pedro Gomes', '1978-06-17', 'M', 'pedro@gmail.com', null, '$2a$10$Ix.qCm5U71fFzjkd2/z3T.gKtgr9NzUzpqVOqAXU8uAcvv3ftooWS', null, null, 3);
-- (2, 'admin1Nhyir@', '29896637032', 'Poliana Micheline Milit�o', '1999-07-18', 'F', 'admin@gmail.com', 'padrao@gmail', '$2a$10$Ix.qCm5U71fFzjkd2/z3T.gKtgr9NzUzpqVOqAXU8uAcvv3ftooWS', null, null);

INSERT INTO ficha (usuario_id, peso, altura, problema_cardiaco, dor_peito_atividade, dor_peito_ultimo_mes, problema_osseo_articular, medicamento_pressao_coracao, impedimento_atividade) 
VALUES
(3, 88.30, 1.81, 0, 0, 0, 1, 0, 1);

INSERT INTO rotina_usuario (usuario_id, meta_id) VALUES
(3, 1);

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

INSERT INTO mural (usuario_id, midia_id, dt_postagem)
VALUES
(1, 47, '2024-01-01 19:31:00'),
(1, 48, '2024-03-26 11:42:50'),

(3, 49, '2024-01-08 11:42:50'),
(3, 50, '2024-04-08 11:42:50'),
(3, 51, '2024-07-05 09:22:54');