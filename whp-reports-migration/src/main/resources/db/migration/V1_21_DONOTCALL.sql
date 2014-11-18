CREATE TABLE whp_reports.do_not_call (
    id serial NOT NULL,
    entity varchar(100),
    entity_id varchar(100),
    alert_type varchar(100),
    mobile_number varchar(20),
    updated_by varchar(100),
    updated_on timestamp
);