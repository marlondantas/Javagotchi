package br.com.luaazul.javagotchi.controller.javagotchi;


import org.javacord.api.entity.message.embed.EmbedBuilder;
import java.text.MessageFormat;
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
		
		String status= "Vida: {0}/{1} Fome: {2}/{3}";
		status = MessageFormat.format(status,bichoVirtual.getVida(),bichoVirtual.getVidaTotal(),bichoVirtual.getFome(),"0");
		embed.addInlineField("Status:",status);
		
		// TODO esquema para pegar as coisa do bicho, provalvemente dentro da propria classe dele
		embed.setImage("https://cdn.discordapp.com/attachments/674061878313484318/866494986152837121/GatoGarconette2.gif");
		
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
	
	public BichoVirtualDAO getBichoVirtualDAO() {
		return bichoVirtualDAO;
	}
	
}
