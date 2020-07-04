delete
from company_type;

insert into company_type(id, type)
values (1, 'IT');

insert into company_type(id, type)
values (2, 'Intel');

INSERT INTO company(id, name, user_id)
VALUES (1, 'companyOne', 1);

insert into company_company_type(company_id, company_type_id)
values (1, 1);

INSERT INTO field(id, display_name, asset, short_name, company_id)
VALUES (1, 'Number of Employee', '100', 'numberOfEmployee', 1);

INSERT INTO field(id, display_name, asset, short_name, company_id)
VALUES (2, 'Number of Employee', '100', 'numberOfEmployee', 1);

INSERT INTO factor(id, display_name, asset, short_name, company_id)
VALUES (1, 'Factor 1', '555', 'Factor 1', 1);
