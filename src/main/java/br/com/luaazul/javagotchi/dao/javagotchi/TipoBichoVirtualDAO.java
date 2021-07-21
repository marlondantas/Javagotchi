package br.com.luaazul.javagotchi.dao.javagotchi;

import javax.persistence.EntityManager;

import br.com.luaazul.javagotchi.dao.AbstractDAO;
import br.com.luaazul.javagotchi.model.tamagotchi.TipoBichoVirtual;

public class TipoBichoVirtualDAO extends AbstractDAO<Integer,TipoBichoVirtual>{


	public TipoBichoVirtualDAO() {
	}

	public TipoBichoVirtualDAO(EntityManager entityManager) {
		super(entityManager);
	}
	
	
}
