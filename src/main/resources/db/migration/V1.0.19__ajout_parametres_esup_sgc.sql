-- --------------------------------------------------------

--
-- Ajout categorie et parametre ESUP-SGC
--
UPDATE preferences_application_categorie
SET ordre = ordre + 1
WHERE ordre > 6;

INSERT INTO preferences_application_categorie (cat_id, cat_desc, ordre)
VALUES ('11', 'Web-Service de récupération de photo via ESUP-SGC', '7');

INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre)
VALUES (
	'ESUP_SGC_PHOTO_URL',
	'Ex. : https://esup-sgc.example.org/wsrest/photo/%s/restrictedPhotoSupannEtuId?cardEtat=ENABLED',
	NULL,
	NULL,
	'11',
	'STRING',
	1
);

