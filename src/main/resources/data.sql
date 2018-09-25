
-- CREATION OF PARAMETERS_TYPE
-- ALTER SEQUENCE public.parameter_type_seq RESTART;

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
-- ALTER SEQUENCE public.parameter_seq RESTART;

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
-- ALTER SEQUENCE public.user_account_seq RESTART;

insert into public.user_account(id, uid, password, first_name, last_name, trigram, tec_member, office_department, enabled, LASTPASSWORDRESETDATE, fk_parameter_division ) 
SELECT nextval('public.user_account_seq'), 'a24169', '$2a$10$zM.PjxkIyRs55ptt494FX.9FFMU1OMIuT.tnEaUF8fGOYHLKXwL2m', 'Hugues', 'Poumeyrol', 'HPO', true, 'DSI/TPS/TEC/SAR', true, to_timestamp('01-01-2016', 'dd-MM-yyyy'),
 (select id from public.parameter where fk_parameter_type = (select id from public.parameter_type where label  = 'division')  and label = 'SAR')
WHERE NOT EXISTS (select 1 from public.user_account where uid = 'a24169');


insert into public.user_account(id, uid, password, first_name, last_name, trigram, tec_member, office_department, enabled, LASTPASSWORDRESETDATE ) 
SELECT nextval('public.user_account_seq'), 'a24168', '1234', 'Gaëlle', 'Thomas', 'GTO', true, 'DSI/TPS/TEC/MERGINAC', true, to_timestamp('01-01-2016', 'dd-MM-yyyy')
WHERE NOT EXISTS (select 1 from public.user_account where uid = 'a24168');


-- CREATION OF REQUEST_USER_ACCOUNT
-- ALTER SEQUENCE public.request_user_account_seq RESTART;

insert into public.request_user_account(id, uid, desired_password, first_name, last_name, state, request_date, reply_date ) 
SELECT nextval('public.request_user_account_seq'), 'a24169', '1234', 'Hugues', 'Poumeyrol', 2, to_timestamp('18-09-2018', 'dd-MM-yyyy'), to_timestamp('19-09-2018', 'dd-MM-yyyy')
WHERE NOT EXISTS (select 1 from public.request_user_account where uid = 'a24169');

insert into public.request_user_account(id, uid, desired_password, first_name, last_name, state, request_date ) 
SELECT nextval('public.request_user_account_seq'), 'a24165', '1234', 'Jules', 'Martin', 1, to_timestamp('01-08-2018', 'dd-MM-yyyy')
WHERE NOT EXISTS (select 1 from public.request_user_account where uid = 'a24165');


insert into public.request_user_account(id, uid, desired_password, first_name, last_name, state, request_date ) 
SELECT nextval('public.request_user_account_seq'), 'a24170', '1234', 'Fred', 'Durandet', 1, to_timestamp('19-09-2018', 'dd-MM-yyyy')
WHERE NOT EXISTS (select 1 from public.request_user_account where uid = 'a24170');


insert into public.request_user_account(id, uid, desired_password, first_name, last_name, state, request_date ) 
SELECT nextval('public.request_user_account_seq'), 'a24120', '1234', 'Pierre', 'Smith', 1, to_timestamp('07-09-2018', 'dd-MM-yyyy')
WHERE NOT EXISTS (select 1 from public.request_user_account where uid = 'a24120');


insert into public.request_user_account(id, uid, desired_password, first_name, last_name, state, request_date, reply_date) 
SELECT nextval('public.request_user_account_seq'), 'b2555', '1234', 'John', 'Doe', 3, to_timestamp('19-09-2018', 'dd-MM-yyyy'), to_timestamp('20-09-2018', 'dd-MM-yyyy')
WHERE NOT EXISTS (select 1 from public.request_user_account where uid = 'b2555');


-- SECURITY AUTH

INSERT INTO USER_CRED (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE) 
select nextval('USER_seq'), 'admin', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', 'admin', 'admin', 'admin@admin.com', true, to_timestamp('01-01-2016', 'dd-MM-yyyy')
WHERE NOT EXISTS (select 1 from USER_CRED where USERNAME = 'admin');

INSERT INTO USER_CRED (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE) 
select nextval('USER_seq'), 'user', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'enabled@user.com', true, to_timestamp('01-01-2016','dd-MM-yyyy')
WHERE NOT EXISTS (select 1 from USER_CRED where USERNAME = 'user');

INSERT INTO USER_CRED (ID, USERNAME, PASSWORD, FIRSTNAME, LASTNAME, EMAIL, ENABLED, LASTPASSWORDRESETDATE)
select nextval('USER_seq'), 'disabled', '$2a$08$UkVvwpULis18S19S5pZFn.YHPZt3oaqHZnDwqbCW9pft6uFtkXKDC', 'user', 'user', 'disabled@user.com', false, to_timestamp('01-01-2016','dd-MM-yyyy')
WHERE NOT EXISTS (select 1 from USER_CRED where USERNAME = 'disabled');
 
INSERT INTO AUTHORITY (ID, NAME) select  nextval('AUTHORITY_seq'), 'ROLE_USER' WHERE NOT EXISTS (select 1 from AUTHORITY where ID = 1);
INSERT INTO AUTHORITY (ID, NAME) select  nextval('AUTHORITY_seq'), 'ROLE_ADMIN' WHERE NOT EXISTS (select 1 from AUTHORITY where ID = 2);
INSERT INTO AUTHORITY (ID, NAME) select  nextval('AUTHORITY_seq'), 'ROLE_CONSULT' WHERE NOT EXISTS (select 1 from AUTHORITY where ID = 3);

 
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) select  1, 1 WHERE NOT EXISTS (select 1 from USER_AUTHORITY where USER_ID = 1 AND AUTHORITY_ID = 1);
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) select  1, 2 WHERE NOT EXISTS (select 1 from USER_AUTHORITY where USER_ID = 1 AND AUTHORITY_ID = 2);
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) select  2, 1 WHERE NOT EXISTS (select 1 from USER_AUTHORITY where USER_ID = 2 AND AUTHORITY_ID = 1);
INSERT INTO USER_AUTHORITY (USER_ID, AUTHORITY_ID) select  3, 1 WHERE NOT EXISTS (select 1 from USER_AUTHORITY where USER_ID = 3 AND AUTHORITY_ID = 1);


