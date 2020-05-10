delete from field;
delete from company;

insert into company(id,name,user_id)
values (7,'companyName',1);

insert into field(display_name,asset,short_name,company_id)
values ('Area of company', '10 hectares', 'areaOfCompany',7)