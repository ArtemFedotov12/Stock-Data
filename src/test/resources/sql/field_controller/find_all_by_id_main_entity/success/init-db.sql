delete from field;
delete from company;

insert into company(id, name, user_id)
values (12, 'companyName', 1);

insert into field(id, display_name, asset, short_name, company_id)
values (5, 'disp1', 'asset1', 'asset1', 12);

insert into field(id, display_name, asset, short_name, company_id)
values (6, 'disp2', 'asset2', 'asset1', 12);

insert into field(id, display_name, asset, short_name, company_id)
values (7, 'disp3', 'asset3', 'asset3', 12);