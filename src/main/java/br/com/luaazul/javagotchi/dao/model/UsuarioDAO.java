package br.com.luaazul.javagotchi.dao.model;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

import br.com.luaazul.javagotchi.dao.AbstractDAO;
import br.com.luaazul.javagotchi.model.Usuario;

public class UsuarioDAO extends AbstractDAO<Integer, Usuario> {

	private  final String BUSCAR_USUARIO = "SELECT user FROM Usuario user WHERE user.usuario = :DS_USUARIO";
	
	public UsuarioDAO(){
		super();
	}
	
	public Usuario verificarUsuario(Usuario usuario) {
		
		Query query = this.entityManager.createQuery(BUSCAR_USUARIO, Usuario.class);
		
		query.setParameter("DS_USUARIO",usuario.getUsuario());
		
		try {
			return (Usuario) query.getSingleResult();
		}
		catch ( NoResultException nre ) {
			return null;
		}
		
	} 
	
	
	
}
