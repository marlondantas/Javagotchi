package br.com.luaazul.javagotchi.controller.javagotchi;


import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.luaazul.javagotchi.model.Usuario;
import br.com.luaazul.javagotchi.model.tamagotchi.BichoVirtual;

import br.com.luaazul.javagotchi.dao.javagotchi.BichoVirtualDAO;

public class BichoVirtualController {
	
	public Logger logger = LoggerFactory.getLogger(BichoVirtualController.class);
	
	private BichoVirtualDAO bichoVirtualDAO;
	
	public BichoVirtualController() {
		super();
		bichoVirtualDAO = new BichoVirtualDAO();
	}

	public EmbedBuilder getEmbedBichoVitual(EmbedBuilder embed, boolean adotar, BichoVirtual bichoVirtual) {
		
		if(adotar){
			embed.addField("ADOTANTO UM NOVO AMIGO", "CUIDA BEM DELE");
		}
		
		//ser tiver mostra o pet
		embed.addField("NOME:",bichoVirtual.getNome());
		embed.addInlineField("VIDA", "10/10?");
		// TODO esquema para pegar as coisa do bicho, provalvemente dentro da propria classe dele
		embed.setImage("https://media.discordapp.net/attachments/834243996096266311/864999834708738078/slim.gif");
		
		return embed;	
	}
	
	public void executarProcedureStatus() {
		logger.info("INICIANDO CRITICA DE STATUS {}","POR HORA");
		
		
		
		
		logger.info("FINALIZANDO CRITICA DE STATUS {}","POR HORA");
	}
	
	public BichoVirtual verificarBicho(Usuario usuario) {
		
		BichoVirtual bichoVirtual = bichoVirtualDAO.buscarBichoAtivo(usuario);
		
		if(bichoVirtual != null) {
			return bichoVirtual;
		}
		
		return null;
	}
	
	public BichoVirtual adotarBicho(Usuario usuario, String nome) throws Exception {
		logger.info("Iniciando operacao adotarBicho para o USUARIO {}",usuario.getUsuario());
		try {
			BichoVirtual bichoVirtual = new BichoVirtual();

			bichoVirtual.setNome(nome);
			bichoVirtual.setFelicidade(100f);
			bichoVirtual.setServidor(usuario.getServidor());
			bichoVirtual.setUsuario(usuario.getUsuario());
			bichoVirtual.setStatus('1');
			bichoVirtualDAO.save(bichoVirtual);
			
			return bichoVirtual;
		} catch (Exception e) {
			logger.error("ACONTECEU UM ERRO DURANTE O PROCESSAMENTO {}",e.getMessage());
			e.printStackTrace();
			throw new Exception();
		}finally {
			logger.info("FINALIZANDO operacao adotarBicho para o USUARIO {}",usuario.getUsuario());
		}
	}
	
	public void alimentarBicho() {
		
	}

	public void dormirBicho() {
		
	}
	
	public void brincarBicho() {
		
	}
	
	public void treinarBicho() {
		
	}
}
