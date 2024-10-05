USE vitalisDB;

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
        WHILE dia <= 6 DO
            INSERT INTO rotina_diaria (rotina_semanal_id, dia, concluido)
            VALUES 
            (rotina_semanal_id, dia, 0);

            SET rotina_diaria_id = LAST_INSERT_ID();
           
           -- Verifica se a ficha terá um treino leve...
           case new.rotina_alternativa
           		WHEN 0 THEN	-- Não tem treino leve
           		
           			-- Caso a caso a meta seja X... Monte o treino para a rotina diária
					CASE NEW.meta_id
						WHEN 1 then	-- Emagrecimento
							INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo)
							VALUES
							(1, rotina_diaria_id, 0, 15, 3, '00:01:00'),
							(2, rotina_diaria_id, 0, 12, 3, '00:02:00'),
							(3, rotina_diaria_id, 0, 10, 3, '00:01:30'),
							(4, rotina_diaria_id, 0, 20, 3, '00:01:00'),
							(5, rotina_diaria_id, 0, 8, 3, '00:02:00'),
							(6, rotina_diaria_id, 0, 15, 3, '00:01:30'),
							(7, rotina_diaria_id, 0, 12, 3, '00:01:00');
						
		                WHEN 2 then	-- Ganho de massa
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
						WHEN 1 then	-- Emagrecimento
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
							END CASE;
						
		                WHEN 2 then	-- Ganho de massa
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
							END CASE;
		            END CASE;
		           
           		WHEN 1 THEN	-- Terá um treino leve. Todos os treinos leves são padrões.
					CASE dia
						when 1 or 3 or 5 then
							INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo)
							VALUES
							(36, rotina_diaria_id, 0, 1, 1, '00:10:00'),
							(59, rotina_diaria_id, 0, 1, 10, '00:06:00'),
							(10, rotina_diaria_id, 0, 10, 4, '00:06:30'),
							(1, rotina_diaria_id, 0, 15, 4, '00:08:00');
						when 2 or 4 or 6 then
							INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo)
							VALUES
							(36, rotina_diaria_id, 0, 1, 1, '00:10:00'),
							(24, rotina_diaria_id, 0, 20, 10, '00:10:00'),
							(25, rotina_diaria_id, 0, 100, 1, '00:05:30'),
							(1, rotina_diaria_id, 0, 15, 4, '00:08:00'),
							(52, rotina_diaria_id, 0, 15, 4, '00:07:30');
					END CASE;
		
					CASE padrao
						-- Baseadas em Emagrecimento
						WHEN 1 THEN
							INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
							(rotina_diaria_id, 25, 0),
							(rotina_diaria_id, 8, 0),
							(rotina_diaria_id, 2, 0),
							(rotina_diaria_id, 11, 0),
							(rotina_diaria_id, 13, 0);
						WHEN 2 THEN
							INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
							(rotina_diaria_id, 1, 0), -- Virada Paulista
							(rotina_diaria_id, 14, 0), -- Tapioca Recheada
							(rotina_diaria_id, 20, 0), -- Feijoada Light com Acompanhamentos
							(rotina_diaria_id, 26, 0), -- Panqueca de Banana
							(rotina_diaria_id, 31, 0); -- Bife com Salada
						
						-- Baseadas em Ganho de massa
						WHEN 3 THEN
							INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
							(rotina_diaria_id, 4, 0), -- Frango e Batata Doce
							(rotina_diaria_id, 11, 0), -- Aveia com Frutas e Castanhas
							(rotina_diaria_id, 15, 0), -- Strogonoff de Frango com Legumes e Macarrão Integral
							(rotina_diaria_id, 25, 0), -- Smoothie Verde
							(rotina_diaria_id, 30, 0); -- Carne Moída com Batata Doce
						WHEN 4 THEN
							INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
							(rotina_diaria_id, 3, 0), -- Salmão com Quinoa
							(rotina_diaria_id, 4, 0), -- Frango e Batata Doce
							(rotina_diaria_id, 15, 0), -- Strogonoff de Frango com Legumes e Macarrão Integral
							(rotina_diaria_id, 29, 0), -- Smoothie Protéico
							(rotina_diaria_id, 30, 0); -- Carne Moída com Batata Doce
					END CASE;
				           			
           	END CASE;
			
            -- Padrão serve para variar as rotinas diárias
            SET padrao = padrao + 1;
            IF padrao > 4 THEN
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

DELIMITER //

-- FINALIZADA
	-- TREINO LEVE	 -> IMPLEMENTADO (Padrão para todos)
	-- EMAGRECIMENTO -> 3/3 TREINOS, 3/3 REF 
	-- GANHAR MASSA	 -> 3/3 TREINOS, 3/3 REF 
CREATE PROCEDURE atualizar_rotinas_expiradas()
BEGIN
    DECLARE done INT DEFAULT 0;
    DECLARE rotina_usuario_id INT;
    DECLARE ultimo_mes INT;
    DECLARE ultimo_ano INT;
    DECLARE rotina_mensal_id INT;
    DECLARE rotina_semanal_id INT;
    DECLARE rotina_diaria_id INT;
    DECLARE num_semana INT;
    DECLARE dia INT;
    DECLARE padrao INT DEFAULT 1;
    DECLARE meta_id INT;
    DECLARE treino_aleatorio INT;
	DECLARE ref_aleatoria INT;
	DECLARE rotina_alternativa INT;

    -- Cursor para iterar sobre os usuários
    DECLARE cursor_usuario CURSOR FOR
    SELECT id_rotina_usuario FROM rotina_usuario;

    -- Manipulador para o final do cursor
    DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = 1;

    OPEN cursor_usuario;
    read_loop: LOOP
        FETCH cursor_usuario INTO rotina_usuario_id;
        IF done THEN
            LEAVE read_loop;
        END IF;

        -- Verifica a última rotina mensal do usuário
        SELECT mes, ano 
        INTO ultimo_mes, ultimo_ano
        FROM rotina_mensal
        WHERE rotina_usuario_id = rotina_usuario_id
        ORDER BY ano DESC, mes DESC
        LIMIT 1;

        -- Se a última rotina mensal for anterior ao mês atual, criar nova rotina mensal
        IF ultimo_mes < MONTH(CURDATE()) OR ultimo_ano < YEAR(CURDATE()) THEN

            -- Cria a nova rotina mensal
            INSERT INTO rotina_mensal (rotina_usuario_id, mes, ano, concluido)
            VALUES 
            (rotina_usuario_id, MONTH(CURDATE()), YEAR(CURDATE()), 0);

            SET rotina_mensal_id = LAST_INSERT_ID();

            -- Cria as rotinas semanais para a nova rotina mensal
            SET num_semana = 1;
            WHILE num_semana <= 5 DO
				-- Gera um número aleatório entre 1 e 3 para selecionar a rotina de treino
				SET treino_aleatorio = FLOOR(1 + RAND() * 3);
            
                INSERT INTO rotina_semanal (rotina_mensal_id, num_semana, concluido)
                VALUES 
                (rotina_mensal_id, num_semana, 0);

                SET rotina_semanal_id = LAST_INSERT_ID();

                -- Cria as rotinas diárias para cada semana
                SET dia = 1;
                WHILE dia <= 6 DO
                    INSERT INTO rotina_diaria (rotina_semanal_id, dia, concluido)
                    VALUES 
                    (rotina_semanal_id, dia, 0);

                    SET rotina_diaria_id = LAST_INSERT_ID();

                    SELECT rotina_alternativa INTO rotina_alternativa FROM rotina_usuario 
                    	WHERE id_rotina_usuario = rotina_usuario_id;

					CASE rotina_alternativa
						WHEN 0 THEN
							-- Obtém a meta do usuário
							SELECT meta_id INTO meta_id FROM rotina_usuario WHERE id_rotina_usuario = rotina_usuario_id;

							-- Adiciona os treinos diários com base na meta do usuário e no número aleatório
							CASE meta_id
								WHEN 1 THEN -- EMAGRECIMENTO
									CASE treino_aleatorio
										WHEN 1 THEN	-- ROTINA 1
											CASE dia
												WHEN 1 THEN
													INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
													(21, rotina_diaria_id, 0, 10, 3, '00:03:30'), -- Burpees
													(2, rotina_diaria_id, 0, 15, 4, '00:05:50'), -- Agachamento Livre
													(1, rotina_diaria_id, 0, 12, 3, '00:05:15'), -- Flexão de Braço
													(9, rotina_diaria_id, 0, 1, 3, '00:04:30'), -- Prancha Abdominal
													(32, rotina_diaria_id, 0, 20, 4, '00:06:30'); -- Mountain Climbers
												WHEN 2 THEN
													INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
													(22, rotina_diaria_id, 0, 30, 3, '00:07:30'), -- Jumping Jacks
													(23, rotina_diaria_id, 0, 12, 4, '00:05:50'), -- Afundo
													(19, rotina_diaria_id, 0, 15, 3, '00:06:00'), -- Prancha Superman
													(33, rotina_diaria_id, 0, 20, 3, '00:06:00'), -- Bicicleta Abdominal
													(24, rotina_diaria_id, 0, 30, 3, '00:07:30'); -- Polichinelos
												WHEN 3 THEN
													INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
													(25, rotina_diaria_id, 0, 50, 3, '00:05:00'), -- Pula Corda
													(26, rotina_diaria_id, 0, 15, 3, '00:05:50'), -- Ponte (Glúteos)
													(13, rotina_diaria_id, 0, 12, 3, '00:05:15'), -- Tríceps Pulley
													(34, rotina_diaria_id, 0, 15, 3, '00:05:30'), -- Abdominal Infra
													(27, rotina_diaria_id, 0, 20, 5, '00:06:10'); -- Sprint
												WHEN 4 THEN
													INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
													(28, rotina_diaria_id, 0, 30, 3, '00:07:00'), -- High Knees
													(17, rotina_diaria_id, 0, 15, 3, '00:05:50'), -- Elevação Lateral
													(14, rotina_diaria_id, 0, 30, 3, '00:06:15'), -- Prancha Lateral
													(35, rotina_diaria_id, 0, 20, 4, '00:06:30'), -- Elevação de Panturrilha
													(29, rotina_diaria_id, 0, 20, 4, '00:06:30'); -- Skaters
												WHEN 5 THEN
													INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
													(36, rotina_diaria_id, 0, 1, 1, '00:25:00'), -- Corrida (Cardio)
													(4, rotina_diaria_id, 0, 20, 3, '00:05:00'), -- Abdominal Crunch
													(30, rotina_diaria_id, 0, 12, 3, '00:05:15'), -- Pullover
													(11, rotina_diaria_id, 0, 10, 4, '00:06:30'), -- Leg Press
													(37, rotina_diaria_id, 0, 15, 3, '00:05:30'); -- Prancha com Toque no Ombro
												WHEN 6 THEN
													INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
													(21, rotina_diaria_id, 0, 10, 3, '00:03:30'), -- Burpees
													(38, rotina_diaria_id, 0, 10, 3, '00:05:00'), -- Flexão com Abertura
													(39, rotina_diaria_id, 0, 1, 3, '00:04:30'), -- Agachamento Isométrico
													(22, rotina_diaria_id, 0, 15, 3, '00:05:30'), -- Jumping Jacks
													(40, rotina_diaria_id, 0, 15, 3, '00:05:30'); -- Abdominal V-Sit
											END CASE;
										WHEN 2 THEN -- ROTINA 2
											CASE dia
												WHEN 1 THEN
													INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
													(2, rotina_diaria_id, 0, 15, 3, '00:05:30'),
													(1, rotina_diaria_id, 0, 12, 4, '00:06:15'),
													(4, rotina_diaria_id, 0, 10, 3, '00:05:15'),
													(21, rotina_diaria_id, 0, 12, 3, '00:05:15'),
													(9, rotina_diaria_id, 0, 1, 4, '00:06:30');
												WHEN 2 THEN
													INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
													(41, rotina_diaria_id, 0, 1, 3, '00:04:30'),
													(23, rotina_diaria_id, 0, 15, 4, '00:06:40'),
													(42, rotina_diaria_id, 0, 12, 3, '00:05:30'),
													(24, rotina_diaria_id, 0, 1, 3, '00:05:00'),
													(14, rotina_diaria_id, 0, 12, 4, '00:06:30');
												WHEN 3 THEN
													INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
													(2, rotina_diaria_id, 0, 15, 3, '00:05:30'),
													(1, rotina_diaria_id, 0, 12, 4, '00:06:15'),
													(4, rotina_diaria_id, 0, 10, 3, '00:05:15'),
													(21, rotina_diaria_id, 0, 12, 3, '00:05:15'),
													(9, rotina_diaria_id, 0, 1, 4, '00:06:30');
												WHEN 4 THEN
													INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
													(41, rotina_diaria_id, 0, 1, 3, '00:04:30'),
													(23, rotina_diaria_id, 0, 15, 4, '00:06:40'),
													(42, rotina_diaria_id, 0, 12, 3, '00:05:30'),
													(24, rotina_diaria_id, 0, 1, 3, '00:05:00'),
													(14, rotina_diaria_id, 0, 12, 4, '00:06:30');
												WHEN 5 THEN
													INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
													(2, rotina_diaria_id, 0, 15, 3, '00:05:30'),
													(1, rotina_diaria_id, 0, 12, 4, '00:06:15'),
													(4, rotina_diaria_id, 0, 10, 3, '00:05:15'),
													(21, rotina_diaria_id, 0, 12, 3, '00:05:15'),
													(9, rotina_diaria_id, 0, 1, 4, '00:06:30');
												WHEN 6 THEN
													INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
													(41, rotina_diaria_id, 0, 1, 3, '00:04:30'),
													(23, rotina_diaria_id, 0, 15, 4, '00:06:40'),
													(42, rotina_diaria_id, 0, 12, 3, '00:05:30'),
													(24, rotina_diaria_id, 0, 1, 3, '00:05:00'),
													(14, rotina_diaria_id, 0, 12, 4, '00:06:30');
											END CASE;
										WHEN 3 THEN -- ROTINA 3
											CASE dia
												WHEN 1 THEN
													INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
													(41, rotina_diaria_id, 0, 1, 3, '00:04:30'),
													(1, rotina_diaria_id, 0, 15, 3, '00:05:40'),
													(24, rotina_diaria_id, 0, 1, 3, '00:05:00'),
													(4, rotina_diaria_id, 0, 1, 3, '00:03:00'),
													(9, rotina_diaria_id, 0, 1, 3, '00:04:30');
												WHEN 2 THEN
													INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
													(2, rotina_diaria_id, 0, 10, 3, '00:04:35'),
													(21, rotina_diaria_id, 0, 15, 3, '00:05:40'),
													(42, rotina_diaria_id, 0, 15, 3, '00:05:30'),
													(14, rotina_diaria_id, 0, 1, 3, '00:04:00'),
													(1, rotina_diaria_id, 0, 15, 4, '00:06:30');
												WHEN 3 THEN
													INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
													(41, rotina_diaria_id, 0, 1, 3, '00:04:30'),
													(1, rotina_diaria_id, 0, 15, 3, '00:05:40'),
													(24, rotina_diaria_id, 0, 1, 3, '00:05:00'),
													(4, rotina_diaria_id, 0, 1, 3, '00:03:00'),
													(9, rotina_diaria_id, 0, 1, 3, '00:04:30');
												WHEN 4 THEN
													INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
													(21, rotina_diaria_id, 0, 15, 3, '00:05:40'), -- burpee
													(1, rotina_diaria_id, 0, 15, 4, '00:06:30'), -- flexao de braço
													(42, rotina_diaria_id, 0, 15, 3, '00:05:30'), -- elevação de quadril
													(2, rotina_diaria_id, 0, 10, 3, '00:04:35'), -- prancha lateral
													(14, rotina_diaria_id, 0, 1, 3, '00:04:00'); -- agachamento livre
												WHEN 5 THEN
													INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
													(2, rotina_diaria_id, 0, 10, 3, '00:04:35'), -- agachamento livre 
													(21, rotina_diaria_id, 0, 15, 3, '00:05:40'), -- burpee
													(42, rotina_diaria_id, 0, 15, 3, '00:05:40'), -- elevação de quadril
													(14, rotina_diaria_id, 0, 1, 3, '00:04:30'), -- prancha lateral
													(1, rotina_diaria_id, 0, 15, 3, '00:05:40'); -- flexao de braço
												WHEN 6 THEN
													INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
													(41, rotina_diaria_id, 0, 1, 3, '00:04:35'), -- corrida no lugar
													(24, rotina_diaria_id, 0, 15, 3, '00:05:40'), -- polichinelo
													(3, rotina_diaria_id, 0, 15, 3, '00:06:00'), -- abdominal crunch
													(9, rotina_diaria_id, 0, 1, 3, '00:04:35'), -- prancha
													(23, rotina_diaria_id, 0, 15, 3, '00:06:00'); -- afundo
											END CASE;
									END CASE;
								WHEN 2 THEN		-- GANHAR MASSA
									CASE treino_aleatorio
										WHEN 1 THEN
											CASE dia
												WHEN 1 THEN
													INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
													(43, rotina_diaria_id, 0, 12, 4, '00:05:45'),
													(10, rotina_diaria_id, 0, 12, 4, '00:05:45'),
													(7, rotina_diaria_id, 0, 15, 4, '00:06:30'),
													(13, rotina_diaria_id, 0, 15, 4, '00:06:30');
												WHEN 2 THEN
													INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
													(2, rotina_diaria_id, 0, 12, 4, '00:05:45'),
													(11, rotina_diaria_id, 0, 15, 4, '00:06:45'),
													(44, rotina_diaria_id, 0, 12, 4, '00:05:45'),
													(45, rotina_diaria_id, 0, 20, 4, '00:07:30'),
													(34, rotina_diaria_id, 0, 20, 4, '00:07:30');
												WHEN 3 THEN
													INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
													(46, rotina_diaria_id, 0, 12, 4, '00:05:45'),
													(16, rotina_diaria_id, 0, 15, 4, '00:05:45'),
													(5, rotina_diaria_id, 0, 8, 4, '00:04:15'),
													(47, rotina_diaria_id, 0, 15, 4, '00:06:30'),
													(48, rotina_diaria_id, 0, 20, 4, '00:07:30');
												WHEN 4 THEN
													INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
													(49, rotina_diaria_id, 0, 12, 4, '00:05:45'),
													(17, rotina_diaria_id, 0, 15, 4, '00:05:45'),
													(50, rotina_diaria_id, 0, 15, 4, '00:05:45'),
													(51, rotina_diaria_id, 0, 15, 4, '00:05:45'),
													(9, rotina_diaria_id, 0, 1, 4, '00:06:15');
												WHEN 5 THEN
													INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
													(52, rotina_diaria_id, 0, 12, 4, '00:05:45'),
													(53, rotina_diaria_id, 0, 15, 4, '00:05:45'),
													(15, rotina_diaria_id, 0, 15, 4, '00:05:45'),
													(35, rotina_diaria_id, 0, 15, 4, '00:05:45'),
													(54, rotina_diaria_id, 0, 15, 4, '00:05:45');
												WHEN 6 THEN
													INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
													(55, rotina_diaria_id, 0, 10, 6, '00:07:45'),
													(56, rotina_diaria_id, 0, 12, 8, '00:09:45'),
													(57, rotina_diaria_id, 0, 15, 4, '00:05:45'),
													(58, rotina_diaria_id, 0, 15, 4, '00:05:45'),
													(59, rotina_diaria_id, 0, 15, 4, '00:05:15');
											END CASE;
										WHEN 2 THEN
											CASE dia
												WHEN 1 THEN
													INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
													(2, rotina_diaria_id, 0, 10, 4, '00:07:00'), -- Agachamento Livre
													(45, rotina_diaria_id, 0, 15, 4, '00:05:30'), -- Panturrilha no Smith
													(43, rotina_diaria_id, 0, 10, 4, '00:07:00'), -- Supino Reto
													(33, rotina_diaria_id, 0, 15, 4, '00:05:30'); -- Abdominal
												WHEN 2 THEN
													INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
													(7, rotina_diaria_id, 0, 10, 4, '00:05:45'), -- Rosca Direta
													(13, rotina_diaria_id, 0, 12, 4, '00:05:45'), -- Tríceps Pulley
													(44, rotina_diaria_id, 0, 10, 4, '00:05:45'), -- Stiff
													(34, rotina_diaria_id, 0, 15, 4, '00:03:00'); -- Abdominal Infra
												WHEN 3 THEN
													INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
													(46, rotina_diaria_id, 0, 10, 4, '00:05:45'), -- Remada Curvada
													(16, rotina_diaria_id, 0, 12, 4, '00:05:45'), -- Puxada Frontal
													(47, rotina_diaria_id, 0, 15, 4, '00:02:30'), -- Encolhimento de Ombros
													(48, rotina_diaria_id, 0, 15, 4, '00:02:30'); -- Abdominal Oblíquo
												WHEN 4 THEN
													INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
													(49, rotina_diaria_id, 0, 10, 4, '00:05:45'), -- Supino Inclinado
													(17, rotina_diaria_id, 0, 12, 4, '00:03:00'), -- Elevação Lateral
													(50, rotina_diaria_id, 0, 10, 4, '00:03:00'), -- Rosca Martelo
													(51, rotina_diaria_id, 0, 12, 4, '00:03:00'); -- Tríceps Testa
												WHEN 5 THEN
													INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
													(52, rotina_diaria_id, 0, 10, 4, '00:05:45'), -- Agachamento Hack
													(53, rotina_diaria_id, 0, 12, 4, '00:03:00'), -- Extensão de Pernas
													(54, rotina_diaria_id, 0, 10, 4, '00:03:00'), -- Flexão de Pernas
													(55, rotina_diaria_id, 0, 15, 4, '00:03:00'); -- Elevação de Panturrilha
												WHEN 6 THEN
													INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
													(56, rotina_diaria_id, 0, 8, 4, '00:03:00'), -- Barra Fixa
													(52, rotina_diaria_id, 0, 10, 4, '00:05:45'), -- Agachamento Hack
													(57, rotina_diaria_id, 0, 10, 4, '00:05:45'), -- Levantamento Terra
													(58, rotina_diaria_id, 0, 1, 4, '00:01:00'); -- Abdominal Prancha
											END CASE;
										WHEN 3 THEN
											CASE dia
												WHEN 1 THEN
													INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
													(49, rotina_diaria_id, 0, 10, 4, '00:05:45'), -- Supino Inclinado
													(10, rotina_diaria_id, 0, 12, 4, '00:05:45'), -- Desenvolvimento de Ombros
													(7, rotina_diaria_id, 0, 10, 4, '00:03:00'), -- Rosca Direta
													(13, rotina_diaria_id, 0, 12, 4, '00:03:00'); -- Tríceps Pulley
												WHEN 2 THEN
													INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
													(2, rotina_diaria_id, 0, 10, 4, '00:07:00'), -- Agachamento Livre
													(11, rotina_diaria_id, 0, 12, 4, '00:07:00'), -- Leg Press
													(44, rotina_diaria_id, 0, 10, 4, '00:07:00'), -- Stiff
													(45, rotina_diaria_id, 0, 15, 4, '00:07:00'); -- Panturrilha no Smith
												WHEN 3 THEN
													INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
													(46, rotina_diaria_id, 0, 10, 4, '00:07:00'), -- Remada Curvada
													(16, rotina_diaria_id, 0, 12, 4, '00:07:00'), -- Puxada Frontal
													(5, rotina_diaria_id, 0, 10, 4, '00:11:00'), -- Levantamento Terra
													(4, rotina_diaria_id, 0, 15, 4, '00:05:30'); -- Abdominal Crunch
												WHEN 4 THEN
													INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
													(43, rotina_diaria_id, 0, 10, 4, '00:08:15'), -- Supino Reto
													(17, rotina_diaria_id, 0, 12, 4, '00:08:15'), -- Elevação Lateral
													(50, rotina_diaria_id, 0, 10, 4, '00:08:25'), -- Rosca Martelo
													(13, rotina_diaria_id, 0, 12, 4, '00:08:25'); -- Tríceps Pulley
												WHEN 5 THEN
													INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
													(52, rotina_diaria_id, 0, 10, 4, '00:07:00'), -- Agachamento Hack
													(53, rotina_diaria_id, 0, 12, 4, '00:07:00'), -- Extensão de Pernas
													(50, rotina_diaria_id, 0, 10, 4, '00:07:00'), -- Flexão de Pernas
													(45, rotina_diaria_id, 0, 15, 4, '00:07:00'); -- Elevação de Panturrilha
												WHEN 6 THEN
													INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo) VALUES
													(5, rotina_diaria_id, 0, 10, 4, '00:12:00'), -- Levantamento Terra
													(54, rotina_diaria_id, 0, 15, 4, '00:05:45'), -- Abdominal Bicicleta (sexta-feira)
													(53, rotina_diaria_id, 0, 12, 4, '00:08:15'), -- Extensão de Pernas (sábado)
													(44, rotina_diaria_id, 0, 10, 4, '00:08:15'), -- Stiff
													(58, rotina_diaria_id, 0, 15, 4, '00:05:45'); -- Abdominal Canivete
											END CASE;
									END CASE;
							END CASE;

							-- Refeições diárias com base na meta do usuário
							CASE meta_id
								WHEN 1 THEN		-- EMAGRECIMENTO
									CASE treino_aleatorio
										WHEN 1 THEN	-- ROTINA REFEICAO 1
											CASE dia
												WHEN 1 THEN
													INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
													(rotina_diaria_id, 6, 0),
													(rotina_diaria_id, 3, 0),
													(rotina_diaria_id, 7, 0);
												WHEN 2 THEN
													INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
													(rotina_diaria_id, 8, 0),
													(rotina_diaria_id, 9, 0),
													(rotina_diaria_id, 10, 0);
												WHEN 3 THEN
													INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
													(rotina_diaria_id, 11, 0),
													(rotina_diaria_id, 12, 0),
													(rotina_diaria_id, 13, 0);
												WHEN 4 THEN
													INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
													(rotina_diaria_id, 14, 0),
													(rotina_diaria_id, 15, 0),
													(rotina_diaria_id, 16, 0);
												WHEN 5 THEN
													INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
													(rotina_diaria_id, 17, 0),
													(rotina_diaria_id, 7, 0),
													(rotina_diaria_id, 18, 0);
												WHEN 6 THEN
													INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
													(rotina_diaria_id, 19, 0),
													(rotina_diaria_id, 20, 0),
													(rotina_diaria_id, 21, 0);
											END CASE;
										WHEN 2 THEN	-- ROTINA REFEICAO 2
											CASE dia
												WHEN 1 THEN
													INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
													(rotina_diaria_id, 24, 0),
													(rotina_diaria_id, 8, 0),
													(rotina_diaria_id, 9, 0),
													(rotina_diaria_id, 11, 0),
													(rotina_diaria_id, 18, 0);
												WHEN 2 THEN
													INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
													(rotina_diaria_id, 25, 0),
													(rotina_diaria_id, 8, 0),
													(rotina_diaria_id, 2, 0),
													(rotina_diaria_id, 11, 0),
													(rotina_diaria_id, 13, 0);
												WHEN 3 THEN
													INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
													(rotina_diaria_id, 11, 0),
													(rotina_diaria_id, 8, 0),
													(rotina_diaria_id, 3, 0),
													(rotina_diaria_id, 21, 0),
													(rotina_diaria_id, 24, 0);
												WHEN 4 THEN
													INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
													(rotina_diaria_id, 14, 0),
													(rotina_diaria_id, 23, 0),
													(rotina_diaria_id, 13, 0),
													(rotina_diaria_id, 8, 0),
													(rotina_diaria_id, 22, 0);
												WHEN 5 THEN
													INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
													(rotina_diaria_id, 26, 0),
													(rotina_diaria_id, 8, 0),
													(rotina_diaria_id, 4, 0),
													(rotina_diaria_id, 19, 0),
													(rotina_diaria_id, 27, 0);
												WHEN 6 THEN
													INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
													(rotina_diaria_id, 25, 0),
													(rotina_diaria_id, 11, 0),
													(rotina_diaria_id, 28, 0),
													(rotina_diaria_id, 8, 0),
													(rotina_diaria_id, 18, 0);
											END CASE;
										WHEN 3 THEN	-- ROTINA REFEICAO 3
											CASE dia
												WHEN 1 THEN
													INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
													(rotina_diaria_id, 9, 0), -- Frango com Arroz Integral e Salada
													(rotina_diaria_id, 11, 0), -- Aveia com Frutas e Castanhas
													(rotina_diaria_id, 24, 0), -- Omelete de Claras
													(rotina_diaria_id, 25, 0), -- Smoothie Verde
													(rotina_diaria_id, 27, 0); -- Salada de Atum
												WHEN 2 THEN
													INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
													(rotina_diaria_id, 7, 0), -- Frango com Legumes Salteados
													(rotina_diaria_id, 8, 0), -- Iogurte Grego com Frutas e Granola
													(rotina_diaria_id, 12, 0), -- Macarrão Integral com Molho de Tomate e Frango
													(rotina_diaria_id, 19, 0), -- Overnight Oats com Frutas Vermelhas
													(rotina_diaria_id, 30, 0); -- Carne Moída com Batata Doce
												WHEN 3 THEN
													INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
													(rotina_diaria_id, 6, 0), -- Omelete de Legumes
													(rotina_diaria_id, 13, 0), -- Peixe Assado com Batatas Assadas e Espinafre
													(rotina_diaria_id, 17, 0), -- Pão Integral com Ovo Mexido e Abacate
													(rotina_diaria_id, 28, 0), -- Salada com Quinoa
													(rotina_diaria_id, 32, 0); -- Tilápia com Legumes
												WHEN 4 THEN
													INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
													(rotina_diaria_id, 1, 0), -- Virada Paulista
													(rotina_diaria_id, 14, 0), -- Tapioca Recheada
													(rotina_diaria_id, 20, 0), -- Feijoada Light com Acompanhamentos
													(rotina_diaria_id, 26, 0), -- Panqueca de Banana
													(rotina_diaria_id, 31, 0); -- Bife com Salada
												WHEN 5 THEN
													INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
													(rotina_diaria_id, 4, 0), -- Frango e Batata Doce
													(rotina_diaria_id, 15, 0), -- Strogonoff de Frango com Legumes e Macarrão Integral
													(rotina_diaria_id, 18, 0), -- Lentilhas com Legumes e Arroz Integral
													(rotina_diaria_id, 21, 0), -- Wraps de Frango com Salada
													(rotina_diaria_id, 29, 0); -- Smoothie Protéico
												WHEN 6 THEN
													INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
													(rotina_diaria_id, 5, 0), -- Carne com Aveia de Flocos
													(rotina_diaria_id, 22, 0), -- Risoto de Abóbora com Frango Desfiado
													(rotina_diaria_id, 23, 0), -- Salada Caprese com Pão Integral
													(rotina_diaria_id, 24, 0), -- Omelete de Claras
													(rotina_diaria_id, 30, 0); -- Carne Moída com Batata Doce
											END CASE;
									END CASE;
								WHEN 2 THEN		-- GANHO DE MASSA
									CASE treino_aleatorio
										WHEN 1 THEN	-- ROTINA REFEICAO 1
											CASE dia
												WHEN 1 THEN
													INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
													(rotina_diaria_id, 6, 0),
													(rotina_diaria_id, 8, 0),
													(rotina_diaria_id, 9, 0),
													(rotina_diaria_id, 27, 0),
													(rotina_diaria_id, 3, 0);
												WHEN 2 THEN
													INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
													(rotina_diaria_id, 29, 0),
													(rotina_diaria_id, 11, 0),
													(rotina_diaria_id, 30, 0),
													(rotina_diaria_id, 21, 0),
													(rotina_diaria_id, 32, 0);
												WHEN 3 THEN
													INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
													(rotina_diaria_id, 11, 0),
													(rotina_diaria_id, 29, 0),
													(rotina_diaria_id, 4, 0),
													(rotina_diaria_id, 8, 0),
													(rotina_diaria_id, 31, 0);
												WHEN 4 THEN
													INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
													(rotina_diaria_id, 26, 0),
													(rotina_diaria_id, 19, 0),
													(rotina_diaria_id, 3, 0),
													(rotina_diaria_id, 25, 0),
													(rotina_diaria_id, 13, 0);
												WHEN 5 THEN
													INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
													(rotina_diaria_id, 6, 0),
													(rotina_diaria_id, 21, 0),
													(rotina_diaria_id, 4, 0),
													(rotina_diaria_id, 8, 0),
													(rotina_diaria_id, 16, 0);
												WHEN 6 THEN
													INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
													(rotina_diaria_id, 29, 0),
													(rotina_diaria_id, 10, 0),
													(rotina_diaria_id, 13, 0),
													(rotina_diaria_id, 8, 0),
													(rotina_diaria_id, 3, 0);
											END CASE;
										WHEN 2 THEN -- ROTINA REFEICAO 2
											CASE dia
												WHEN 1 THEN
													INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
													(rotina_diaria_id, 3, 0), -- Salmão com Quinoa
													(rotina_diaria_id, 4, 0), -- Frango e Batata Doce
													(rotina_diaria_id, 15, 0), -- Strogonoff de Frango com Legumes e Macarrão Integral
													(rotina_diaria_id, 29, 0), -- Smoothie Protéico
													(rotina_diaria_id, 30, 0); -- Carne Moída com Batata Doce

												WHEN 2 THEN
													INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
													(rotina_diaria_id, 5, 0), -- Carne com Aveia de Flocos
													(rotina_diaria_id, 7, 0), -- Frango com Legumes Salteados
													(rotina_diaria_id, 12, 0), -- Macarrão Integral com Molho de Tomate e Frango
													(rotina_diaria_id, 22, 0), -- Risoto de Abóbora com Frango Desfiado
													(rotina_diaria_id, 29, 0); -- Smoothie Protéico
												WHEN 3 THEN
													INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
													(rotina_diaria_id, 1, 0), -- Virada Paulista
													(rotina_diaria_id, 8, 0), -- Iogurte Grego com Frutas e Granola
													(rotina_diaria_id, 14, 0), -- Tapioca Recheada
													(rotina_diaria_id, 25, 0), -- Smoothie Verde
													(rotina_diaria_id, 31, 0); -- Bife com Salada
												WHEN 4 THEN
													INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
													(rotina_diaria_id, 2, 0), -- Salada de Frango Grelhado
													(rotina_diaria_id, 9, 0), -- Frango com Arroz Integral e Salada
													(rotina_diaria_id, 16, 0), -- Carne Magra com Purê de Batata Doce e Brócolis
													(rotina_diaria_id, 20, 0), -- Feijoada Light com Acompanhamentos
													(rotina_diaria_id, 32, 0); -- Tilápia com Legumes
												WHEN 5 THEN
													INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
													(rotina_diaria_id, 6, 0), -- Omelete de Legumes
													(rotina_diaria_id, 13, 0), -- Peixe Assado com Batatas Assadas e Espinafre
													(rotina_diaria_id, 18, 0), -- Lentilhas com Legumes e Arroz Integral
													(rotina_diaria_id, 23, 0), -- Salada Caprese com Pão Integral
													(rotina_diaria_id, 29, 0); -- Smoothie Protéico
												WHEN 6 THEN
													INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
													(rotina_diaria_id, 4, 0), -- Frango e Batata Doce
													(rotina_diaria_id, 11, 0), -- Aveia com Frutas e Castanhas
													(rotina_diaria_id, 15, 0), -- Strogonoff de Frango com Legumes e Macarrão Integral
													(rotina_diaria_id, 25, 0), -- Smoothie Verde
													(rotina_diaria_id, 30, 0); -- Carne Moída com Batata Doce
											END CASE;
										WHEN 3 THEN -- ROTINA REFEICAO 3
											CASE dia
												WHEN 1 THEN
													INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
													(rotina_diaria_id, 3, 0), -- Salmão com Quinoa
													(rotina_diaria_id, 12, 0), -- Macarrão Integral com Molho de Tomate e Frango
													(rotina_diaria_id, 14, 0), -- Tapioca Recheada
													(rotina_diaria_id, 25, 0), -- Smoothie Verde
													(rotina_diaria_id, 16, 0); -- Carne Magra com Purê de Batata Doce e Brócolis

												WHEN 2 THEN
													INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
													(rotina_diaria_id, 5, 0), -- Carne com Aveia de Flocos
													(rotina_diaria_id, 9, 0), -- Frango com Arroz Integral e Salada
													(rotina_diaria_id, 20, 0), -- Feijoada Light com Acompanhamentos
													(rotina_diaria_id, 29, 0), -- Smoothie Protéico
													(rotina_diaria_id, 22, 0); -- Risoto de Abóbora com Frango Desfiado

												WHEN 3 THEN
													INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
													(rotina_diaria_id, 1, 0), -- Virada Paulista
													(rotina_diaria_id, 7, 0), -- Frango com Legumes Salteados
													(rotina_diaria_id, 11, 0), -- Aveia com Frutas e Castanhas
													(rotina_diaria_id, 25, 0), -- Smoothie Verde
													(rotina_diaria_id, 31, 0); -- Bife com Salada

												WHEN 4 THEN
													INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
													(rotina_diaria_id, 6, 0), -- Omelete de Legumes
													(rotina_diaria_id, 13, 0), -- Peixe Assado com Batatas Assadas e Espinafre
													(rotina_diaria_id, 18, 0), -- Lentilhas com Legumes e Arroz Integral
													(rotina_diaria_id, 29, 0), -- Smoothie Protéico
													(rotina_diaria_id, 30, 0); -- Carne Moída com Batata Doce

												WHEN 5 THEN
													INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
													(rotina_diaria_id, 2, 0), -- Salada de Frango Grelhado
													(rotina_diaria_id, 8, 0), -- Iogurte Grego com Frutas e Granola
													(rotina_diaria_id, 15, 0), -- Strogonoff de Frango com Legumes e Macarrão Integral
													(rotina_diaria_id, 22, 0), -- Risoto de Abóbora com Frango Desfiado
													(rotina_diaria_id, 32, 0); -- Tilápia com Legumes

												WHEN 6 THEN
													INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
													(rotina_diaria_id, 4, 0), -- Frango e Batata Doce
													(rotina_diaria_id, 9, 0), -- Frango com Arroz Integral e Salada
													(rotina_diaria_id, 19, 0), -- Overnight Oats com Frutas Vermelhas
													(rotina_diaria_id, 29, 0), -- Smoothie Protéico
													(rotina_diaria_id, 31, 0); -- Bife com Salada
											END CASE;
									END CASE;
							END CASE;

						WHEN 1 THEN
							CASE dia
								when 1 or 3 or 5 then
									INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo)
									VALUES
									(36, rotina_diaria_id, 0, 1, 1, '00:10:00'),
									(59, rotina_diaria_id, 0, 1, 10, '00:06:00'),
									(10, rotina_diaria_id, 0, 10, 4, '00:06:30'),
									(1, rotina_diaria_id, 0, 15, 4, '00:08:00');
								when 2 or 4 or 6 then
									INSERT INTO treino (exercicio_id, rotina_diaria_id, concluido, repeticao, serie, tempo)
									VALUES
									(36, rotina_diaria_id, 0, 1, 1, '00:10:00'),
									(24, rotina_diaria_id, 0, 20, 10, '00:10:00'),
									(25, rotina_diaria_id, 0, 100, 1, '00:05:30'),
									(1, rotina_diaria_id, 0, 15, 4, '00:08:00'),
									(52, rotina_diaria_id, 0, 15, 4, '00:07:30');
							END CASE;

							-- Aleatoriamente, para a rotina diária, escolha uma das refs
							SET ref_aleatoria = FLOOR(1 + RAND() * 4);
							CASE ref_aleatoria
								WHEN 1 THEN
									INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
									(rotina_diaria_id, 25, 0),
									(rotina_diaria_id, 8, 0),
									(rotina_diaria_id, 2, 0),
									(rotina_diaria_id, 11, 0),
									(rotina_diaria_id, 13, 0);
								WHEN 2 THEN
									INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
									(rotina_diaria_id, 1, 0), -- Virada Paulista
									(rotina_diaria_id, 14, 0), -- Tapioca Recheada
									(rotina_diaria_id, 20, 0), -- Feijoada Light com Acompanhamentos
									(rotina_diaria_id, 26, 0), -- Panqueca de Banana
									(rotina_diaria_id, 31, 0); -- Bife com Salada
								WHEN 3 THEN
									INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
									(rotina_diaria_id, 4, 0), -- Frango e Batata Doce
									(rotina_diaria_id, 11, 0), -- Aveia com Frutas e Castanhas
									(rotina_diaria_id, 15, 0), -- Strogonoff de Frango com Legumes e Macarrão Integral
									(rotina_diaria_id, 25, 0), -- Smoothie Verde
									(rotina_diaria_id, 30, 0); -- Carne Moída com Batata Doce
								WHEN 4 THEN
									INSERT INTO refeicao_diaria (rotina_diaria_id, refeicao_id, concluido) VALUES
									(rotina_diaria_id, 3, 0), -- Salmão com Quinoa
									(rotina_diaria_id, 4, 0), -- Frango e Batata Doce
									(rotina_diaria_id, 15, 0), -- Strogonoff de Frango com Legumes e Macarrão Integral
									(rotina_diaria_id, 29, 0), -- Smoothie Protéico
									(rotina_diaria_id, 30, 0); -- Carne Moída com Batata Doce
							END CASE;
					END CASE;

					-- Incrementa o dia para a próxima rotina diária
					SET dia = dia + 1;
                END WHILE;

                -- Incrementa a semana para a próxima rotina semanal
                SET num_semana = num_semana + 1;
            END WHILE;
        END IF;
    END LOOP;

    CLOSE cursor_usuario;
END //

DELIMITER ;

-- Evento para ocorrer a chamada da procedure da inserção de novas rotinas mensais
DELIMITER //

CREATE EVENT IF NOT EXISTS atualiza_rotinas_mensais
-- Rode o evento a cada 1 mês
ON SCHEDULE EVERY 1 MONTH
STARTS (TIMESTAMP(DATE_ADD(LAST_DAY(CURDATE()), INTERVAL 1 DAY)))	-- Começa no primeiro dia do mês
DO
CALL atualizar_rotinas_expiradas();	-- Chama a procedure

DELIMITER ;

-- ----------------------------------------- --