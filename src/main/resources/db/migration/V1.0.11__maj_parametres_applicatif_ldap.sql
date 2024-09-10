-- --------------------------------------------------------

--
-- Maj paramètres
--

-- Modification de la description de ETUDIANT_MAIL_LDAP
UPDATE preferences_application set pref_desc='Afficher le mail récupéré via le CAS' where pref_id='ETUDIANT_MAIL_LDAP';

-- Suppression des paramètres représentant des filtres ldap
DELETE from preferences_application where pref_id='LDAP_FILTRE_ETUDIANT';

DELETE from preferences_application where pref_id='LDAP_FILTRE_GESTIONNAIRE';
