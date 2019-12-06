create extension if not exists pgcrypto;

update profile set password = crypt(password, gen_salt('bf', 8));


insert into  personal_account values( default,'Водоканал','208103','Чита, ул. Кирова, 16-81',true,'Владимир', 'Номоконов','Михайлович',1,'27-09-2018');
insert into  personal_account values( default,'Энергосбыт','123654789','Чита, ул. Кирова, 16-81',true,'Владимир', 'Номоконов','Михайлович',1,'27-09-2018');

insert into  personal_account values( default,'Водоканал','208103','Чита, ул. Кирова, 16-81',true,'Иван', 'Иванов','Иванович',2,'28-09-2018');
insert into  personal_account values( default,'Энергосбыт','123654789','Чита, ул. Кирова, 16-81',true,'Иван', 'Иванов','Иванович',2,'28-09-2018');

insert into  personal_account values( default,'Водоканал','208103','Чита, ул. Кирова, 16-81',true,'Петр', 'Петров','Петрович',3,'20-09-2018');
insert into  personal_account values( default,'Энергосбыт','123654789','Чита, ул. Кирова, 16-81',true,'Петр', 'Петров','Петрович',3,'20-09-2018');

