create table company (
                         id bigint not null auto_increment,
                         removal_date datetime(6),
                         company_type varchar(255) not null,
                         name varchar(255),
                         user_id bigint not null,
                         primary key (id)
) engine=InnoDB;


INSERT INTO company (company_type, name, user_id)
VALUES ('some', 'name1', 1);

INSERT INTO company (company_type, name, user_id)
VALUES ('some', 'name2', 1);

INSERT INTO company (company_type, name, user_id)
VALUES ('some', 'name3', 1);