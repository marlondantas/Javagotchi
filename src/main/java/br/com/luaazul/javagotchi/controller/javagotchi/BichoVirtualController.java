package br.com.luaazul.javagotchi.controller.javagotchi;

import java.util.Date;

import javax.persistence.EntityManager;

import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.luaazul.javagotchi.model.Usuario;
import br.com.luaazul.javagotchi.model.tamagotchi.BichoVirtual;

import br.com.luaazul.javagotchi.dao.model.RegistroDAO;
import br.com.luaazul.javagotchi.dao.javagotchi.BichoVirtualDAO;
import br.com.luaazul.javagotchi.model.Registro;

public class BichoVirtualController {
	
	public Logger logger = LoggerFactory.getLogger(BichoVirtualController.class);
	
	private BichoVirtualDAO bichoVirtualDAO;
	
	public BichoVirtualController() {
		super();
		bichoVirtualDAO = new BichoVirtualDAO();
	}

	public void executarProcedureStatus() {
		logger.info("INICIANDO CRITICA DE STATUS {}","POR HORA");
		
		
		
		
		logger.info("FINALIZANDO CRITICA DE STATUS {}","POR HORA");
	}
	
	public BichoVirtual verificarBicho(Usuario usuario,EmbedBuilder embed) {
		
		BichoVirtual bichoVirtual = bichoVirtualDAO.buscarBichoAtivo(usuario);
		
		if(bichoVirtual != null) {
			embed.addField("NOME:",bichoVirtual.getNome());
			embed.addInlineField("VIDA", "10/10?");
			
			embed.setImage("https://media.discordapp.net/attachments/828779647146786856/864307122559909919/SlimePreview.png");
			
			return bichoVirtual;
		}
		
		return null;
	}
	
	public void adotarBicho(Usuario usuario, String nome) {
		logger.info("Iniciando operacao adotarBicho para o USUARIO {}",usuario.getUsuario());
		try {
			BichoVirtual bichoVirtual = new BichoVirtual();

			bichoVirtual.setNome(nome);
			bichoVirtual.setFelicidade(100f);
			bichoVirtual.setServidor(usuario.getServidor());
			bichoVirtual.setUsuario(usuario.getUsuario());
			bichoVirtual.setStatus('1');
			bichoVirtualDAO.save(bichoVirtual);
			
		} catch (Exception e) {
			logger.error("ACONTECEU UM ERRO DURANTE O PROCESSAMENTO {}",e.getMessage());
			e.printStackTrace();
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
