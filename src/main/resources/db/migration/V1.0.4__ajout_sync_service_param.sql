-- --------------------------------------------------------

--
-- Structure de la table preferences_service_sync
--

CREATE TABLE preferences_service_sync (
  service_name varchar(50) NOT NULL  COMMENT 'Nom du service',
  method_name varchar(50) NOT NULL  COMMENT 'Nom de la méthode à appeler',
  username varchar(20) DEFAULT NULL COMMENT 'Uid utilisateur',
  last_update datetime(6) COMMENT 'Date et heure de derniere modification',
  PRIMARY KEY (service_name, method_name)
);


