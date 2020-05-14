delete from field;
delete from company;

insert into company(id,name,user_id)
values (15,'company1',1);

insert into company(id,name,user_id)
values (25,'company2',2);

insert into field(id,display_name,asset,short_name,company_id)
values (6,'field2', 'asset2', 'areaOfCompany',25);