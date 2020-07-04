delete from field;
delete from company;

insert into company(id,name,user_id)
values (17,'company1',1);

insert into field(id,display_name,asset,short_name,company_id)
values (5,'field1', 'asset1', 'areaOfCompany',17);


insert into company(id,name,user_id)
values (18,'company2',2);

insert into field(id,display_name,asset,short_name,company_id)
values (6,'field2', 'asset2', 'areaOfCompany',18);