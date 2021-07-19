package br.com.luaazul.javagotchi.view.Operacao;

import java.awt.Color;

import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.entity.message.embed.EmbedBuilder;
import org.javacord.api.event.message.MessageCreateEvent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.luaazul.javagotchi.view.AbstractOperacao;
import br.com.luaazul.javagotchi.controller.model.UsuarioController;
import br.com.luaazul.javagotchi.controller.javagotchi.BichoVirtualController;
import br.com.luaazul.javagotchi.model.Usuario;
import br.com.luaazul.javagotchi.model.tamagotchi.BichoVirtual;
import br.com.luaazul.javagotchi.util.exception.OperacaoInvalida;
import br.com.luaazul.javagotchi.util.exception.UsuarioNaoExiste;
import br.com.luaazul.javagotchi.util.exception.UsuarioNaoTemBichoVitual;
import br.com.luaazul.javagotchi.controller.MessageController;

public class BathOperacao extends AbstractOperacao {

	public static Logger logger = LoggerFactory.getLogger(BathOperacao.class);
	
	
	UsuarioController usuarioController = new UsuarioController();
	BichoVirtualController bichoVirtualController = new BichoVirtualController();

	@Override
	public MessageBuilder execute(MessageCreateEvent event, String[] arrayParametros) throws UsuarioNaoExiste, UsuarioNaoTemBichoVitual,OperacaoInvalida, Exception {
		
		MessageBuilder mensagem = new MessageBuilder();
		EmbedBuilder embed = MessageController.getEmbed("Hora do banho",Color.GRAY);
		BichoVirtual bichoVirtual;
		
		//verifica o usuario (existe ou nao)
		Usuario usuarioExiste = usuarioController.verificarUsuarioServidor(event.getMessageAuthor().getIdAsString(), event.getServer().get().getIdAsString());
		
		if(usuarioExiste == null){
			//cria o usuario
			usuarioExiste = new Usuario(event.getMessageAuthor().getIdAsString(), event.getServer().get().getIdAsString());
			usuarioController.getUsuarioDAO().save(usuarioExiste);

			bichoVirtual = adotarBicho(usuarioExiste,arrayParametros[1]);	
			
			embed = bichoVirtualController.getEmbedBichoVitual(embed, true, bichoVirtual);
			mensagem.setEmbed(embed);
			return mensagem;
		}

		//verifica se ele tem pet
		bichoVirtual = bichoVirtualController.verificarBicho(usuarioExiste);
		
		//se tiver mostra o pet
		if(bichoVirtual != null){
			embed.addField("Info","Voce já tem um bicho virtual");
			
			embed = bichoVirtualController.getEmbedBichoVitual(embed, false, bichoVirtual);
			mensagem.setEmbed(embed);
			return mensagem;
		}
		
		usuarioExiste.setServidor(event.getServer().get().getIdAsString());
		
		bichoVirtual = adotarBicho(usuarioExiste, arrayParametros[1]);	
		
		embed = bichoVirtualController.getEmbedBichoVitual(embed, true, bichoVirtual);
		mensagem.setEmbed(embed);
		return mensagem;
	}
	
	private BichoVirtual adotarBicho(Usuario usuarioExiste, String nome) throws Exception {
		return bichoVirtualController.adotarBicho(usuarioExiste, nome);
	}
	
}
