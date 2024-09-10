-- --------------------------------------------------------

--
-- Maj paramètres
--

-- Modification Id
INSERT INTO preferences_application_type (type_id, secret) VALUES ('VALUES', 0);

UPDATE preferences_application set type_id='VALUES' where pref_id in ('ADMINS','LOG_MAIL_TO');

UPDATE preferences_application set pref_desc='Logins des administrateurs supplémentaires de l''application' where pref_id = 'ADMINS';

UPDATE preferences_application set pref_desc='Destinataires des mails d''erreur' where pref_id = 'LOG_MAIL_TO';