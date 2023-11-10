-- --------------------------------------------------------

--
-- Ajout paramètres 
--



-- paramètres Pégase API
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('PEGASE_API_INS_EXT_URL', 'Base Url de l''API du module INS-EXT de Pégase (attention à conserver la structure de l''url d''exemple)', NULL, 'https://ins-pegase.fr/api/ins/ext/v1', '3', 'STRING', 0);
