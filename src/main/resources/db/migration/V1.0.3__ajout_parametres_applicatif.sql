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
INSERT INTO preferences_application_categorie (cat_id, cat_desc, ordre) VALUES ('2', 'Pégase Access token', '2');
INSERT INTO preferences_application_categorie (cat_id, cat_desc, ordre) VALUES ('3', 'Pégase API', '3');
INSERT INTO preferences_application_categorie (cat_id, cat_desc, ordre) VALUES ('4', 'Pégase Paramétrage', '4');
INSERT INTO preferences_application_categorie (cat_id, cat_desc, ordre) VALUES ('5', 'Profils', '5');
INSERT INTO preferences_application_categorie (cat_id, cat_desc, ordre) VALUES ('6', 'Affichage', '6');
INSERT INTO preferences_application_categorie (cat_id, cat_desc, ordre) VALUES ('7', 'Aide', '7');
INSERT INTO preferences_application_categorie (cat_id, cat_desc, ordre) VALUES ('8', 'Administration', '8');

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
VALUES ('LDAP_DISPLAYNAME_ATTRIBUTE', 'Attribut ldap contenant le displayname de l''utilisateur connecté ', NULL, 'displayName', '1', 'STRING',1);
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('LDAP_CODETU_ATTRIBUTE', 'Attribut ldap contenant le code apprenant de l''étudiant', NULL, 'supannEtuId', '1', 'STRING',2);
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('LDAP_MAIL_ATTRIBUTE', 'Attribut ldap contenant le mail de l''étudiant', NULL, 'mail', '1', 'STRING', 3);
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('LDAP_FILTRE_ETUDIANT', 'Filtre ldap identifiant les étudiants', NULL, '(supannEtuId=*)', '1', 'STRING', 4);
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('LDAP_FILTRE_GESTIONNAIRE', 'Filtre ldap identifiant les gestionnaires de l''application (possibilité de consulter tous les dossiers)', NULL, NULL, '1', 'STRING', 5);

-- paramètres Pégase token d’accès
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('ACCESSTOKEN_URL', 'Url de l''API du serveur OAuth Pégase pour la récupération de l''access token', NULL, NULL, '2', 'STRING',1);
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('ACCESSTOKEN_USERNAME', 'Username pour s''authentifier auprès du serveur OAuth Pégase en tant qu''applicatif MDW', NULL, NULL, '2', 'STRING', 2);
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('ACCESSTOKEN_PASSWORD', 'password pour s''authentifier auprès du serveur OAuth Pégase en tant qu''applicatif MDW', NULL, NULL, '2', 'SECRET_STRING', 3);
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('ACCESSTOKEN_DURATION', 'Durée en heure de conservation de l''access token', NULL, '6', '2', 'STRING', 4);

-- paramètres Pégase API
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('PEGASE_API_INS_URL', 'Base Url de l''API du module INS (Gestion v5) de Pégase (attention à conserver la structure de l''url d''exemple)', NULL, 'https://ins-pegase.fr/api/v5/ins', '3', 'STRING', 1);
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('PEGASE_API_CHC_URL', 'Base Url de l''API CHC (Application v5) de Pégase (attention à conserver la structure de l''url d''exemple)', NULL, 'https://chc-pegase.fr/api/chc/v5', '3', 'STRING', 2);
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('PEGASE_API_COC_URL', 'Base Url de l''API COC (Publication v1) de Pégase (attention à conserver la structure de l''url d''exemple)', NULL, 'https://coc.pegase.fr/api/coc/publication/v1', '3', 'STRING', 3);
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('PEGASE_API_PAI_URL', 'Base Url de l''API PAI (Paiement v1) de Pégase (attention à conserver la structure de l''url d''exemple)', NULL, 'https://pai-pegase.fr/api/v1', '3', 'STRING', 4);
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('PEGASE_TEST_PERIODE', 'Permet de cibler une période pour tester les appels aux API.', NULL, 'PER-2020', '3', 'STRING', 5);
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('PEGASE_TEST_APPRENANT', 'Permet de cibler un dossier pour tester les appels aux API.', NULL, '000000001', '3', 'STRING', 6);
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('PEGASE_TEST_CHEMIN', 'Permet de cibler un chemin pour tester les appels aux API.', NULL, 'F-ING-HYD>F-ING-HYD-A4', '3', 'STRING', 7);

-- paramètres Pégase Paramétrage
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('PEGASE_ETAB', 'Code de l''établissement dans Pégase', NULL, 'ETAB00', '4', 'STRING', 1);
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('PEGASE_ID_PJ_PHOTO', 'Code de la PJ Pégase correspondant à la photo de l''étudiant', NULL, 'PHOTO-001', '4', 'STRING', 2);
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('PEGASE_DEMO_APPRENANT', 'Permet de cibler un dossier par défaut. Attention : A renseigner uniquement pour une démonstration ou en phase de test/développement.', NULL, '000000001', '4', 'STRING', 3);


-- paramètres des profils
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('ACCES_GESTIONNAIRE_ACTIF', 'Activer le profil gestionnaire de l''application. Un gestionnaire peut consulter tous les dossiers en indiquant le code apprenant dans l''Url', NULL, 'false', '5', 'BOOLEAN', 1);
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('ACCES_ETUDIANT_ACTIF', 'Activer le profil étudiant pour que les étudiants puissent consulter leurs dossiers sur l''application', NULL, 'true', '5', 'BOOLEAN', 2);

-- paramètres d'affichage
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('BTN_CERT', 'Proposer le téléchargement du certificat de scolarité', NULL, 'true', '6', 'BOOLEAN', 1);
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('BTN_ATTEST_PAI', 'Proposer le téléchargement de l''attestation de paiement', NULL, 'true', '6', 'BOOLEAN', 2);
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('NOTE_BAREME', 'Afficher le barême avec les notes', NULL, 'true', '6', 'BOOLEAN', 3);
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('NOTE_COEFF', 'Afficher le coeff avec le libelle du résultat', NULL, 'true', '6', 'BOOLEAN', 4);
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('NOTE_ECTS', 'Afficher les crédits ECTS avec les résultats', NULL, 'true', '6', 'BOOLEAN', 5);
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('NOTE_CONTROLE', 'Afficher les contrôles avec les résultats', NULL, 'false', '6', 'BOOLEAN', 6);
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('INSCRIPTION_DETAIL', 'Afficher le détail de l''inscription (statut, paiement, PJ, photo) : true, false, button', NULL, 'button', '6', 'LIST_STRING', 7);
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('UNIV_LOGO_PATH', 'Path vers le logo de l''université (à placer dans le répertoire ''images'')', NULL, './images/logo.png', '6', 'STRING', 8);
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('CURSUS_FAC_ITALIQUE', 'Afficher les objets de formation non obligatoires du cursus en italique', NULL, 'true', '6', 'BOOLEAN', 9);
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('INSCRIPTION_STATUTS', 'Statuts des inscriptions à afficher séparés par des virgules', NULL, 'valide', '6', 'STRING', 10);
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('INFO_CONNEXION', 'true si on veut afficher une pop-up d''info à la connexion sur l''application', NULL, 'true', '6', 'BOOLEAN', 11);
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('INFO_CONNEXION_PREF', 'true si on veut donner la possibilité à l''utilisateur de masquer la pop-up d''info de connexion via case à cocher', NULL, 'true', '6', 'BOOLEAN', 12);
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('ETUDIANT_MAIL_LDAP', 'true si on veut afficher le mail extrait du ldap', NULL, 'true', '6', 'BOOLEAN', 13);
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('ETUDIANT_RESUME', 'true si on veut afficher le nom/prenom et le numéro de dossier au dessus du menu latéral', NULL, 'true', '6', 'BOOLEAN', 14);

-- paramètres d'aide
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('DOC_URL', 'URL de la documentation de l''application', NULL, 'https://wiki.fr', '7', 'STRING', 1);
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('HELP_URL', 'URL du helpdesk', NULL, 'https://helpdesk.univ.fr', '7', 'STRING', 2);

-- paramètres d'administration
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('SHOW_SQL', 'true pour tracer les requêtes SQL dans les logs de l''application', NULL, 'true', '8', 'BOOLEAN', 1);


-- 
-- Insertion des valeurs
-- 
INSERT INTO preferences_application_valeurs (val_id, pref_id, valeur, libelle) VALUES (1, 'INSCRIPTION_DETAIL', 'button', 'Via clic bouton');
INSERT INTO preferences_application_valeurs (val_id, pref_id, valeur, libelle) VALUES (2, 'INSCRIPTION_DETAIL', 'true', 'Toujours');
INSERT INTO preferences_application_valeurs (val_id, pref_id, valeur, libelle) VALUES (3, 'INSCRIPTION_DETAIL', 'false', 'Jamais');
