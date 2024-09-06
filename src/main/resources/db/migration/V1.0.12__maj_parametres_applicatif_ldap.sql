-- --------------------------------------------------------

--
-- Maj paramètres
--

-- Modification Id
UPDATE preferences_application set pref_id='CAS_CODETU_ATTRIBUTE' where pref_id='LDAP_CODETU_ATTRIBUTE';

UPDATE preferences_application set pref_id='CAS_MAIL_ATTRIBUTE' where pref_id='LDAP_MAIL_ATTRIBUTE';

UPDATE preferences_application set pref_id='CAS_DISPLAYNAME_ATTRIBUTE' where pref_id='LDAP_DISPLAYNAME_ATTRIBUTE';

UPDATE preferences_application set pref_id='ETUDIANT_MAIL_CAS' where pref_id='ETUDIANT_MAIL_LDAP';


UPDATE preferences_application set pref_desc='Attribut CAS contenant le code apprenant de l''étudiant' where pref_id='CAS_CODETU_ATTRIBUTE';

UPDATE preferences_application set pref_desc='Attribut CAS contenant le mail de l''étudiant' where pref_id='CAS_MAIL_ATTRIBUTE';

UPDATE preferences_application set pref_desc='Attribut CAS contenant le displayname de l''utilisateur' where pref_id='CAS_DISPLAYNAME_ATTRIBUTE';


UPDATE preferences_application set pref_desc='Attribut CAS permettant d''identifier un étudiant' where pref_id='CAS_FILTRE_ETUDIANT';

UPDATE preferences_application set pref_desc='Attribut CAS permettant d''identifier un gestionnaire' where pref_id='CAS_FILTRE_GESTIONNAIRE';


UPDATE preferences_application_categorie set cat_desc='CAS' where cat_id='1';
