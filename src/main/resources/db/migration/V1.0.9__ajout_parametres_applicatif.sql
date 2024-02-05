-- --------------------------------------------------------

--
-- Ajout paramètres 
--

-- Décalage des paramètres d'affichage
update preferences_application pa set ordre = pa.ordre + 1 where cat_id=6 and ordre > 2;

-- Ajout du paramètre de contrôle du paiement
INSERT INTO preferences_application (pref_id, pref_desc, secret, valeur, cat_id, type_id, ordre) 
VALUES ('CTRL_ATTEST_PAI', 'Contrôler la validité du paiement pour proposer l''attestation de paiement', NULL, 'true', '6', 'BOOLEAN', 2);

