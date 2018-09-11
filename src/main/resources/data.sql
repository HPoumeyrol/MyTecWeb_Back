
-- CREATION OF PARAMETERS_TYPE
ALTER SEQUENCE public.parameter_type_seq RESTART;

insert into public.parameter_type(id, label) 
SELECT nextval('public.parameter_type_seq'), 'division'
WHERE NOT EXISTS (select 1 from public.parameter_type where label = 'division');

insert into public.parameter_type(id, label) 
SELECT nextval('public.parameter_type_seq'), 'location'
WHERE NOT EXISTS (select 1 from public.parameter_type where label = 'location');

insert into public.parameter_type(id, label) 
SELECT nextval('public.parameter_type_seq'), 'tec_team'
WHERE NOT EXISTS (select 1 from public.parameter_type where label = 'tec_team');

insert into public.parameter_type(id, label) 
SELECT nextval('public.parameter_type_seq'), 'function'
WHERE NOT EXISTS (select 1 from public.parameter_type where label = 'function');

insert into public.parameter_type(id, label) 
SELECT nextval('public.parameter_type_seq'), 'rolemytec'
WHERE NOT EXISTS (select 1 from public.parameter_type where label = 'rolemytec');

insert into public.parameter_type(id, label) 
SELECT nextval('public.parameter_type_seq'), 'working_time'
WHERE NOT EXISTS (select 1 from public.parameter_type where label = 'working_time');


-- CREATION OF PARAMETERS
ALTER SEQUENCE public.parameter_seq RESTART;

--DIVISION
insert into public.parameter (id, fk_parameter_type, label, str_value) 
SELECT nextval('public.parameter_seq'), (select id from public.parameter_type where label  = 'division'), 'MGNT', 'Management'
WHERE NOT EXISTS (select 1 from public.parameter where fk_parameter_type = (select id from public.parameter_type where label  = 'division')  and label = 'MGNT');

insert into public.parameter(id, fk_parameter_type, label, str_value) 
SELECT nextval('public.parameter_seq'), (select id from public.parameter_type where label  = 'division'), 'SAR', 'Support Automatisation Reporting'
WHERE NOT EXISTS (select 1 from public.parameter where fk_parameter_type = (select id from public.parameter_type where label  = 'division')  and label = 'SAR');

insert into public.parameter(id, fk_parameter_type, label, str_value) 
SELECT nextval('public.parameter_seq'), (select id from public.parameter_type where label  = 'division'), 'TECPARIS', 'Tec Paris'
WHERE NOT EXISTS (select 1 from public.parameter where fk_parameter_type = (select id from public.parameter_type where label  = 'division')  and label = 'TECPARIS');

insert into public.parameter(id, fk_parameter_type, label, str_value) 
SELECT nextval('public.parameter_seq'), (select id from public.parameter_type where label  = 'division'), 'TECMERIGNAC', 'Tec Mérignac'
WHERE NOT EXISTS (select 1 from public.parameter where fk_parameter_type = (select id from public.parameter_type where label  = 'division')  and label = 'TECMERIGNAC');


--LOCATION
insert into public.parameter(id, fk_parameter_type, label, str_value) 
SELECT nextval('public.parameter_seq'), (select id from public.parameter_type where label  = 'location'), 'PARIS', 'Paris'
WHERE NOT EXISTS (select 1 from public.parameter where fk_parameter_type = (select id from public.parameter_type where label  = 'location')  and label = 'PARIS');

insert into public.parameter(id, fk_parameter_type, label, str_value) 
SELECT nextval('public.parameter_seq'), (select id from public.parameter_type where label  = 'location'), 'MERIGNAC', 'Mérignac'
WHERE NOT EXISTS (select 1 from public.parameter where fk_parameter_type = (select id from public.parameter_type where label  = 'location')  and label = 'MERIGNAC');

--TECTEAM
insert into public.parameter(id, fk_parameter_type, label, str_value) 
SELECT nextval('public.parameter_seq'), (select id from public.parameter_type where label  = 'tec_team'), 'DIGITAL', 'Digital'
WHERE NOT EXISTS (select 1 from public.parameter where fk_parameter_type = (select id from public.parameter_type where label  = 'tec_team')  and label = 'DIGITAL');

insert into public.parameter(id, fk_parameter_type, label, str_value) 
SELECT nextval('public.parameter_seq'), (select id from public.parameter_type where label  = 'tec_team'), 'MONETIQUE', 'Monétique'
WHERE NOT EXISTS (select 1 from public.parameter where fk_parameter_type = (select id from public.parameter_type where label  = 'tec_team')  and label = 'MONETIQUE');

--FUNCTION
insert into public.parameter(id, fk_parameter_type, label, str_value) 
SELECT nextval('public.parameter_seq'), (select id from public.parameter_type where label  = 'function'), 'MGR', 'Manager'
WHERE NOT EXISTS (select 1 from public.parameter where fk_parameter_type = (select id from public.parameter_type where label  = 'function')  and label = 'MGR');

insert into public.parameter(id, fk_parameter_type, label, str_value) 
SELECT nextval('public.parameter_seq'), (select id from public.parameter_type where label  = 'function'), 'TM', 'Test Manager'
WHERE NOT EXISTS (select 1 from public.parameter where fk_parameter_type = (select id from public.parameter_type where label  = 'function')  and label = 'TM');

insert into public.parameter(id, fk_parameter_type, label, str_value) 
SELECT nextval('public.parameter_seq'), (select id from public.parameter_type where label  = 'function'), 'TST', 'Testeur'
WHERE NOT EXISTS (select 1 from public.parameter where fk_parameter_type = (select id from public.parameter_type where label  = 'function')  and label = 'TST');

insert into public.parameter(id, fk_parameter_type, label, str_value) 
SELECT nextval('public.parameter_seq'), (select id from public.parameter_type where label  = 'function'), 'AUTDEV', 'Automaticien'
WHERE NOT EXISTS (select 1 from public.parameter where fk_parameter_type = (select id from public.parameter_type where label  = 'function')  and label = 'AUTDEV');

insert into public.parameter(id, fk_parameter_type, label, str_value) 
SELECT nextval('public.parameter_seq'), (select id from public.parameter_type where label  = 'function'), 'ANASP', 'Analyste support'
WHERE NOT EXISTS (select 1 from public.parameter where fk_parameter_type = (select id from public.parameter_type where label  = 'function')  and label = 'ANASP');


--ROLEMYTEC
insert into public.parameter(id, fk_parameter_type, label, str_value) 
SELECT nextval('public.parameter_seq'), (select id from public.parameter_type where label  = 'rolemytec'), 'ADM', 'Administrateur'
WHERE NOT EXISTS (select 1 from public.parameter where fk_parameter_type = (select id from public.parameter_type where label  = 'rolemytec')  and label = 'ADM');

insert into public.parameter(id, fk_parameter_type, label, str_value) 
SELECT nextval('public.parameter_seq'), (select id from public.parameter_type where label  = 'rolemytec'), 'TM', 'Test Manager'
WHERE NOT EXISTS (select 1 from public.parameter where fk_parameter_type = (select id from public.parameter_type where label  = 'rolemytec')  and label = 'TM');

insert into public.parameter(id, fk_parameter_type, label, str_value) 
SELECT nextval('public.parameter_seq'), (select id from public.parameter_type where label  = 'rolemytec'), 'CONSLT', 'Consult'
WHERE NOT EXISTS (select 1 from public.parameter where fk_parameter_type = (select id from public.parameter_type where label  = 'rolemytec')  and label = 'CONSLT');


--WORKINGTIME
insert into public.parameter(id, fk_parameter_type, label, str_value, num_value) 
SELECT nextval('public.parameter_seq'), (select id from public.parameter_type where label  = 'working_time'), '100', '100 %', 100.0
WHERE NOT EXISTS (select 1 from public.parameter where fk_parameter_type = (select id from public.parameter_type where label  = 'working_time')  and label = '100');

insert into public.parameter(id, fk_parameter_type, label, str_value, num_value) 
SELECT nextval('public.parameter_seq'), (select id from public.parameter_type where label  = 'working_time'), '90', '90 %', 90.0
WHERE NOT EXISTS (select 1 from public.parameter where fk_parameter_type = (select id from public.parameter_type where label  = 'working_time')  and label = '90');


insert into public.parameter(id, fk_parameter_type, label, str_value, num_value) 
SELECT nextval('public.parameter_seq'), (select id from public.parameter_type where label  = 'working_time'), '80', '80 %', 80.0
WHERE NOT EXISTS (select 1 from public.parameter where fk_parameter_type = (select id from public.parameter_type where label  = 'working_time')  and label = '80');

insert into public.parameter(id, fk_parameter_type, label, str_value, num_value) 
SELECT nextval('public.parameter_seq'), (select id from public.parameter_type where label  = 'working_time'), '50', '50 %', 50.0
WHERE NOT EXISTS (select 1 from public.parameter where fk_parameter_type = (select id from public.parameter_type where label  = 'working_time')  and label = '50');


-- CREATION OF USER_ACCOUNT
ALTER SEQUENCE public.user_account_seq RESTART;

insert into public.user_account(id, uid, password, first_name, last_name, trigram, tec_member, office_department, enabled ) 
SELECT nextval('public.user_account_seq'), 'a24169', '1234', 'Hugues', 'Poumeyrol', 'HPO', true, 'DSI/TPS/TEC/SAR', true
WHERE NOT EXISTS (select 1 from public.user_account where uid = 'a24169');

