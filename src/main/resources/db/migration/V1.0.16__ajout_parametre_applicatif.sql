-- --------------------------------------------------------

--
-- Maj paramètres
--

INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre)
VALUES ('PEGASE_API_IDT_URL', 'Base Url de l''API du module IDT de Pégase (attention à conserver la structure de l''url d''exemple)', NULL, 'https://idt.pegase.fr/api/idt/v1', '3', 'STRING', 1);

UPDATE preferences_application set pref_desc = 'Base Url de l''API du module PIECE EXT de Pégase (attention à conserver la structure de l''url d''exemple)'
WHERE pref_id ='PEGASE_API_PIECE_EXT_URL';

UPDATE preferences_application set ordre = 2
WHERE pref_id ='PEGASE_API_INS_EXT_URL';

UPDATE preferences_application set ordre = 3
WHERE pref_id ='PEGASE_API_INS_URL';

UPDATE preferences_application set ordre = 4
WHERE pref_id ='PEGASE_API_PIECE_EXT_URL';

UPDATE preferences_application set ordre = 5
WHERE pref_id ='PEGASE_API_CHC_URL';

UPDATE preferences_application set ordre = 6
WHERE pref_id ='PEGASE_API_COC_URL';

UPDATE preferences_application set ordre = 7
WHERE pref_id ='PEGASE_API_PAI_URL';

UPDATE preferences_application set ordre = 8
WHERE pref_id ='PEGASE_TEST_APPRENANT';

UPDATE preferences_application set ordre = 9
WHERE pref_id ='PEGASE_TEST_PERIODE';

UPDATE preferences_application set ordre = 10
WHERE pref_id ='PEGASE_TEST_CHEMIN';

UPDATE preferences_application set pref_desc = 'Code apprenant pour tester les appels aux APIs'
WHERE pref_id ='PEGASE_TEST_APPRENANT';

UPDATE preferences_application set pref_desc = 'Code période pour tester les appels aux APIs'
WHERE pref_id ='PEGASE_TEST_PERIODE';

UPDATE preferences_application set pref_desc = 'Chemin pour tester les appels aux APIs'
WHERE pref_id ='PEGASE_TEST_CHEMIN';
