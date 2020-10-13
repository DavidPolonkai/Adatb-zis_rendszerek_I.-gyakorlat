--DDL(létrehozás)
create table gyarto(
    adoszam int primary key,
    nev varchar2(100) NOT NULL,
    telephely varchar2(100)
);
/

create table termek(
    tkod int primary key,
    nev varchar2(100) NOT NULL,
    ear int CHECK(ear>0),
    gyarto int references gyarto(adoszam)
);
/
--DDL(mező deffiníció módosítása)
alter table termek MODIFY nev varchar2(100) NOT NULL;/
--DML(Adatfelvitel)
insert into gyarto values(1,'jó.bt','Miskolc');/
insert into termek values(1,'Termek1',2000,1);/

insert into termek(tkod,nev,ear,gyarto) values(2,'T2',1400,1);/
insert into termek(tkod,nev,ear,gyarto) values(3,'T3',3000,1);/

--DQL(Adat lekérdezés)
select * from termek 
select * from gyarto

create table egysegek(
    aru int references termek(tkod),
    db int  
);/

create table alkatresz(
    akod int primary key,
    nev varchar2(100) NOT NULL
);/

CREATE TABLE KOMPONENS(
    TERMEK INT REFERENCES TERMEK(TKOD),
    ALKATRESZ INT REFERENCES ALKATRESZ(AKOD)
);/


