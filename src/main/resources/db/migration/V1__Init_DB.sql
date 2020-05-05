create table company (
                         id bigint not null auto_increment,
                         removal_date datetime(6),
                         name varchar(255),
                         user_id bigint,
                         primary key (id)
) engine=InnoDB;
    

create table company_company_type (
                                      company_id bigint not null,
                                      company_type_id bigint not null,
                                      primary key (company_id, company_type_id)
) engine=InnoDB;
    

create table company_type (
                              id bigint not null auto_increment,
                              removal_date datetime(6),
                              type varchar(255),
                              primary key (id)
) engine=InnoDB;
    

create table factor (
                        id bigint not null auto_increment,
                        removal_date datetime(6),
                        asset varchar(255),
                        display_name varchar(255),
                        short_name varchar(255),
                        company_id bigint,
                        primary key (id)
) engine=InnoDB;
    

create table field (
                       id bigint not null auto_increment,
                       removal_date datetime(6),
                       asset varchar(255),
                       display_name varchar(255),
                       short_name varchar(255),
                       company_id bigint,
                       primary key (id)
) engine=InnoDB;
    

alter table company_type
    add constraint UK_hxeflmab0qyf7keqfkymqwm98 unique (type);
    

alter table company_company_type
    add constraint FKjualf6muup5stj5462m4jhht6
        foreign key (company_type_id)
            references company_type (id);
    

alter table company_company_type
    add constraint FK986e5255w62sycycv8fiun4lk
        foreign key (company_id)
            references company (id);
    

alter table factor
    add constraint FKaua8inqphua1pwkv8pm9os0kg
        foreign key (company_id)
            references company (id);
    

alter table field
    add constraint FKma9fnk329nb5mb9ta6y34p5sp
        foreign key (company_id)
            references company (id);