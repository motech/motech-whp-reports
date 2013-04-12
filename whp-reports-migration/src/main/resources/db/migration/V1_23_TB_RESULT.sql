CREATE TABLE whp_reports.treatment_outcome (
    name varchar(100) UNIQUE NOT NULL
);

INSERT INTO whp_reports.treatment_outcome(name) VALUES ('Cured');
INSERT INTO whp_reports.treatment_outcome(name) VALUES ('Died');
INSERT INTO whp_reports.treatment_outcome(name) VALUES ('Failure');
INSERT INTO whp_reports.treatment_outcome(name) VALUES ('Defaulted');
INSERT INTO whp_reports.treatment_outcome(name) VALUES ('Transferred Out');
INSERT INTO whp_reports.treatment_outcome(name) VALUES ('Switched Over To MDR-TB Treatment');
INSERT INTO whp_reports.treatment_outcome(name) VALUES ('Treatment Completed');