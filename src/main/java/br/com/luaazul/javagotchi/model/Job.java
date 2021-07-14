package br.com.luaazul.javagotchi.model;

import java.util.Iterator;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "TBOD_JOB")
public class Job {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "ID_JOB")
	private int id;
	@Column(name = "DS_NOME_JOB")
	private String nome;	
	@Column(name = "DS_CLASSE")
	private String classe;
	@Column(name = "DS_CRON")
	private String cron;
	@Column(name = "ID_ATIVO")
	private boolean ativo;
	
	//@Column(name = "CD_PARAMETRO")
	@Transient
	private List<Integer> parametros;
	
	Job(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClasse() {
		return classe;
	}

	public void setClasse(String classe) {
		this.classe = classe;
	}

	public String getCron() {
		return cron;
	}

	public void setCron(String cron) {
		this.cron = cron;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setAtivo(boolean ativo) {
		this.ativo = ativo;
	}

	public List<Integer> getParametros() {
		return parametros;
	}

	public void setParametros(List<Integer> parametros) {
		
		this.parametros.clear();
		for (Integer parametro : parametros) {
			this.parametros.add(parametro);
		}
		
	}
	
	
	
}
