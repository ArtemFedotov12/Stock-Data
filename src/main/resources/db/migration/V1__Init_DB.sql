/*
 Conventions
 UQ_tableName_columnName UQ - UNIQUE
 FK_ForeignKeyTable_PrimaryKeyTable FK - Foreign Key
 */
create table company
(
    id           bigint       not null auto_increment,
    removal_date datetime(6),
    name         varchar(255) not null,
    user_id      bigint       not null,
    primary key (id)

) engine = InnoDB;


create table company_type
(
    id           bigint       not null auto_increment,
    removal_date datetime(6),
    type         varchar(255) not null,
    primary key (id),
    CONSTRAINT UQ_companyType_type UNIQUE (type)
) engine = InnoDB;


create table company_company_type
(
    company_id      bigint not null,
    company_type_id bigint not null,
    primary key (company_id, company_type_id),

    CONSTRAINT FK_companyCompanyType_companyId
        foreign key (company_id)
            references company (id),

    CONSTRAINT FK_companyCompanyType_companyTypeId
        foreign key (company_type_id)
            references company_type (id)

) engine = InnoDB;


create table factor
(
    id           bigint       not null auto_increment,
    removal_date datetime(6),
    asset        varchar(255) not null,
    display_name varchar(255) not null,
    short_name   varchar(255),
    company_id   bigint       not null,
    primary key (id),

    CONSTRAINT FK_factor_company
        foreign key (company_id)
            references company (id)
) engine = InnoDB;


create table field
(
    id           bigint       not null auto_increment,
    removal_date datetime(6),
    asset        varchar(255) not null,
    display_name varchar(255),
    short_name   varchar(255) not null,
    company_id   bigint       not null,
    primary key (id),

    CONSTRAINT FK_field_company
        foreign key (company_id)
            references company (id)
) engine = InnoDB;

