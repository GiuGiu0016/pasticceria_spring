package it.dstech.model;
//Dolce( Nome, quantità, costo**)

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Dolce {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	
	private String nome;
	
	@OneToOne
	private Ricetta ricetta;
	
	private int quantita;
	
	private double costoDolce;
	
	public Dolce() {}

	public Dolce(long id, String nome, int quantita, double costoDolce, Ricetta ricetta) {
		this.id = id;
		this.nome = nome;
		this.quantita = quantita;
		this.costoDolce = costoDolce;
		this.ricetta = ricetta;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public double getCostoDolce() {
		return costoDolce;
	}

	public void setCostoDolce(double costoDolce) {
		this.costoDolce = costoDolce;
	}

	
	
	public Ricetta getRicetta() {
		return ricetta;
	}

	public void setRicetta(Ricetta ricetta) {
		this.ricetta = ricetta;
	}

	@Override
	public String toString() {
		return "Dolce [id=" + id + ", nome=" + nome + ", quantita=" + quantita + ", costoDolce=" + costoDolce + "]";
	}
}
