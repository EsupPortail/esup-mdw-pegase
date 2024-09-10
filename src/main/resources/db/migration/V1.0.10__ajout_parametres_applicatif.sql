-- --------------------------------------------------------

--
-- Ajout paramètres 
--

-- Ajout catégorie
INSERT INTO preferences_application_type (type_id, secret) VALUES ('KEY_VALUES', 0);

-- Ajout du paramètre d'identification des étudiants à partir d'attributs retournés par le CAS
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('CAS_FILTRE_ETUDIANT', 'Attribut et les valeurs permettant d''identifier un étudiant', NULL, NULL, '1', 'KEY_VALUES', 4);

-- Ajout du paramètre d'identification des étudiants à partir d'attributs retournés par le CAS
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre)
VALUES ('CAS_FILTRE_GESTIONNAIRE', 'Attribut et les valeurs permettant d''identifier un gestionnaire', NULL, NULL, '1', 'KEY_VALUES', 5);

