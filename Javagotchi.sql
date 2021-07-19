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

update Javagotchi.TBOD_BICHO_VIRTUAL set VL_VIDA_TOTAL = 50;
update Javagotchi.TBOD_BICHO_VIRTUAL set VL_VIDA = 50;




SET SQL_SAFE_UPDATES = 0;



 select bichovirtu0_.ID_PET as id_pet1_0_, bichovirtu0_.VL_ENERGIA as vl_energ2_0_, bichovirtu0_.VL_EX_PONTOS as vl_ex_po3_0_, bichovirtu0_.VL_FELICIDADE as vl_felic4_0_, bichovirtu0_.VL_FOME as vl_fome5_0_, bichovirtu0_.VL_LIMPEZA as vl_limpe6_0_, bichovirtu0_.DS_NOME as ds_nome7_0_, bichovirtu0_.DS_SERVIDOR as ds_servi8_0_, bichovirtu0_.CD_STATUS as cd_statu9_0_, bichovirtu0_.CD_TIPO_BICHO as cd_tipo10_0_, bichovirtu0_.DS_USUARIO as ds_usua11_0_, bichovirtu0_.VL_VIDA as vl_vida12_0_, bichovirtu0_.VL_VIDA_TOTAL as vl_vida13_0_, bichovirtu0_.CD_VIVO as cd_vivo14_0_ 
  from Javagotchi.TBOD_BICHO_VIRTUAL bichovirtu0_ where bichovirtu0_.CD_STATUS='1';

delete from Javagotchi.TBOD_BICHO_VIRTUAL;

