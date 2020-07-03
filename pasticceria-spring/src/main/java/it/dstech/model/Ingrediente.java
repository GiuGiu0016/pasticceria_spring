package it.dstech.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Ingrediente {

	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	
	private String nome;
	
	private double costoing;
	
	
	public double getCostoing() {
		return costoing;
	}


	public void setCostoing(double costoing) {
		this.costoing = costoing;
	}


	public Ingrediente() {
		super();
	}


	public Ingrediente(long id, String nome, double costo) {
		this.id = id;
		this.nome = nome;
		this.costoing = costo;
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





	@Override
	public String toString() {
		return "Ingrediente [id=" + id + ", nome=" + nome + ", costo=" + costoing + "]";
	}
	
	
	
}
