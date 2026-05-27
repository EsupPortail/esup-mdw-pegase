-- --------------------------------------------------------

--
-- Suppression du ACCESSTOKEN_PASSWORD pour modifier la méthode de cryptage
--

update preferences_application set secret = null where pref_id = 'ACCESSTOKEN_PASSWORD';

