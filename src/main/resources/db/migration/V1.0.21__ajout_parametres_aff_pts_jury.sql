-- --------------------------------------------------------

--
-- Ajout preferences_application et POINTS_JURY
--

update preferences_application set ordre = ordre + 1 where cat_id = 6 and ordre > 5;

INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre)
VALUES ('POINTS_JURY', 'Afficher les points de jury avec les résultats', NULL, 'false', '6', 'BOOLEAN', 6);

