package br.com.luaazul.javagotchi.dao.javagotchi;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.luaazul.javagotchi.dao.AbstractDAO;
import br.com.luaazul.javagotchi.model.Usuario;
import br.com.luaazul.javagotchi.model.tamagotchi.BichoVirtual;

public class BichoVirtualDAO extends AbstractDAO<Integer,BichoVirtual>{

	private final static String BUSCAR_BICHO_HASH_ATIVO= "SELECT BICHO FROM BichoVirtual BICHO WHERE BICHO.usuario =:DS_USUARIO and  BICHO.servidor =:DS_SERVIDOR AND BICHO.vivo = '0' ";
	
	public BichoVirtualDAO() {
	}

	public BichoVirtual buscarBichoAtivo(Usuario usuario) {
		
		Query query = entityManager.createQuery(BUSCAR_BICHO_HASH_ATIVO, BichoVirtual.class);
		
		query.setParameter("DS_USUARIO", usuario.getUsuario());
		query.setParameter("DS_SERVIDOR", usuario.getServidor());
		
		try {
			return (BichoVirtual) query.getSingleResult();
		} catch ( NoResultException nre ) {
			return null;
		}
	}
	
	
}
