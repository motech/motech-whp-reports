CREATE TABLE whp_reports.patient (
    patient_pk serial NOT NULL,
    patient_id varchar(32) UNIQUE,
    patient_alert_fk integer,
    patient_address_fk integer,
    first_name varchar(30),
    last_name varchar(30),
    gender varchar(10),
    phone_no varchar(20),
    phi varchar(30),
    status varchar(30),
    is_active char(1)
);

CREATE TABLE whp_reports.patient_therapy (
    therapy_pk serial NOT NULL,
    patient_fk integer,
    therapy_id varchar(30),

    is_current_therapy char(1),
    patient_age integer,
    creation_date date,
    start_date date,
    close_date date,
    status varchar(30),
    treatment_category varchar(30),
    disease_class varchar(30),
    current_phase varchar(10),

    ip_start_date date,
    ip_end_date date,
    ip_pills_taken integer,
    ip_pills_remaining integer,
    ip_total_doses integer,

    cp_start_date date,
    cp_end_date date,
    cp_pills_taken integer,
    cp_pills_remaining integer,
    cp_total_doses integer,

    eip_start_date date,
    eip_end_date date,
    eip_pills_taken integer,
    eip_pills_remaining integer,
    eip_total_doses integer
);


CREATE TABLE whp_reports.patient_treatment (
    treatment_pk serial NOT NULL,
    therapy_fk integer,
    provider_id varchar(30),
    provider_district varchar(30),
    tb_id varchar(30),
    start_date date,
    end_date date,
    treatment_outcome varchar(30),
    patient_type varchar(30),
    tb_registration_no varchar(30),
    pretreatment_result varchar(30),
    pretreatment_weight integer,
    is_current_treatment char(1),
    is_paused char(1),
    paused_date date,
    reasons_for_pause varchar(30),
    paused_duration integer
);

CREATE TABLE whp_reports.patient_alert (
    alert_pk serial NOT NULL,
    missed_doses integer,
    missed_doses_severity integer,
    missed_doses_as_of date,
    adherence_missing integer,
    adherence_missing_severity integer,
    adherence_missing_as_of date,
    not_started integer,
    not_started_severity integer,
    not_started_as_of date
);

CREATE TABLE whp_reports.patient_address (
    address_pk serial NOT NULL,
    location varchar(30),
    landmark varchar(30),
    block varchar(30),
    village varchar(30),
    district varchar(30),
    state varchar(30)
);