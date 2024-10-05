USE vitalisDB;
GO

-- ---------------------------------------------------------------------------
-- TRIGGERS && PROCEDURES
-- ---------------------------------------------------------------------------
CREATE TRIGGER cria_rotina
ON rotina_usuario
AFTER INSERT
AS
BEGIN
    DECLARE @rotina_mensal_id INT;
    DECLARE @rotina_semanal_id INT;
    DECLARE @rotina_diaria_id INT;
    DECLARE @num_semana INT = 1;
    DECLARE @dia INT;
    DECLARE @padrao INT = 1;
    DECLARE @meta_id INT;
   	DECLARE @current_date DATE = GETDATE();

    SELECT @meta_id = meta_id FROM inserted;

    -- Cria a rotina mensal do usuário
    INSERT INTO rotina_mensal (rotina_usuario_id, mes, ano, concluido)
    VALUES ((SELECT id_rotina_usuario FROM inserted), MONTH(@current_date), YEAR(@current_date), 0);

    SET @rotina_mensal_id = SCOPE_IDENTITY();

    -- Cria as rotinas semanais do usuário para a rotina mensal
    WHILE @num_semana <= 5
    BEGIN
        INSERT INTO rotina_semanal (rotina_mensal_id, num_semana, concluido)
        VALUES (@rotina_mensal_id, @num_semana, 0);

        SET @rotina_semanal_id = SCOPE_IDENTITY();

        -- Para cada semana, criar sua rotina por dia
        SET @dia = 1;
        WHILE @dia <= 6
        BEGIN
            INSERT INTO rotina_diaria (rotina_semanal_id, dia, concluido)
            VALUES (@rotina_semanal_id, @dia, 0);

            SET @rotina_diaria_id = SCOPE_IDENTITY();

            -- Verifica se a ficha terá um treino leve...
            IF (SELECT rotina_alternativa FROM inserted) = 0
            BEGIN
                -- Caso a caso a meta seja X... Monte o treino para a rotina diária
                IF @meta_id = 1 -- Emagrecimento
                BEGIN
                    INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo)
                    VALUES
                    (1, @rotina_diaria_id, 0, 15, 3, '00:01:00'),
                    (2, @rotina_diaria_id, 0, 12, 3, '00:02:00'),
                    (3, @rotina_diaria_id, 0, 10, 3, '00:01:30'),
                    (4, @rotina_diaria_id, 0, 20, 3, '00:01:00'),
                    (5, @rotina_diaria_id, 0, 8, 3, '00:02:00'),
                    (6, @rotina_diaria_id, 0, 15, 3, '00:01:30'),
                    (7, @rotina_diaria_id, 0, 12, 3, '00:01:00');
                END
                ELSE IF @meta_id = 2 -- Ganho de massa
                BEGIN
                    INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo)
                    VALUES
                    (8, @rotina_diaria_id, 0, 15, 3, '00:01:00'),
                    (9, @rotina_diaria_id, 0, 12, 3, '00:02:00'),
                    (10, @rotina_diaria_id, 0, 10, 3, '00:01:30'),
                    (11, @rotina_diaria_id, 0, 20, 3, '00:01:00'),
                    (12, @rotina_diaria_id, 0, 8, 3, '00:02:00'),
                    (13, @rotina_diaria_id, 0, 15, 3, '00:01:30'),
                    (14, @rotina_diaria_id, 0, 12, 3, '00:01:00');
                END

                -- Caso a caso a meta seja X... Monte as refeições para a rotina diária
                IF @meta_id = 1 -- Emagrecimento
                BEGIN
                    INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido)
                    VALUES
                    (@rotina_diaria_id, 1, 0),
                    (@rotina_diaria_id, 2, 0),
                    (@rotina_diaria_id, 3, 0);
                END
                ELSE IF @meta_id = 2 -- Ganho de massa
                BEGIN
                    INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido)
                    VALUES
                    (@rotina_diaria_id, 5, 0),
                    (@rotina_diaria_id, 2, 0),
                    (@rotina_diaria_id, 3, 0);
                END
            END
            ELSE -- Terá um treino leve
            BEGIN
                IF @dia IN (1, 3, 5)
                BEGIN
                    INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo)
                    VALUES
                    (36, @rotina_diaria_id, 0, 1, 1, '00:10:00'),
                    (59, @rotina_diaria_id, 0, 1, 10, '00:06:00'),
                    (10, @rotina_diaria_id, 0, 10, 4, '00:06:30'),
                    (1, @rotina_diaria_id, 0, 15, 4, '00:08:00');
                END
                ELSE IF @dia IN (2, 4, 6)
                BEGIN
                    INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo)
                    VALUES
                    (36, @rotina_diaria_id, 0, 1, 1, '00:10:00'),
                    (24, @rotina_diaria_id, 0, 20, 10, '00:10:00'),
                    (25, @rotina_diaria_id, 0, 100, 1, '00:05:30'),
                    (1, @rotina_diaria_id, 0, 15, 4, '00:08:00'),
                    (52, @rotina_diaria_id, 0, 15, 4, '00:07:30');
                END

                -- Padrão serve para variar as rotinas diárias
                INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido)
                VALUES
                (@rotina_diaria_id, 25, 0),
                (@rotina_diaria_id, 8, 0),
                (@rotina_diaria_id, 2, 0),
                (@rotina_diaria_id, 11, 0),
                (@rotina_diaria_id, 13, 0);
            END

            -- Padrão serve para variar as rotinas diárias
            SET @padrao = @padrao + 1;
            IF @padrao > 4
                SET @padrao = 1;

            -- Para cada repetição, aumenta o dia
            SET @dia = @dia + 1;
        END

        -- Para cada repetição, aumenta a semana
        SET @num_semana = @num_semana + 1;
    END
END
GO


------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------

CREATE PROCEDURE atualizar_rotinas_expiradas
AS
BEGIN
    -- Declara vari�veis
    DECLARE @done INT = 0;
    DECLARE @rotina_usuario_id INT;
    DECLARE @ultimo_mes INT;
    DECLARE @ultimo_ano INT;
    DECLARE @rotina_mensal_id INT;
    DECLARE @rotina_semanal_id INT;
    DECLARE @rotina_diaria_id INT;
    DECLARE @num_semana INT;
    DECLARE @dia INT;
    DECLARE @treino_aleatorio INT;
    DECLARE @meta_id INT;
    DECLARE @rotina_alternativa INT;
    DECLARE @ref_aleatoria INT;

    -- Declara cursor
    DECLARE cursor_usuario CURSOR FOR
    SELECT id_rotina_usuario
    FROM rotina_usuario;

    -- Abre o cursor
    OPEN cursor_usuario;

    -- Inicia o loop para ler dados do cursor
    FETCH NEXT FROM cursor_usuario INTO @rotina_usuario_id;

    WHILE @@FETCH_STATUS = 0
    BEGIN
        -- Obt�m a �ltima rotina mensal do usu�rio
        SELECT @ultimo_mes = mes, @ultimo_ano = ano
        FROM rotina_mensal
        WHERE rotina_usuario_id = @rotina_usuario_id
        ORDER BY ano DESC, mes DESC;

        IF @ultimo_mes < MONTH(GETDATE()) OR @ultimo_ano < YEAR(GETDATE())
        BEGIN
            -- Cria a nova rotina mensal
            INSERT INTO rotina_mensal (rotina_usuario_id, mes, ano, concluido)
            VALUES (@rotina_usuario_id, MONTH(GETDATE()), YEAR(GETDATE()), 0);

            SET @rotina_mensal_id = SCOPE_IDENTITY();

            -- Cria as rotinas semanais para a nova rotina mensal
            SET @num_semana = 1;
            WHILE @num_semana <= 5
            BEGIN
                -- Gera um n�mero aleat�rio entre 1 e 3 para selecionar a rotina de treino
                SET @treino_aleatorio = ABS(CHECKSUM(NEWID()) % 3) + 1;

                INSERT INTO rotina_semanal (rotina_mensal_id, num_semana, concluido)
                VALUES (@rotina_mensal_id, @num_semana, 0);

                SET @rotina_semanal_id = SCOPE_IDENTITY();

                -- Cria as rotinas di�rias para cada semana
                SET @dia = 1;
                WHILE @dia <= 6
                BEGIN
                    INSERT INTO rotina_diaria (rotina_semanal_id, dia, concluido)
                    VALUES (@rotina_semanal_id, @dia, 0);

                    SET @rotina_diaria_id = SCOPE_IDENTITY();

                    SELECT @rotina_alternativa = rotina_alternativa 
                    FROM rotina_usuario 
                        WHERE id_rotina_usuario = @rotina_usuario_id;

                    IF @rotina_alternativa = 0 -- Treino normal
                    BEGIN
                        -- Obt�m a meta do usu�rio
                        SELECT @meta_id = meta_id
                        FROM rotina_usuario
                        WHERE id_rotina_usuario = @rotina_usuario_id;

                        -- Adiciona os treinos di�rios com base na meta do usu�rio e no n�mero aleat�rio
                        IF @meta_id = 1 -- EMAGRECIMENTO
                        BEGIN
                            IF @treino_aleatorio = 1 -- ROTINA 1
                            BEGIN
                                IF @dia = 1
                                BEGIN
                                INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
                                                (21, @rotina_diaria_id, 0, 10, 3, '00:03:30'), -- Burpees
                                                (2, @rotina_diaria_id, 0, 15, 4, '00:05:50'), -- Agachamento Livre
                                                (1, @rotina_diaria_id, 0, 12, 3, '00:05:15'), -- Flex�o de Bra�o
                                                (9, @rotina_diaria_id, 0, 1, 3, '00:04:30'), -- Prancha Abdominal
                                                (32, @rotina_diaria_id, 0, 20, 4, '00:06:30'); -- Mountain Climbers
                                END

                                IF @dia = 2
                                BEGIN
                                INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
                                                (22, @rotina_diaria_id, 0, 30, 3, '00:07:30'), -- Jumping Jacks
                                                (23, @rotina_diaria_id, 0, 12, 4, '00:05:50'), -- Afundo
                                                (19, @rotina_diaria_id, 0, 15, 3, '00:06:00'), -- Prancha Superman
                                                (33, @rotina_diaria_id, 0, 20, 3, '00:06:00'), -- Bicicleta Abdominal
                                                (24, @rotina_diaria_id, 0, 30, 3, '00:07:30'); -- Polichinelos
                                END

                                IF @dia = 3
                                BEGIN
                                INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
                                                (25, @rotina_diaria_id, 0, 50, 3, '00:05:00'), -- Pula Corda
                                                (26, @rotina_diaria_id, 0, 15, 3, '00:05:50'), -- Ponte (Gl�teos)
                                                (13, @rotina_diaria_id, 0, 12, 3, '00:05:15'), -- Tr�ceps Pulley
                                                (34, @rotina_diaria_id, 0, 15, 3, '00:05:30'), -- Abdominal Infra
                                                (27, @rotina_diaria_id, 0, 20, 5, '00:06:10'); -- Sprint
                                END

                                IF @dia = 4
                                BEGIN
                                INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
                                                (28, @rotina_diaria_id, 0, 30, 3, '00:07:00'), -- High Knees
                                                (17, @rotina_diaria_id, 0, 15, 3, '00:05:50'), -- Eleva��o Lateral
                                                (14, @rotina_diaria_id, 0, 30, 3, '00:06:15'), -- Prancha Lateral
                                                (35, @rotina_diaria_id, 0, 20, 4, '00:06:30'), -- Eleva��o de Panturrilha
                                                (29, @rotina_diaria_id, 0, 20, 4, '00:06:30'); -- Skaters
                                END

                                IF @dia = 5
                                BEGIN
                                INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
                                                (36, @rotina_diaria_id, 0, 1, 1, '00:25:00'), -- Corrida (Cardio)
                                                (4, @rotina_diaria_id, 0, 20, 3, '00:05:00'), -- Abdominal Crunch
                                                (30, @rotina_diaria_id, 0, 12, 3, '00:05:15'), -- Pullover
                                                (11, @rotina_diaria_id, 0, 10, 4, '00:06:30'), -- Leg Press
                                                (37, @rotina_diaria_id, 0, 15, 3, '00:05:30'); -- Prancha com Toque no Ombro
                                END

                                IF @dia = 6
                                BEGIN
                                INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
                                                (21, @rotina_diaria_id, 0, 10, 3, '00:03:30'), -- Burpees
                                                (38, @rotina_diaria_id, 0, 10, 3, '00:05:00'), -- Flex�o com Abertura
                                                (39, @rotina_diaria_id, 0, 1, 3, '00:04:30'), -- Agachamento Isom�trico
                                                (22, @rotina_diaria_id, 0, 15, 3, '00:05:30'), -- Jumping Jacks
                                                (40, @rotina_diaria_id, 0, 15, 3, '00:05:30'); -- Abdominal V-Sit
                                END
                            END
                            ELSE IF @treino_aleatorio = 2 -- ROTINA 2
                            BEGIN
                                IF @dia = 1
                                BEGIN
                                    INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
                                                (2, @rotina_diaria_id, 0, 15, 3, '00:05:30'),
                                                (1, @rotina_diaria_id, 0, 12, 4, '00:06:15'),
                                                (4, @rotina_diaria_id, 0, 10, 3, '00:05:15'),
                                                (21, @rotina_diaria_id, 0, 12, 3, '00:05:15'),
                                                (9, @rotina_diaria_id, 0, 1, 4, '00:06:30');
                                END
                                
                                IF @dia = 2
                                BEGIN
                                    INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
                                                (41, @rotina_diaria_id, 0, 1, 3, '00:04:30'),
                                                (23, @rotina_diaria_id, 0, 15, 4, '00:06:40'),
                                                (42, @rotina_diaria_id, 0, 12, 3, '00:05:30'),
                                                (24, @rotina_diaria_id, 0, 1, 3, '00:05:00'),
                                                (14, @rotina_diaria_id, 0, 12, 4, '00:06:30');
                                END

                                IF @dia = 3
                                BEGIN
                                    INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
                                                (2, @rotina_diaria_id, 0, 15, 3, '00:05:30'),
                                                (1, @rotina_diaria_id, 0, 12, 4, '00:06:15'),
                                                (4, @rotina_diaria_id, 0, 10, 3, '00:05:15'),
                                                (21, @rotina_diaria_id, 0, 12, 3, '00:05:15'),
                                                (9, @rotina_diaria_id, 0, 1, 4, '00:06:30');
                                END

                                IF @dia = 4
                                BEGIN
                                    INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
                                                (41, @rotina_diaria_id, 0, 1, 3, '00:04:30'),
                                                (23, @rotina_diaria_id, 0, 15, 4, '00:06:40'),
                                                (42, @rotina_diaria_id, 0, 12, 3, '00:05:30'),
                                                (24, @rotina_diaria_id, 0, 1, 3, '00:05:00'),
                                                (14, @rotina_diaria_id, 0, 12, 4, '00:06:30');
                                END

                                IF @dia = 5
                                BEGIN
                                    INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
                                                (2, @rotina_diaria_id, 0, 15, 3, '00:05:30'),
                                                (1, @rotina_diaria_id, 0, 12, 4, '00:06:15'),
                                                (4, @rotina_diaria_id, 0, 10, 3, '00:05:15'),
                                                (21, @rotina_diaria_id, 0, 12, 3, '00:05:15'),
                                                (9, @rotina_diaria_id, 0, 1, 4, '00:06:30');
                                END

                                IF @dia = 6
                                BEGIN
                                    INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
                                                (41, @rotina_diaria_id, 0, 1, 3, '00:04:30'),
                                                (23, @rotina_diaria_id, 0, 15, 4, '00:06:40'),
                                                (42, @rotina_diaria_id, 0, 12, 3, '00:05:30'),
                                                (24, @rotina_diaria_id, 0, 1, 3, '00:05:00'),
                                                (14, @rotina_diaria_id, 0, 12, 4, '00:06:30');
                                END
                            END
                            ELSE IF @treino_aleatorio = 3 -- ROTINA 3
                            BEGIN
                                IF @dia = 1
                                BEGIN
                                    INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
                                                (41, @rotina_diaria_id, 0, 1, 3, '00:04:30'),
                                                (1, @rotina_diaria_id, 0, 15, 3, '00:05:40'),
                                                (24, @rotina_diaria_id, 0, 1, 3, '00:05:00'),
                                                (4, @rotina_diaria_id, 0, 1, 3, '00:03:00'),
                                                (9, @rotina_diaria_id, 0, 1, 3, '00:04:30');
                                END
                                
                                IF @dia = 2
                                BEGIN
                                    INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
                                                (2, @rotina_diaria_id, 0, 10, 3, '00:04:35'),
                                                (21, @rotina_diaria_id, 0, 15, 3, '00:05:40'),
                                                (42, @rotina_diaria_id, 0, 15, 3, '00:05:30'),
                                                (14, @rotina_diaria_id, 0, 1, 3, '00:04:00'),
                                                (1, @rotina_diaria_id, 0, 15, 4, '00:06:30');
                                END

                                IF @dia = 3
                                BEGIN
                                    INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
                                                (41, @rotina_diaria_id, 0, 1, 3, '00:04:30'),
                                                (1, @rotina_diaria_id, 0, 15, 3, '00:05:40'),
                                                (24, @rotina_diaria_id, 0, 1, 3, '00:05:00'),
                                                (4, @rotina_diaria_id, 0, 1, 3, '00:03:00'),
                                                (9, @rotina_diaria_id, 0, 1, 3, '00:04:30');
                                END

                                IF @dia = 4
                                BEGIN
                                    INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
                                                (21, @rotina_diaria_id, 0, 15, 3, '00:05:40'), -- burpee
                                                (1, @rotina_diaria_id, 0, 15, 4, '00:06:30'), -- flexao de bra�o
                                                (42, @rotina_diaria_id, 0, 15, 3, '00:05:30'), -- eleva��o de quadril
                                                (2, @rotina_diaria_id, 0, 10, 3, '00:04:35'), -- prancha lateral
                                                (14, @rotina_diaria_id, 0, 1, 3, '00:04:00'); -- agachamento livre
                                END

                                IF @dia = 5
                                BEGIN
                                    INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
                                                (2, @rotina_diaria_id, 0, 10, 3, '00:04:35'), -- agachamento livre 
                                                (21, @rotina_diaria_id, 0, 15, 3, '00:05:40'), -- burpee
                                                (42, @rotina_diaria_id, 0, 15, 3, '00:05:40'), -- eleva��o de quadril
                                                (14, @rotina_diaria_id, 0, 1, 3, '00:04:30'), -- prancha lateral
                                                (1, @rotina_diaria_id, 0, 15, 3, '00:05:40'); -- flexao de bra�o
                                END

                                IF @dia = 6
                                BEGIN
                                    INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
                                                (41, @rotina_diaria_id, 0, 1, 3, '00:04:35'), -- corrida no lugar
                                                (24, @rotina_diaria_id, 0, 15, 3, '00:05:40'), -- polichinelo
                                                (3, @rotina_diaria_id, 0, 15, 3, '00:06:00'), -- abdominal crunch
                                                (9, @rotina_diaria_id, 0, 1, 3, '00:04:35'), -- prancha
                                                (23, @rotina_diaria_id, 0, 15, 3, '00:06:00'); -- afundo
                                END
                            END
                        END
                        ELSE IF @meta_id = 2 -- GANHAR MASSA
                        BEGIN
                            IF @treino_aleatorio = 1 -- ROTINA 1
                            BEGIN
                                IF @dia = 1
                                BEGIN
                                    INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
                                                (43, @rotina_diaria_id, 0, 12, 4, '00:05:45'),
                                                (10, @rotina_diaria_id, 0, 12, 4, '00:05:45'),
                                                (7, @rotina_diaria_id, 0, 15, 4, '00:06:30'),
                                                (13, @rotina_diaria_id, 0, 15, 4, '00:06:30');
                                END
                                
                                IF @dia = 2
                                BEGIN
                                    INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
                                                (2, @rotina_diaria_id, 0, 12, 4, '00:05:45'),
                                                (11, @rotina_diaria_id, 0, 15, 4, '00:06:45'),
                                                (44, @rotina_diaria_id, 0, 12, 4, '00:05:45'),
                                                (45, @rotina_diaria_id, 0, 20, 4, '00:07:30'),
                                                (34, @rotina_diaria_id, 0, 20, 4, '00:07:30');
                                END

                                IF @dia = 3
                                BEGIN
                                    INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
                                                (46, @rotina_diaria_id, 0, 12, 4, '00:05:45'),
                                                (16, @rotina_diaria_id, 0, 15, 4, '00:05:45'),
                                                (5, @rotina_diaria_id, 0, 8, 4, '00:04:15'),
                                                (47, @rotina_diaria_id, 0, 15, 4, '00:06:30'),
                                                (48, @rotina_diaria_id, 0, 20, 4, '00:07:30');
                                END

                                IF @dia = 4
                                BEGIN
                                    INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
                                                (49, @rotina_diaria_id, 0, 12, 4, '00:05:45'),
                                                (17, @rotina_diaria_id, 0, 15, 4, '00:05:45'),
                                                (50, @rotina_diaria_id, 0, 15, 4, '00:05:45'),
                                                (51, @rotina_diaria_id, 0, 15, 4, '00:05:45'),
                                                (9, @rotina_diaria_id, 0, 1, 4, '00:06:15');
                                END

                                IF @dia = 5
                                BEGIN
                                    INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
                                                (52, @rotina_diaria_id, 0, 12, 4, '00:05:45'),
                                                (53, @rotina_diaria_id, 0, 15, 4, '00:05:45'),
                                                (15, @rotina_diaria_id, 0, 15, 4, '00:05:45'),
                                                (35, @rotina_diaria_id, 0, 15, 4, '00:05:45'),
                                                (54, @rotina_diaria_id, 0, 15, 4, '00:05:45');
                                END

                                IF @dia = 6
                                BEGIN
                                    INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
                                                (55, @rotina_diaria_id, 0, 10, 6, '00:07:45'),
                                                (56, @rotina_diaria_id, 0, 12, 8, '00:09:45'),
                                                (57, @rotina_diaria_id, 0, 15, 4, '00:05:45'),
                                                (58, @rotina_diaria_id, 0, 15, 4, '00:05:45'),
                                                (59, @rotina_diaria_id, 0, 15, 4, '00:05:15');
                                END
                            END
                            ELSE IF @treino_aleatorio = 2 -- ROTINA 2
                            BEGIN
                                IF @dia = 1
                                BEGIN
                                    INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
                                                (2, @rotina_diaria_id, 0, 10, 4, '00:07:00'), -- Agachamento Livre
                                                (45, @rotina_diaria_id, 0, 15, 4, '00:05:30'), -- Panturrilha no Smith
                                                (43, @rotina_diaria_id, 0, 10, 4, '00:07:00'), -- Supino Reto
                                                (33, @rotina_diaria_id, 0, 15, 4, '00:05:30'); -- Abdominal
                                END
                                
                                IF @dia = 2
                                BEGIN
                                    INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
                                                (7, @rotina_diaria_id, 0, 10, 4, '00:05:45'), -- Rosca Direta
                                                (13, @rotina_diaria_id, 0, 12, 4, '00:05:45'), -- Tr�ceps Pulley
                                                (44, @rotina_diaria_id, 0, 10, 4, '00:05:45'), -- Stiff
                                                (34, @rotina_diaria_id, 0, 15, 4, '00:03:00'); -- Abdominal Infra
                                END

                                IF @dia = 3
                                BEGIN
                                    INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
                                                (46, @rotina_diaria_id, 0, 10, 4, '00:05:45'), -- Remada Curvada
                                                (16, @rotina_diaria_id, 0, 12, 4, '00:05:45'), -- Puxada Frontal
                                                (47, @rotina_diaria_id, 0, 15, 4, '00:02:30'), -- Encolhimento de Ombros
                                                (48, @rotina_diaria_id, 0, 15, 4, '00:02:30'); -- Abdominal Obl�quo
                                END

                                IF @dia = 4
                                BEGIN
                                    INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
                                                (49, @rotina_diaria_id, 0, 10, 4, '00:05:45'), -- Supino Inclinado
                                                (17, @rotina_diaria_id, 0, 12, 4, '00:03:00'), -- Eleva��o Lateral
                                                (50, @rotina_diaria_id, 0, 10, 4, '00:03:00'), -- Rosca Martelo
                                                (51, @rotina_diaria_id, 0, 12, 4, '00:03:00'); -- Tr�ceps Testa
                                END

                                IF @dia = 5
                                BEGIN
                                    INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
                                                (52, @rotina_diaria_id, 0, 10, 4, '00:05:45'), -- Agachamento Hack
                                                (53, @rotina_diaria_id, 0, 12, 4, '00:03:00'), -- Extens�o de Pernas
                                                (54, @rotina_diaria_id, 0, 10, 4, '00:03:00'), -- Flex�o de Pernas
                                                (55, @rotina_diaria_id, 0, 15, 4, '00:03:00'); -- Eleva��o de Panturrilha
                                END

                                IF @dia = 6
                                BEGIN
                                    INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
                                                (56, @rotina_diaria_id, 0, 8, 4, '00:03:00'), -- Barra Fixa
                                                (52, @rotina_diaria_id, 0, 10, 4, '00:05:45'), -- Agachamento Hack
                                                (57, @rotina_diaria_id, 0, 10, 4, '00:05:45'), -- Levantamento Terra
                                                (58, @rotina_diaria_id, 0, 1, 4, '00:01:00'); -- Abdominal Prancha
                                END
                            END
                            ELSE IF @treino_aleatorio = 3 -- ROTINA 3
                            BEGIN
                                IF @dia = 1
                                BEGIN
                                    INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
                                                (49, @rotina_diaria_id, 0, 10, 4, '00:05:45'), -- Supino Inclinado
                                                (10, @rotina_diaria_id, 0, 12, 4, '00:05:45'), -- Desenvolvimento de Ombros
                                                (7, @rotina_diaria_id, 0, 10, 4, '00:03:00'), -- Rosca Direta
                                                (13, @rotina_diaria_id, 0, 12, 4, '00:03:00'); -- Tr�ceps Pulley
                                END
                                
                                IF @dia = 2
                                BEGIN
                                    INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
                                                (2, @rotina_diaria_id, 0, 10, 4, '00:07:00'), -- Agachamento Livre
                                                (11, @rotina_diaria_id, 0, 12, 4, '00:07:00'), -- Leg Press
                                                (44, @rotina_diaria_id, 0, 10, 4, '00:07:00'), -- Stiff
                                                (45, @rotina_diaria_id, 0, 15, 4, '00:07:00'); -- Panturrilha no Smith
                                END

                                IF @dia = 3
                                BEGIN
                                    INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
                                                (46, @rotina_diaria_id, 0, 10, 4, '00:07:00'), -- Remada Curvada
                                                (16, @rotina_diaria_id, 0, 12, 4, '00:07:00'), -- Puxada Frontal
                                                (5, @rotina_diaria_id, 0, 10, 4, '00:11:00'), -- Levantamento Terra
                                                (4, @rotina_diaria_id, 0, 15, 4, '00:05:30'); -- Abdominal Crunch
                                END

                                IF @dia = 4
                                BEGIN
                                    INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
                                                (43, @rotina_diaria_id, 0, 10, 4, '00:08:15'), -- Supino Reto
                                                (17, @rotina_diaria_id, 0, 12, 4, '00:08:15'), -- Eleva��o Lateral
                                                (50, @rotina_diaria_id, 0, 10, 4, '00:08:25'), -- Rosca Martelo
                                                (13, @rotina_diaria_id, 0, 12, 4, '00:08:25'); -- Tr�ceps Pulley
                                END

                                IF @dia = 5
                                BEGIN
                                    INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
                                                (52, @rotina_diaria_id, 0, 10, 4, '00:07:00'), -- Agachamento Hack
                                                (53, @rotina_diaria_id, 0, 12, 4, '00:07:00'), -- Extens�o de Pernas
                                                (50, @rotina_diaria_id, 0, 10, 4, '00:07:00'), -- Flex�o de Pernas
                                                (45, @rotina_diaria_id, 0, 15, 4, '00:07:00'); -- Eleva��o de Panturrilha
                                END

                                IF @dia = 6
                                BEGIN
                                    INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
                                                (5, @rotina_diaria_id, 0, 10, 4, '00:12:00'), -- Levantamento Terra
                                                (54, @rotina_diaria_id, 0, 15, 4, '00:05:45'), -- Abdominal Bicicleta (sexta-feira)
                                                (53, @rotina_diaria_id, 0, 12, 4, '00:08:15'), -- Extens�o de Pernas (s�bado)
                                                (44, @rotina_diaria_id, 0, 10, 4, '00:08:15'), -- Stiff
                                                (58, @rotina_diaria_id, 0, 15, 4, '00:05:45'); -- Abdominal Canivete
                                END
                            END
                        END

                    -- Refei��es di�rias com base na meta do usu�rio
                        IF @meta_id = 1 -- EMAGRECIMENTO
                        BEGIN
                            IF @treino_aleatorio = 1 -- ROTINA REFEICAO 1
                            BEGIN
                                IF @dia = 1
                                BEGIN
                                    INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
                                                (@rotina_diaria_id, 6, 0),
                                                (@rotina_diaria_id, 3, 0),
                                                (@rotina_diaria_id, 7, 0);
                                END
                                
                                IF @dia = 2
                                BEGIN
                                    INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
                                                (@rotina_diaria_id, 8, 0),
                                                (@rotina_diaria_id, 9, 0),
                                                (@rotina_diaria_id, 10, 0);
                                END

                                IF @dia = 3
                                BEGIN
                                    INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
                                                (@rotina_diaria_id, 11, 0),
                                                (@rotina_diaria_id, 12, 0),
                                                (@rotina_diaria_id, 13, 0);
                                END

                                IF @dia = 4
                                BEGIN
                                    INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
                                                (@rotina_diaria_id, 14, 0),
                                                (@rotina_diaria_id, 15, 0),
                                                (@rotina_diaria_id, 16, 0);
                                END

                                IF @dia = 5
                                BEGIN
                                    INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
                                                (@rotina_diaria_id, 17, 0),
                                                (@rotina_diaria_id, 7, 0),
                                                (@rotina_diaria_id, 18, 0);
                                END

                                IF @dia = 6
                                BEGIN
                                    INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
                                                (@rotina_diaria_id, 19, 0),
                                                (@rotina_diaria_id, 20, 0),
                                                (@rotina_diaria_id, 21, 0);
                                END
                            END
                            ELSE IF @treino_aleatorio = 2 -- ROTINA REFEICAO 2
                            BEGIN
                                IF @dia = 1
                                BEGIN
                                    INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
                                                (@rotina_diaria_id, 24, 0),
                                                (@rotina_diaria_id, 8, 0),
                                                (@rotina_diaria_id, 9, 0),
                                                (@rotina_diaria_id, 11, 0),
                                                (@rotina_diaria_id, 18, 0);
                                END
                                
                                IF @dia = 2
                                BEGIN
                                    INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
                                                (@rotina_diaria_id, 25, 0),
                                                (@rotina_diaria_id, 8, 0),
                                                (@rotina_diaria_id, 2, 0),
                                                (@rotina_diaria_id, 11, 0),
                                                (@rotina_diaria_id, 13, 0);
                                END

                                IF @dia = 3
                                BEGIN
                                    INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
                                                (@rotina_diaria_id, 11, 0),
                                                (@rotina_diaria_id, 8, 0),
                                                (@rotina_diaria_id, 3, 0),
                                                (@rotina_diaria_id, 21, 0),
                                                (@rotina_diaria_id, 24, 0);
                                END

                                IF @dia = 4
                                BEGIN
                                    INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
                                                (@rotina_diaria_id, 14, 0),
                                                (@rotina_diaria_id, 23, 0),
                                                (@rotina_diaria_id, 13, 0),
                                                (@rotina_diaria_id, 8, 0),
                                                (@rotina_diaria_id, 22, 0);
                                END

                                IF @dia = 5
                                BEGIN
                                    INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
                                                (@rotina_diaria_id, 26, 0),
                                                (@rotina_diaria_id, 8, 0),
                                                (@rotina_diaria_id, 4, 0),
                                                (@rotina_diaria_id, 19, 0),
                                                (@rotina_diaria_id, 27, 0);
                                END

                                IF @dia = 6
                                BEGIN
                                    INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
                                                (@rotina_diaria_id, 25, 0),
                                                (@rotina_diaria_id, 11, 0),
                                                (@rotina_diaria_id, 28, 0),
                                                (@rotina_diaria_id, 8, 0),
                                                (@rotina_diaria_id, 18, 0);
                                END
                            END
                            ELSE IF @treino_aleatorio = 3 -- ROTINA REFEICAO 3
                            BEGIN
                                IF @dia = 1
                                BEGIN
                                    INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
                                                (@rotina_diaria_id, 9, 0), -- Frango com Arroz Integral e Salada
                                                (@rotina_diaria_id, 11, 0), -- Aveia com Frutas e Castanhas
                                                (@rotina_diaria_id, 24, 0), -- Omelete de Claras
                                                (@rotina_diaria_id, 25, 0), -- Smoothie Verde
                                                (@rotina_diaria_id, 27, 0); -- Salada de Atum
                                END
                                
                                IF @dia = 2
                                BEGIN
                                    INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
                                                (@rotina_diaria_id, 7, 0), -- Frango com Legumes Salteados
                                                (@rotina_diaria_id, 8, 0), -- Iogurte Grego com Frutas e Granola
                                                (@rotina_diaria_id, 12, 0), -- Macarr�o Integral com Molho de Tomate e Frango
                                                (@rotina_diaria_id, 19, 0), -- Overnight Oats com Frutas Vermelhas
                                                (@rotina_diaria_id, 30, 0); -- Carne Mo�da com Batata Doce
                                END

                                IF @dia = 3
                                BEGIN
                                    INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
                                                (@rotina_diaria_id, 6, 0), -- Omelete de Legumes
                                                (@rotina_diaria_id, 13, 0), -- Peixe Assado com Batatas Assadas e Espinafre
                                                (@rotina_diaria_id, 17, 0), -- P�o Integral com Ovo Mexido e Abacate
                                                (@rotina_diaria_id, 28, 0), -- Salada com Quinoa
                                                (@rotina_diaria_id, 32, 0); -- Til�pia com Legumes
                                END

                                IF @dia = 4
                                BEGIN
                                    INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
                                                (@rotina_diaria_id, 1, 0), -- Virada Paulista
                                                (@rotina_diaria_id, 14, 0), -- Tapioca Recheada
                                                (@rotina_diaria_id, 20, 0), -- Feijoada Light com Acompanhamentos
                                                (@rotina_diaria_id, 26, 0), -- Panqueca de Banana
                                                (@rotina_diaria_id, 31, 0); -- Bife com Salada
                                END

                                IF @dia = 5
                                BEGIN
                                    INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
                                                (@rotina_diaria_id, 4, 0), -- Frango e Batata Doce
                                                (@rotina_diaria_id, 15, 0), -- Strogonoff de Frango com Legumes e Macarr�o Integral
                                                (@rotina_diaria_id, 18, 0), -- Lentilhas com Legumes e Arroz Integral
                                                (@rotina_diaria_id, 21, 0), -- Wraps de Frango com Salada
                                                (@rotina_diaria_id, 29, 0); -- Smoothie Prot�ico
                                END

                                IF @dia = 6
                                BEGIN
                                    INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
                                                (@rotina_diaria_id, 5, 0), -- Carne com Aveia de Flocos
                                                (@rotina_diaria_id, 22, 0), -- Risoto de Ab�bora com Frango Desfiado
                                                (@rotina_diaria_id, 23, 0), -- Salada Caprese com P�o Integral
                                                (@rotina_diaria_id, 24, 0), -- Omelete de Claras
                                                (@rotina_diaria_id, 30, 0); -- Carne Mo�da com Batata Doce
                                END
                            END
                        END
                        ELSE IF @meta_id = 2 -- GANHO DE MASSA
                        BEGIN
                            IF @treino_aleatorio = 1 -- ROTINA REFEICAO 1
                            BEGIN
                                IF @dia = 1
                                BEGIN
                                    INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
                                                (@rotina_diaria_id, 6, 0),
                                                (@rotina_diaria_id, 8, 0),
                                                (@rotina_diaria_id, 9, 0),
                                                (@rotina_diaria_id, 27, 0),
                                                (@rotina_diaria_id, 3, 0);
                                END
                                
                                IF @dia = 2
                                BEGIN
                                    INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
                                                (@rotina_diaria_id, 29, 0),
                                                (@rotina_diaria_id, 11, 0),
                                                (@rotina_diaria_id, 30, 0),
                                                (@rotina_diaria_id, 21, 0),
                                                (@rotina_diaria_id, 32, 0);
                                END

                                IF @dia = 3
                                BEGIN
                                    INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
                                                (@rotina_diaria_id, 11, 0),
                                                (@rotina_diaria_id, 29, 0),
                                                (@rotina_diaria_id, 4, 0),
                                                (@rotina_diaria_id, 8, 0),
                                                (@rotina_diaria_id, 31, 0);
                                END

                                IF @dia = 4
                                BEGIN
                                    INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
                                                (@rotina_diaria_id, 26, 0),
                                                (@rotina_diaria_id, 19, 0),
                                                (@rotina_diaria_id, 3, 0),
                                                (@rotina_diaria_id, 25, 0),
                                                (@rotina_diaria_id, 13, 0);
                                END

                                IF @dia = 5
                                BEGIN
                                    INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
                                                (@rotina_diaria_id, 6, 0),
                                                (@rotina_diaria_id, 21, 0),
                                                (@rotina_diaria_id, 4, 0),
                                                (@rotina_diaria_id, 8, 0),
                                                (@rotina_diaria_id, 16, 0);
                                END

                                IF @dia = 6
                                BEGIN
                                    INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
                                                (@rotina_diaria_id, 29, 0),
                                                (@rotina_diaria_id, 10, 0),
                                                (@rotina_diaria_id, 13, 0),
                                                (@rotina_diaria_id, 8, 0),
                                                (@rotina_diaria_id, 3, 0);
                                END
                            END
                            ELSE IF @treino_aleatorio = 2 -- ROTINA REFEICAO 2
                            BEGIN
                                IF @dia = 1
                                BEGIN
                                    INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
                                                (@rotina_diaria_id, 3, 0), -- Salm�o com Quinoa
                                                (@rotina_diaria_id, 4, 0), -- Frango e Batata Doce
                                                (@rotina_diaria_id, 15, 0), -- Strogonoff de Frango com Legumes e Macarr�o Integral
                                                (@rotina_diaria_id, 29, 0), -- Smoothie Prot�ico
                                                (@rotina_diaria_id, 30, 0); -- Carne Mo�da com Batata Doce
                                END
                                
                                IF @dia = 2
                                BEGIN
                                    INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
                                                (@rotina_diaria_id, 5, 0), -- Carne com Aveia de Flocos
                                                (@rotina_diaria_id, 7, 0), -- Frango com Legumes Salteados
                                                (@rotina_diaria_id, 12, 0), -- Macarr�o Integral com Molho de Tomate e Frango
                                                (@rotina_diaria_id, 22, 0), -- Risoto de Ab�bora com Frango Desfiado
                                                (@rotina_diaria_id, 29, 0); -- Smoothie Prot�ico
                                END

                                IF @dia = 3
                                BEGIN
                                    INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
                                                (@rotina_diaria_id, 1, 0), -- Virada Paulista
                                                (@rotina_diaria_id, 8, 0), -- Iogurte Grego com Frutas e Granola
                                                (@rotina_diaria_id, 14, 0), -- Tapioca Recheada
                                                (@rotina_diaria_id, 25, 0), -- Smoothie Verde
                                                (@rotina_diaria_id, 31, 0); -- Bife com Salada
                                END

                                IF @dia = 4
                                BEGIN
                                    INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
                                                (@rotina_diaria_id, 2, 0), -- Salada de Frango Grelhado
                                                (@rotina_diaria_id, 9, 0), -- Frango com Arroz Integral e Salada
                                                (@rotina_diaria_id, 16, 0), -- Carne Magra com Pur� de Batata Doce e Br�colis
                                                (@rotina_diaria_id, 20, 0), -- Feijoada Light com Acompanhamentos
                                                (@rotina_diaria_id, 32, 0); -- Til�pia com Legumes
                                END

                                IF @dia = 5
                                BEGIN
                                    INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
                                                (@rotina_diaria_id, 6, 0), -- Omelete de Legumes
                                                (@rotina_diaria_id, 13, 0), -- Peixe Assado com Batatas Assadas e Espinafre
                                                (@rotina_diaria_id, 18, 0), -- Lentilhas com Legumes e Arroz Integral
                                                (@rotina_diaria_id, 23, 0), -- Salada Caprese com P�o Integral
                                                (@rotina_diaria_id, 29, 0); -- Smoothie Prot�ico
                                END

                                IF @dia = 6
                                BEGIN
                                    INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
                                                (@rotina_diaria_id, 4, 0), -- Frango e Batata Doce
                                                (@rotina_diaria_id, 11, 0), -- Aveia com Frutas e Castanhas
                                                (@rotina_diaria_id, 15, 0), -- Strogonoff de Frango com Legumes e Macarr�o Integral
                                                (@rotina_diaria_id, 25, 0), -- Smoothie Verde
                                                (@rotina_diaria_id, 30, 0); -- Carne Mo�da com Batata Doce
                                END
                            END
                            ELSE IF @treino_aleatorio = 3 -- ROTINA REFEICAO 3
                            BEGIN
                                IF @dia = 1
                                BEGIN
                                    INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
                                                (@rotina_diaria_id, 3, 0), -- Salm�o com Quinoa
                                                (@rotina_diaria_id, 12, 0), -- Macarr�o Integral com Molho de Tomate e Frango
                                                (@rotina_diaria_id, 14, 0), -- Tapioca Recheada
                                                (@rotina_diaria_id, 25, 0), -- Smoothie Verde
                                                (@rotina_diaria_id, 16, 0); -- Carne Magra com Pur� de Batata Doce e Br�colis
                                END
                                
                                IF @dia = 2
                                BEGIN
                                    INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
                                                (@rotina_diaria_id, 5, 0), -- Carne com Aveia de Flocos
                                                (@rotina_diaria_id, 9, 0), -- Frango com Arroz Integral e Salada
                                                (@rotina_diaria_id, 20, 0), -- Feijoada Light com Acompanhamentos
                                                (@rotina_diaria_id, 29, 0), -- Smoothie Prot�ico
                                                (@rotina_diaria_id, 22, 0); -- Risoto de Ab�bora com Frango Desfiado
                                END

                                IF @dia = 3
                                BEGIN
                                    INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
                                                (@rotina_diaria_id, 1, 0), -- Virada Paulista
                                                (@rotina_diaria_id, 7, 0), -- Frango com Legumes Salteados
                                                (@rotina_diaria_id, 11, 0), -- Aveia com Frutas e Castanhas
                                                (@rotina_diaria_id, 25, 0), -- Smoothie Verde
                                                (@rotina_diaria_id, 31, 0); -- Bife com Salada
                                END

                                IF @dia = 4
                                BEGIN
                                    INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
                                                (@rotina_diaria_id, 6, 0), -- Omelete de Legumes
                                                (@rotina_diaria_id, 13, 0), -- Peixe Assado com Batatas Assadas e Espinafre
                                                (@rotina_diaria_id, 18, 0), -- Lentilhas com Legumes e Arroz Integral
                                                (@rotina_diaria_id, 29, 0), -- Smoothie Prot�ico
                                                (@rotina_diaria_id, 30, 0); -- Carne Mo�da com Batata Doce
                                END

                                IF @dia = 5
                                BEGIN
                                    INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
                                                (@rotina_diaria_id, 2, 0), -- Salada de Frango Grelhado
                                                (@rotina_diaria_id, 8, 0), -- Iogurte Grego com Frutas e Granola
                                                (@rotina_diaria_id, 15, 0), -- Strogonoff de Frango com Legumes e Macarr�o Integral
                                                (@rotina_diaria_id, 22, 0), -- Risoto de Ab�bora com Frango Desfiado
                                                (@rotina_diaria_id, 32, 0); -- Til�pia com Legumes
                                END

                                IF @dia = 6
                                BEGIN
                                    INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
                                                (@rotina_diaria_id, 4, 0), -- Frango e Batata Doce
                                                (@rotina_diaria_id, 9, 0), -- Frango com Arroz Integral e Salada
                                                (@rotina_diaria_id, 19, 0), -- Overnight Oats com Frutas Vermelhas
                                                (@rotina_diaria_id, 29, 0), -- Smoothie Prot�ico
                                                (@rotina_diaria_id, 31, 0); -- Bife com Salada
                                END
                            END
                        END

                    END

                    ELSE                        -- Treino Leve, rotina alternativa
                    BEGIN
                        IF @dia IN (1, 3, 5)
                        BEGIN
							INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo)
							VALUES
							(36, @rotina_diaria_id, 0, 1, 1, '00:10:00'),
							(59, @rotina_diaria_id, 0, 1, 10, '00:06:00'),
							(10, @rotina_diaria_id, 0, 10, 4, '00:06:30'),
							(1, @rotina_diaria_id, 0, 15, 4, '00:08:00');
						END
                        ELSE IF @dia IN (2, 4, 6)
                        BEGIN
                            INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo)
							VALUES
							(36, @rotina_diaria_id, 0, 1, 1, '00:10:00'),
							(24, @rotina_diaria_id, 0, 20, 10, '00:10:00'),
							(25, @rotina_diaria_id, 0, 100, 1, '00:05:30'),
							(1, @rotina_diaria_id, 0, 15, 4, '00:08:00'),
							(52, @rotina_diaria_id, 0, 15, 4, '00:07:30');
                        END

                        -- Aleatoriamente, para a rotina diária, escolha uma das refs
						SET @ref_aleatoria = ABS(CHECKSUM(NEWID())) % 4 + 1;
						IF @ref_aleatoria = 1
                        BEGIN
							INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
							(@rotina_diaria_id, 25, 0),
							(@rotina_diaria_id, 8, 0),
							(@rotina_diaria_id, 2, 0),
							(@rotina_diaria_id, 11, 0),
							(@rotina_diaria_id, 13, 0);
                        END
						ELSE IF @ref_aleatoria = 2
                        BEGIN
							INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
							(@rotina_diaria_id, 1, 0), -- Virada Paulista
							(@rotina_diaria_id, 14, 0), -- Tapioca Recheada
							(@rotina_diaria_id, 20, 0), -- Feijoada Light com Acompanhamentos
							(@rotina_diaria_id, 26, 0), -- Panqueca de Banana
							(@rotina_diaria_id, 31, 0); -- Bife com Salada
                        END
						ELSE IF @ref_aleatoria = 3
                        BEGIN
							INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
							(@rotina_diaria_id, 4, 0), -- Frango e Batata Doce
							(@rotina_diaria_id, 11, 0), -- Aveia com Frutas e Castanhas
							(@rotina_diaria_id, 15, 0), -- Strogonoff de Frango com Legumes e Macarrão Integral
							(@rotina_diaria_id, 25, 0), -- Smoothie Verde
							(@rotina_diaria_id, 30, 0); -- Carne Moída com Batata Doce
                        END
						ELSE IF @ref_aleatoria = 4
                        BEGIN
							INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
							(@rotina_diaria_id, 3, 0), -- Salmão com Quinoa
							(@rotina_diaria_id, 4, 0), -- Frango e Batata Doce
							(@rotina_diaria_id, 15, 0), -- Strogonoff de Frango com Legumes e Macarrão Integral
							(@rotina_diaria_id, 29, 0), -- Smoothie Protéico
							(@rotina_diaria_id, 30, 0); -- Carne Moída com Batata Doce
                        END

                    END

                    -- Incrementa o dia para a pr�xima rotina di�ria
                        SET @dia = @dia + 1;
                END

                -- Incrementa a semana para a pr�xima rotina semanal
                SET @num_semana = @num_semana + 1;
            END
        END

        FETCH NEXT FROM cursor_usuario INTO @rotina_usuario_id; -- Vai pro próximo user
    END

    -- Fecha e desaloca o cursor
    CLOSE cursor_usuario;
    DEALLOCATE cursor_usuario;
END
GO

------------------------------------------------------------------------------------------------------------
------------------------------------------------------------------------------------------------------------
USE msdb;
GO

-- Verifica se o job já existe e o remove
IF EXISTS (SELECT job_id FROM msdb.dbo.sysjobs WHERE name = N'atualiza_rotinas_mensais')
BEGIN
    EXEC sp_delete_job @job_name = N'atualiza_rotinas_mensais';
END
GO

-- Cria o job
EXEC sp_add_job
    @job_name = N'atualiza_rotinas_mensais', 
    @enabled = 1,  -- O job já é criado habilitado
    @description = N'Job para atualizar rotinas mensais.',
    @notify_level_eventlog = 2;
GO

-- Adiciona um passo ao job
EXEC sp_add_jobstep
    @job_name = N'atualiza_rotinas_mensais', 
    @step_name = N'Executar atualizar_rotinas_expiradas',
    @subsystem = N'TSQL',
    @command = N'EXEC dbo.atualizar_rotinas_expiradas;',
    @retry_attempts = 5,
    @retry_interval = 5;
GO

-- Adiciona um agendamento ao job para ser executado mensalmente no primeiro dia do próximo mês
DECLARE @NextMonthStartDate INT;

-- Calcula o primeiro dia do próximo mês no formato YYYYMMDD
SET @NextMonthStartDate = CONVERT(INT, CONVERT(VARCHAR, DATEADD(MONTH, 1, DATEADD(DAY, -DAY(GETDATE()) + 1, GETDATE())), 112));

EXEC sp_add_jobschedule
    @job_name = N'atualiza_rotinas_mensais',
    @name = N'Agendamento Mensal',
    @freq_type = 8,  -- Tipo de frequência: 8 = Mensal
    @freq_interval = 1,  -- Executa no primeiro dia de cada mês
    @freq_recurrence_factor = 1,  -- Repetição mensal (1 mês)
    @active_start_date = @NextMonthStartDate,  -- Data calculada para o primeiro dia do próximo mês
    @active_start_time = 010000;  -- Hora de início: 01:00:00 (1 da manhã)
GO