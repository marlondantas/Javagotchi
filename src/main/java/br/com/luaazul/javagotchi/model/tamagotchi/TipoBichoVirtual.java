package br.com.luaazul.javagotchi.model.tamagotchi;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;


@Entity
@Table( name = "TBOD_TIPO_BICHO",indexes = { 
		@Index(name = "IDX_TIPO_BICHO_VIRTUAL", columnList = "CD_TIPO_BICHO")})
public class TipoBichoVirtual {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column( name = "CD_TIPO_BICHO")
	private int id;
	
	@Column( name="DS_NOME_RACA")
	private String nomeRaca;
	
	@Column(nullable=true,name = "VL_FOME_MAXIMA",columnDefinition = "integer default 25")
	private Integer fomeMaxima;
	@Column(nullable=true,name = "VL_ENERGIA_MAXIMA",columnDefinition = "integer default 100")
	private Integer energiaMaxima;
	@Column(nullable=true,name = "VL_FELICIDADE_MAXIMA",columnDefinition = "integer default 100")
	private Integer felicidadeMaxima;
	@Column(nullable=true,name = "VL_VIDA_MAXIMA",columnDefinition = "integer default 100")
	private Integer vidafomeMaxima;
	
	@Column(nullable=true,name = "VL_FOME_PERCA",columnDefinition = "integer default 100")
	private Integer fomePercaHora;
	@Column(nullable=true,name = "VL_ENERGIA_PERCA",columnDefinition = "integer default 100")
	private Integer energiaPercaHora;
	@Column(nullable=true,name = "VL_FELICIDADE_PERCA",columnDefinition = "integer default 100")
	private Integer felicidadePercaHora;
	@Column(nullable=true,name = "VL_VIDA_PERCA",columnDefinition = "integer default 100")
	private Integer vidaPercaHora;

	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name = "CD_TIPO_BICHO")
	private List<EstadoTipoBicho> imgsEstados;
	
	public TipoBichoVirtual() {
		
	}
	
	public TipoBichoVirtual(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFomeMaxima() {
		return fomeMaxima;
	}

	public void setFomeMaxima(int fomeMaxima) {
		this.fomeMaxima = fomeMaxima;
	}

	public int getEnergiaMaxima() {
		return energiaMaxima;
	}

	public void setEnergiaMaxima(int energiaMaxima) {
		this.energiaMaxima = energiaMaxima;
	}

	public int getFelicidadeMaxima() {
		return felicidadeMaxima;
	}

	public void setFelicidadeMaxima(int felicidadeMaxima) {
		this.felicidadeMaxima = felicidadeMaxima;
	}

	public int getVidafomeMaxima() {
		return vidafomeMaxima;
	}

	public void setVidafomeMaxima(int vidafomeMaxima) {
		this.vidafomeMaxima = vidafomeMaxima;
	}

	public int getFomePercaHora() {
		return fomePercaHora;
	}

	public void setFomePercaHora(int fomePercaHora) {
		this.fomePercaHora = fomePercaHora;
	}

	public int getEnergiaPercaHora() {
		return energiaPercaHora;
	}

	public void setEnergiaPercaHora(int energiaPercaHora) {
		this.energiaPercaHora = energiaPercaHora;
	}

	public int getFelicidadePercaHora() {
		return felicidadePercaHora;
	}

	public void setFelicidadePercaHora(int felicidadePercaHora) {
		this.felicidadePercaHora = felicidadePercaHora;
	}

	public int getVidaPercaHora() {
		return vidaPercaHora;
	}

	public void setVidaPercaHora(int vidaPercaHora) {
		this.vidaPercaHora = vidaPercaHora;
	}

	public List<EstadoTipoBicho> getImgsEstados() {
		return imgsEstados;
	}

	public void setImgsEstados(List<EstadoTipoBicho> imgsEstados) {
		this.imgsEstados = imgsEstados;
	}
	
	
}
