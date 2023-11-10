-- --------------------------------------------------------

--
-- Modification des libellés 
--


UPDATE preferences_application
set pref_desc = 'Base Url de l''API du module INS de Pégase (attention à conserver la structure de l''url d''exemple)' 
where pref_id = 'PEGASE_API_INS_URL';

UPDATE preferences_application
set pref_desc='Base Url de l''API CHC de Pégase (attention à conserver la structure de l''url d''exemple)'
where pref_id = 'PEGASE_API_CHC_URL';

UPDATE preferences_application 
set pref_desc= 'Base Url de l''API COC (Publication) de Pégase (attention à conserver la structure de l''url d''exemple)'
where pref_id = 'PEGASE_API_COC_URL';

UPDATE preferences_application
set pref_desc='Base Url de l''API PAI de Pégase (attention à conserver la structure de l''url d''exemple)'
where pref_id = 'PEGASE_API_PAI_URL';


