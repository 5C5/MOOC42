DELETE FROM Apprenant;
DELETE FROM Notion;
DELETE FROM Connaissance;
DELETE FROM Competence;
DELETE FROM CompetenceNotion;

INSERT INTO Apprenant (prenom, nom, mot_de_passe) VALUES ('Colas','PICARD', 'mdp');

INSERT INTO Notion (nom_notion) VALUES ('OU / OR');
INSERT INTO Notion (nom_notion) VALUES ('ET / AND');
INSERT INTO Notion (nom_notion) VALUES ('NOT');
INSERT INTO Notion (nom_notion) VALUES ('XOR');

INSERT INTO Connaissance (id_apprenant, id_notion, niveau) VALUES (1, 1, 2); -- Moyen OR 
INSERT INTO Connaissance (id_apprenant, id_notion, niveau) VALUES (1, 2, 1); -- Facile AND
INSERT INTO Connaissance (id_apprenant, id_notion, niveau) VALUES (1, 3, 2); -- Moyen NOT
INSERT INTO Connaissance (id_apprenant, id_notion, niveau) VALUES (1, 4, 0); -- Rien XOR

-- Niveau facile OR deverouille
INSERT INTO Competence (id_apprenant, niveau, score, date_competence, remarque) VALUES (1, 1, 0, DATE'2016-01-01', 'Validation du niveau Facile pour OU / OR');
INSERT INTO CompetenceNotion (id_competence, id_notion) VALUES (1, 1);
-- Niveau facile AND deverouille
INSERT INTO Competence (id_apprenant, niveau, score, date_competence, remarque) VALUES (1, 1, 0, DATE'2016-01-02', 'Validation du niveau Facile pour ET / AND');
INSERT INTO CompetenceNotion (id_competence, id_notion) VALUES (2, 2);
-- Niveau facile NOT deverouille
INSERT INTO Competence (id_apprenant, niveau, score, date_competence, remarque) VALUES (1, 1, 0, DATE'2016-01-03', 'Validation du niveau Facile pour NOT');
INSERT INTO CompetenceNotion (id_competence, id_notion) VALUES (3, 3);
-- Exercice facile avec OR et NOT
INSERT INTO Competence (id_apprenant, niveau, score, date_competence, remarque) VALUES (1, 1, 100, DATE'2016-02-05', 'Exercice niveau Facile avec OU / OR et NOT');
INSERT INTO CompetenceNotion (id_competence, id_notion) VALUES (4, 1);
INSERT INTO CompetenceNotion (id_competence, id_notion) VALUES (4, 3);
-- Niveau moyen OR deverouille
INSERT INTO Competence (id_apprenant, niveau, score, date_competence, remarque) VALUES (1, 2, 0, DATE'2016-03-10', 'Validation du niveau Moyen pour OU / OR');
INSERT INTO CompetenceNotion (id_competence, id_notion) VALUES (5, 1);
-- Niveau moyen NOT deverouille 
INSERT INTO Competence (id_apprenant, niveau, score, date_competence, remarque) VALUES (1, 2, 0, DATE'2016-03-11', 'Validation du niveau Moyen pour NOT');
INSERT INTO CompetenceNotion (id_competence, id_notion) VALUES (6, 3);
-- Exercice facile avec OR, AND et NOT
INSERT INTO Competence (id_apprenant, niveau, score, date_competence, remarque) VALUES (1, 1, 20, DATE'2016-04-20', 'Exercice niveau Facile avec OU / OR, ET / AND et NOT ');
INSERT INTO CompetenceNotion (id_competence, id_notion) VALUES (7, 1);
INSERT INTO CompetenceNotion (id_competence, id_notion) VALUES (7, 2);
INSERT INTO CompetenceNotion (id_competence, id_notion) VALUES (7, 3);
-- Exercice moyen avec OR et NOT
INSERT INTO Competence (id_apprenant, niveau, score, date_competence, remarque) VALUES (1, 2, 20, DATE'2016-04-21', 'Exercice niveau Moyen avec OU / OR et NOT ');
INSERT INTO CompetenceNotion (id_competence, id_notion) VALUES (8, 1);
INSERT INTO CompetenceNotion (id_competence, id_notion) VALUES (8, 3);

COMMIT;