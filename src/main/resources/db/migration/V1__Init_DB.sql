/*
 Conventions
 UQ_tableName_columnName UQ - UNIQUE
 FK_ForeignKeyTable_PrimaryKeyTable FK - Foreign Key
 */
create table company
(
    id BIGSERIAL,
    removal_date timestamp,
    name         varchar(255) not null,
    user_id      int8 not null,
    primary key (id)

) ;


create table company_type
(
    id BIGSERIAL,
    removal_date timestamp,
    type         varchar(255) not null,
    primary key (id),
    CONSTRAINT UQ_companyType_type UNIQUE (type)
) ;


create table company_company_type
(
    company_id      int8 not null,
    company_type_id int8 not null,
    primary key (company_id, company_type_id),

    CONSTRAINT FK_companyCompanyType_companyId
        foreign key (company_id)
            references company (id),

    CONSTRAINT FK_companyCompanyType_companyTypeId
        foreign key (company_type_id)
            references company_type (id)

) ;


create table factor
(
    id BIGSERIAL,
    removal_date timestamp,
    asset        varchar(255) not null,
    display_name varchar(255) not null,
    short_name   varchar(255),
    company_id   bigserial       not null,
    primary key (id),

    CONSTRAINT FK_factor_company
        foreign key (company_id)
            references company (id)
) ;


create table field
(
    id BIGSERIAL,
    removal_date timestamp,
    asset        varchar(255) not null,
    display_name varchar(255) not null,
    short_name   varchar(255) not null,
    company_id   bigserial       not null,
    primary key (id),

    CONSTRAINT FK_field_company
        foreign key (company_id)
            references company (id)
) ;
