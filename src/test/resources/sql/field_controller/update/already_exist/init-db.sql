delete
from field;
delete
from company;

insert into company(id, name, user_id)
values (10, 'companyName', 1);

insert into field(id, asset, display_name, short_name, company_id)
values (18,'asset2','disp2','asset2',10);

insert into field(id, asset, display_name, short_name, company_id)
values (19,'asset3','disp3','asset3',10);