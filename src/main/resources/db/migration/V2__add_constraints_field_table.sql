/*
 in table already can exist rows where display_name is null!!!
 */
update field
set field.display_name = 'Default'
where field.display_name is null;

ALTER TABLE field
    modify display_name VARCHAR(255) NOT NULL default 'Def';

