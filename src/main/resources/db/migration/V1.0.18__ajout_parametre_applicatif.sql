-- --------------------------------------------------------

--
-- Maj paramètres
--
update preferences_application set ordre = ordre + 1 where ordre > 13;

INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre)
VALUES ('CURSUS_FAC_BUTTON', 'Afficher un bouton ''facultatif'' dédié pour les objets de formation non obligatoires du cursus', NULL, 'true', '6', 'BOOLEAN', 14);
