delete
from field;
delete
from company;

insert into company(id, name, user_id)
values (10, 'companyName', 1);

insert into field(id, asset, display_name, short_name, company_id)
values (18,'asset1','disp1','asset1',10)