package br.com.luaazul.javagotchi.controller;

import java.awt.Color;
import java.text.MessageFormat;

import org.javacord.api.entity.message.embed.EmbedBuilder;

public class MessageController {

	
	public static EmbedBuilder getEmbed(String Title, Color cor) {
		
		EmbedBuilder embed = new EmbedBuilder() 
				.setTitle(Title)
			    .setFooter("Bot by Mar@MoonBlue")
			    .setColor(cor);
		
		return embed;
		
	} 
	
	public static EmbedBuilder getEmbedCritica(String title, String titleField, String conteudoField) {
		
		EmbedBuilder embed = getEmbed(title, Color.red);
		embed.addField(titleField, conteudoField);
		
		return embed; 
	}
	
	public static EmbedBuilder getEmbedSucesso(String title,String titleField, String conteudoField) {
		EmbedBuilder embed = getEmbed(title, Color.red);
		embed.addField(titleField, conteudoField);
		
		return embed; 
	}
	
	public static EmbedBuilder getEmbedAviso(String title,String titleField, String conteudoField) {
		EmbedBuilder embed = getEmbed(title, Color.red);
		embed.addField(titleField, conteudoField);
		
		return embed; 
	}
	
	public static String getCitarUsuario(String idUsuario) {
		return MessageFormat.format("<@{0}>",idUsuario);
	}
	
}
