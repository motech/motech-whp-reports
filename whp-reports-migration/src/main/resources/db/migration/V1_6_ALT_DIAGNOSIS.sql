CREATE TABLE whp_reports.alternate_diagnosis (
    code varchar(10) UNIQUE NOT NULL,
    text varchar(100) UNIQUE NOT NULL
);


INSERT INTO whp_reports.alternate_diagnosis(text, code) values ('Other bacterial diseases,not elsewhere classified', '1027');
INSERT INTO whp_reports.alternate_diagnosis(text, code) values ('Malignant neoplasm of bronchus and lung', '1110');
INSERT INTO whp_reports.alternate_diagnosis(text, code) values ('Angina pectoris', '1330');
INSERT INTO whp_reports.alternate_diagnosis(text, code) values ('Chronic ischaemic heart disease', '1334');
INSERT INTO whp_reports.alternate_diagnosis(text, code) values ('Heart failure', '1354');
INSERT INTO whp_reports.alternate_diagnosis(text, code) values ('Heart disease, unspecified', '1356');
INSERT INTO whp_reports.alternate_diagnosis(text, code) values ('Pneumonia, organism unspecified', '1389');
INSERT INTO whp_reports.alternate_diagnosis(text, code) values ('Vasomotor and allergic rhinitis', '1392');
INSERT INTO whp_reports.alternate_diagnosis(text, code) values ('Chronic sinusitis', '1394');
INSERT INTO whp_reports.alternate_diagnosis(text, code) values ('Other disorders of nose and nasal sinuses', '1396');
INSERT INTO whp_reports.alternate_diagnosis(text, code) values ('Bronchitis, not specified as acute or chronic', '1400');
INSERT INTO whp_reports.alternate_diagnosis(text, code) values ('Chronic obstructive pulmonary disease, unspecified', '1403');
INSERT INTO whp_reports.alternate_diagnosis(text, code) values ('Asthma', '1404');
INSERT INTO whp_reports.alternate_diagnosis(text, code) values ('Bronchiectasis', '1406');
INSERT INTO whp_reports.alternate_diagnosis(text, code) values ('Pulmonary oedema', '1410');
INSERT INTO whp_reports.alternate_diagnosis(text, code) values ('Pulmonary eosinophilia, not elsewhere classified', '1411');
INSERT INTO whp_reports.alternate_diagnosis(text, code) values ('Abscess of lung and mediastinum', '1412');
INSERT INTO whp_reports.alternate_diagnosis(text, code) values ('Pleural effusion, not elsewhere classified', '1414');
INSERT INTO whp_reports.alternate_diagnosis(text, code) values ('Respiratory disorder, unspecified', '1417');
INSERT INTO whp_reports.alternate_diagnosis(text, code) values ('Haemoptysis', '1740');
INSERT INTO whp_reports.alternate_diagnosis(text, code) values ('Cough', '1741');
INSERT INTO whp_reports.alternate_diagnosis(text, code) values ('Dyspnoea', '1742');
INSERT INTO whp_reports.alternate_diagnosis(text, code) values ('Pleurisy', '1754');
INSERT INTO whp_reports.alternate_diagnosis(text, code) values ('Abnormal sputum', '1756');
INSERT INTO whp_reports.alternate_diagnosis(text, code) values ('Fever of unknown origin', '1843');
INSERT INTO whp_reports.alternate_diagnosis(text, code) values ('Allergy, unspecified', '1948');