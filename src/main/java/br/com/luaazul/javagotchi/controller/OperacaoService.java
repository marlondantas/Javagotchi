package br.com.luaazul.javagotchi.controller;

import java.util.HashMap;
import java.util.Map;

import org.javacord.api.event.message.MessageCreateEvent;
import org.javacord.api.listener.message.MessageCreateListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.luaazul.javagotchi.model.Registro;
import br.com.luaazul.javagotchi.model.Servidor;
import br.com.luaazul.javagotchi.controller.model.RegistroController;
import br.com.luaazul.javagotchi.controller.model.ServidorService;
import br.com.luaazul.javagotchi.view.*;
import br.com.luaazul.javagotchi.view.Operacao.AdoptOperacao;
import br.com.luaazul.javagotchi.view.Operacao.InfoOperacao;
import br.com.luaazul.javagotchi.view.Operacao.PrefixoOperacao;

public class OperacaoService implements MessageCreateListener{

	public static Logger logger = LoggerFactory.getLogger(OperacaoService.class);
	
	private Map<String, AbstractOperacao> operacoes;
	
	private ServidorService servidorService;
	
	private RegistroController registroController;
	
	public OperacaoService(){
		this.operacoes = this.gerarOperacoes();
		this.servidorService = new ServidorService();
		this.registroController = new RegistroController();
	}
	
	@Override
	public void onMessageCreate(MessageCreateEvent event) {
	
		//verifica se a mensagem e de um bot
		if(event.getMessageAuthor().isBotUser()) {
			return;
		}
		
		//busca o servidor
		Servidor servidor = new Servidor();
		servidor.setIdServidor(event.getServer().get().getIdAsString());
		
		String prefixo = servidorService.setServidor(servidor).getPrefixo();
		
		String[] arrayValores = event.getMessage().getContent().toLowerCase().split(" ");
		
		String chaveOperacao = arrayValores[0].replace(prefixo,"");
		// TODO fazer um enum
		
		if(this.operacoes.containsKey(chaveOperacao)) {
			logger.info("Operacao Iniciada");
			Registro registro = new Registro(event.getMessageAuthor().getIdAsString(), event.getMessage().getContent().toLowerCase(),this.operacoes.get(chaveOperacao).getClass().getName());
			
			try {
				
				//retornar a mensagem
				this.operacoes.get(chaveOperacao).execute(event,arrayValores);
				
			} catch (Exception e) {
				e.printStackTrace();
				logger.error("Operacao Finalizada COM ERRO {}",e.getMessage());
				registro.setResponse(e.getMessage());
				registro.setStatus(3);
				
			} finally{
				registroController.getRegistroDAO().save(registro);
			}
			logger.info("Operacao Finalizada");
		// TODO essa classe envia a mensagem depois do tratamento e das conexao muito louca
		}
		
	}
	
	
	
	public Map<String, AbstractOperacao> gerarOperacoes() {
		Map<String, AbstractOperacao> operacoes = new HashMap<String, AbstractOperacao>();
		
		//javagotchi
		operacoes.put("info", new InfoOperacao());
		operacoes.put("adopt", new AdoptOperacao());
		
		
		//geral
		operacoes.put("prefixo", new PrefixoOperacao());
		
		return operacoes;
	}

}
