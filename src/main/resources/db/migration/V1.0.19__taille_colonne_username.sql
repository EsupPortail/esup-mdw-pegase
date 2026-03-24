-- --------------------------------------------------------

--
-- Maj paramètres
--


ALTER TABLE preferences_utilisateur MODIFY COLUMN username VARCHAR(100) NOT NULL COMMENT 'Uid utilisateur';
