CREATE TABLE whp_reports.reasons_for_closure (
    code varchar(10) UNIQUE NOT NULL,
    text varchar(100) UNIQUE NOT NULL
);


INSERT INTO whp_reports.reasons_for_closure(text, code) values ('Sputum container mapped to patient', '0');
INSERT INTO whp_reports.reasons_for_closure(text, code) values ('Diagnosis TB Negative', '1');
INSERT INTO whp_reports.reasons_for_closure(text, code) values ('Container lost', '2');                            
INSERT INTO whp_reports.reasons_for_closure(text, code) values ('Invalid Container', '3');                         
INSERT INTO whp_reports.reasons_for_closure(text, code) values ('Patient lost to follow-up', '4');        
INSERT INTO whp_reports.reasons_for_closure(text, code) values ('Patient did not get sputum tested (no reason stated)', '5');
INSERT INTO whp_reports.reasons_for_closure(text, code) values ('Patient did not get sputum tested (cough improved)', '6');
INSERT INTO whp_reports.reasons_for_closure(text, code) values ('Patient started on TB treatment elsewhere', '7');