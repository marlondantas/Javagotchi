package br.com.luaazul.javagotchi;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import br.com.luaazul.javagotchi.controller.DiscordService;
import br.com.luaazul.javagotchi.controller.DiscordToken;
import br.com.luaazul.javagotchi.controller.OperacaoService;
import br.com.luaazul.javagotchi.dao.GeradorManagerFactory;

public class Javagotchi 
{
	
	public static Logger logger = LoggerFactory.getLogger(Javagotchi.class);
	
	public static List<String> argumentos = new ArrayList<String>();
	
    public static void main( String[] args ) throws IOException
    {	
    	List<String> argumentos = Arrays.asList(args);
    			
    	logger.info("INICIANDO O PROJETO: {} - [{}]",Javagotchi.class.getName(), new Date());

    	if(argumentos.contains("PROD")) {
    		logger.info("Conectando ao banco de dados PROD");
    		GeradorManagerFactory.iniciarBanco();
    	}
    	
    	if(argumentos.contains("DEV")) {
    		logger.info("Conectando ao banco de dados DEV");
    		GeradorManagerFactory.iniciarBanco("JavagotchiDEV");
    	}
    	
    	if(argumentos.contains("JOBS") || argumentos.contains("ALL") ) {
    		logger.info("Carregando JOBS");
    	}
        	
    	if(argumentos.contains("FUNCTIONS") || argumentos.contains("ALL")  ) {
    		//Verifica se vai entrar as funcoes
    		logger.info("Carregando TOKEN");
    		DiscordToken.carregarProperties();
    		
    		logger.info("Carregando FUNCOES");
    		DiscordService discordService = new DiscordService(DiscordToken.getTOKEN());
    		
    		//Escutar as chamadas.
    		discordService.addOperacao(new OperacaoService());
    		
    		discordService.startBot();
    		
    		logger.info("Link para acessar o bot {}",discordService.getClient().createBotInvite());
    		
    	}
    	
    	// TODO ambiente WEB
    	
    	logger.info("Projeto Carregado com sucesso! - [{}]", new Date());
    	
    }
    
}
