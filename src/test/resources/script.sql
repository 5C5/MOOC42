CREATE TABLE	 apprenant (
	id INT PRIMARY KEY NOT NULL,
	prenom VARCHAR(50) NOT NULL,
	nom VARCHAR(50),
	motdepasse VARCHAR(50) NOT NULL
	);
	
INSERT INTO apprenant VALUES (
	011, 	'COLAS', 	'PICARD');
	
CREATE TABLE  connaissances(
	id_apprenant INT PRIMARY KEY NOT NULL,
	id_notion INT NOT NULL
	);
	
ALTER TABLE connaissances ALTER COLUMN id_notion SET NOT PRIMARY KEY;
	
CREATE TABLE notions (
	id INT PRIMARY KEY NOT NULL,
	nom VARCHAR(4)
	);
	
INSERT INTO notions VALUES
(01, 'OU'),
(02, 'AND');
	
CREATE TABLE competences (
	id_apprenant INT PRIMARY KEY NOT NULL,
	id_notion INT,
	difficulte INT,
	score INT
	);
	
ALTER TABLE competences ALTER COLUMN id_notion SET NOT NULL;