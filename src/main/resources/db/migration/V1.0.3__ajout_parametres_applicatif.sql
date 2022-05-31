-- --------------------------------------------------------

--
-- Structure de la table preferences_application
--

CREATE TABLE preferences_application (
  pref_id varchar(100) NOT NULL,
  pref_desc varchar(255) DEFAULT NULL,
  secret varchar(100) DEFAULT NULL,
  valeur varchar(200) DEFAULT NULL,
  cat_id int(11) NOT NULL,
  type_id varchar(20) NOT NULL,
  ordre int(11) NOT NULL
);

-- --------------------------------------------------------

--
-- Structure de la table preferences_application_categorie
--

CREATE TABLE preferences_application_categorie (
  cat_id int(11) NOT NULL,
  cat_desc varchar(255) DEFAULT NULL,
  ordre int(11) NOT NULL
);

-- --------------------------------------------------------

--
-- Structure de la table preferences_application_type
--

CREATE TABLE preferences_application_type (
  type_id varchar(20) NOT NULL,
  secret tinyint(1) NOT NULL
);

-- --------------------------------------------------------

--
-- Structure de la table preferences_application_valeurs
--

CREATE TABLE preferences_application_valeurs (
  val_id int(11) NOT NULL,
  pref_id varchar(100) NOT NULL,
  valeur varchar(100) NOT NULL,
  libelle varchar(200) NOT NULL
);



--
-- Index pour la table preferences_application
--
ALTER TABLE preferences_application
  ADD PRIMARY KEY (pref_id);

--
-- Index pour la table preferences_application_categorie
--
ALTER TABLE preferences_application_categorie
  ADD PRIMARY KEY (cat_id);

--
-- Index pour la table preferences_application_type
--
ALTER TABLE preferences_application_type
  ADD PRIMARY KEY (type_id);

--
-- Index pour la table preferences_application_valeurs
--
ALTER TABLE preferences_application_valeurs
  ADD PRIMARY KEY (val_id);

--
-- Contraintes pour la table preferences_application
--
ALTER TABLE preferences_application
  ADD CONSTRAINT FK_TYPE_PREF FOREIGN KEY (type_id) REFERENCES preferences_application_type (type_id),
  ADD CONSTRAINT FK_CAT_PREF FOREIGN KEY (cat_id) REFERENCES preferences_application_categorie (cat_id);

--
-- Contraintes pour la table preferences_application_valeurs
--
ALTER TABLE preferences_application_valeurs
  ADD CONSTRAINT FK_PREF FOREIGN KEY (pref_id) REFERENCES preferences_application (pref_id);
 
COMMIT;
 
-- 
-- Insertion des catégories
-- 
INSERT INTO preferences_application_categorie (cat_id, cat_desc, ordre) VALUES ('1', 'LDAP', '1');
INSERT INTO preferences_application_categorie (cat_id, cat_desc, ordre) VALUES ('2', 'Pégase', '2');
INSERT INTO preferences_application_categorie (cat_id, cat_desc, ordre) VALUES ('3', 'Profils', '3');
INSERT INTO preferences_application_categorie (cat_id, cat_desc, ordre) VALUES ('4', 'Affichage', '4');
INSERT INTO preferences_application_categorie (cat_id, cat_desc, ordre) VALUES ('5', 'Aide', '5');

-- 
-- Insertion des types
-- 
INSERT INTO preferences_application_type (type_id, secret) VALUES ('STRING', 0);
INSERT INTO preferences_application_type (type_id, secret) VALUES ('BOOLEAN', 0);
INSERT INTO preferences_application_type (type_id, secret) VALUES ('SECRET_STRING', 1);
INSERT INTO preferences_application_type (type_id, secret) VALUES ('LIST_STRING', 0);

-- 
-- Insertion des paramètres
-- 

-- paramètres LDAP
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('LDAP_CODETU_ATTRIBUTE', 'Attribut ldap contenant le code apprenant de l''étudiant', NULL, 'supannEtuId', '1', 'STRING',1);
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('LDAP_MAIL_ATTRIBUTE', 'Attribut ldap contenant le mail de l''étudiant', NULL, 'mail', '1', 'STRING', 2);
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('LDAP_FILTRE_ETUDIANT', 'Filtre ldap identifiant les étudiants', NULL, '(supannEtuId=*)', '1', 'STRING', 3);
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('LDAP_FILTRE_GESTIONNAIRE', 'Filtre ldap identifiant les gestionnaire de l''application (possibilité de consulter tous les dossiers)', NULL, NULL, '1', 'STRING', 4);

-- paramètres Pégase
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('ACCESSTOKEN_URL', 'Url de l''API du serveur OAuth Pégase pour la récupération de l''access-token', NULL, NULL, '2', 'STRING',1);
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('ACCESSTOKEN_USERNAME', 'Username pour s''authentifier auprès du serveur OAuth Pégase en tant qu''applicatif MDW', NULL, NULL, '2', 'STRING', 2);
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('ACCESSTOKEN_PASSWORD', 'password pour s''authentifier auprès du serveur OAuth Pégase en tant qu''applicatif MDW', NULL, NULL, '2', 'SECRET_STRING', 3);

-- paramètres des profils
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('ACCES_GESTIONNAIRE_ACTIF', 'Activer le profil gestionnaire de l''application. Un gestionnaire peut consulter tous les dossiers', NULL, 'false', '3', 'BOOLEAN', 1);
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('ACCES_ETUDIANT_ACTIF', 'Activer le profil étudiant pour que les étudiants puissent consulter leurs dossiers sur l''application', NULL, 'true', '3', 'BOOLEAN', 2);

-- paramètres d'affichage
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('NOTE_BAREME', 'Afficher le barême avec les notes', NULL, 'true', '4', 'BOOLEAN', 1);
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('NOTE_COEFF', 'Afficher le coeff avec le libelle du résultat', NULL, 'true', '4', 'BOOLEAN', 2);
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('NOTE_ECTS', 'Afficher les crédits ECTS avec les résultats', NULL, 'true', '4', 'BOOLEAN', 3);
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('NOTE_CONTROLE', 'Afficher les contrôles avec les résultats', NULL, 'false', '4', 'BOOLEAN', 4);
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('INSCRIPTION_DETAIL', 'Afficher le détail de l''inscription (statut, paiement, PJ, photo) : true, false, button', NULL, 'button', '4', 'LIST_STRING', 5);
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('UNIV_LOGO_PATH', 'Path vers le logo de l''université (à placer dans le répertoire ''images'')', NULL, './images/logo.png', '4', 'STRING', 6);
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('CURSUS_FAC_ITALIQUE', 'Afficher les objets de formation non obligatoires du cursus en italique', NULL, 'true', '4', 'BOOLEAN', 7);
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('INSCRIPTION_STATUTS', 'Statuts des inscriptions à afficher séparés par des virgules', NULL, 'valide', '4', 'STRING', 8);

-- paramètres d'aide
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('DOC_URL', 'URL de la documentation de l''application', NULL, 'https://wiki.fr', '5', 'STRING', 1);
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('HELP_URL', 'URL du helpdesk', NULL, 'https://helpdesk.univ-lorraine.fr', '5', 'STRING', 2);


-- 
-- Insertion des valeurs
-- 
INSERT INTO preferences_application_valeurs (val_id, pref_id, valeur, libelle) VALUES (1, 'INSCRIPTION_DETAIL', 'button', 'Via clic bouton');
INSERT INTO preferences_application_valeurs (val_id, pref_id, valeur, libelle) VALUES (2, 'INSCRIPTION_DETAIL', 'true', 'Toujours');
INSERT INTO preferences_application_valeurs (val_id, pref_id, valeur, libelle) VALUES (3, 'INSCRIPTION_DETAIL', 'false', 'Jamais');
