CREATE USER 'javagotchi'@'%' IDENTIFIED BY 'javagotchi';

SELECT DISTINCT TABLE_NAME, CONCAT('select * from ',TABLE_SCHEMA, '.',TABLE_NAME,';') 'select_QUERY'
    FROM INFORMATION_SCHEMA.COLUMNS
    WHERE TABLE_SCHEMA='Javagotchi';
    
create database Javagotchi;
create database JavagotchiDEV;

use Javagotchi;

grant all on Javagotchi to javagotchi;
grant all on JavagotchiDEV to javagotchi;

select * from Javagotchi.TBOD_SERVIDOR;

select * from Javagotchi.TBOD_REGISTRO;

select * from Javagotchi.TBOD_USUARIO;
select * from Javagotchi.TBOD_BICHO_VIRTUAL;

select * from Javagotchi.TBOD_TIPO_BICHO;
select * from TBOD_ESTADO_TIPO_BICHO;

SET SQL_SAFE_UPDATES = 0;


'setup';

insert into Javagotchi.TBOD_TIPO_BICHO(ds_nome_raca) VALUES("Gatito de copos");

insert into Javagotchi.TBOD_ESTADO_TIPO_BICHO value ('1','0','https://media.discordapp.net/attachments/674061878313484318/866494986152837121/GatoGarconette2.gif');

 insert into TBOD_BICHO_VIRTUAL (VL_ENERGIA, VL_EX_PONTOS, VL_FELICIDADE, VL_FOME, VL_LIMPEZA, DS_NOME, DS_SERVIDOR, CD_STATUS, CD_TIPO_BICHO, DS_USUARIO, VL_VIDA, VL_VIDA_TOTAL, CD_VIVO) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
