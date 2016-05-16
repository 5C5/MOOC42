DROP TABLE Apprenant;
DROP TABLE Notion;
DROP TABLE Connaissance;
DROP TABLE Competence;
DROP TABLE CompetenceNotion;

CREATE TABLE Apprenant (
	id_apprenant INT PRIMARY KEY AUTO_INCREMENT,
	prenom VARCHAR(50) NOT NULL,
	nom VARCHAR(50) NOT NULL,
	mot_de_passe VARCHAR(50) NOT NULL,
	theme VARCHAR(20)
	);
	
CREATE TABLE Notion (
	id_notion INT PRIMARY KEY AUTO_INCREMENT,
	nom_notion VARCHAR(20)
	);
	
CREATE TABLE Connaissance (
	id_connaissance INT PRIMARY KEY AUTO_INCREMENT,
	id_apprenant INT NOT NULL,
	id_notion INT NOT NULL,
	niveau INT DEFAULT 0
	);
	
CREATE TABLE Competence (
	id_competence INT PRIMARY KEY AUTO_INCREMENT,
	id_apprenant INT NOT NULL,
	niveau INT DEFAULT 0,
	score INT DEFAULT 0,
	date_competence DATE,
	remarque VARCHAR(200)
	);

CREATE TABLE CompetenceNotion (
	id_competence_notion INT PRIMARY KEY AUTO_INCREMENT,
	id_competence INT NOT NULL,
	id_notion INT NOT NULL
	);
