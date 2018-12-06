DROP TABLE IF EXISTS form2.arbitr;
CREATE TABLE IF NOT EXISTS form2.arbitr
(
  id                       serial not null
    constraint arbitr_pkey
    primary key,
  user_id                  varchar(255),
  the_sro_fullname         varchar(1024),
  arbitr_inn_number        varchar(255),
  arbitr_snils_number      varchar(255),
  arbitr_surname           varchar(512),
  arbitr_name              varchar(512),
  arbitr_secondname        varchar(512),
  arbitr_efrsb_reg_number  varchar(255),
  arbitr_rreestr_reg_date  varchar(255),
  arbitr_sro_entry_date    varchar(255),
  arbitr_efrsb_url_address varchar(512),
  is_arbitr_active         boolean default false
);

DROP TABLE IF EXISTS form2.the_sro;
CREATE TABLE IF NOT EXISTS form2.the_sro
(
  id                                      serial not null
    constraint the_sro_pkey
    primary key,
  sro_shortname                           varchar(1024),
  sro_fullname                            varchar(1024),
  sro_efrsb_reg_number                    varchar(255),
  sro_efrsb_reg_date                      varchar(255),
  sro_inn_number                          varchar(255),
  sro_ogrn_number                         varchar(255),
  sro_jur_address                         varchar(1024),
  rreestr_jur_address                     varchar(1024),
  sro_post_address                        varchar(1024),
  sro_phone_fax_number                    varchar(255),
  sro_email_address                       varchar(255),
  sro_website_address                     varchar(255),
  rreestr_compensation_fund_summa         varchar(255),
  sro_compensation_fund_summa             varchar(255),
  sro_compensation_fund_summa_change_date varchar(255),
  sro_manager_fio                         varchar(1024),
  sro_contact_fio                         varchar(1024),
  is_sro_active                           boolean default false,
  is_sro_in_the_rreestr                   boolean default false,
  sro_efrsb_url_address                   varchar(512)
);
