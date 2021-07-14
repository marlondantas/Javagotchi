package br.com.luaazul.javagotchi.controller.model;

import org.javacord.api.entity.message.embed.EmbedBuilder;
import javax.persistence.EntityManager;
import java.text.MessageFormat;


import br.com.luaazul.javagotchi.model.Usuario;
import br.com.luaazul.javagotchi.dao.model.UsuarioDAO;

public class UsuarioController {

	private UsuarioDAO usuarioDAO;
	
	public UsuarioController() {
		usuarioDAO = new UsuarioDAO();
	}

	public Usuario verificarUsuario(String idUsuario, String idServidor,EmbedBuilder embed) {
		
		Usuario usuario = verificarUsuario(idUsuario, idServidor);
		
		if(usuario != null) {
			embed.addField("Dono:",MessageFormat.format("<@{0}>",idUsuario) );
			return usuario;
		} else {
			embed.addField(":thinking:","Você ainda não faz parte da brincadeira\nUse o comando *!!adopt <nome>* para começar");
		}
		
		return null;
	}
	
	public Usuario verificarUsuario(String idUsuario, String idServidor) {
		Usuario usuario = new Usuario(idUsuario,idServidor);		
		return usuarioDAO.verificarUsuario(usuario);
	}
	
	public EntityManager getEntityManager() {
		return usuarioDAO.getEntityManager();
	}
	
	public UsuarioDAO getUsuarioDAO() {
		return this.usuarioDAO;
	}
	
}
