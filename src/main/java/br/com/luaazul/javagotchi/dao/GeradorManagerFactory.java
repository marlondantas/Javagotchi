package br.com.luaazul.javagotchi.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class GeradorManagerFactory {

	public static EntityManagerFactory factory;
	
	public static EntityManager entityManager;
	
	public static void iniciarBanco(String ambiente) {
		//JavagotchiDEV
		GeradorManagerFactory.factory = Persistence.createEntityManagerFactory(ambiente);
		entityManager = getEntityManager();
	}
	
	public static void iniciarBanco() {
		GeradorManagerFactory.factory = Persistence.createEntityManagerFactory("Javagotchi");
		entityManager = getEntityManager();
	}
	
	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
	
}
