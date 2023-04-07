-- --------------------------------------------------------

--
-- Ajout paramètres
--

INSERT INTO preferences_application_categorie (cat_id, cat_desc, ordre) VALUES ('10', 'Css', '10');

INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('CSS_MAIN_COLOR', 'Couleur principale de l''application', NULL, '#343a40', '10', 'STRING', 1);

INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('CSS_SECOND_COLOR', 'Couleur secondaire de l''application', NULL, 'var(--_lumo-button-color, var(--lumo-primary-text-color))', '10', 'STRING', 2);

INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('CSS_HEADER_CARD_SEP_COLOR', 'Couleur du séparateur sous le header des cartes', NULL, '#343a40', '10', 'STRING', 3);

INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('CSS_TEXT_COLOR', 'Couleur des textes (labels)', NULL, '#343a40', '10', 'STRING', 4);

INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('CSS_BTN_COLOR', 'Couleur des boutons', NULL, 'var(--_lumo-button-color, var(--lumo-primary-text-color))', '10', 'STRING', 5);

INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('CSS_BACKGROUND_COLOR', 'Couleur du background de l''application', NULL, 'var(--lumo-contrast-5pct)', '10', 'STRING', 6);


