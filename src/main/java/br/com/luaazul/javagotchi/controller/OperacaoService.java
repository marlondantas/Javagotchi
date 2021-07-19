package br.com.luaazul.javagotchi.controller;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import org.javacord.api.entity.message.MessageBuilder;
import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.luaazul.javagotchi.model.Registro;
import br.com.luaazul.javagotchi.model.Servidor;
import br.com.luaazul.javagotchi.util.exception.OperacaoInvalida;
import br.com.luaazul.javagotchi.util.exception.UsuarioNaoExiste;
import br.com.luaazul.javagotchi.util.exception.UsuarioNaoTemBichoVitual;
import br.com.luaazul.javagotchi.controller.model.RegistroController;
import br.com.luaazul.javagotchi.controller.model.ServidorService;
import br.com.luaazul.javagotchi.view.*;
import br.com.luaazul.javagotchi.view.Operacao.AdoptOperacao;
import br.com.luaazul.javagotchi.view.Operacao.BathOperacao;
import br.com.luaazul.javagotchi.view.Operacao.FeedOperacao;
import br.com.luaazul.javagotchi.view.Operacao.InfoOperacao;
import br.com.luaazul.javagotchi.view.Operacao.PlayOperacao;
import br.com.luaazul.javagotchi.view.Operacao.PrefixoOperacao;
import br.com.luaazul.javagotchi.view.Operacao.SleepOperacao;
import br.com.luaazul.javagotchi.view.Operacao.TrainOperacao;
import br.com.luaazul.javagotchi.controller.MessageController;

public class OperacaoService implements MessageCreateListener {

	public static Logger logger = LoggerFactory.getLogger(OperacaoService.class);

	private Map<String, AbstractOperacao> operacoes;

	private ServidorService servidorService;

	private RegistroController registroController;

	public OperacaoService() {
		this.operacoes = this.gerarOperacoes();
		this.servidorService = new ServidorService();
		this.registroController = new RegistroController();
	}

	@Override
	public void onMessageCreate(MessageCreateEvent event) {

		// verifica se a mensagem e de um bot
		if (event.getMessageAuthor().isBotUser() || !event.isServerMessage()) {
			return;
		}

		// busca o servidor
		Servidor servidor = new Servidor();
		servidor.setIdServidor(event.getServer().get().getIdAsString());

		String prefixo = servidorService.setServidor(servidor).getPrefixo();

		String[] arrayValores = event.getMessage().getContent().toLowerCase().split(" ");

		String chaveOperacao = arrayValores[0].replace(prefixo, "");
		// TODO fazer um enum

		if (this.operacoes.containsKey(chaveOperacao)) {
			logger.info("Operacao Iniciada");
			Registro registro = new Registro(event.getMessageAuthor().getIdAsString(),
					event.getMessage().getContent().toLowerCase(),
					this.operacoes.get(chaveOperacao).getClass().getName());

			MessageBuilder messageBuilder = new MessageBuilder();

			try {

				// retornar a mensagem
				messageBuilder = this.operacoes.get(chaveOperacao).execute(event, arrayValores);

				registro.setResponse(messageBuilder.toString());
				registro.setStatus(1); //sucesso


			} catch (UsuarioNaoExiste usuarioNaoExiste) {
				logger.error("Operacao Finalizada COM ERRO {}", usuarioNaoExiste.getMessage());
				registro.setResponse(usuarioNaoExiste.getMessage());
				registro.setStatus(3);// 3 critica do sistema

				messageBuilder.setEmbed(MessageController.getEmbedCritica("AVISO", ":thinking:",
						"Você ainda não faz parte da brincadeira\nUse o comando !!adopt <nome> para começar"));

			} catch (UsuarioNaoTemBichoVitual usuarioNaoExiste) {
				logger.error("Operacao Finalizada COM ERRO {}", usuarioNaoExiste.getMessage());
				registro.setResponse(usuarioNaoExiste.getMessage());
				registro.setStatus(3); // 3 critica do sistema

				messageBuilder.setEmbed(MessageController.getEmbedCritica("AVISO", ":thinking:",
						"Você ainda não tem um bicho virtual\nUse o comando !!adopt <nome> para adotar um"));

			} catch (OperacaoInvalida operacaoInvalida) {
				logger.error("Operacao Finalizada COM ERRO {}", operacaoInvalida.getMessage());
				registro.setResponse(operacaoInvalida.getMessage());
				registro.setStatus(5); // 5 erro do usuario.
				
				messageBuilder.setEmbed(MessageController.getEmbedCritica("AVISO", ":thinking:",
						"Você deve colocar os parametros correto no comando!"));
				
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("Operacao Finalizada COM ERRO GENERICO {}", e.getMessage());
				registro.setResponse(e.getMessage());
				registro.setStatus(4); // erro generico

				messageBuilder.append("¯\\_(ツ)_/¯");
			}

			finally {
				enviarMensagem(registro, messageBuilder, event);
				registroController.getRegistroDAO().save(registro);
			}
			logger.info("Operacao Finalizada");
			// TODO essa classe envia a mensagem depois do tratamento e das conexao muito
			// louca
		}

	}

	public Map<String, AbstractOperacao> gerarOperacoes() {
		Map<String, AbstractOperacao> operacoes = new HashMap<String, AbstractOperacao>();

		// javagotchi
		operacoes.put("info", new InfoOperacao());
		operacoes.put("adopt", new AdoptOperacao());

		//TODO tabela de operacaoes
		operacoes.put("feed", new FeedOperacao());
		operacoes.put("sleep", new SleepOperacao());
		operacoes.put("play", new PlayOperacao());
		operacoes.put("train", new TrainOperacao());
		operacoes.put("bath", new BathOperacao());
		
		//Geral
		operacoes.put("prefixo", new PrefixoOperacao());

		return operacoes;
	}
	
	public void enviarMensagem(Registro registro, MessageBuilder messageBuilder, MessageCreateEvent event) {
		long idMensagem = 0l;
		try {
			idMensagem = messageBuilder.send(event.getChannel()).get().getId();
		} catch (InterruptedException e) {
			e.printStackTrace();
			registro.setObservacao(e.getMessage());
		} catch (ExecutionException e) {
			e.printStackTrace();
			registro.setObservacao(e.getMessage());
		}
		registro.setCodigoMensagem(idMensagem + "");
	}

}
