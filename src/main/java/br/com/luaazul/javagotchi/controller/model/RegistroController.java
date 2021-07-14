package br.com.luaazul.javagotchi.controller.model;

import javax.persistence.EntityManager;

import br.com.luaazul.javagotchi.model.Registro;
import br.com.luaazul.javagotchi.dao.model.RegistroDAO;

public class RegistroController {

	private RegistroDAO registroDAO;
	
	public RegistroController() {
		 registroDAO = new RegistroDAO();
	}
	
	public RegistroDAO getRegistroDAO() {
		return this.registroDAO;
	}
	
	
	public EntityManager getEntityManager() {
		return this.registroDAO.getEntityManager();
	}
	
}
