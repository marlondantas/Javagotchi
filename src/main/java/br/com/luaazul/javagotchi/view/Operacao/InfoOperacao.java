package br.com.luaazul.javagotchi.view.Operacao;

import java.awt.Color;

import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.entity.message.MessageBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.luaazul.javagotchi.view.AbstractOperacao;
import br.com.luaazul.javagotchi.controller.model.UsuarioController;
import br.com.luaazul.javagotchi.controller.javagotchi.BichoVirtualController;
import br.com.luaazul.javagotchi.model.Usuario;
import br.com.luaazul.javagotchi.model.tamagotchi.BichoVirtual;
import br.com.luaazul.javagotchi.util.exception.UsuarioNaoExiste;
import br.com.luaazul.javagotchi.util.exception.UsuarioNaoTemBichoVitual;
import br.com.luaazul.javagotchi.controller.MessageController;

public class InfoOperacao extends AbstractOperacao {

	public static Logger logger = LoggerFactory.getLogger(InfoOperacao.class);
	
	
	UsuarioController usuarioController = new UsuarioController();
	BichoVirtualController bichoVirtualController = new BichoVirtualController();

	@Override
	public MessageBuilder execute(MessageCreateEvent event, String[] arrayParametros) throws UsuarioNaoExiste, UsuarioNaoTemBichoVitual, Exception {
		
		MessageBuilder mensagem = new MessageBuilder();
		
		EmbedBuilder embed = MessageController.getEmbed("Info: Bicho Virtual", Color.MAGENTA); 
		//verifica o usuario (existe ou nao)
		Usuario usuarioExiste = usuarioController.verificarUsuarioServidor(event.getMessageAuthor().getIdAsString(), event.getServer().get().getIdAsString());
		
		if(usuarioExiste == null){
			throw new UsuarioNaoExiste();
		}

		embed.addField("DONO",MessageController.getCitarUsuario(usuarioExiste.getUsuario()));
		
		//verifica se ele tem pet
		BichoVirtual bichoVirtual = bichoVirtualController.verificarBicho(usuarioExiste);
		
		//se nao tiver mostra o pet
		if(bichoVirtual == null){
			throw new UsuarioNaoTemBichoVitual();
		}
		
		embed = bichoVirtualController.getEmbedBichoVitual(embed, false, bichoVirtual);
		
		mensagem.setEmbed(embed);
		
		return mensagem;
	}
	
}
