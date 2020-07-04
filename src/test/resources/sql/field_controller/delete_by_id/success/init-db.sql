delete
from field;

delete
from company;

insert into company(id, name, user_id)
values (10, 'company1', 1);

insert into field(id, display_name, asset, short_name, company_id)
values (5, 'Number of Employee', '100', 'numberOfEmployee', 10);