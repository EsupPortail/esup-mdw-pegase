CREATE TABLE utilisateur (
  username varchar(20) NOT NULL COMMENT "Uid utilisateur",
  display_name varchar(100) COMMENT "Nom affiché",
  code_etudiant varchar(20) COMMENT "Identifiant etudiant",
  last_login datetime(6) COMMENT "Date et heure de derniere connexion",
  last_role varchar(50) COMMENT "Dernier role",
  PRIMARY KEY (username)
) ENGINE = INNODB COMMENT "Utilisateurs";

CREATE TABLE preferences_utilisateur (
  username varchar(20) NOT NULL COMMENT "Uid utilisateur",
  pref_id varchar(100) NOT NULL COMMENT "Preference",
  valeur varchar(200) COMMENT "Valeur",
  last_update datetime(6) COMMENT "Date et heure de derniere modification",
  PRIMARY KEY (username, pref_id)
) ENGINE = INNODB COMMENT "Preferences utilisateurs";

CREATE TABLE historique_recherche (
  username varchar(20) NOT NULL COMMENT "Uid utilisateur",
  date_create datetime(6) NOT NULL COMMENT "Date et heure de la recherche",
  uid_apprenant varchar(20) NOT NULL COMMENT "Uid etudiant",
  code_apprenant varchar(20) NOT NULL COMMENT "Code apprenant",
  display_name varchar(200) COMMENT "DisplayName",
  mail varchar(200) COMMENT "Mail",
  PRIMARY KEY (username, date_create)
) ENGINE = INNODB COMMENT "Historique d'accès depuis la recherche";


ALTER TABLE preferences_utilisateur ADD CONSTRAINT fk_username FOREIGN KEY (username) REFERENCES utilisateur (username);