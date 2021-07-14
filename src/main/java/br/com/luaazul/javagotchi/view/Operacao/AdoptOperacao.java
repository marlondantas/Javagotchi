package br.com.luaazul.javagotchi.view.Operacao;

import java.awt.Color;

import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.slf4j.LoggerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.luaazul.javagotchi.view.AbstractOperacao;
import br.com.luaazul.javagotchi.controller.model.UsuarioController;
import br.com.luaazul.javagotchi.controller.javagotchi.BichoVirtualController;
import br.com.luaazul.javagotchi.controller.model.RegistroController;
import br.com.luaazul.javagotchi.model.Registro;
import br.com.luaazul.javagotchi.model.Usuario;
import br.com.luaazul.javagotchi.model.tamagotchi.BichoVirtual;
import br.com.luaazul.javagotchi.util.exception.UsuarioNaoExiste;
import br.com.luaazul.javagotchi.util.exception.UsuarioNaoTemBichoVitual;

public class AdoptOperacao extends AbstractOperacao {

	public static Logger logger = LoggerFactory.getLogger(AdoptOperacao.class);
	
	
	UsuarioController usuarioController = new UsuarioController();
	BichoVirtualController bichoVirtualController = new BichoVirtualController();

	@Override
	public void execute(MessageCreateEvent event, String[] arrayParametros) throws UsuarioNaoExiste, UsuarioNaoTemBichoVitual, Exception {
		
		EmbedBuilder embed = new EmbedBuilder() 
				.setTitle("Info: Bicho Virtual")
			    .setFooter("Bot by Mar@MoonBlue")
			    .setColor(Color.red);
		
		try {
			//verifica o usuario (existe ou nao)
			Usuario usuarioExiste = usuarioController.verificarUsuario(event.getMessageAuthor().getIdAsString(), event.getServer().get().getIdAsString());
			
			if(usuarioExiste == null){
				//cria o usuario
				usuarioExiste = new Usuario(event.getMessageAuthor().getIdAsString(), event.getServer().get().getIdAsString());
				usuarioController.getUsuarioDAO().save(usuarioExiste);
				
				embed.addField("ADOTANTO UM NOVO AMIGO", "CUIDA BEM DELE");
				adotarBicho(usuarioExiste,arrayParametros[1]);
				
			}
	
			//verifica se ele tem pet
			BichoVirtual bichoVirtual = bichoVirtualController.verificarBicho(usuarioExiste,embed);
			
			//se nao tiver mostra o pet
			if(bichoVirtual != null){
				event.getMessage().reply(embed);
				return;
			}

			adotarBicho(usuarioExiste, arrayParametros[1]);			
			bichoVirtual = bichoVirtualController.verificarBicho(usuarioExiste,embed);
			
			event.getMessage().reply(embed);
			
		} catch (Exception e) {
			logger.error("ERRO: {}",e.getMessage());
			e.printStackTrace();
			throw new Exception(e);
		}
	}
	
	private void adotarBicho(Usuario usuarioExiste, String nome) {
		bichoVirtualController.adotarBicho(usuarioExiste, nome);
	}
	
}
