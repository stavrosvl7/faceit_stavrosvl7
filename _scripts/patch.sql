create sequence dom_country_id_seq start with 1;

CREATE TABLE dom_country (
    id bigint primary key DEFAULT nextval('dom_country_id_seq'),
    name text not null,
    code text unique not null,
    phone_international_prefix text unique not null
);

create sequence web_user_id_seq start with 1;

CREATE TABLE web_user (
    id bigint primary key DEFAULT nextval('web_user_id_seq'),
    first_name text ,
    last_name text ,
    nick_name text ,
    password text not null,
    email text unique not null,
    byte_payload text,
    country_id bigint not null,
    CONSTRAINT country_id_fk FOREIGN KEY (country_id) REFERENCES dom_country (id)
);

insert into dom_country values(nextval('dom_country_id_seq'),'Greece','gr','+30');
insert into dom_country values(nextval('dom_country_id_seq'),'USA','us','+1');
insert into web_user values(nextval('web_user_id_seq'),'gianna','rouvali','ioannarouv15','$2y$10$K/KG1GjUw9TCp3vAltw3/OEpBcuVWpSzbIigI/WmGDAE6Brab0lz2 ','ioannarouv15@gmail.com',null,'1');
insert into web_user values(nextval('web_user_id_seq'),'kostas','rouvalis','zigkompikoule','$2y$10$K/KG1GjUw9TCp3vAltw3/OEpBcuVWpSzbIigI/WmGDAE6Brab0lz2 ','zigkompikoule@gmail.com',null,'2');
insert into web_user values(nextval('web_user_id_seq'),'stavros','vlachakis','stavrosvl7','$2y$10$K/KG1GjUw9TCp3vAltw3/OEpBcuVWpSzbIigI/WmGDAE6Brab0lz2 ','stavrosvl7@gmail.com',null,'1');