CREATE USER 'javagotchi'@'%' IDENTIFIED BY 'javagotchi';

SELECT DISTINCT TABLE_NAME, CONCAT('select * from ',TABLE_SCHEMA, '.',TABLE_NAME,';') 'select_QUERY'
    FROM INFORMATION_SCHEMA.COLUMNS
    WHERE TABLE_SCHEMA='Javagotchi';
    
create database Javagotchi;
create database JavagotchiDEV;

grant all on Javagotchi to javagotchi;
grant all on JavagotchiDEV to javagotchi;

select * from DeusaColheita.TBOD_SERVIDOR;
select * from DeusaColheita.TBOD_JOGO  where ds_jogador = '297938915178840065' order by id_total_pontos desc limit 3;
select * from DeusaColheita.TBOD_HISTORICO_JOGO;

select * from Javagotchi.TBOD_SERVIDOR;

select * from Javagotchi.TBOD_REGISTRO;

select * from Javagotchi.TBOD_USUARIO;
select * from Javagotchi.TBOD_BICHO_VIRTUAL;